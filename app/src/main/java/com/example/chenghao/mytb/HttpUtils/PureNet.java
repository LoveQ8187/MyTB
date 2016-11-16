package com.example.chenghao.mytb.HttpUtils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenghao on 2016/10/28.
 */
public class PureNet {
    private final static String TAG="Qin:PureNet";
    private static boolean isXml=false;
    public static String get(String url){
        return post(url,null);
    }

    //处理inputStream，返回String
    public static String post(String url,Map param){
        Log.d(TAG,"start post");
        HttpURLConnection conn=null;
        try{
            URL u=new URL(url);
            conn=(HttpURLConnection)u.openConnection();
            Log.d(TAG,"open connection");
            StringBuffer sb=null;
            if(param!=null){
                sb=new StringBuffer();
                //默认为false,post方法需要写入参数,设定true
                conn.setDoOutput(true);
                //设定post方法,默认get
                conn.setRequestMethod("POST");
                //对输出流封装成高级输出流
                //获得输出流
                OutputStream out=conn.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                //将参数封装成键值对的形式
                Set<String>keySet=param.keySet();
                Iterator<String>it=keySet.iterator();
                while (it.hasNext()){
                    String i=it.next();
                    sb.append(i).append("=").append(param.get(i)).append("&");
                }
                //将参数通过输出流写入
                writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());
                writer.close();//一定要关闭,不然可能出现参数不全的错误
                sb=null;
            }
            conn.connect();
            Log.d(TAG,"connect");
            sb=new StringBuffer();
            int recode=conn.getResponseCode();
            if(recode==200){
                Log.d(TAG,"get data");
                InputStream in=conn.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                sb=new StringBuffer();
                String line=null;
                while ((line=bufferedReader.readLine())!=null){
                    sb.append(line).append(System.getProperty("line.separator"));
                }
                bufferedReader.close();
                if(sb.length()==0)return null;
                return sb.toString().substring(0,sb.toString().length()-System.getProperty("line.separator").length());
            }
        }catch (IOException e){
            Log.d(TAG,"get data fail");
            return null;
        }finally {
            if(conn!=null)conn.disconnect();
        }/*
        InputStream getIn=post(url,param,true);
        Log.d(TAG,"get InputStream");
        StringBuffer sb;
        if(getIn!=null){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getIn));
                sb = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append(System.getProperty("line.separator"));
                }
                bufferedReader.close();
                if (sb.length() == 0) return null;
                Log.d(TAG,"return data");
                return sb.toString().substring(0, sb.toString().length() - System.getProperty("line.separator").length());
            }catch (IOException e){
                Log.d(TAG,"read data fail");
            }
        }*/
        return null;
    }

    //建立网络连接，得到返回数据
    public static InputStream post(String url,Map param,boolean getIsXml){
        isXml=getIsXml;
        InputStream in=null;
        HttpURLConnection conn=null;
        try{
            URL u=new URL(url);
            conn=(HttpURLConnection)u.openConnection();
            Log.d(TAG,"open connection");
            StringBuffer sb;
            if(param!=null){
                sb=new StringBuffer();
                //默认为false,post方法需要写入参数,设定true
                conn.setDoOutput(true);
                //设定post方法,默认get
                conn.setRequestMethod("POST");
                //对输出流封装成高级输出流
                //获得输出流
                OutputStream out=conn.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                //将参数封装成键值对的形式
                Set<String>keySet=param.keySet();
                Iterator<String>it=keySet.iterator();
                while (it.hasNext()){
                    String i=it.next();
                    sb.append(i).append("=").append(param.get(i)).append("&");
                }
                //将参数通过输出流写入
                writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());
                writer.close();//一定要关闭,不然可能出现参数不全的错误
                sb=null;//释放内存
            }
            conn.connect();
            Log.d(TAG,"connect");
            int recode=conn.getResponseCode();
            if(recode==200){
                Log.d(TAG,"get data success");
                in=conn.getInputStream();
                return in;
            }
        }catch (IOException e){
            e.printStackTrace();
            Log.d(TAG,"get data fail");
            return null;
        }finally {
            if(conn!=null)conn.disconnect();
        }
        return null;
    }


}
