package com.example.chenghao.mytb.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

    private static HashMap<String,Bitmap>restoreImgMsg=new HashMap<>();//用于缓存菜单的图片信息
    private ArrayList<HashMap<String,String>>itemList;  //存储列表需要显示的数据
    private HashMap<String,String>item;
    private Context mContext;
    private LayoutInflater mInflater;//获取LayoutInfalter对象导入布局
    private boolean isCookInfoList=false;//判断是否用作GetCookBookFragment中的listview

    private int layoutId=R.layout.cook_step_list;
    private int imageViewId=R.id.step_pic;
    private int textViewId=R.id.step_msg;
    private int textView2Id;
    public CookListAdapter(Context mContext, ArrayList<HashMap<String,String>>itemList){
        textView2Id=0;
        this.mContext=mContext;
        this.itemList=itemList;
        mInflater= LayoutInflater.from(this.mContext);
    }

    //为代码复用，用此类填充GetCookBookFragment中的listview，所以重载构造函数,传入新的控件id值
    public CookListAdapter(Context context,ArrayList<HashMap<String,String>>itemList,int layoutId){
        isCookInfoList=true;
        this.layoutId=layoutId;
        imageViewId=R.id.food_pic;
        textViewId=R.id.cook_title;
        textView2Id=R.id.cook_discribtion;

        this.mContext=context;
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
            Log.d(TAG,"layoutId="+String.valueOf(layoutId));
            convertView=mInflater.inflate(layoutId,null);
            viewHolder.cookStep=(TextView)convertView.findViewById(textViewId);
            viewHolder.imageView=(ImageView)convertView.findViewById(imageViewId);
            if(textView2Id!=0){
                //如果是用于GetCookBookFragment，则需要获取另一个textview用于显示菜的标签
                viewHolder.cookTag=(TextView)convertView.findViewById(textView2Id);
            }
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        item=itemList.get(i);
        String imgUrl;
        String cookStepOrTitle;
        String cookTag;
        if(textView2Id==0) {
            imgUrl = item.get("imgUrl");
            cookStepOrTitle = item.get("cookStep");
        }else {
            imgUrl = item.get("cookImgUrl");
            cookStepOrTitle = item.get("cookTitle");
            cookTag=item.get("cookTag");
            viewHolder.cookTag.setText(cookTag);
        }

        if(restoreImgMsg.containsKey(imgUrl)){
            //如果缓存中包含图片信息，直接读取
            Log.d(TAG,"img is existed,url="+imgUrl);
            viewHolder.imageView.setImageBitmap(restoreImgMsg.get(imgUrl));
        }else {
            ImageLoadAsyncTask imgLoader = new ImageLoadAsyncTask(viewHolder.imageView);
            imgLoader.execute(imgUrl);
            //获取viewHolder.imageView的bitmap
           // Bitmap restoreBitmap = ((BitmapDrawable) ((ImageView) viewHolder.imageView).getDrawable()).getBitmap();
            //缓存图片信息
           // restoreImgMsg.put(imgUrl, restoreBitmap);
        }
        viewHolder.cookStep.setText(cookStepOrTitle);
        Log.d(TAG, "load item data success");
        return convertView;
    }

    static class ViewHolder{
        //步骤图片or菜的图片
        ImageView imageView;
        //步骤详细or菜名
        TextView cookStep;
        //用于GetCookBookFragment中显示便签
        TextView cookTag;
    }

    //释放缓存中的图片
    public static void releaseImgMsg(){
        if(!restoreImgMsg.isEmpty())restoreImgMsg.clear();
    }

    //缓存图片
    public static void setImgMsg(String url,Bitmap bitmap){
        restoreImgMsg.put(url, bitmap);
    }
}
