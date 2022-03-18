package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ExampleEntity;

@Stateless
public class ExampleFacade extends AbstractFacade<ExampleEntity>{
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public ExampleFacade() {
        super(ExampleEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
