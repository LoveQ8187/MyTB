package com.example.chenghao.mytb.HttpUtils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenghao on 2016/11/10.
 * 此类用于解析查询菜谱后返回的json文件
 */
public class JsonForCookMsg {
    private final static String TAG="Qin:JsonForCookMsg";

    public static List<CookMsg> getCookMsgList(String cookMsg){
        Log.d(TAG,"start json");
        CookMsg getCookMsg;
        net.sf.json.JSONArray stepArray;
        net.sf.json.JSONArray jsonArray;
        String result;
        HashMap<String,String>step;
        ArrayList<HashMap<String,String>>steps;

        List<CookMsg> cookMsgs=new ArrayList<>();
        net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(cookMsg);
        result=obj.getString("result");
        obj=net.sf.json.JSONObject.fromObject(result);
        jsonArray=net.sf.json.JSONArray.fromObject(obj.getString("data"));

        for(int i=0;i<jsonArray.size();i++){
            getCookMsg=new CookMsg();
            steps=new ArrayList<>();
            obj=jsonArray.getJSONObject(i);
            getCookMsg.setCookTitle(obj.getString("title"));
            getCookMsg.setCookTage(obj.getString("tags"));
            getCookMsg.setCookImtro(obj.getString("imtro"));
            getCookMsg.setIngredients(obj.getString("ingredients"));
            getCookMsg.setBurden(obj.getString("burden"));
            //读取菜谱步骤
            stepArray=net.sf.json.JSONArray.fromObject(obj.getString("steps"));
            for(int j=0;j<stepArray.size();j++){
                step=new HashMap();
                obj=stepArray.getJSONObject(j);
                step.put(obj.getString("img"),obj.getString("step"));
                steps.add(step);
            }
            getCookMsg.setCookSteps(steps);
            cookMsgs.add(getCookMsg);
            Log.d(TAG,"add cook message");
        }
        Log.d(TAG,"finish json for cook");
        return  cookMsgs;
    }
}
