package com.github.iot.shadow;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 设备影子
 *
 * @author jie
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseShadow<T> {

    private State<T> state;

    private Metadata metadata;

    private LocalDateTime timestamp;

    private long version;
}
