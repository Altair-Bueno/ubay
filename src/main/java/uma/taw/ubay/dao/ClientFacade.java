package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ClientEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Stateless
public class ClientFacade extends AbstractFacade<ClientEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public ClientFacade() {
        super(ClientEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ClientEntity> filterByName(String name){
        List<ClientEntity> clientEntityList = this.findAll();
        List<ClientEntity> res = new LinkedList<>();

        for(ClientEntity c : clientEntityList){
            String fullName = c.getName() + " " +  c.getLastName();
            if(fullName.toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT))){
                res.add(c);
            }
        }
        return res;
    }
}