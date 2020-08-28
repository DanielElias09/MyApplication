package com.example.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DB.DBManager;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //databaseAdapter = DatabaseAdapter.getInstance(this);

        login_btn=findViewById(R.id.login_button);
        signup_btn=findViewById(R.id.login_signup);

        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*boolean isExist = dbManager.checkUser(username.getText().toString(), password.getText().toString());
                if(isExist==false)
                {
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("username", username.getText().toString());*/


                //databaseAdapter.logIn(username.getText().toString(), password.getText().toString());
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

}
