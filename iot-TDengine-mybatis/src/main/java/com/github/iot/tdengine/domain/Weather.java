package com.github.iot.tdengine.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author jie
 */
@Data
public class Weather {

    private Timestamp ts;

    private int temperature;

    private float humidity;
}
