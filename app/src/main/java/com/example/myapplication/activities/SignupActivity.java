package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.DB.DBManager;
import com.example.myapplication.DB.FirebaseManager;
import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText username;
    private EditText fullname;
    private EditText email;
    private EditText password;
    private Button save_btn;
    private Button login_btn;
    private DatabaseAdapter databaseAdapter;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseAdapter = DatabaseAdapter.getInstance(this);
        databaseAdapter = DatabaseAdapter.getInstance(this);
        firebaseManager = FirebaseManager.getInstance();

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
                SignupActivity.this.finish();
            }
        });

    }

    public void addUser(View view){

        String _username = username.getText().toString();
        String _fullname = fullname.getText().toString();
        String _email = email.getText().toString();
        String _password = password.getText().toString();

        if(_username.equals("") || _fullname.equals("") || _email.equals("") || _password.equals(""))
        {
            Toast.makeText(this, "Missing details", Toast.LENGTH_LONG).show();
            return;
        }

        User newUser = new User(_username, _fullname, _email, _password);
        String res = databaseAdapter.addNewUser(newUser);
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        firebaseManager.setUser(newUser);

        username.setText("");
        fullname.setText("");
        email.setText("");
        password.setText("");


    }
}
