package com.github.iot.api.restentity.client;

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
public class ClientOne extends BaseResoult {
    private Client[] data;
}
