package com.example.chenghao.mytb.Utils;

import android.view.MotionEvent;
import android.view.View;

import com.example.chenghao.mytb.Animations.Animations;

/**
 * Created by chenghao on 2016/9/20.
 */
public class EyesTouchLisener implements View.OnTouchListener {

    private View myView;
    public EyesTouchLisener(View myView){
        this.myView=myView;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                myView.startAnimation(Animations.specialScale(view));
                break;
            case MotionEvent.ACTION_UP:
                myView.clearAnimation();
                break;
        }
        return true;

    }
}
