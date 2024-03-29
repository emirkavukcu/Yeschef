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

import com.example.yeschef.jsonObjects.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    Context ctx;
    List<Comment> data;

    public CommentAdapter (Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.comment_row_layout, parent, false);
        CommentViewHolder holder = new CommentViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.txtDetail.setText(data.get(position).getContent());
        holder.txtCreator.setText( "By: " + data.get(position).getByUser() );
        holder.txtRating.setText( "Rating: " + data.get(position).getRating().toString() + "/5");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView txtCreator;
        TextView txtDetail;
        TextView txtRating;
        ConstraintLayout row;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDetail = itemView.findViewById(R.id.textCommentDetail);
            txtCreator = itemView.findViewById(R.id.textCommentCreator);
            txtRating = itemView.findViewById(R.id.textCommentRating);
            row = itemView.findViewById(R.id.row_Comment);
        }
    }
}
