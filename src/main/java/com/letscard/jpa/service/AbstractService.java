package com.letscard.jpa.service;

import com.letscard.jpa.dao.AbstractDAO;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T, Id extends Serializable, NaturalId extends Serializable> implements Service<T, Id, NaturalId> {

    private AbstractDAO<T, Id, NaturalId> dao;

    public AbstractService(AbstractDAO<T, Id, NaturalId> dao) {
        this.dao = dao;
    }

    public AbstractDAO<T, Id, NaturalId> getDao() {
        return this.dao;
    }

    public void create(T entity) {
        dao.begin();
        dao.create(entity);
        dao.commit();
    }

    public T readByNaturalId(NaturalId naturalId) {
        getDao().begin();
        T t = getDao().readByNaturalId(naturalId);
        getDao().commit();
        return t;
    }

    public T read(Id id) {
        dao.begin();
        T t = dao.read(id);
        dao.commit();
        return t;
    }

    public T update(T entity) {
        dao.begin();
        T t = dao.update(entity);
        dao.commit();
        return t;
    }

    public abstract void delete(T entity);

    public List<T> readAll() {
        dao.begin();
        List<T> l = dao.readAll();
        dao.commit();
        return l;
    }

    public void deleteAll() {
        for (T t : readAll())
            delete(t);
    }
}
