package com.example.yeschef;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeschef.databinding.FragmentSignupBinding;
import com.example.yeschef.HomeLoginFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;


public class SignupFragment extends Fragment {

    FragmentSignupBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(getLayoutInflater());
        YesChefRepository repo = new YesChefRepository();
        ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;

        binding.buttonSignup2.setOnClickListener(v ->{

            String username = binding.textUsernameSignup.getText().toString();
            String password = binding.textPasswordSignup.getText().toString();
            repo.signUser(srv, username, password);
            binding.textSignupSuccess.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }
}