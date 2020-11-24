package com.github.iot.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author jie
 */
public abstract class SuperSavaMongoData<T,ID ,M extends MongoRepository<T, ID>> extends SuperConsumer<T> {
    @Autowired
    protected  M dao;
    @Override
    protected void msgHandler(String topic, T entity) {
        dao.insert(entity);
    }
}
