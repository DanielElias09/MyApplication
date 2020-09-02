package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.models.Recipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditRecipeFragment extends Fragment {


    private Long id_recipe;
    private View rootView;
    DatabaseAdapter databaseAdapter;
    private FirebaseDatabase database;
    private DatabaseReference reff;
    private EditText recipe_name;
    private Spinner recipe_category;
    private EditText recipe_ingredients;
    private EditText recipe_method;
    private EditText recipe_image;
    private Button save_btn;

    public EditRecipeFragment() {
    }

    public static EditRecipeFragment newInstance(Long id) {
        EditRecipeFragment fragment = new EditRecipeFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_recipe, container, false);
        id_recipe = getArguments().getLong("id");
        databaseAdapter = DatabaseAdapter.getInstance(getContext());
        database = FirebaseDatabase.getInstance();
        Recipe recipe = databaseAdapter.getRecipeById(id_recipe);

        recipe_image = rootView.findViewById(R.id.edit_recipe_imagepath);
        recipe_name = rootView.findViewById(R.id.edit_recipe_name);
        recipe_ingredients = rootView.findViewById((R.id.edit_recipe_ingredients));
        recipe_method = rootView.findViewById(R.id.edit_recipe_recipe);
        recipe_category = rootView.findViewById(R.id.edit_recipe_category);
        save_btn = rootView.findViewById(R.id.edit_recipe_btn);

        recipe_image.setText(recipe.getImagePath());
        recipe_category.setSelection(getSpinnerSelection(recipe.getCategory()));
        recipe_ingredients.setText(recipe.getRecipeIngredients());
        recipe_method.setText(recipe.getRecipeMethod());
        recipe_name.setText(recipe.getRecipeName());

        save_btn.setOnClickListener(view -> {
            if(recipe_category.getSelectedItem().toString().equals("Select Category"))
            {
                Toast.makeText(getActivity(), "Failed. Select Category", Toast.LENGTH_LONG).show();
                return;
            }
            recipe.setRecipeName(recipe_name.getText().toString());
            recipe.setImagePath(recipe_image.getText().toString());
            recipe.setCategory(recipe_category.getSelectedItem().toString());
            recipe.setRecipeIngredients(recipe_ingredients.getText().toString());
            recipe.setRecipeMethod(recipe_method.getText().toString());
            databaseAdapter.updateRecipe(recipe);
            Toast.makeText(getActivity(), "Recipe updated", Toast.LENGTH_LONG).show();

            reff = database.getReference("recipes/recipe"+recipe.getId());
            reff.setValue(recipe);

        });

        return rootView;
    }

    public int getSpinnerSelection(String category){
        switch (category){
            case "Pies":
                return 1;
            case "Pasta":
                return 2;
            case "Meats":
                return 3;
            case "Salads":
                return 4;
            case "Drinks":
                return 5;
            case "Desserts":
                return 6;
        }
        return 0;
    }
}