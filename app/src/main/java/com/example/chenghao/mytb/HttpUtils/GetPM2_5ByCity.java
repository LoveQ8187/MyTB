package com.example.chenghao.mytb.HttpUtils;

import android.util.Log;

/**
 * Created by chenghao on 2016/10/31.
 */
public class GetPM2_5ByCity {
    private final static String TAG="Qin:GetPM2_5ByCity";

    public static String excute(String cityName){
        String url=UnKManager.AIR_URL+"?city="+cityName+"&key="+UnKManager.AIR_KEY;
        Log.d(TAG,url);
        return PureNet.get(url);
    }

}
