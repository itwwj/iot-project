package com.github.iot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jie
 */
@Data
@AllArgsConstructor
public class HttpAuthCode {
    private Integer statusCode;

    public static HttpAuthCode ok() {
        return new HttpAuthCode(200);
    }

    public static HttpAuthCode error() {
        return new HttpAuthCode(404);
    }
}
