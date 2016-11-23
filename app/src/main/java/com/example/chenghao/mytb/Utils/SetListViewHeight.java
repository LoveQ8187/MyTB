package com.example.chenghao.mytb.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by chenghao on 2016/11/22.
 * //根据listview的项目设置listview高度
 */
public class SetListViewHeight {
    public static void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter listAdapter=listView.getAdapter();
        if(listAdapter==null)
            return;

        int height=0;
        int length=listAdapter.getCount();
        for(int i=0;i<length;i++){
            View itemView=listAdapter.getView(i,null,listView);
            //计算子项itemView的宽高
            itemView.measure(0,0);
            height+=itemView.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnTextView(ListView listView, TextView textView){
        //测量textview高度
        textView.measure(0,0);
        int height=textView.getMeasuredHeight();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height=height;
        listView.setLayoutParams(params);
    }
}
