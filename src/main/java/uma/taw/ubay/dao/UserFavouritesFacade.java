package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.UserFavouritesEntity;

@Stateless
public class UserFavouritesFacade extends AbstractFacade<UserFavouritesEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public UserFavouritesFacade() {
        super(UserFavouritesEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}