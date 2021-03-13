package com.github.iot.api.restentity.broker;

import com.github.iot.api.restentity.BaseResoult;
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
public class BrokersResponse extends BaseResoult {
    private Brokers data;
}
