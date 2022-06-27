package com.accenture.ch.calorie_tracker.core.generic;

import com.accenture.ch.calorie_tracker.core.error.NotFoundException;

import java.util.List;

public interface AbstractEntityService<T extends AbstractEntity>{
    List<T> findAll();

    T findById(String id) throws NotFoundException;

    T create(T entity);

    T save(T entity);

    T updateById(String id, T entity) throws NotFoundException;

    void deleteById(String id) throws NotFoundException;

    boolean existsById(String id);

}
