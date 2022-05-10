package uma.taw.ubay.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;

import java.util.List;

@Stateless
public class CategoryFacade extends AbstractFacade<CategoryEntity> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public CategoryFacade() {
        super(CategoryEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryEntity searchById(int id){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryTable = query.from(CategoryEntity.class);
        query.select(categoryTable).where(builder.equal(categoryTable.get("id"), id));
        return em.createQuery(query).getSingleResult();

    }

    public List<CategoryEntity> findAllSortedById(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryTable = query.from(CategoryEntity.class);
        query
                .select(categoryTable)
                .orderBy(builder.asc(categoryTable.get("id")));

        return em.createQuery(query).getResultList();
    }
}