package com.letscard.jpa.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

/*
    AbstractDAO implements the DAO interface and implements all its methods.
    It defines other useful methods to interface with the EntityManager
 */
public abstract class AbstractDAO<T, Id extends Serializable, NaturalId extends Serializable> implements DAO<T, Id, NaturalId> {

    //private final static EntityManagerSingleton entityManagerFactory;
    private final static EntityManager entityManager;

    static {
        /*entityManagerFactory = Persistence.createEntityManagerFactory("letscard-unit");
        entityManager = entityManagerFactory.createEntityManager();*/
        entityManager = EntityManagerSingleton.createEntityManager();
    }

    private final Class<T> tClass;

    AbstractDAO(Class<T> tClass) {
        this.tClass = tClass;
    }

    public EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    public void begin() {
        getTransaction().begin();
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void commit() {
        flush();
        getTransaction().commit();
    }

    public void rollback() {
        getTransaction().rollback();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> gettClass() {
        return tClass;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public T readByNaturalId(NaturalId naturalId) {
        T t = getEntityManager()
                .unwrap(Session.class)
                .bySimpleNaturalId(tClass)
                .load(naturalId);
        return t;
    }

    public T read(Id id) {
        return getEntityManager().find(tClass, id);
    }

    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    public List<T> readAll() {
        return getEntityManager().createQuery("from " + tClass.getName(), tClass).getResultList();
    }

    public void deleteAll() {
        for (T t : readAll())
            delete(t);
    }
}
