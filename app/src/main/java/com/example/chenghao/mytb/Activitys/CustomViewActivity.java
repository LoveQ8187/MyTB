package com.example.chenghao.mytb.Activitys;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.chenghao.mytb.Fragments.CustomViewFragment;
import com.example.chenghao.mytb.R;

public class CustomViewActivity extends TableActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

       FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
       fragmentTransaction.add(R.id.fragment_container,new CustomViewFragment());
       fragmentTransaction.commit();
    }
}
