package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductFacade extends AbstractFacade<ProductEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public ProductFacade() {
        super(ProductEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ProductEntity> uploadedProducts(ClientEntity client){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        query.select(productTable).where(builder.equal(productTable.get("vendor"), client));
        return em.createQuery(query).getResultList();
    }
}