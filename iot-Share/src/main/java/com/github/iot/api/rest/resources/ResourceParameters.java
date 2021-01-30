package com.github.iot.api.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceParameters {


    /**
     * 资源类型名。指定要使用哪个资源类型创建资源。
     */
    private String type;
    /**
     * 资源参数。要跟对应的资源类型的 params 里指定的格式相一致。
     */
    private ConfigBean config;
    /**
     * 资源描述
     */
    private String description;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConfigBean {
        private String url;
        private String headers;
        private String method;
    }
}


