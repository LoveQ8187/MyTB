package com.example.chenghao.mytb.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chenghao.mytb.R;

/**
 * Created by chenghao on 2016/10/9.
 */
public class ViewGroupTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_linear_layout);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewGroupTestActivity.this,TableActivity.class));

        finish();
    }
}
