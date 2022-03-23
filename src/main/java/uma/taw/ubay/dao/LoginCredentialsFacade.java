package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.LoginCredentialsEntity;

@Stateless
public class LoginCredentialsFacade extends AbstractFacade<LoginCredentialsEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public LoginCredentialsFacade() {
        super(LoginCredentialsEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}