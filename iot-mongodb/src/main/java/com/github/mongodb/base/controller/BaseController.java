package com.github.mongodb.base.controller;


import com.github.mongodb.base.R;
import com.github.mongodb.base.service.BaseDataService;

/**
 * @author jie
 */
public interface BaseController <Entity >{


    /**
     * 获取实体的类型
     *
     * @return
     */
    Class<Entity> getEntityClass();

    /**
     * 获取service
     * @return
     */
    BaseDataService<Entity> getservice();
    /**
     * 成功返回
     *
     * @param data 返回内容
     * @param <T>  返回类型
     * @return R
     */
    default <T> R<T> success(T data) {
        return R.success(data);
    }

    /**
     * 成功返回
     *
     * @return R.true
     */
    default R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg 失败消息
     * @param <T> 返回类型
     * @return
     */
    default <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    /**
     * 失败返回
     *
     * @param msg  失败消息
     * @param args 动态参数
     * @param <T>  返回类型
     * @return
     */
    default <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
    }


}
