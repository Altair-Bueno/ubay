package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.sql.Date;
import java.util.stream.Stream;

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

    public Stream<BidEntity> filterBids(ClientEntity vendor, Date startDate, Date endDate, Double minAmount, Double maxAmount, String productName, String clientName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // Select bidTable.* from Bid bidTable, Product productTable where productTable.vendor_id = :vendorID and bidTable.product_id = productTable.id order by bidTable.publish_date
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        query.select(bidTable)
                .where(builder.equal(productTable.get("id"), productTable.get("id")))
                .where(builder.equal(productTable.get("vendor"), vendor))
                .orderBy(builder.desc(bidTable.get("publishDate")));

        return em.createQuery(query).getResultStream();
    }
}