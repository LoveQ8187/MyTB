package com.example.chenghao.mytb.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenghao.mytb.Interfaces.BackHandleInterface;
import com.example.chenghao.mytb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BackHandleFragment extends Fragment {

    protected BackHandleInterface mBackHandleInterface;

    protected abstract boolean onBackFragmentPressed();

    public BackHandleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_back_handle, container, false);
    }

}
