package com.example.do_an_cnpm.data_source.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {

    <S extends T> List<S> findAll(Example<S> example);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(long id);

    void save(T o);

    void saveAll(Iterable<T> iterable);

    void deleteById(long id);

    void delete(T o);

    boolean existsById(long id);
}


