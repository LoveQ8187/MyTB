package com.example.chenghao.mytb.Utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenghao on 2016/9/20.
 */
public class ZoomOutPic {
    View view;
    public ZoomOutPic(View view){
        this.view=view;
    }
    public void zoomOut(){
        ViewGroup.MarginLayoutParams params=new ViewGroup.MarginLayoutParams(view.getLayoutParams());

    }
}
