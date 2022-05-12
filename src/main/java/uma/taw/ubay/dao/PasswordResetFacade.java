package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.PasswordResetEntity;

@Stateless
public class PasswordResetFacade extends AbstractFacade<PasswordResetEntity>{

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public PasswordResetFacade() {
        super(PasswordResetEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasswordResetEntity searchPasswordResetByLoginCredentials(LoginCredentialsEntity login){
        try{
            return (PasswordResetEntity) em.createQuery("SELECT pr FROM PasswordResetEntity pr WHERE pr.user = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch(Exception e){
            return null;
        }

    }
}
