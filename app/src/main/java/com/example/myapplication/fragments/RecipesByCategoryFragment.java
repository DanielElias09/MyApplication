package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.adapters.DatabaseAdapter;
import com.example.myapplication.adapters.RecyclerViewCategoriesAdapter;
import com.example.myapplication.adapters.RecyclerViewRecipesAdapter;
import com.example.myapplication.models.Categories;
import com.example.myapplication.models.Recipe;

import java.util.List;

public class RecipesByCategoryFragment extends Fragment {
    List<Recipe> recipes;
    String category;
    View rootView;
    RecyclerView rv_recipes;
    DatabaseAdapter databaseAdapter;

    public static RecipesByCategoryFragment newInstance(String category) {
        RecipesByCategoryFragment fragment = new RecipesByCategoryFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_recipes_by_category, container, false);
        databaseAdapter = DatabaseAdapter.getInstance(this.getContext());
        category = this.getArguments().get("category").toString();
        recipes = databaseAdapter.getAllRecipesByCategory(category);

        rv_recipes = rootView.findViewById(R.id.recyclerView_recipes);
        RecyclerViewRecipesAdapter recyclerViewAdapter = new RecyclerViewRecipesAdapter(recipes, this.getContext(), (HomeActivity) this.getActivity());
        rv_recipes.setAdapter(recyclerViewAdapter);
        rv_recipes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return rootView;
    }
}
