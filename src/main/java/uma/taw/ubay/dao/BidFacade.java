package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BidFacade extends AbstractFacade<BidEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public BidFacade() {
        super(BidEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<BidEntity> filterBids(ClientEntity vendor, int page, Date startDate, Date endDate, String productTitle, String clientName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // Select bidTable.* from Bid bidTable, Product productTable where productTable.vendor_id = :vendorID and bidTable.product_id = productTable.id order by bidTable.publish_date
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("product"),productTable));
        predicateList.add(builder.equal(bidTable.get("user"),clientTable));
        predicateList.add(builder.equal(productTable.get("vendor"), vendor));
        if (startDate != null) predicateList.add(builder.greaterThanOrEqualTo(bidTable.get("publishDate"),startDate));
        if (endDate != null) predicateList.add(builder.lessThanOrEqualTo(bidTable.get("publishDate"),endDate));
        if (productTitle != null) predicateList.add(builder.like(builder.lower(productTable.get("title")),"%" + productTitle.toLowerCase() + "%"));
        if (clientName != null) predicateList.add(builder.like(builder.lower(clientTable.get("name")),"%"+ clientName.toLowerCase() + "%"));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.desc(bidTable.get("publishDate")));
        return em.createQuery(query)
                .setFirstResult(page * 10)
                .setMaxResults(10)
                .getResultList();
    }
}