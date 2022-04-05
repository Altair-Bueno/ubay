package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import uma.taw.ubay.entity.BidEntity;
import uma.taw.ubay.entity.ClientEntity;

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

    public Stream<BidEntity> getBidsByClient(ClientEntity client) {
        Query query = em.createQuery("select bid from BidEntity bid where bid.user.id = :client",BidEntity.class);
        query.setParameter("client",client.getId());
        return query.getResultStream();
    }
    public Stream<BidEntity> getBidsByVendor(ClientEntity vendor) {
        Query query = em.createQuery("select bid from BidEntity bid, ProductEntity product where product.vendor.id = :vendor and bid.product.id = product.id");
        query.setParameter("vendor",vendor.getId());
        return query.getResultStream();
    }
}