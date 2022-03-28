package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;

import java.util.ArrayList;
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

    public List<ClientEntity> filterByGender(GenderEnum gender){
        List<ClientEntity> clientEntityList = this.findAll();
        List<ClientEntity> res = new ArrayList<>();

        for(ClientEntity c : clientEntityList){
            if(c.getGender().toString().toUpperCase(Locale.ROOT).equals(gender.toString().toUpperCase(Locale.ROOT))){
                res.add(c);
            }
        }
        return res;
    }

    public List<ClientEntity> filterByAddress(String address){
        List<ClientEntity> clientEntityList = this.findAll();
        List<ClientEntity> res = new ArrayList<>();

        for(ClientEntity c : clientEntityList){
            if(c.getAddress().toUpperCase(Locale.ROOT).contains(address.toUpperCase(Locale.ROOT))){
                res.add(c);
            }
        }
        return res;
    }

    public List<ClientEntity> filterByCity(String city){
        List<ClientEntity> clientEntityList = this.findAll();
        List<ClientEntity> res = new ArrayList<>();

        for(ClientEntity c : clientEntityList){
            if(c.getCity().toUpperCase(Locale.ROOT).equals(city.toUpperCase(Locale.ROOT))){
                res.add(c);
            }
        }
        return res;
    }

    public List<ClientEntity> filterByID(String ID){
        List<ClientEntity> clientEntityList = this.findAll();
        List<ClientEntity> res = new ArrayList<>();

        for(ClientEntity c : clientEntityList){
            if(String.valueOf(c.getId()).equals(ID)){
                res.add(c);
                break;
            }
        }
        return res;
    }

    //Como linkeo si el botón de delete está presionado para que se ejecute esto? TODO
    public void deleteUserByID(String ID){
        em.createQuery("DELETE FROM ClientEntity WHERE id = :ID")
                .executeUpdate();
    }
}