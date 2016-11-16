package com.example.chenghao.mytb.Animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.chenghao.mytb.Animations.Interpolators.ExParabolaInterpolators;
import com.example.chenghao.mytb.Animations.Interpolators.LinesInterpolators;
import com.example.chenghao.mytb.Animations.Interpolators.ParabolaInterpolators;

/**
 * Created by chenghao on 2016/9/19.
 * 动画管理类，定义多种动画方式
 */
public class Animations {

    //旋转动画
    public static AnimationSet setRotateAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        RotateAnimation rotateAnimation=new RotateAnimation(
                0,-360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
        );
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(-1);
        animationSet.addAnimation(rotateAnimation);
        return animationSet;
    }

    //大小动画
    public static AnimationSet setScaleAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        ScaleAnimation scaleAnimation=new ScaleAnimation(
                0,1f,0,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
        );
        scaleAnimation.setDuration(1000);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }


    public static AnimationSet specialScale(final View view){

        final int ANIMATION_TIME=1000;
        AnimationSet animationSet=new AnimationSet(true);

        /* *******************动画开始************************* */
        //设置动画开始，图案由小变大
        final ScaleAnimation riseScaleAnimation=new ScaleAnimation(
               1f,2f,1f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
        );
        riseScaleAnimation.setDuration(ANIMATION_TIME);

        //设置动画开始，旋转由慢变快
        RotateAnimation riseRotateAnimation=new RotateAnimation(0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        riseRotateAnimation.setInterpolator(new ParabolaInterpolators());
        riseRotateAnimation.setDuration(ANIMATION_TIME);
        riseRotateAnimation.setFillAfter(true);
         /* *******************动画过程中************************* */
        final ScaleAnimation keepScaleAnimation=new ScaleAnimation(
                1f,1f,1f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f
        );
        keepScaleAnimation.setDuration(ANIMATION_TIME);
        keepScaleAnimation.setRepeatMode(Animation.RESTART);
        keepScaleAnimation.setRepeatCount(-1);
        keepScaleAnimation.setStartTime(ANIMATION_TIME);

        final RotateAnimation keepRotateAnimation=new RotateAnimation(0,-360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //keepRotateAnimation.setInterpolator(new ParabolaInterpolators());
        keepRotateAnimation.setDuration(ANIMATION_TIME);
        //keepScaleAnimation.setStartTime(ANIMATION_TIME);
        //keepRotateAnimation.setRepeatMode(Animation.RESTART);
        keepRotateAnimation.setRepeatCount(-1);
        //keepRotateAnimation.setFillAfter(true);


        riseScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
              //  view.startAnimation(riseScaleAnimation);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
              //  view.startAnimation(keepRotateAnimation);
                //view.startAnimation(keepScaleAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationSet.addAnimation(riseScaleAnimation);
        animationSet.addAnimation(riseRotateAnimation);
        animationSet.addAnimation(keepRotateAnimation);
        animationSet.addAnimation(keepScaleAnimation);
        return animationSet;
    }

    public static AnimationSet setAlphaAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        AlphaAnimation alphaAnimation=new AlphaAnimation(
          1,0
        );
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static AnimationSet setTransitionAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation(
          Animation.RELATIVE_TO_SELF,0,
          Animation.RELATIVE_TO_SELF,0.5f,
          Animation.RELATIVE_TO_SELF,0,
          Animation.RELATIVE_TO_SELF,0
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(translateAnimation);
        return animationSet;

    }

    public static AnimationSet setRotate3DAnimation(float width,float heigth){
        AnimationSet animationSet=new AnimationSet(true);
        Rotate3DAnimation rotate3DAnimation=new Rotate3DAnimation(
            90,0,width,heigth,310.0f,false
        );
        rotate3DAnimation.setDuration(1000);
        animationSet.addAnimation(rotate3DAnimation);
        return animationSet;
    }


}
