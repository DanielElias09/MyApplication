package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.models.Categories;

import java.util.ArrayList;
import java.util.List;


public class AllCategoriesFragment extends Fragment {

    Categories categories;
    View rootView;
    RecyclerView rv_categories;

    public static AllCategoriesFragment newInstance() {
        AllCategoriesFragment fragment = new AllCategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.fragment_all_categories, container, false);
        categories = new Categories();
        rv_categories = rootView.findViewById(R.id.recyclerView_categories);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(categories, this.getContext());
        rv_categories.setAdapter(recyclerViewAdapter);
        rv_categories.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return rootView;
    }

}