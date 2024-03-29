package com.example.yeschef;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yeschef.jsonObjects.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    Context ctx;
    List<Recipe> data;

   public RecipeAdapter(Context ctx, List<Recipe> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.recipe_row_layout, parent, false);
        RecipeViewHolder holder = new RecipeViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.txtTitle.setText(data.get(position).getTitle().toString());
        holder.txtCreator.setText( "By: " +data.get(position).getCreatorUserName().toString() );
        if(data.get(position).getRatingOverall() != -1) {
            holder.txtRating.setText("Rating: " + data.get(position).getRatingOverall() +  "/5" );
        }
        else{
            holder.txtRating.setText("Not Rated");
        }

        holder.row.setOnClickListener(v->{

            NavController navController = Navigation.findNavController((Activity) ctx,R.id.fragmentContainer);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("RecipeId",data.get(holder.getAdapterPosition()).getId());

            navController.navigate(R.id.action_recipeListFragment_to_selectedRecipeFragment,dataBundle);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtCreator;
        TextView txtRating;
        ConstraintLayout row;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textRecipeTitle);
            txtCreator = itemView.findViewById(R.id.textCommentCreator);
            txtRating = itemView.findViewById(R.id.textCommentRating);
            row = itemView.findViewById(R.id.row_Comment);
        }
    }
}
