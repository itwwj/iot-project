package com.github.iot.shadow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    /**
     * 设备的预期状态更新时间
     */
    private LocalDateTime desired;
    /**
     * 设备的报告状态更新时间
     */
    private LocalDateTime reported;
}
