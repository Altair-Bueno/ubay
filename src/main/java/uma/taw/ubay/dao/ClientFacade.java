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

        if(!"".equals(address)){
            predicateList.add(builder.like(clientTable.get("address"), address + "%"));
        }

        if(!"".equals(city)){
            predicateList.add(builder.like(clientTable.get("city"), city + "%"));
        }

        if(!"".equals(name)){
            predicateList.add(builder.like(clientTable.get("name"), name + "%"));
        }

        if(!"".equals(lastName)){
            predicateList.add(builder.like(clientTable.get("lastName"), lastName + "%"));
        }
        query.select(clientTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.asc(clientTable.get("id")));
        return em.createQuery(query).getResultList();
    }
}