package com.example.foodhouse.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodhouse.R;
import com.example.foodhouse.adapter.CommentAdapter;
import com.example.foodhouse.model.Comment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String PostKey;
    FirebaseDatabase firebaseDatabase;
    CommentAdapter commentAdapter;
    static String COMMENT_KEY = "Comments";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_detail);

        getSupportActionBar().hide();


        foodName = (TextView)findViewById(R.id.txtRecipeName);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

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
        PostKey = getIntent().getExtras().getString("postKey");

        iniRvComment();

    }

    private void iniRvComment() {

        mCommentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mComment = new ArrayList<>();
                for (DataSnapshot snap:snapshot.getChildren()) {

                    Comment comment = snap.getValue(Comment.class);
                    mComment.add(comment);
                }

                commentAdapter = new CommentAdapter(getApplicationContext(),mComment);
                mCommentRecyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}