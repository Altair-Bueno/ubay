package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.entity.ProductFavouritesEntity;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProductEntity> getClientFavouriteProducts(ClientEntity client){
        try {
            return em.createQuery("SELECT p.product FROM ProductFavouritesEntity p WHERE p.user = :user", ProductEntity.class)
                    .setParameter("user", client)
                    .getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product){
        return em.createQuery("SELECT p FROM ProductFavouritesEntity p WHERE p.user = :user AND p.product = :product", ProductFavouritesEntity.class)
                .setParameter("user", client)
                .setParameter("product", product)
                .getSingleResult();
    }
}