package com.example.myapplication.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.fragments.AddRecipeFragment;
import com.example.myapplication.fragments.AllCategoriesFragment;
import com.example.myapplication.fragments.MyAccountFragment;

public class HomeActivity extends AppCompatActivity {


    private DatabaseAdapter databaseAdapter;
    private String username;
    private BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseAdapter = DatabaseAdapter.getInstance(this);
        username = getIntent().getExtras().getString("username");
        bottom_nav = findViewById(R.id.home_bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_add_recipe:
                    selectedFragment = AddRecipeFragment.newInstance(username);
                    break;
                case R.id.nav_my_account:
                    selectedFragment = MyAccountFragment.newInstance();
                    break;
                case R.id.nav_home:
                    selectedFragment = AllCategoriesFragment.newInstance();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllCategoriesFragment()).commit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.user_option_addrecipe:
                selectedFragment = AddRecipeFragment.newInstance(username);
                break;
            case R.id.user_option_myaccount:
                selectedFragment = new MyAccountFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    }
}