package com.example.chenghao.mytb.Animations;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;

import com.example.chenghao.mytb.Animations.Interpolators.ParabolaInterpolators;

/**
 * Created by chenghao on 2016/9/20.
 */
public class TestObjectAnimations{
    final static String ROTATION="rotation";
    View view;
    ObjectAnimator objectAnimator;

    public TestObjectAnimations(View view){
        this.view=view;
    }
    public void runAnimation(){
        getObjectAnimator().start();
    }

    private ObjectAnimator getObjectAnimator(){
        objectAnimator=ObjectAnimator.ofFloat(view,ROTATION,0f,36000f);
        objectAnimator.setDuration(50000);
        objectAnimator.setRepeatMode(Animation.RESTART);
        objectAnimator.setRepeatCount(100);
        objectAnimator.setInterpolator(new AnticipateInterpolator());
        return objectAnimator;
    }


}
