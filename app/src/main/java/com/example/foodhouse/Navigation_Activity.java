package com.example.foodhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodhouse.activity.ProfileActivity;
import com.example.foodhouse.activity.RecipeActivity;
import com.example.foodhouse.activity.SignIn_Activity;
import com.google.android.material.navigation.NavigationView;

public class Navigation_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), RecipeActivity.class));
        } else if (item.getItemId() == R.id.nav_profile){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }else if (item.getItemId() == R.id.nav_changeProfile){

        }else if (item.getItemId() == R.id.nav_Share){

        }else if (item.getItemId() == R.id.nav_Logout){
            startActivity(new Intent(getApplicationContext(), SignIn_Activity.class));
        }
        return true;
    }
}