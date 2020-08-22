package com.example.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DB.DBManager;
import com.example.myapplication.R;

public class SignupActivity extends AppCompatActivity {

    EditText username;
    EditText fullname;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username=findViewById(R.id.signup_username);
        fullname=findViewById(R.id.signup_fullname);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);

    }

    public void addUser(View view){
        DBManager dbManager = new DBManager(this);
        String res = dbManager.addUser(username.getText().toString(), fullname.getText().toString(), email.getText().toString(), password.getText().toString());
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        username.setText("");
        fullname.setText("");
        email.setText("");
        password.setText("");
    }
}
