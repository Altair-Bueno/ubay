package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ClientEntity;

@Stateless
public class CategoryFacade extends AbstractFacade<ClientEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public CategoryFacade() {
        super(ClientEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}