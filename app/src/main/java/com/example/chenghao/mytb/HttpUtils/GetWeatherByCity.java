package com.example.chenghao.mytb.HttpUtils;


import android.os.Bundle;
import net.sf.json.JSONObject;


/**
 * Created by chenghao on 2016/10/28.
 */
public class GetWeatherByCity {


    public static String excute(String cityName){
        String url=UnKManager.WEATHER_URL+"?cityname="+cityName+"&key="+UnKManager.WEATHER_KEY;
        return PureNet.get(url);
    }

    //获取当日气温
    public static String GetTodayInfoByCity(String city) {
        String result=excute(city);
        if(result!=null){
            net.sf.json.JSONObject obj= net.sf.json.JSONObject.fromObject(result);
            result=obj.getString("resultcode");
            if(result.equals("200")){
                result=obj.getString("result");
                obj=JSONObject.fromObject(result);
                result=obj.getString("today");
                return result;
            }else{
                return null;
            }
        }
        return result;
    }
}
