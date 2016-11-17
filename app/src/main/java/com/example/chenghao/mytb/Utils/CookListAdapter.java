package com.example.chenghao.mytb.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by chenghao on 2016/11/15.
 * 自定义adapter，用于异步加载做菜步骤中的图片
 */
public class CookListAdapter extends BaseAdapter {

    private List<Map>itemList;//存储列表需要显示的数据
    private int layoutId;//list单行的布局id
    private Context mContext;
    private LayoutInflater mInflater;//获取LayoutInfalter对象导入布局

    public CookListAdapter(Context mContext,List<Map>itemList, int layoutId){
        this.mContext=mContext;
        this.itemList=itemList;
        this.layoutId=layoutId;
        mInflater= LayoutInflater.from(this.mContext);
    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
