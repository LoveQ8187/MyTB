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
import com.example.chenghao.mytb.Utils.CookListAdapter;
import com.example.chenghao.mytb.Utils.SetListViewHeight;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetCookBookFragment extends Fragment {

    public static boolean isReturnForBackStack=false;//判断是否从back栈中返回，若是，则重新填充cookList
    private static boolean isGetCookMessageSuccess=false;//判断是否返回正确的菜谱信息
    private final static String TAG="Qin:GetCookBookFragment";
    private Context mContext;
    private Handler childHandle,mainHandle;
    private static List<CookMsg>cookMsgs=null;
    private SimpleAdapter simpleAdapter;
    private CookListAdapter cookListAdapter;
    private static ArrayList<HashMap<String,String>>msgItem;//运管处存储用于填充listview的cookMsg

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
            cookListAdapter=new CookListAdapter(mContext,msgItem,R.layout.cook_list);
            cookList.setAdapter(cookListAdapter);
            SetListViewHeight.setListViewHeightBasedOnChildren(cookList);
            Log.d(TAG,"reload cookList");
            isReturnForBackStack=false;
        }
        new Thread(getCookInfo).start();

        mainHandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG,"update cook listview ");
                if(isGetCookMessageSuccess) {
                    //子线程获取到正确的菜谱信息
                    cookListAdapter = new CookListAdapter(mContext, msgItem, R.layout.cook_list);
                    cookList.setAdapter(cookListAdapter);
                    SetListViewHeight.setListViewHeightBasedOnChildren(cookList);
                    setOnLoading.setVisibility(View.GONE);
                }else {
                    //子线程获取菜谱信息失败
                    setOnLoading.setVisibility(View.VISIBLE);
                    setOnLoading.setText(getString(R.string.load_fail));
                }
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
                //释放CookListAdapter中的图片缓存信息
                CookListAdapter.releaseImgMsg();
                String cookName=setCookName.getText().toString();
                Message message=childHandle.obtainMessage();
                message.obj=cookName;
                childHandle.sendMessage(message);
                setOnLoading.setVisibility(View.VISIBLE);
                cookList.setAdapter(null);
                setOnLoading.setText(getString(R.string.on_loading));
                SetListViewHeight.setListViewHeightBasedOnTextView(cookList,setOnLoading);
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
                    Message mainMsg=mainHandle.obtainMessage();
                    if(cookMsgs!=null){
                        setMsgItem();
                        isGetCookMessageSuccess=true;
                        Log.d(TAG,"childHandle got the cook message");
                    }else {
                        isGetCookMessageSuccess=false;
                    }
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
            map.put("cookTag",cookMsg.getCookTage());
            map.put("cookImgUrl",cookMsg.getCookImgUrl());
            msgItem.add(map);
        }
        Log.d(TAG,"set Msg Items Success");
    }
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

    @Override
    public void onDestroy() {
        //释放CookListAdapter中的图片缓存信息
        CookListAdapter.releaseImgMsg();
        super.onDestroy();
    }
}
