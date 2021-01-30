package com.github.iot.api.rest.banned;

import com.github.iot.api.rest.BaseResoult;
import lombok.Data;

/**
 * @author jie
 */
@Data
public class Gbanned extends BaseResoult {

    /**
     * 由对象构成的数组，对象中的字段与 POST 方法中的 Request Body 相同
     */
    private String[] data;
    /**
     * 分页信息
     */
    private Object meta;
}
