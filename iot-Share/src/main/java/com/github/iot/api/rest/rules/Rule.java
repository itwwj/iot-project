package com.github.iot.api.rest.rules;

import com.alibaba.fastjson.annotation.JSONField;
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
public class Rule {
    /**
     * Rule ID
     */
    private String id;
    /**
     * SQL 语句，与请求中的 rawsql 一致
     */
    private String rawsql;
    /**
     * Topic 列表，表示哪些 topic 可以匹配到此规则
     */
    @JSONField(name = "for")
    private List<String> forX;
    /**
     * 统计指标
     */
    private List<MetricsBean> metrics;
    /**
     * 规则的描述信息，与请求中的 description 一致
     */
    private String description;
    /**
     * 动作列表
     */
    private List<ActionsBean> actions;
    private boolean enabled;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetricsBean {

        private int speed_max;
        private double speed_last5m;
        private double speed;
        private String node;
        private int matched;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActionsBean {
        /**
         * 动作参数，与请求中的 actions.params 一致
         */
        private Object params;
        /**
         * 动作名字，与请求中的 actions.name 一致
         */
        private String name;
        /**
         * Action ID
         */
        private String id;
        /**
         * 统计指标
         */
        private List<MetricsBeanX> metrics;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MetricsBeanX {
            private int success;
            private String node;
            private int failed;
        }
    }
}
