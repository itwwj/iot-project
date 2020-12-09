package com.github.mongodb.base.controller;

import com.github.mongodb.base.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

/**
 * @author jie
 */
public abstract class SuperDataController<E , S extends BaseDataService,ID> implements DeleteController<E,ID>,QueryController<E> ,PageController<E>,SaveController<E>{

    @Autowired
    private S service;

    @Override
    public Class<E> getEntityClass() {
        return(Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public BaseDataService<E> getservice() {
        return service;
    }
}
