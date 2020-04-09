package com.letscard.jpa.dao;

import java.io.Serializable;
import java.util.List;

/*
    DAO interface. This class define the basic CRUD operation.
 */
public interface DAO<T, Id extends Serializable, NaturalId extends Serializable> {

    void create(T entity);

    T readByNaturalId(NaturalId naturalId);

    T read(Id id);

    T update(T entity);

    void delete(T entity);

    List<T> readAll();

    void deleteAll();

}
