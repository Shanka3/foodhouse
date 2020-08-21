package com.example.foodhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodhouse.R;
import com.example.foodhouse.adapter.CommentAdapter;
import com.example.foodhouse.model.Comment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    RecyclerView mCommentRecyclerView;
    List<Comment> mComment;

    private TextView foodDescription,foodName, ratingsUsers;
    private ImageView foodImage;
    private EditText userComments;
    private Button userPost;
    private  String publisherID;
    private String publisherName;
    private DocumentReference documentReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().hide();

        foodName = (TextView)findViewById(R.id.txtRecipeName);
        ratingsUsers = (TextView)findViewById(R.id.ratings_btn);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        userComments = (EditText)findViewById(R.id.comment);
        userPost = (Button)findViewById(R.id.post);

        mCommentRecyclerView = (RecyclerView)findViewById(R.id.rv_Comment);
        userPost.setEnabled(false);

        userComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    userPost.setEnabled(false);
                } else {
                    userPost.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            foodName.setText(bundle.getString("RecipeName"));
            foodDescription.setText(bundle.getString("Description"));

            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into(foodImage);
        }

        userPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPost.setVisibility(View.INVISIBLE);
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                userID = firebaseAuth.getCurrentUser().getUid();
                documentReference = firebaseFirestore.collection("users").document(userID);


                publisherID = firebaseAuth.getCurrentUser().getUid();
                    Comment comment = new Comment(
                        userComments.getText().toString(),
                        publisherID,
                        publisherName
                );

                String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                FirebaseDatabase.getInstance().getReference("Comments").child(myCurrentDateTime).setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailActivity.this, "Comment Added", Toast.LENGTH_SHORT).show();
                            userComments.setText("");
                            userPost.setVisibility(View.VISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailActivity.this, "Error !" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailActivity.this,1);
        mCommentRecyclerView.setLayoutManager(gridLayoutManager);

        mComment = new ArrayList<>();

        final CommentAdapter commentAdapter = new CommentAdapter(DetailActivity.this,mComment);
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

        ratingsUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RatingActivity.class));
            }
        });

    }

}