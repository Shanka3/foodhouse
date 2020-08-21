package com.example.foodhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhouse.R;
import com.example.foodhouse.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>  {

    private Context mContext;
    private List<Comment> mComment;


    public CommentAdapter(Context mContext, List<Comment> mComment) {
        this.mContext = mContext;
        this.mComment = mComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_comment, parent, false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.userComment.setText(mComment.get(position).getUserComments());
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

    TextView userComment;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        userComment = itemView.findViewById(R.id.tvUserComments);

    }
   }
}
