package com.github.iot.mapper;

import com.github.iot.entity.TestMongoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jie
 */
@Repository
public interface TestMongoDataDao  extends MongoRepository<TestMongoData, String> {
}
