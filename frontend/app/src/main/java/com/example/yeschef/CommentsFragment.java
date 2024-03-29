package com.example.yeschef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yeschef.databinding.FragmentCommentsBinding;
import com.example.yeschef.databinding.FragmentRecipeListBinding;
import com.example.yeschef.jsonObjects.Comment;
import com.example.yeschef.jsonObjects.Recipe;

import java.util.List;
import java.util.concurrent.ExecutorService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.yeschef.databinding.ActivityMainBinding;

public class CommentsFragment extends Fragment {

    FragmentCommentsBinding binding;
    Handler CommentsDataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Comment> data = (List<Comment>)msg.obj;

            CommentAdapter adp = new CommentAdapter(getActivity(),data);
            binding.recViewComments.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getToolBar().setTitle("Comments");

        binding = FragmentCommentsBinding.inflate(getLayoutInflater());

        binding.recViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        String recipeId =  getArguments().getString("RecipeId");

        YesChefRepository repo = new YesChefRepository();

        ExecutorService srv=((YesChefApplication)getActivity().getApplication()).srv;

        repo.getCommentsOfRecipe(srv,CommentsDataHandler, recipeId);

        return binding.getRoot();
    }
}