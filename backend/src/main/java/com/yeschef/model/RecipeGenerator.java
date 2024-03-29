package com.yeschef.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeGenerator {
	
    public List<String> recipes = new ArrayList<>();
    
    public RecipeGenerator() {
        String pancakeInstructions = "1. Whisk together dry ingredients in a bowl.\n"
                + "2. Whisk together wet ingredients in another bowl.\n"
                + "3. Combine wet and dry ingredients, fold in blueberries.\n"
                + "4. Cook spoonfuls of batter on a griddle until golden brown.\n"
                + "5. Stack and serve with maple syrup.";
        
        String parfaitInstructions = "1. Layer Greek yogurt, granola, and mixed berries in a glass.\n"
                + "2. Drizzle honey and sprinkle with chopped nuts.\n"
                + "3. Repeat for a nutritious parfait.";
        
        String wrapInstructions = "1. Spread hummus on each tortilla.\n"
                + "2. Layer with vegetables and feta cheese.\n"
                + "3. Roll up tightly, cut in half, and enjoy.";
        
        String quinoaInstructions = "1. Combine cooked quinoa with roasted vegetables.\n"
                + "2. Toss in feta and fresh basil.\n"
                + "3. Drizzle with balsamic vinaigrette, toss gently, and serve.";
        
        String chickenInstructions = "1. Marinate chicken in lemon juice, minced garlic, herbs, olive oil, salt, and pepper.\n"
                + "2. Grill until cooked through. Serve with side dishes.";
        
        String lasagnaInstructions = "1. Brown ground beef with garlic, drain excess fat.\n"
                + "2. Layer noodles with beef, sauce, ricotta, and mozzarella.\n"
                + "3. Repeat layers, sprinkle with Parmesan and Italian seasoning.\n"
                + "4. Bake until bubbly and golden. Let it rest before serving.";

        recipes.add(pancakeInstructions);
        recipes.add(parfaitInstructions);
        recipes.add(wrapInstructions);
        recipes.add(quinoaInstructions);
        recipes.add(chickenInstructions);
        recipes.add(lasagnaInstructions);
    }
}
