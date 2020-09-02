package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.adapters.RecyclerViewRecipesAdapter;
import com.example.myapplication.models.Recipe;
import com.example.myapplication.models.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {

    private View rootView;
    private DatabaseAdapter databaseAdapter;
    private TextView fullname_tv;
    private TextView username_tv;
    private TextView email_tv;
    private RecyclerView recipes_rv;
    private Button edit_btn;
    public MyAccountFragment() {
    }

    public static MyAccountFragment newInstance(String username) {
        MyAccountFragment fragment = new MyAccountFragment();
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
        rootView = inflater.inflate(R.layout.fragment_my_account, container, false);
        databaseAdapter = DatabaseAdapter.getInstance(this.getContext());
        String username = getArguments().getString("username");
        User user = databaseAdapter.getUserByUsername(getArguments().getString("username"));
        List<Recipe> recipes = databaseAdapter.getAllRecipesByUsername(user.getUsername());

        edit_btn = rootView.findViewById(R.id.my_account_edit_btn);

        edit_btn.setOnClickListener(view -> {
            Fragment fragment = EditUserFragment.newInstance(username);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });

        fullname_tv = rootView.findViewById(R.id.my_account_fullname);
        username_tv = rootView.findViewById(R.id.my_account_username);
        email_tv = rootView.findViewById(R.id.my_account_email);
        recipes_rv = rootView.findViewById(R.id.my_accounts_recipes);

        fullname_tv.setText(user.getFullname());
        username_tv.setText(user.getUsername());
        email_tv.setText(user.getEmail());

        RecyclerViewRecipesAdapter recyclerViewAdapter = new RecyclerViewRecipesAdapter(recipes, this.getContext(), (HomeActivity) this.getActivity());
        recipes_rv.setAdapter(recyclerViewAdapter);
        recipes_rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return rootView;
    }
}