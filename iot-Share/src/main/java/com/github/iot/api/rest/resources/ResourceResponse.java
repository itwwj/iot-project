package com.github.iot.api.rest.resources;

import com.github.iot.api.rest.BaseResoult;
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
