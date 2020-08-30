package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.myapplication.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewRecipesAdapter extends RecyclerView.Adapter<RecyclerViewRecipesAdapter.ViewHolder>{

    Context mContext;
    List<Recipe> recipes;
    List<String> mImages = new ArrayList<>();
    List<String> mNames = new ArrayList<>();
    List<String> mCreators = new ArrayList<>();
    HomeActivity ha;

    public RecyclerViewRecipesAdapter(Context mContext, List<Recipe> recipes, HomeActivity ha) {
        this.mContext = mContext;
        this.recipes = recipes;
        loadRecipes();
        this.ha = ha;
    }
    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void loadRecipes(){
        for (int i = 0; i< recipes.size(); i++){
            mImages.add(recipes.get(i).getImagePath());
            mCreators.add(recipes.get(i).getUserName());
            mNames.add(recipes.get(i).getRecipeName());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        holder.name.setText(mNames.get(position));
        holder.creator.setText(mCreators.get(position));
        holder.parentLayout.setOnClickListener(view -> {
            Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         CircleImageView image;
         TextView name;
         TextView creator;
         RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_recipe_image);
            creator = itemView.findViewById(R.id.item_recipe_creator);
            name = itemView.findViewById(R.id.item_recipe_name);
        }
    }
}
