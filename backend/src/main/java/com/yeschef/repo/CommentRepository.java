package com.yeschef.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yeschef.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>{
	public Optional<Comment> findById(String id);

	
}
