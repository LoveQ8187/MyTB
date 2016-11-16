package com.example.chenghao.mytb.Animations.Interpolators;

import android.view.animation.Interpolator;

/**
 * Created by chenghao on 2016/9/28.
 */
public class ExParabolaInterpolators implements Interpolator {
    @Override
    public float getInterpolation(float v) {
        return 1-v*v;
    }
}
