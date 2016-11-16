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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenghao.mytb.HttpUtils.GetPM2_5ByCity;
import com.example.chenghao.mytb.R;

import net.sf.json.JSONArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetPM2_5Fragment extends Fragment {

    private static String cityName="";
    private final static String TAG="Qin";
    private Handler mainHandle,childHandle;
    private Context mContext=null;
    public GetPM2_5Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_get_air, container, false);


        final EditText getCityName=(EditText)view.findViewById(R.id.air_input_city);
        final Button searchBt=(Button)view.findViewById(R.id.air_search);
        final TextView noInfo=(TextView)view.findViewById(R.id.no_message);
        final TextView pmTime=(TextView)view.findViewById(R.id.pm_time);
        final TextView pmValue=(TextView)view.findViewById(R.id.pm_value);
        final TextView pmQuality=(TextView)view.findViewById(R.id.pm_quality);
        //接受子线程传回的数据并更新UI
        mainHandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String getResult=(String) msg.obj;
                Log.d(TAG,getResult);
                net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(getResult);
                getResult=obj.getString("resultcode");
                if(getResult.equals("200")){
                    getResult=obj.getString("result");
                    net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(getResult);
                    obj=jsonArray.getJSONObject(0);
                    getResult=obj.getString("citynow");
                    obj=net.sf.json.JSONObject.fromObject(getResult);
                    noInfo.setVisibility(View.GONE);
                    pmTime.setText(getString(R.string.td_date)+obj.getString("date"));
                    pmQuality.setText(getString(R.string.td_air)+obj.getString("quality"));
                    pmValue.setText(getString(R.string.td_pm)+obj.getString("AQI"));
                }else {
                    noInfo.setVisibility(View.VISIBLE);
                    noInfo.setText(getString(R.string.no_air_info));
                    pmTime.setText(null);
                    pmValue.setText(null);
                    pmQuality.setText(null);
                }
            }
        };

        new Thread(getPollutionRunnable).start();

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName=getCityName.getText().toString();
                if(childHandle!=null){
                    Message childMsg=childHandle.obtainMessage();
                    childMsg.obj=cityName;
                    childHandle.sendMessage(childMsg);
                }
            }
        });
        return view;
    }

    Runnable getPollutionRunnable=new Runnable() {
        @Override
        public void run() {
            Log.e(TAG,"start child process");
            Looper.prepare();
            childHandle=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                   try {
                       Log.d(TAG,"childThread is called");
                       Message getPoluutionInfo = mainHandle.obtainMessage();
                       getPoluutionInfo.obj = GetPM2_5ByCity.excute(cityName);
                       Log.d(TAG,"get air message");
                       mainHandle.sendMessage(getPoluutionInfo);
                   }catch (Exception e){

                   }
                }
            };
            Looper.loop();
        }
    };


}
