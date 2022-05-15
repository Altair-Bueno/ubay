package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.ProductKeys;
import uma.taw.ubay.dto.products.ProductClientDTO;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.entity.ProductFavouritesEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Francisco Javier Hernández
 */

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

    /**
     * @author Filtro: José Luis Bueno Pachón y Francisco Javier Hernández
     * @author Paginación: Francisco Javier Hernández
     */
    public ProductTupleResult filterAndGetByPage(ClientEntity client, String name, CategoryEntity category, boolean owned, int page){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        query.select(productTable);
        int actualSize = 0;

        if(name != null){
            predicateList.add(builder.like(builder.upper(productTable.get("title")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if(category != null){
            predicateList.add(builder.equal(productTable.get("category"), category));
        }

        if(client != null && owned){
            predicateList.add(builder.equal(productTable.get("vendor"), client));
        }

        query.select(productTable).where(predicateList.toArray(new Predicate[0]));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductTupleResult<>(productEntities, actualSize);
    }

    public static class ProductTupleResult<T>{
        private List<T> productEntities;
        private int actualSize = 0;

        public ProductTupleResult(List<T> pE, int aS){
            productEntities = pE;
            actualSize = aS;
        }

        public List<T> getProductEntities() {
            return productEntities;
        }

        public int getActualSize() {
            return actualSize;
        }
    }

}