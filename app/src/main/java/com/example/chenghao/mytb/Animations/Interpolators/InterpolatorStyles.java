package com.example.chenghao.mytb.Animations.Interpolators;


import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by chenghao on 2016/9/21.
 */
public class InterpolatorStyles {
    private static String[] IStyles={
            "ParabolaInterpolators",
            "ExParabolaInterpolators",
            "LinesInterpolators"
    };

    public static String[] getIStyles(){
        return IStyles;
    }

    public static Interpolator setInterpolatorStyle(String styleName){
        //当未设置插值器名称，则返回默认插值器
        if(styleName==null)
            return defaultInterpolator;
        switch (styleName){
            case "ParabolaInterpolators":
                return new ParabolaInterpolators();
            case "ExParabolaInterpolators":
                return new ExParabolaInterpolators();
            case "LinesInterpolators":
                return new LinesInterpolators();
            default:
                return defaultInterpolator;
        }
    }

    //定义一个默认插值器
    public static LinearInterpolator defaultInterpolator=new LinearInterpolator();


}
