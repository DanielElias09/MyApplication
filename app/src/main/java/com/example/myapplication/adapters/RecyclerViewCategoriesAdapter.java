package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.fragments.RecipesByCategoryFragment;
import com.example.myapplication.models.Categories;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewCategoriesAdapter extends RecyclerView.Adapter<RecyclerViewCategoriesAdapter.ViewHolder>{

    Categories categories;
    Context mContext;
    List<String> mImages = new ArrayList<>();
    List<String> mImageNames = new ArrayList<>();
    HomeActivity ha;

    public RecyclerViewCategoriesAdapter(Categories categories, Context mContext, HomeActivity ha) {
        mImages = categories.getAllImages();
        mImageNames = categories.getAllNames();
        this.mContext = mContext;
        this.ha = ha;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        holder.category.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = RecipesByCategoryFragment.newInstance(mImageNames.get(position));
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
        TextView category;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_category_image);
            category = itemView.findViewById(R.id.item_category_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
