package com.github.iot.shadow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class State<T> {
    /**
     * 设备的预期状态
     */
    private T desired;
    /**
     * 设备的报告状态
     */
    private T reported;
}
