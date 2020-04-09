package com.letscard.jpa.service;

import java.io.Serializable;
import java.util.List;

public interface Service<T, Id extends Serializable, NaturalId extends Serializable> {

    void create(T entity);

    T readByNaturalId(NaturalId naturalId);

    T read(Id id);

    T update(T entity);

    void delete(T entity);

    List<T> readAll();

    void deleteAll();

}
