package com.example.chenghao.mytb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.chenghao.mytb.HttpUtils.CookMsg;
import com.example.chenghao.mytb.HttpUtils.ImageLoadAsyncTask;
import com.example.chenghao.mytb.R;
import com.example.chenghao.mytb.Utils.CookListAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * 用于显示菜谱的详细信息
 */
public class CookInfoFragment extends BackHandleFragment {

    private final static String TAG="Qin:CookInfoFragment";
    private static Context mContext;
    private CookMsg cookMsg;
    private ArrayList<HashMap<String,String>> restoreStepMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    public CookInfoFragment() {
        cookMsg=GetCookBookFragment.remainCookMsg;
        restoreStepMsg=cookMsg.getCookSteps();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_cook_info, container, false);

        TextView showCookTitle=(TextView)view.findViewById(R.id.show_cook_title);
        showCookTitle.setText(cookMsg.getCookTitle());
        TextView showCookTag=(TextView)view.findViewById(R.id.show_cook_tag);
        showCookTag.setText(getString(R.string.cook_tag)+cookMsg.getCookTage());
        TextView showIngredients=(TextView)view.findViewById(R.id.show_ingredients);
        showIngredients.setText(getString(R.string.cook_ingredients)+cookMsg.getIngredients());
        TextView showCookBurden=(TextView)view.findViewById(R.id.show_burden);
        showCookBurden.setText(getString(R.string.cook_burden)+cookMsg.getBurden());

        ImageView showCookPic=(ImageView)view.findViewById(R.id.show_cook_pic);
        ImageLoadAsyncTask imgLoader=new ImageLoadAsyncTask(showCookPic);
        String url=cookMsg.getCookImgUrl();
        if (url!=null) {
            Log.d(TAG, "load cook pic by:"+url);
            imgLoader.execute(url);
        }

        ListView showCookStep=(ListView)view.findViewById(R.id.cook_step_list);
        CookListAdapter cookListAdapter=new CookListAdapter(mContext,restoreStepMsg);
        showCookStep.setAdapter(cookListAdapter);
        setListViewHeightBasedOnChildren(showCookStep);
        return view;
    }

    //根据listview的项目设置listview高度
    private void setListViewHeightBasedOnChildren(ListView listView){
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

    @Override
    protected boolean onBackFragmentPressed() {
        return false;
    }
}
