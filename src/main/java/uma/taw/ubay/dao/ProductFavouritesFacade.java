package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ProductFavouritesEntity;

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
}