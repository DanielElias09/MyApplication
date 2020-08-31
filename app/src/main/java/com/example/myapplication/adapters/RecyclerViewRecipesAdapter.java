package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.fragments.RecipeFragment;
import com.example.myapplication.fragments.RecipesByCategoryFragment;
import com.example.myapplication.models.Categories;
import com.example.myapplication.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewRecipesAdapter extends RecyclerView.Adapter<RecyclerViewRecipesAdapter.ViewHolder>
{
    List<Recipe> recipes;
    Context mContext;
    List<String> mImages = new ArrayList<>();
    List<String> mImageNames = new ArrayList<>();
    List<String> mCreators = new ArrayList<>();
    List<Long> mIds = new ArrayList<>();
    HomeActivity ha;

    public RecyclerViewRecipesAdapter(List<Recipe> recipes, Context mContext, HomeActivity ha) {
        this.recipes = recipes;
        loadRecipes();
        this.mContext = mContext;
        this.ha = ha;
    }

    public void loadRecipes(){
        for(int i=0; i<recipes.size(); i++){
            mImages.add(recipes.get(i).getImagePath());
            mImageNames.add(recipes.get(i).getRecipeName());
            mCreators.add(recipes.get(i).getUserName());
            mIds.add(recipes.get(i).getId());
        }
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        holder.name.setText(mImageNames.get(position));
        holder.creator.setText(mCreators.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = RecipeFragment.newInstance(mIds.get(position));
                ha.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        TextView creator;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_recipe_image);
            name = itemView.findViewById(R.id.item_recipe_text);
            creator = itemView.findViewById(R.id.item_recipe_creator);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
