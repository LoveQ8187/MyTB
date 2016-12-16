package com.example.chenghao.mytb.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenghao.mytb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberClickFragment extends Fragment {


    public NumberClickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_number_click, container, false);
        return view;
    }

}
