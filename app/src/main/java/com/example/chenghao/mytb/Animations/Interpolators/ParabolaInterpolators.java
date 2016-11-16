package com.example.chenghao.mytb.Animations.Interpolators;


/**
 * Created by chenghao on 2016/9/21.
 */
public class ParabolaInterpolators implements android.view.animation.Interpolator{

    @Override
    public float getInterpolation(float v) {
        return v*v;
    }
}
