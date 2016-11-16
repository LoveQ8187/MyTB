package com.example.chenghao.mytb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenghao.mytb.R;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment implements View.OnTouchListener{
    private static String URL="https://www.baidu.com";//默认Url
    private static String LOCAL_URL="file:///android_asset/example.html";
    private  WebView myWebView=null;
    //监听界面手势
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private static String rememberURL=null;
    private static String rememberTitle=null;
    private Context mContext;

    public WebFragment() {
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
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_web, container, false);

        final EditText showTitle=(EditText)view.findViewById(R.id.show_title);
        final Button backButton=(Button)view.findViewById(R.id.back_button);
        backButton.getBackground().setAlpha(0);
        myWebView=(WebView)view.findViewById(R.id.my_web_view);

        //监听在输入栏点击回车,跳转到指定页面
        showTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEND
                        || i == EditorInfo.IME_ACTION_DONE
                        || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction()))
                {
                    URL=showTitle.getText().toString();
                    myWebView.loadUrl(URL);
                }
                return false;
            }
        });
        //当地址栏坐标丢失，还原title
        showTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    showTitle.setText(rememberTitle);
                }else {
                    showTitle.setText(myWebView.getUrl());
                }
            }
        });

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(URL);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                showTitle.setText(title);//设置标题
                rememberTitle=title;
            }
        });

        //设置webview支持js
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //点击返回，返回上一页面
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rememberURL=myWebView.getOriginalUrl();
                myWebView.loadUrl(rememberURL);
            }
        });

        myWebView.loadUrl(URL);
        return view;

    }



}
