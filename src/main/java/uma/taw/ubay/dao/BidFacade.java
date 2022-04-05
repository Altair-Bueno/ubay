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
        Query query = em.createQuery("select bid from BidEntity bid where bid.user = :client",BidEntity.class);
        query.setParameter("client",client);
        return query.getResultStream();
    }
}