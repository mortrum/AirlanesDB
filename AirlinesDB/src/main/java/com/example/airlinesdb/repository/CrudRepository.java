package com.example.airlinesdb.repository;

import org.jooq.SelectConditionStep;

import java.util.List;

public interface CrudRepository<T> {

    void insert(T object);

    void update(T object);

    T find(Integer id);

    void delete(Integer id);
}
