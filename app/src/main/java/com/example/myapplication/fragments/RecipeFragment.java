package com.example.myapplication.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.models.Recipe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {


    private View rootView;
    private DatabaseAdapter databaseAdapter;
    private Recipe recipe;
    private Long id;
    private String current_username;

    private CircleImageView recipe_image;
    private TextView recipe_name;
    private TextView recipe_username;
    private TextView recipe_ingredients;
    private TextView recipe_method;
    private Button edit_btn;

    public RecipeFragment() {
    }

    public static RecipeFragment newInstance(Long id, String current_username) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        args.putString("username", current_username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_recipe, container, false);
        databaseAdapter = DatabaseAdapter.getInstance(this.getContext());
        id = getArguments().getLong("id");
        current_username = getArguments().getString("username");

        recipe = databaseAdapter.getRecipeById(id);

        recipe_image = rootView.findViewById(R.id.recipe_view_image);
        recipe_name = rootView.findViewById(R.id.recipe_view_name);
        recipe_username = rootView.findViewById(R.id.recipe_view_username);
        recipe_ingredients = rootView.findViewById((R.id.recipe_view_ingredients));
        recipe_method = rootView.findViewById(R.id.recipe_view_method);
        edit_btn = rootView.findViewById(R.id.recipe_edit_btn);

        edit_btn.setVisibility(View.INVISIBLE);
        if(recipe.getUserName().equals(current_username))
            edit_btn.setVisibility(View.VISIBLE);

        Glide.with(this.getContext())
                .asBitmap()
                .load(recipe.getImagePath())
                .into(recipe_image);
        recipe_name.setText(recipe.getRecipeName());
        recipe_username.setText(recipe.getUserName());
        recipe_ingredients.setText(recipe.getRecipeIngredients());
        recipe_method.setText(recipe.getRecipeMethod());

        return rootView;
    }
}