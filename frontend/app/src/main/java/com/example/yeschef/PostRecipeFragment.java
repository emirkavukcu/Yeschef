package com.example.yeschef;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yeschef.databinding.FragmentPostRecipeBinding;
import java.util.concurrent.ExecutorService;

public class PostRecipeFragment extends Fragment {
    FragmentPostRecipeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPostRecipeBinding.inflate(getLayoutInflater());

        binding.btnSubmitRecipe.setOnClickListener(v -> {

            YesChefRepository repo = new YesChefRepository();
            ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;
            String title = binding.textGetTitle.getText().toString();
            String description = binding.textGetRecipe.getText().toString();
            String userId = ((YesChefApplication)getActivity().getApplication()).currentUserId;

            repo.postRecipe(srv, title, description, userId);
            binding.textRecipePosted.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }
}