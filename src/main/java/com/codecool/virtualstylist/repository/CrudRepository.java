package com.codecool.virtualstylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("crudDataAccess")
public interface CrudRepository<T, ID extends Serializable>  extends JpaRepository<T, ID> {
    <S extends T> S save(S entity);
    Optional<T> findById(ID primaryKey);
    List<T> findAll();
    void delete(T entity);
    boolean existsById(ID primaryKey);

}
