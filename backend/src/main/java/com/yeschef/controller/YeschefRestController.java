package com.yeschef.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import com.yeschef.repo.CommentRepository;
import com.yeschef.repo.UserRepository;
import com.yeschef.repo.RecipeRepository;
import com.yeschef.model.Comment;
import com.yeschef.model.CommentPayload;
import com.yeschef.model.CommentWithCreator;
import com.yeschef.model.User;
import com.yeschef.model.UserPayload;
import com.yeschef.model.UserSignupPayload;
import com.yeschef.model.Recipe;
import com.yeschef.model.RecipeGenerator;
import com.yeschef.model.RecipePayload;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/yeschef")
public class YeschefRestController {
	
	@Autowired private RecipeRepository recipeRepository;
	@Autowired private CommentRepository commentRepository;
	@Autowired private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(YeschefRestController.class);
	
	@PostConstruct
	public void init() {
		
		if(recipeRepository.count() == 0) {
			
			logger.info("Database is empty, initializing...");
			
			// Create users
	        User user1 = new User("emir", "1234");
	        User user2 = new User("zeynep", "1234");
	        User user3 = new User("berkay", "1234");
	        User user4 = new User("umut", "1234");

	        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

	        // Create recipes
	        List<User> users = userRepository.findAll();
	        
	        RecipeGenerator recipeGenerator = new RecipeGenerator();

	        Recipe recipe1 = new Recipe("Pancakes", recipeGenerator.recipes.get(0), "breakfast", users.get(0));
	        Recipe recipe2 = new Recipe("Parfait", recipeGenerator.recipes.get(1), "breakfast", users.get(1));
	        Recipe recipe3 = new Recipe("Wrap", recipeGenerator.recipes.get(2), "lunch", users.get(2));
	        Recipe recipe4 = new Recipe("Quinoa", recipeGenerator.recipes.get(3), "lunch", users.get(3));
	        Recipe recipe5 = new Recipe("Chicken", recipeGenerator.recipes.get(4), "dinner", users.get(0));
	        Recipe recipe6 = new Recipe("Lasagna", recipeGenerator.recipes.get(5), "dinner", users.get(1));

	        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2, recipe3, recipe4, recipe5, recipe6));

	        // Create comments
	        List<Recipe> recipes = recipeRepository.findAll();

