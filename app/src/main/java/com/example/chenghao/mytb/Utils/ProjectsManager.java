package com.example.chenghao.mytb.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.chenghao.mytb.Activitys.*;


/**
 * Created by chenghao on 2016/9/18.
 * 管理本应用的多个测试项目，当添加新的项目时，修改此类
 */
public class ProjectsManager extends AppCompatActivity {
    final static private String itemArray[]={
            "WebTest",
            "HttpTest",
            "AnimationTest",
            "CustomView",
            "ViewGroupTest",
            "GM2048"
    };
    private Context context;
    public ProjectsManager(Context context){
        this.context=context;
    }

    public static String[]getItemArray(){
        return itemArray;
    }

    public Intent getIntent(int i){
        Intent intent=null;
        switch (i){
            case 0:
                intent=new Intent(context,WebTestActivity.class);
                break;
            case 1:
                intent=new Intent(context,HttpTestActivity.class);
                break;
            case 2:
                intent=new Intent(context,AnimationTestActivity.class);
                break;
            case 3:
                intent=new Intent(context,CustomViewActivity.class);
                break;
            case 4:
                intent=new Intent(context,ViewGroupTestActivity.class);break;
            case 5:
                intent=new Intent(context,GM2048.class);break;
            default:
                break;
        }
        return intent;
    }
}
