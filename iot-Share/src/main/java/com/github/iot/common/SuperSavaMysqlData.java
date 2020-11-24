package com.github.iot.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 往mysql数据库存入数据的父类抽象，只要继承此类即可将消息存入mysql
 * @author jie
 */
public abstract class SuperSavaMysqlData<T,M extends BaseMapper<T>> extends SuperConsumer<T>{
    @Autowired
    protected M mapper;
    @Override
    protected void msgHandler(String topic, T entity) {
        mapper.insert(entity);
    }
}
