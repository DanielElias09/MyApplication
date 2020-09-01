package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.DBManager;
import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.main_login_button);
        btn_signup = findViewById(R.id.main_signup_button);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
