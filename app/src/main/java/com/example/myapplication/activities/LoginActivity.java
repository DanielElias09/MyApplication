package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    Button signup_btn;

    EditText username;
    EditText password;

    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseAdapter = DatabaseAdapter.getInstance(this);

        login_btn=findViewById(R.id.login_button);
        signup_btn=findViewById(R.id.login_signup);

        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!((databaseAdapter.logIn(username.getText().toString(), password.getText().toString()))))
                {
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

}
