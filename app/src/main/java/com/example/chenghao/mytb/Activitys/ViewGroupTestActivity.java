package com.example.chenghao.mytb.Activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.chenghao.mytb.Fragments.NumberClickFragment;
import com.example.chenghao.mytb.R;

/**
 * Created by chenghao on 2016/10/9.
 */
public class ViewGroupTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        Toolbar myToolbar=(Toolbar)findViewById(R.id.toolbar);
        myToolbar.setTitle("ViewGroupTestActivity");

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.view_fragment_container,new NumberClickFragment());



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewGroupTestActivity.this,TableActivity.class));

        finish();
    }
}
