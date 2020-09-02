package com.example.myapplication.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.DB.FirebaseManager;
import com.example.myapplication.R;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.models.Recipe;

import java.io.IOException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddRecipeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_USERNAME = "username";
    private View rootView;
    private FirebaseManager firebaseManager;
    private DatabaseAdapter databaseAdapter;
    private String username;

    private EditText recipe_name;
    private Spinner category;
    private EditText ingredients;
    private EditText recipe;
    private EditText imagePath;
    private Button add_btn;
    private Bitmap bitmapImage;

    private TextView username_tv;

    private Context context;

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

    public static AddRecipeFragment newInstance(String username) {
        AddRecipeFragment fragment = new AddRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, String.valueOf(username));
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

        recipe_name = rootView.findViewById(R.id.add_recipe_name);
        category = rootView.findViewById(R.id.add_recipe_category);
        ingredients = rootView.findViewById(R.id.add_recipe_ingredients);
        recipe = rootView.findViewById(R.id.add_recipe_recipe);
        imagePath = rootView.findViewById(R.id.add_recipe_imagepath);

        add_btn = rootView.findViewById(R.id.add_recipe_btn);

        add_btn.setOnClickListener(this);
        context = getActivity();
        username = (String) this.getArguments().get(ARG_USERNAME);
        databaseAdapter = DatabaseAdapter.getInstance(this.getActivity());
        firebaseManager = FirebaseManager.getInstance();
        return rootView;
    }

    public void onClick(View view){
        if(imagePath.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Missing image path", Toast.LENGTH_LONG).show();
            return;
        }
        addRecipe(view);
    }

    private void addRecipe(View view) {
        String _recipe_name = recipe_name.getText().toString();
        String _category = category.getSelectedItem().toString();
        String _ingredients = ingredients.getText().toString();
        String _recipe = recipe.getText().toString();
        String _imagePath = imagePath.getText().toString();

        if(_category.equals("Select Category"))
        {
            Toast.makeText(getActivity(), "Failed. Select Category", Toast.LENGTH_LONG).show();
            return;
        }
        Recipe newRecipe = new Recipe( _recipe_name, _ingredients, _recipe, _category, this.username, _imagePath);
        Long res = databaseAdapter.addNewRecipe(newRecipe);
        if(res == -1)
            Toast.makeText(this.getActivity(), "Failed", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this.getActivity(), "Successfully inserted", Toast.LENGTH_LONG).show();

        newRecipe.setId(res);
        firebaseManager.setRecipe(newRecipe);


        FirebaseManager.uploadImage(newRecipe.getImagePath(),"recipe"+newRecipe.getId(), new FirebaseManager.Listener() {
            @Override
            public void onSuccess(String url) {
            }

            @Override
            public void onFail() {

            }
        });

        category.setSelection(0);
        recipe_name.setText("");
        ingredients.setText("");
        recipe.setText("");
        imagePath.setText("");
    }
}