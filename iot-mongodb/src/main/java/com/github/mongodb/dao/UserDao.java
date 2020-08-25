package com.github.mongodb.dao;

import com.github.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jie
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {
}
