package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;

/**
 * @author Jose Luís Bueno
 */
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

    public LoginCredentialsEntity searchClientLoginByClient(ClientEntity client){
        var query = em.createQuery("SELECT a FROM LoginCredentialsEntity a WHERE a.user = :client");
        query.setParameter("client", client);
        return (LoginCredentialsEntity) query.getResultList().get(0);
    }
}