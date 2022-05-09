package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    public Stream<BidEntity> getFilteredBidsFromVendor(ClientEntity vendor, int page, Date startDate, Date endDate, String productTitle, String clientName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // select bidTable.*
        // from BidEntity bidTable, ProductEntity productTable, ClientEntity clientTable
        // where productTable.vendor = :vendor and
        //      bidTable.product = productTable and
        //      bidTable.user = clientTable
        // order by bidTable.publish_date
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
                .getResultStream();
    }

    public Stream<BidEntity> getFilteredBidsFromUser(ClientEntity user, int page, Date startDate, Date endDate, String productTitle, String vendorName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // select bidTable.*
        // from BidEntity bidTable, ProductEntity productTable, ClientEntity clientTable
        // where bidTable.user = :user and
        //      bidTable.product = productTable and
        //      bidTable.vendor = clientTable
        // order by bidTable.publish_date
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("user"), user));
        predicateList.add(builder.equal(bidTable.get("product"),productTable));
        predicateList.add(builder.equal(productTable.get("vendor"),clientTable));
        if (startDate != null) predicateList.add(builder.greaterThanOrEqualTo(bidTable.get("publishDate"),startDate));
        if (endDate != null) predicateList.add(builder.lessThanOrEqualTo(bidTable.get("publishDate"),endDate));
        if (productTitle != null) predicateList.add(builder.like(builder.lower(productTable.get("title")),"%" + productTitle.toLowerCase() + "%"));
        if (vendorName != null) predicateList.add(builder.like(builder.lower(clientTable.get("name")),"%"+ vendorName.toLowerCase() + "%"));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.desc(bidTable.get("publishDate")));
        return em.createQuery(query)
                .setFirstResult(page * 10)
                .setMaxResults(10)
                .getResultStream();
    }

    public BidEntity getHighestBidByProduct(ProductEntity product) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // select bidTable
        // from BidEntity bidTable
        // where bidTable.product = :product
        // having bidTable.amount = max(bidTable.amount)
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        query
                .select(bidTable)
                .where(builder.equal(bidTable.get("product"), product))
                .orderBy(builder.desc(bidTable.get("amount")));

        List<BidEntity> resultList = em.createQuery(query)
                .setMaxResults(1).getResultList();
        return resultList == null || resultList.isEmpty() ? null : resultList.get(0);
    }

    /*
     * Returns a list containing the (closed) bids
     * that have been made by the user given
     * by parameter.
     *
     */
    public List<BidEntity> productsBiddedClosedProducts(ClientEntity sesion){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Join<BidEntity, ProductEntity> join = bidTable.join("product", JoinType.INNER);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("user"), sesion));
        predicateList.add(builder.isNotNull(join.get("closeDate")));
        predicateList.add(builder.lessThanOrEqualTo(join.get("closeDate"), new java.util.Date()));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.desc(join.get("closeDate")));

        return em.createQuery(query)
                .getResultList();
    }

    public boolean isWinnerBid(ClientEntity client, BidEntity bid){
        return getHighestBidByProduct(bid.getProduct()).getUser().equals(client);
    }
}