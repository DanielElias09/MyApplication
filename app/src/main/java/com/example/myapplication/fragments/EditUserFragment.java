package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.models.User;


public class EditUserFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String username;
    private View rootView;
    private DatabaseAdapter databaseAdapter;

    private Button save_details_btn;
    private Button save_password_btn;

    private EditText new_password;
    private EditText new_username;
    private EditText new_fullname;
    private EditText new_email;

    public EditUserFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static EditUserFragment newInstance(String username) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_user, container, false);
        username = getArguments().getString("username");
        databaseAdapter = DatabaseAdapter.getInstance(getContext());

        User user = databaseAdapter.getUserByUsername(username);

        save_details_btn = rootView.findViewById(R.id.edit_user_save_details);
        save_password_btn = rootView.findViewById(R.id.edit_user_save_password);
        new_password = rootView.findViewById(R.id.edit_user_password);
        new_username = rootView.findViewById(R.id.edit_user_username);
        new_fullname = rootView.findViewById(R.id.edit_user_fullname);
        new_email = rootView.findViewById(R.id.edit_user_email);

        new_username.setText(user.getUsername());
        new_fullname.setText(user.getFullname());
        new_email.setText(user.getEmail());

        save_password_btn.setOnClickListener(view -> {
            user.setPassword(new_password.getText().toString());
            String res = databaseAdapter.updateUser(user, username);
            Toast.makeText(getActivity(), res, Toast.LENGTH_LONG).show();
            new_password.setText("");
        });

        save_details_btn.setOnClickListener(view -> {
            user.setUsername(new_username.getText().toString());
            user.setFullname(new_fullname.getText().toString());
            user.setEmail(new_email.getText().toString());
            String res = databaseAdapter.updateUser(user, username);
            Toast.makeText(getActivity(), res, Toast.LENGTH_LONG).show();
            User user2 = databaseAdapter.getUserByUsername(user.getUsername());
            new_username.setText(user2.getUsername());
            new_fullname.setText(user2.getFullname());
            new_email.setText(user2.getEmail());
        });

        return rootView;
    }
}