package com.github.iot.api.rest.rules;

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
public class RuleParameters {

    /**
     * 规则的 SQL 语句
     */
    private String rawsql;
    /**
     * 规则描述
     */
    private String description;
    /**
     * 动作列表
     */
    private List<ActionsBean> actions;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActionsBean {
        /**
         * 动作名称
         */
        private String name;
        /**
         * 动作参数。参数以 key-value 形式表示。
         * 详情可参看添加规则的示例
         */
        private Object params;

    }
}
