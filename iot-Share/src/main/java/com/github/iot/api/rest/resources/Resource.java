package com.github.iot.api.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    /**
     * 资源 ID
     */
    private String id;
    /**
     * 资源所从属的资源类型的名字
     */
    private String type;
    /**
     * 资源的配置。参数以 key-value 形式表示。
     * 详情可参看后面的示例
     */
    private Object config;
    /**
     * 	资源的状态信息
     */
    private List<StatusBean> status;
    /**
     * 资源的描述信息
     */
    private Object description;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusBean {
        private String node;
        private boolean is_alive;
    }
}
