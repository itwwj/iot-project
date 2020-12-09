package com.github.mongodb.base.service;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 通用service
 *
 * @author jie
 */
public interface BaseDataService<T> {
    /**
     * 获取service
     *
     * @return
     */
    MongoTemplate getDao();

    /**
     * 获取实体的类型
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 增
     *
     * @param t
     */
    default T save(T t) {
        return (T) getDao().insert(t);
    }

    /**
     * 批量增加
     *
     * @param ib
     * @return
     */
    default Collection<T> saveAll(Collection<T> ib) {
        return getDao().insertAll(ib);
    }

    /**
     * 删
     *
     * @param id
     */
    default DeleteResult deleteById(Object id) {
        return getDao().remove(id, "id");
    }

    /**
     * 条件删除
     * @param query
     * @return
     */
    default DeleteResult deleteById(Query query) {
        return getDao().remove(query, getEntityClass());
    }

    /**
     * 改
     *
     * @param t
     */
    default T update(T t) {
        return (T) getDao().save(t);
    }

    /**
     * 查
     *
     * @param id
     * @return
     */
    default T findById(Object id) {
        return getDao().findById(id, getEntityClass());
    }

    /**
     * 分页查询
     *
     * @return
     */
    default Page<T> findPage(int current,int size) throws ParseException {
        PageRequest pageRequest = PageRequest.of(current,size);
        List<T> list = getDao().find(new Query().with(pageRequest), getEntityClass());
        int count = (int) getDao().count(new Query(), getEntityClass());
        return PageableExecutionUtils.getPage(list, pageRequest, () -> count);
    }


    /**
     * 条件查询分页查询
     * @return
     */
    default Page<T> findByExample(Map<String,String> map,int current,int size) {
        Query query=new Query();
        for (String key : map.keySet()) {
            query.addCriteria(Criteria.where(key).is(map.get(key)));
        }
        PageRequest pageRequest = PageRequest.of(current,size);
        List<T> list = getDao().find(query.with(pageRequest), getEntityClass());
        int count = (int) getDao().count(query, getEntityClass());
        return PageableExecutionUtils.getPage(list, pageRequest, () -> count);
    }
}
