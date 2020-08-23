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

public class SignupActivity extends AppCompatActivity {

    EditText username;
    EditText fullname;
    EditText email;
    EditText password;
    Button save_btn;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save_btn=findViewById(R.id.signup_button);
        login_btn=findViewById(R.id.signup_login);

        username=findViewById(R.id.signup_username);
        fullname=findViewById(R.id.signup_fullname);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser(view);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addUser(View view){
        DBManager dbManager = new DBManager(this);
        String res = dbManager.addUser(username.getText().toString(), fullname.getText().toString(), email.getText().toString(), password.getText().toString());
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        username.setText("Username");
        fullname.setText("Full Name");
        email.setText("Email");
        password.setText("Password");
    }
}
