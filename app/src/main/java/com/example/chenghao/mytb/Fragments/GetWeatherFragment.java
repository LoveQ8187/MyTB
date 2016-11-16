package com.example.chenghao.mytb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chenghao.mytb.HttpUtils.GetWeatherByCity;
import com.example.chenghao.mytb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetWeatherFragment extends Fragment {

    private final static String TAG="Qin";
    private String cityName="";
    private Context mcontext;
    private static String info="";

    private Handler mMainHandler,mChildHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext=getActivity();
    }

    public GetWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_get_weather, container, false);
        final EditText getCityName=(EditText)view.findViewById(R.id.set_city);
        final Button searchBt=(Button)view.findViewById(R.id.search);
        final TextView todayTitle=(TextView)view.findViewById(R.id.today_title);
        final TextView showWeather=(TextView)view.findViewById(R.id.td_weather);
        final TextView showTemperature=(TextView)view.findViewById(R.id.td_temperature);
        final TextView showWind=(TextView)view.findViewById(R.id.td_wind);
        final TextView showDressingDevice=(TextView)view.findViewById(R.id.td_dressing_advice);
        new Thread(getWeatherRunable).start();

        mMainHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String getResult=(String)msg.obj;
                if (getResult!=null){
                    net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(getResult);
                    todayTitle.setVisibility(View.VISIBLE);
                    todayTitle.setText(getString(R.string.today));
                    showWeather.setText(getString(R.string.td_weather)+obj.getString("weather"));
                    showTemperature.setText(getString(R.string.td_temp)+obj.getString("temperature"));
                    showWind.setText(getString(R.string.td_wind)+obj.getString("wind"));
                    showDressingDevice.setText(obj.getString("dressing_advice"));
                }else {
                    todayTitle.setText(getString(R.string.no_weather_info));
                    todayTitle.setVisibility(View.VISIBLE);
                    showWeather.setText(null);
                    showTemperature.setText(null);
                    showDressingDevice.setText(null);
                    showWind.setText(null);
                }

            }
        };

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName=getCityName.getText().toString();
                if(mChildHandler!=null){
                    Message childMgs=mChildHandler.obtainMessage();
                    childMgs.obj=cityName;
                    mChildHandler.sendMessage(childMgs);
                    Log.d(TAG,"Call childThread to get weather msg");
                }

            }
        });


        return view;
    }

     Runnable getWeatherRunable=new Runnable() {
         @Override
         public void run() {
             Log.e(TAG,"start child process");
             Looper.prepare();
             mChildHandler=new Handler(){
                 @Override
                 public void handleMessage(Message msg) {
                     try{
                         Message getWeatherInfoMsg=mMainHandler.obtainMessage();
                         getWeatherInfoMsg.obj= GetWeatherByCity.GetTodayInfoByCity(cityName);
                         Log.d(TAG,"got message");
                         mMainHandler.sendMessage(getWeatherInfoMsg);
                     }catch (Exception e){
                         e.printStackTrace();
                     }
                 }
             };
             Looper.loop();

         }
     };



}



