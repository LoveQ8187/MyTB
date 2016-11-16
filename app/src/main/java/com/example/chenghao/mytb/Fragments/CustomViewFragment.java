package com.example.chenghao.mytb.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenghao.mytb.R;

public class CustomViewFragment extends Fragment {
    public CustomViewFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_custom_view, container, false);
    }

}
