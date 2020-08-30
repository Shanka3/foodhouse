package com.example.foodhouse.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodhouse.R;
import com.example.foodhouse.adapter.CommentAdapter;
import com.example.foodhouse.model.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SkipDetailActivity extends AppCompatActivity {

    RecyclerView mCommentRecyclerView;
    List<Comment> mComment;

    TextView foodDescription,foodName;
    ImageView foodImage;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_detail);

        getSupportActionBar().hide();


        foodName = (TextView)findViewById(R.id.txtRecipeName);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);

        mCommentRecyclerView = (RecyclerView)findViewById(R.id.rv_Comment);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            foodName.setText(bundle.getString("RecipeName"));
            foodDescription.setText(bundle.getString("Description"));
            // foodImage.setImageResource(bundle.getInt("Image"));

            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into(foodImage);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SkipDetailActivity.this,1);
        mCommentRecyclerView.setLayoutManager(gridLayoutManager);

        mComment = new ArrayList<>();

        final CommentAdapter commentAdapter = new CommentAdapter(SkipDetailActivity.this,mComment);
        mCommentRecyclerView.setAdapter(commentAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Comments");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // mComment.clear();

                for (DataSnapshot itemSnapshot: snapshot.getChildren()){

                    Comment comment = itemSnapshot.getValue(Comment.class);

                    mComment.add(comment);
                }

                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}