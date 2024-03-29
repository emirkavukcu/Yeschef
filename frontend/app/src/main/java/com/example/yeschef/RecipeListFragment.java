package com.example.yeschef;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yeschef.databinding.FragmentRecipeListBinding;
import com.example.yeschef.jsonObjects.Recipe;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecipeListFragment extends Fragment {

    FragmentRecipeListBinding binding;

    Handler recipesDataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Recipe> data = (List<Recipe>)msg.obj;

            RecipeAdapter adp = new RecipeAdapter(getActivity(),data);
            binding.recView.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity)getActivity()).getToolBar().setTitle("Recipes");
        binding = FragmentRecipeListBinding.inflate(getLayoutInflater());
        binding.recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button postRecipeButton = getActivity().findViewById(R.id.btnPostRecipe);
        postRecipeButton.setVisibility(View.VISIBLE);
        postRecipeButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) getActivity(),R.id.fragmentContainer);
            postRecipeButton.setVisibility(View.INVISIBLE);
            navController.navigate(R.id.action_recipeListFragment_to_postRecipeFragment);
        });


        YesChefRepository repo = new YesChefRepository();
        ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;
        repo.getAllRecipes(srv,recipesDataHandler);

        return binding.getRoot();
    }
}