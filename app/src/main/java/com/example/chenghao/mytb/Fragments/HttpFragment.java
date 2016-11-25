package com.example.chenghao.mytb.Fragments;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chenghao.mytb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HttpFragment extends Fragment implements View.OnClickListener{

    private final static int WEATHER_TAG=1;
    private final static int PM_TAG=2;
    private final static int COOKBOOK_TAG=3;

    private ChangeAcitityToolbar changeAcitityToolbar;
    public HttpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_http, container, false);
        Button selectWeather=(Button)view.findViewById(R.id.weather_button);
        selectWeather.setTag(WEATHER_TAG);
        selectWeather.setOnClickListener(this);
        Button selectPM=(Button)view.findViewById(R.id.pm_button);
        selectPM.setTag(PM_TAG);
        selectPM.setOnClickListener(this);
        Button selectCookBook=(Button)view.findViewById(R.id.cook_button);
        selectCookBook.setTag(COOKBOOK_TAG);
        selectCookBook.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int tag=(Integer)view.getTag();
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        switch (tag){
            case WEATHER_TAG:
                fragmentTransaction.replace(R.id.fragment_container, new GetWeatherFragment());
                break;
            case PM_TAG:
                fragmentTransaction.replace(R.id.fragment_container, new GetPM2_5Fragment());
                break;
            case COOKBOOK_TAG:
                fragmentTransaction.replace(R.id.fragment_container, new GetCookBookFragment());
                break;
            default:break;
        }
        fragmentTransaction.commit();
        changeAcitityToolbar.setToolbar(tag);
    }

    //设置回调函数，用于修改HttpTestAtivity的toolbar
    public void setChangeAcitityToolbarAction(ChangeAcitityToolbar changeAcitityToolbar){
        this.changeAcitityToolbar=changeAcitityToolbar;
    }
    public interface ChangeAcitityToolbar{
         void setToolbar(int tag);
    }
}
