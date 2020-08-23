package com.example.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    String username;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView=findViewById(R.id.textViewhome);
        username = this.getIntent().getStringExtra("username");
        textView.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_option_addrecipe:
                Intent intent1 = new Intent(HomeActivity.this, AddRecipe.class);
                startActivity(intent1);
                return true;
            case R.id.user_option_myaccount:
                Intent intent2 = new Intent(HomeActivity.this, MyAccountActivity.class);
                startActivity(intent2);
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
}