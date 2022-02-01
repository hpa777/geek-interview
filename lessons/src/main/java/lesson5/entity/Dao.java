package lesson5.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Dao<T> {

    private final EntityManagerFactory entityManagerFactory;

    private final Class<T> entityClass;

    public Dao(EntityManagerFactory entityManagerFactory, Class<T> typeClass) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityClass = typeClass;
    }

    private <T> T executeForEntityManager(Function<EntityManager, T> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public List<T> findAll() {
        return executeForEntityManager(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> rootEntry = cq.from(entityClass);
            CriteriaQuery<T> all = cq.select(rootEntry);
            return entityManager.createQuery(all).getResultList();
        });
    }

    public T findById(Long id) {
        return executeForEntityManager(entityManager -> entityManager.find(this.entityClass, id));
    }

    public void deleteById(Long id) {
        executeInTransaction(entityManager -> {
            T entity = entityManager.getReference(this.entityClass, id);
            entityManager.remove(entity);
        });
    }

    public void insert(T entity) {
        executeInTransaction(entityManager -> entityManager.persist(entity));
    }

    public void update(T entity) {
        executeInTransaction(entityManager -> entityManager.merge(entity));
    }
}
