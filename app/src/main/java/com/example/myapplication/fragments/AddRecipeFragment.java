package com.example.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecipeFragment extends Fragment implements View.OnClickListener {

    View rootView;

    private EditText username;
    private EditText recipe_name;
    private EditText category;
    private EditText ingredients;
    private EditText recipe;
    private Button add_btn;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters


    public AddRecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment AddRecipeFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static AddRecipeFragment newInstance() {
        AddRecipeFragment fragment = new AddRecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        username = rootView.findViewById(R.id.add_recipe_username);
        recipe_name = rootView.findViewById(R.id.add_recipe_name);
        category = rootView.findViewById(R.id.add_recipe_categpry);
        ingredients = rootView.findViewById(R.id.add_recipe_ingredients);
        recipe = rootView.findViewById(R.id.add_recipe_recipe);
        add_btn = rootView.findViewById(R.id.add_recipe_btn);
        add_btn.setOnClickListener(this);
        context = getActivity();
        return rootView;
    }

    public void onClick(View view){
        addRecipe(view);
    }

    private void addRecipe(View view) {
        /*DBManager dbManager = new DBManager(context);
        String res = dbManager.addRecipe(recipe_name.getText().toString(), ingredients.getText().toString(), recipe.getText().toString(), category.getText().toString(), username.getText().toString());
        Toast.makeText(context, res, Toast.LENGTH_LONG).show();
        username.setText("Username");
        ingredients.setText("Ingredients");
        category.setText("Category");
        recipe_name.setText("Recipe name");
        recipe.setText("Recipe");*/
    }
}