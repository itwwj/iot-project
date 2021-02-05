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
public class ClientPage extends BaseResoult {
    private Client[] data;
    private Meta meta;
}
