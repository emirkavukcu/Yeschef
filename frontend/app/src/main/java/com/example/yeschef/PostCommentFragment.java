package com.example.yeschef;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.yeschef.databinding.FragmentPostCommentBinding;

import java.util.concurrent.ExecutorService;

public class PostCommentFragment extends Fragment {

    FragmentPostCommentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostCommentBinding.inflate(getLayoutInflater());

        Spinner spRates = binding.spinnerRatings;
        ArrayAdapter<String> adp = new ArrayAdapter<>(getActivity(), R.layout.spinner_item);
        adp.setDropDownViewResource(R.layout.spinner_item);

        adp.add("1");
        adp.add("2");
        adp.add("3");
        adp.add("4");
        adp.add("5");

        spRates.setAdapter(adp);

        binding.btnSubmitComment.setOnClickListener(v -> {
            String recipeId =  getArguments().getString("RecipeId");

            YesChefRepository repo = new YesChefRepository();
            ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;
            String userId = ((YesChefApplication)getActivity().getApplication()).currentUserId;

            repo.postComment(srv, binding.textGetComment.getText().toString(), binding.spinnerRatings.getSelectedItem().toString(), userId, recipeId);
            binding.textCommentPosted.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }
}