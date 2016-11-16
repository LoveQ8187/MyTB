package com.example.chenghao.mytb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.chenghao.mytb.HttpUtils.CookMsg;
import com.example.chenghao.mytb.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * 用于显示菜谱的详细信息
 */
public class CookInfoFragment extends Fragment {

    private static Context mContext;
    private CookMsg cookMsg;
    private ArrayList<HashMap<String,String>>restoreStepMsg;

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
        TextView showCookImtro=(TextView)view.findViewById(R.id.show_cook_imtro);
        showCookImtro.setText(cookMsg.getCookImtro());
        TextView showCookTag=(TextView)view.findViewById(R.id.show_cook_tag);
        showCookTag.setText(cookMsg.getCookTage());
        TextView showIngredients=(TextView)view.findViewById(R.id.show_ingredients);
        showIngredients.setText(cookMsg.getIngredients());
        TextView showCookBurden=(TextView)view.findViewById(R.id.show_burden);
        showCookBurden.setText(cookMsg.getBurden());

        ListView showCookStep=(ListView)view.findViewById(R.id.cook_step_list);


        return view;
    }


}
