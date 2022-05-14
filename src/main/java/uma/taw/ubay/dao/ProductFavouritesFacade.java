package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import uma.taw.ubay.ProductKeys;
import uma.taw.ubay.entity.*;
import uma.taw.ubay.dao.ProductFacade.ProductTupleResult;
import uma.taw.ubay.servlet.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Francisco Javier Hernández 70%
 * @author José Luis Bueno 30%
 */

@Stateless
public class ProductFavouritesFacade extends AbstractFacade<ProductFavouritesEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public ProductFavouritesFacade() {
        super(ProductFavouritesEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @author José Luis Bueno Pachón
     */
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity client){
        try {
            return em.createQuery("SELECT p.product FROM ProductFavouritesEntity p WHERE p.user = :user", ProductEntity.class)
                    .setParameter("user", client)
                    .getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductTupleResult<ProductEntity> getClientFavouriteProductsFiltered(String name, CategoryEntity category, int page){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductFavouritesEntity> productFavTable = query.from(ProductFavouritesEntity.class);
        Join<ProductFavouritesEntity, ProductEntity> join = productFavTable.join("product", JoinType.INNER);

        int actualSize = 0;

        if(name != null){
            predicateList.add(builder.like(builder.upper(join.get("title")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if(category != null){
            predicateList.add(builder.equal(join.get("category"), category));
        }

        query.select(join).where(predicateList.toArray(new Predicate[0]));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductTupleResult<>(productEntities, actualSize);
    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductTupleResult<ProductEntity> getClientFavouriteProductsByPage(int page){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductFavouritesEntity> productFavTable = query.from(ProductFavouritesEntity.class);
        Join<ProductFavouritesEntity, ProductEntity> join = productFavTable.join("product", JoinType.INNER);
        int actualSize = 0;

        query.select(join)
                .orderBy(builder.desc(join.get("id")));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductTupleResult(productEntities, actualSize);

    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product){
        return em.createQuery("SELECT p FROM ProductFavouritesEntity p WHERE p.user = :user AND p.product = :product", ProductFavouritesEntity.class)
                .setParameter("user", client)
                .setParameter("product", product)
                .getSingleResult();
    }
}