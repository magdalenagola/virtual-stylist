package com.codecool.virtualstylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CrudRepository<T, ID extends Serializable>  extends JpaRepository<T, ID> {
    <S extends T> S save(S entity);
    T findOne(ID primaryKey);
    List<T> findAll();
    void delete(T entity);
    boolean exists(ID primaryKey);
}