	        Comment comment1 = new Comment("Great Recipe!", String.valueOf(5), users.get(0), recipes.get(0));
	        Comment comment2 = new Comment("This recipe is really good!", String.valueOf(4), users.get(1), recipes.get(1));
	        Comment comment3 = new Comment("Nice breakfast option!", String.valueOf(5), users.get(2), recipes.get(1));
	        Comment comment4 = new Comment("I enjoyed making this for lunch!", String.valueOf(4), users.get(3), recipes.get(2));
	        Comment comment5 = new Comment("Amazing lunch recipe!", String.valueOf(5), users.get(2), recipes.get(3));
	        Comment comment6 = new Comment("Quick and tasty!", String.valueOf(3), users.get(3), recipes.get(4));
	        Comment comment7 = new Comment("Tried this for dinner, loved it!", String.valueOf(5), users.get(0), recipes.get(5));
	        Comment comment8 = new Comment("Perfect for a cozy dinner!", String.valueOf(4), users.get(1), recipes.get(5));
	        
	        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3, comment4,
	        		comment5, comment6, comment7, comment8));
	        
	        List<Comment> comments = commentRepository.findAll();
	        
	        recipes.get(0).addComment(comments.get(0));
	        recipes.get(1).addComment(comments.get(1));
	        recipes.get(1).addComment(comments.get(2));
	        recipes.get(2).addComment(comments.get(3));
	        recipes.get(3).addComment(comments.get(4));
	        recipes.get(4).addComment(comments.get(5));
	        recipes.get(5).addComment(comments.get(6));
	        recipes.get(5).addComment(comments.get(7));
	        
	        recipeRepository.saveAll(recipes);
	        
	        users.get(0).addComment(comments.get(0));
	        users.get(1).addComment(comments.get(1));
	        users.get(2).addComment(comments.get(2));
	        users.get(3).addComment(comments.get(3));
	        users.get(2).addComment(comments.get(4));
	        users.get(3).addComment(comments.get(5));
	        users.get(0).addComment(comments.get(6));
	        users.get(1).addComment(comments.get(7));
	        
	        userRepository.saveAll(users);

			logger.info("Data initilization is finished.");	
		}
		
	}
	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes(){	
		List<Recipe> recipes = recipeRepository.findAll();
		List<Recipe> updatedJsonRecipes = new ArrayList<>();
		
		for(int i = 0 ; i < recipes.size(); i++) {
			Recipe c = recipes.get(i);
			Recipe rcp = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
					c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
			
			User u = rcp.getCreator();
			u.setPostedComments(null);
			u.setPassword(null);
			updatedJsonRecipes.add(rcp);
		}
		return updatedJsonRecipes;
	}
	
	@GetMapping("/recipes/popular")
	public List<Recipe> getAllRecipesDescendingRating(){	
        Sort sort = Sort.by(Sort.Order.desc ("ratingOverall"));	
        
		List<Recipe> recipes = recipeRepository.findAll(sort);;
		List<Recipe> updatedJsonRecipes = new ArrayList<>();
		
		for(int i = 0 ; i < recipes.size(); i++) {
			Recipe c = recipes.get(i);
			Recipe rcp = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
					c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
			
			User u = rcp.getCreator();
			u.setPostedComments(null);
			u.setPassword(null);
			updatedJsonRecipes.add(rcp);
		}
		return updatedJsonRecipes;
	}

	@GetMapping("/recipes/searchid/{id}")
	public Recipe getRecipeById(@PathVariable("id") String id){
		
		Recipe c = recipeRepository.findById(id).get();
		Recipe updatedJsonRecipe = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
				c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
		
		User u = updatedJsonRecipe.getCreator();
		u.setPostedComments(null);
		u.setPassword(null);
		return updatedJsonRecipe;
	}
	
	@GetMapping("/recipes/searchtitle/{title}")
	public List<Recipe> getRecipeByTitle(@PathVariable("title") String title){
		
		List<Recipe> recipes = recipeRepository.findByTitleLikeIgnoreCase(title);
		List<Recipe> updatedJsonRecipes = new ArrayList<>();
		
		for(int i = 0 ; i < recipes.size(); i++) {
			Recipe c = recipes.get(i);
			Recipe rcp = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
					c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
			
			User u = rcp.getCreator();
			u.setPostedComments(null);
			u.setPassword(null);
			updatedJsonRecipes.add(rcp);
		}
		return updatedJsonRecipes;
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") String id){
		User user = userRepository.findById(id).get();
		UserPayload updatedJsonUser = new UserPayload(user.getId(), user.getUsername(), 
				user.getAccountCreateTime(), user.getPostedComments());
		
		return user;
		
	}
	
	@GetMapping("/recipes/category/{category}")
	public List<Recipe> getRecipeByType(@PathVariable("category") String type){
		
		List<Recipe> recipes = recipeRepository.findByTypeLikeIgnoreCase(type);
		List<Recipe> updatedJsonRecipes = new ArrayList<>();
		
		for(int i = 0 ; i < recipes.size(); i++) {
			Recipe c = recipes.get(i);
			Recipe rcp = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
					c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
			
			User u = rcp.getCreator();
			u.setPostedComments(null);
			u.setPassword(null);
			updatedJsonRecipes.add(rcp);
		}
		return updatedJsonRecipes;
	}
	
	@PostMapping("/recipes/create")
	public Recipe createRecipe(@RequestBody RecipePayload payload)
	{
		User usr = userRepository.findById(payload.getCreatorId()).get();
		
		Recipe recipeToSave = new Recipe(payload.getTitle(), payload.getDescription(),
				payload.getType(), usr);
				
		Recipe recipeSaved = recipeRepository.save(recipeToSave);
		
		Recipe c = recipeSaved;
		Recipe updatedJsonRecipe = new Recipe(c.getId(), c.getTitle(), c.getDescription(), c.getType(),c.getPostTime(),
				c.getRatingOverall(), c.getCreator(), c.getComments(), c.getRatingList());
		
		User u = updatedJsonRecipe.getCreator();
		u.setPostedComments(null);
		u.setPassword(null);

		return updatedJsonRecipe;
	}
	
	@PostMapping("/recipes/rate")
	public float rateRecipe(@RequestParam("id") String id, 
			@RequestParam("rating") Integer rating) {
		
		Recipe rcp = recipeRepository.findById(id).get();	
		rcp.addRating(rating);
		recipeRepository.save(rcp);		
		return rcp.getRatingOverall();
	}
	
	@PostMapping("/user/signup")
	public User userSignUp(@RequestBody UserSignupPayload newUser ) {
		
        User userToSignUp = new User(newUser.getUsername(), newUser.getPassword());
        User signedUpUser = userRepository.save(userToSignUp);
		return signedUpUser;	
	}
	
	@PostMapping("/recipes/postcomment")
	public Comment postComment(@RequestBody CommentPayload payload)
	{
		User usr = userRepository.findById(payload.getUserId()).get();
		Recipe rcp = recipeRepository.findById(payload.getRecipeId()).get();	
		Comment commentToPost = new Comment(payload.getContent(), payload.getRating(),
				 usr, rcp);
				
		Comment commentPosted = commentRepository.save(commentToPost);
		rcp.addComment(commentPosted);
		recipeRepository.save(rcp);
		
		return commentPosted;
	}

	@GetMapping("/recipes/{id}/comments")
	public List<CommentWithCreator> getCommentsOfRecipe(@PathVariable("id") String id){	
		
		List<CommentWithCreator> commentList = new ArrayList<>();
		
		Recipe rcp = recipeRepository.findById(id).get();
		List<Comment> rawCommentList = rcp.getComments();
		
		for(int i = 0; i < rawCommentList.size(); i++) {
			
			Comment crnt = rawCommentList.get(i);
			String creatorUserName = crnt.getByUser().getUsername();
			commentList.add(new CommentWithCreator(crnt.getId(), crnt.getContent(), crnt.getCommentPostTime(),
					crnt.getRating(), creatorUserName));
		}
		
		return commentList;
	}
	
	@GetMapping("/users")
	public List<User> userDetails(){	
		List<User> users = userRepository.findAll();	
		/*List<UserPayload> userdetails = new ArrayList<>();
				
		for(int i = 0; i < users.size(); i++) {
			User current = users.get(i);
			userdetails.add(new UserPayload(current.getId(), current.getUsername(), current.getAccountCreateTime(), current.getPostedComments()));
		}
		*/
		return users;
	}

	@GetMapping("/recipes/categories")
	public List<String> getAllCategories() {
	       List<String> categories = new ArrayList<>();
	       categories.add("Breakfast");
	       categories.add("Lunch");
	       categories.add("Dinner");
	       return categories;
	}
}
