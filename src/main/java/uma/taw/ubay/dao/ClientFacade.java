package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author José Luis Bueno Pachón
 */

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

    public List<ClientEntity> filterClients(String name, String lastName, GenderEnum gender, String address, String city, String id){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> query = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);
        query.select(clientTable);
        List<Predicate> predicateList = new ArrayList<>();

        if(!"".equals(id)){
            predicateList.add(builder.equal(clientTable.get("id"), id));
        }

        if(gender != null){
            predicateList.add(builder.equal(clientTable.get("gender"), gender));
        }

        if(address != null){
            predicateList.add(builder.like(builder.upper(clientTable.get("address")), "%" + address.toUpperCase(Locale.ROOT) + "%"));
        }
        if(city != null){
            predicateList.add(builder.like(builder.upper(clientTable.get("city")), "%" + city.toUpperCase(Locale.ROOT) + "%"));
        }

        if(name != null){
            predicateList.add(builder.like(builder.upper(clientTable.get("name")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if(lastName != null){
            predicateList.add(builder.like(builder.upper(clientTable.get("lastName")), "%" + lastName.toUpperCase(Locale.ROOT) + "%"));
        }

        query.select(clientTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.asc(clientTable.get("id")));
        return em.createQuery(query).getResultList();
    }
}