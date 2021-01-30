package com.github.iot.api.rest.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private int page;
    private int limit;
    private int count;
    private int hasnext;
}
