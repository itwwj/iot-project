package com.github.iot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test")
public class TestMongoData {
    @Id
    private String id;
    private String deviceName;
}
