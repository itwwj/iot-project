package com.github.iot.api.restentity.rules;

import com.github.iot.api.restentity.BaseResoult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleResponse extends BaseResoult {
    private Rule rule;
}
