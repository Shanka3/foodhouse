package com.example.foodhouse.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodhouse.R;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription,foodName;
    ImageView foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().hide();


        foodName = (TextView)findViewById(R.id.txtRecipeName);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            foodName.setText(bundle.getString("RecipeName"));
            foodDescription.setText(bundle.getString("Description"));
           // foodImage.setImageResource(bundle.getInt("Image"));

            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into(foodImage);
        }

    }
}