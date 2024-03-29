package com.yeschef.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yeschef.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	public Optional<User> findById(String id);

}
