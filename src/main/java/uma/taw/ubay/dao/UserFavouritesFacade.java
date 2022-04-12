package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.entity.UserFavouritesEntity;

import java.util.List;

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