package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.ProductKeys;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.entity.ProductFavouritesEntity;

import java.util.Date;
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

    public List<ProductEntity> getByPage(int page){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);

        Root<ProductEntity> productTable = query.from(ProductEntity.class);

        query.select(productTable)
                .orderBy(builder.desc(productTable.get("id")));
        return em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();
    }

    public List<ProductEntity> getNotifications(ClientEntity sesion){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductFavouritesEntity> query = builder.createQuery(ProductFavouritesEntity.class);
        CriteriaQuery<ProductEntity> query2 = builder.createQuery(ProductEntity.class);

        Root<ProductFavouritesEntity> productFavTable = query.from(ProductFavouritesEntity.class);
        Root<ProductEntity> productTable = query2.from(ProductEntity.class);

        query.select(productFavTable).where(builder.equal(productFavTable.get("user"), sesion));

        List<ProductFavouritesEntity> userfavs = em.createQuery(query).getResultList();
        List<ProductEntity> productosfavs = new ArrayList<>();

        for(ProductFavouritesEntity pfe : userfavs){
            query2.select(productTable)
                    .where(builder.equal(productTable.get("id"), pfe.getProduct()))
                    .where(builder.isNotNull(productTable.get("closeDate")));
            List<ProductEntity> p = em.createQuery(query2).getResultList();
            if(p != null && p.size() >= 1 && productosfavs.get(0).getCloseDate().compareTo(new Date()) <= 0) productosfavs.add(p.get(0));
        }

        return productosfavs;


    }
}