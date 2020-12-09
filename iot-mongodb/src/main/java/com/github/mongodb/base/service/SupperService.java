package com.github.mongodb.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.ParameterizedType;

/**
 * @author jie
 */
public abstract class SupperService<T> implements BaseDataService<T> {
    @Autowired
    private MongoTemplate mongoTemplate;

    protected Class<T> entityClass = null;

    @Override
    public MongoTemplate getDao() {
        return mongoTemplate;
    }

    @Override
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.entityClass;
    }
}
