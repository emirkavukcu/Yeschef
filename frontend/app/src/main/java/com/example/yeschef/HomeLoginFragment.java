package com.example.yeschef;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeschef.databinding.FragmentHomeLoginBinding;
import com.example.yeschef.jsonObjects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class HomeLoginFragment extends Fragment {

    FragmentHomeLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("DEV", "ON create view home login");
        binding = FragmentHomeLoginBinding.inflate(getLayoutInflater());
        getActivity().findViewById(R.id.btnPostRecipe).setVisibility(View.INVISIBLE);
        YesChefRepository repo = new YesChefRepository();
        ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;
        List<User> currentUsers = new ArrayList<>();
        repo.getAllUsers(srv, currentUsers);


        binding.loginButton.setOnClickListener(v ->{
            String userName = binding.usernameEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            boolean authenticated = false;
            for (User user : currentUsers) {
                Log.i("DEV", user.getUsername());
                Log.i("DEV", user.getPassword());
                if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                    authenticated = true;
                    ((YesChefApplication)getActivity().getApplication()).currentUserId = user.getId();
                    break;
                }
            }

            if (authenticated) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
                navController.navigate(R.id.action_homeLoginFragment_to_recipeListFragment);
            } else {
                binding.errorText.setText("Wrong username or password! Try Again!");
                binding.errorText.setVisibility(View.VISIBLE); // Show the error text
            }
        });

        binding.buttonSignup.setOnClickListener(v ->{

            NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainer);
            navController.navigate(R.id.action_homeLoginFragment_to_signupFragment);

        });

        return binding.getRoot();

    }
}