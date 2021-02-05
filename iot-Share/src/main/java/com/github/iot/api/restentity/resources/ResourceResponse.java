package com.github.iot.api.restentity.resources;

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
public class ResourceResponse extends BaseResoult {
    private Resource data;
}
