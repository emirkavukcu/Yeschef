package com.yeschef.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yeschef.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
	
	public List<Recipe> findByTitleLikeIgnoreCase(String title);
	public Optional<Recipe> findById(String id);
	public List<Recipe> findByTypeLikeIgnoreCase(String type);
}
