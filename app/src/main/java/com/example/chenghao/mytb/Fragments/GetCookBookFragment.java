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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenghao.mytb.HttpUtils.CookMsg;
import com.example.chenghao.mytb.HttpUtils.GetCookMsgs;
import com.example.chenghao.mytb.HttpUtils.PureNet;
import com.example.chenghao.mytb.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetCookBookFragment extends Fragment {

    public static boolean isReturnForBackStack=false;//用于判断是否从back栈中返回，若是，则重新填充cookList
    private final static String TAG="Qin:GetCookBookFragment";
    private Context mContext;
    private Handler childHandle,mainHandle;
    private static List<CookMsg>cookMsgs=null;
    private SimpleAdapter simpleAdapter;
    private static List<HashMap<String,String>>msgItem;//运管处存储用于填充listview的cookMsg

    public static CookMsg remainCookMsg;//用于存储listview选取的cookMsg,供CookInfoFragment使用
    private OnItemClick onItemClick;
    public GetCookBookFragment() {
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
        final View view= inflater.inflate(R.layout.fragment_get_cook_book, container, false);
        final EditText setCookName=(EditText)view.findViewById(R.id.input_cook_name);
        final TextView setOnLoading=(TextView)view.findViewById(R.id.loading);
        setOnLoading.setVisibility(View.GONE);
        Button searchButton=(Button)view.findViewById(R.id.cook_search);
        final ListView cookList=(ListView)view.findViewById(R.id.cool_list);

        //当GetCookBookFragment从back栈返回时cookList会被清空，因此重新填充
        if(isReturnForBackStack){
            simpleAdapter=new SimpleAdapter(
                    mContext,
                    msgItem,
                    R.layout.cook_list,
                    new String[]{"cookTitle","cookImtro"},
                    new int[]{R.id.cook_title,R.id.cook_discribtion}
            );
            cookList.setAdapter(simpleAdapter);
            Log.d(TAG,"reload cookList");
            isReturnForBackStack=false;
        }
        new Thread(getCookInfo).start();

        mainHandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG,"update cook listview");
                simpleAdapter=new SimpleAdapter(
                        mContext,
                        msgItem,
                        R.layout.cook_list,
                        new String[]{"cookTitle","cookImtro"},
                        new int[]{R.id.cook_title,R.id.cook_discribtion}
                );
                cookList.setAdapter(simpleAdapter);
                setOnLoading.setVisibility(View.GONE);
                Toast.makeText(mContext, "Searched Success", Toast.LENGTH_SHORT).show();
            }
        };

        cookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setRemainCookMsg(i);
                onItemClick.OnClick();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cookName=setCookName.getText().toString();
                Message message=childHandle.obtainMessage();
                message.obj=cookName;
                childHandle.sendMessage(message);
                setOnLoading.setVisibility(View.VISIBLE);
            }
        });
        return view;

    }


    Runnable getCookInfo=new Runnable() {
        @Override
        public void run() {
            Looper.prepare();
            childHandle=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    String cookName=(String) msg.obj;
                   // cookMsgs= GetCookMsgs.excute(cookName);
                    cookMsgs=GetCookMsgs.excuteForJson(cookName);
                    setMsgItem();
                    Log.d(TAG,"childHandle got the cook message");
                    Message mainMsg=mainHandle.obtainMessage();
                    mainMsg.obj=null;
                    mainHandle.sendMessage(mainMsg);

                }
            };
            Looper.loop();
        }
    };

    //将获取的list<CookMsg>转化为用于填充listview的List<Map>
    private void setMsgItem(){
        Log.d(TAG,"try to get Msg Items");
        msgItem=new ArrayList<>();
        HashMap<String,String> map;
        CookMsg cookMsg;
        for(int i=0;i<cookMsgs.size();i++){
            cookMsg=cookMsgs.get(i);
            map=new HashMap<>();
            map.put("cookTitle",cookMsg.getCookTitle());
            map.put("cookImtro",cookMsg.getCookImtro());
            map.put("cookImgUrl",cookMsg.getCookImgUrl());
            msgItem.add(map);
        }
        Log.d(TAG,"set Msg Items Success");
    }
    //TODO:remainCookMsg没有存储做菜的步骤信息，需要重新检查代码
    //根据listview的选择设置remainCookMsg
    private void setRemainCookMsg(int i){
        if(!cookMsgs.isEmpty()){
            remainCookMsg=cookMsgs.get(i);
        }
    }
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }
    public interface OnItemClick{
         void OnClick();
    }

}
