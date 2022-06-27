package com.accenture.ch.calorie_tracker.core.generic;

import com.accenture.ch.calorie_tracker.core.error.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.*;


public abstract class AbstractEntityServiceImpl<T extends AbstractEntity> implements AbstractEntityService<T> {
    protected AbstractEntityRepository<T> repository;
    protected Logger logger;
    private String className;


    public AbstractEntityServiceImpl(AbstractEntityRepository<T> repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
        initClassName();
    }

    private void initClassName() {
        try {
            this.className = Class.forName(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName()).getSimpleName();
        } catch (ClassNotFoundException e) {
            this.className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        }
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(String id) throws NotFoundException {
        logger.debug("Attempting to find " + className + " with ID '" + id + "'");
        Optional<T> optional = repository.findById(UUID.fromString(id));

        if (optional.isPresent()) {
            logger.debug("Found " + className + " with ID '" + id + "'");
            return optional.get();
        } else {
            logger.debug(className + " with ID '" + id + "' not found");
            throw new NotFoundException(className + " with ID '" + id + "' not found");
        }
    }

    @Override
    public T create(T entity) {
        logger.debug("Attempting to create " + className);
        entity.setId(null);

        entity = repository.save(entity);
        logger.debug("Created " + className + ". New ID is '" + entity.getId() + "'");

        return entity;
    }

    @Override
    public T save(T entity) {
        logger.debug("Attempting to save " + className);

        entity = repository.save(preSave(entity));
        logger.debug("Saved " + className + " with ID '" + entity.getId() + "'");

        return entity;
    }

    @Override
    public T updateById(String id, T entity) throws NotFoundException {
        logger.debug("Attempting to update " + className + " with ID '" + id + "'");

        T foundEntity = findById(id);
        if (foundEntity != null) {
            BeanUtils.copyProperties(entity, foundEntity, getNullPropertyNames(entity));
            foundEntity.setId(UUID.fromString(id));

            entity = save(foundEntity);
            logger.debug("Updated " + className + " with ID '" + id);

            return entity;
        } else {
            logger.debug(className + " with ID '" + id + "' not found");
            throw new NotFoundException(className + " with ID '" + id + "' not found");
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    protected T preSave(T newEntity) {
        return newEntity;
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        logger.debug("Attempting to delete " + className + " with ID '" + id + "'");
        repository.deleteById(UUID.fromString(id));
        logger.debug("Deleted " + className + " with ID '" + id + "'");
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(UUID.fromString(id));
    }
}
