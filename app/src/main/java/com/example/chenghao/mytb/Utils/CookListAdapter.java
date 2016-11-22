package com.example.chenghao.mytb.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenghao.mytb.HttpUtils.ImageLoadAsyncTask;
import com.example.chenghao.mytb.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by chenghao on 2016/11/15.
 * 自定义adapter，用于异步加载做菜步骤listview中的图片和数据
 */
public class CookListAdapter extends BaseAdapter {

    private final static String TAG="Qing:CookListAdapter";
    private final int layoutId=R.layout.cook_step_list;//list单行的布局id

    private ArrayList<HashMap<String,String>>itemList;  //存储列表需要显示的数据
    private HashMap<String,String>item;
    private Context mContext;
    private LayoutInflater mInflater;//获取LayoutInfalter对象导入布局

    public CookListAdapter(Context mContext, ArrayList<HashMap<String,String>>itemList){
        this.mContext=mContext;
        this.itemList=itemList;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(layoutId,null);
            viewHolder.cookStep=(TextView)convertView.findViewById(R.id.step_msg);
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.step_pic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Log.d(TAG,"itemlist'size="+itemList.size());
        item=itemList.get(i);

        String imgUrl=item.get("imgUrl");
        String cookStep=item.get("cookStep");

        ImageLoadAsyncTask imgLoader=new ImageLoadAsyncTask(viewHolder.imageView);
        imgLoader.execute(imgUrl);
        Log.d(TAG,"load item img");
       // viewHolder.cookTitle.setText(item.get("cookTitle"));
        viewHolder.cookStep.setText(cookStep);
        Log.d(TAG,"load item data success");
        return convertView;
    }

    static class ViewHolder{
        //步骤图片
        ImageView imageView;
        //步骤详细
        TextView cookStep;

    }
}
