package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class DrinksFragment extends CategoryFragment {


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_drinks;
    }
}