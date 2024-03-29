package com.example.yeschef;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeschef.databinding.FragmentSelectedRecipeBinding;
import com.example.yeschef.jsonObjects.Recipe;

import java.util.concurrent.ExecutorService;


public class SelectedRecipeFragment extends Fragment {

    FragmentSelectedRecipeBinding binding;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Recipe rcp = (Recipe) msg.obj;
            binding.textSelectedRecipeTitle.setText(rcp.getTitle());
            binding.textSelectedRecipeDetail.setText(rcp.getDescription());

            if(rcp.getRatingOverall() != -1) {
                binding.textSelectedRecipeRating.setText("Rating: " + rcp.getRatingOverall() +  "/5" );
            }
            else{
                binding.textSelectedRecipeRating.setText("Not Rated");
            }

            ((MainActivity)getActivity()).getToolBar().setTitle(rcp.getTitle());
            return true;

        }
    });
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("DEV", "ON create view");
        binding = FragmentSelectedRecipeBinding.inflate(getLayoutInflater());
        getActivity().findViewById(R.id.btnPostRecipe).setVisibility(View.INVISIBLE);
        String recipeId = getArguments().getString("RecipeId");
        YesChefRepository repo = new YesChefRepository();
        ExecutorService srv = ((YesChefApplication)getActivity().getApplication()).srv;
        repo.getRecipeById(srv,handler,recipeId);

        binding.imageViewPostComment.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) getActivity(),R.id.fragmentContainer);
            Bundle dataBundle = new Bundle();
            dataBundle.putString("RecipeId", recipeId);
            navController.navigate(R.id.action_selectedRecipeFragment_to_postCommentFragment, dataBundle);
        });

        binding.buttonSeeComments.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) getActivity(),R.id.fragmentContainer);
            Bundle dataBundle = new Bundle();
            dataBundle.putString("RecipeId", recipeId);
            navController.navigate(R.id.action_selectedRecipeFragment_to_commentsFragment, dataBundle);
        });

        return binding.getRoot();
    }
}