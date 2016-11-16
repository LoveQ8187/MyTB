package com.example.chenghao.mytb.HttpUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chenghao on 2016/11/15.
 */
public class ImageLoadAsyncTask extends AsyncTask<String,Void,Bitmap> {

    private final static String TAG="Qin:ImageLoadAsyncTask";
    ImageView imgeView;
    HttpURLConnection conn=null;
    public ImageLoadAsyncTask(ImageView imageView) {
        this.imgeView=imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String Url=strings[0];
        Log.d(TAG,"try to get Bitmap by:"+Url);
        try {
            URL url = new URL(Url);
            try {
                conn=(HttpURLConnection)url.openConnection();
                conn.connect();
                InputStream in=conn.getInputStream();
                Log.d(TAG,"get data success");
                Bitmap bitmap= BitmapFactory.decodeStream(in);
                if(bitmap!=null)
                    return bitmap;
            }catch (IOException i){
                Log.e(TAG,"IOException");
                i.printStackTrace();
            }finally {
                if(conn!=null)conn.disconnect();
            }
        }catch(MalformedURLException m){
            Log.e(TAG,"MalformedURLException");
            m.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null)
        {
            imgeView.setImageBitmap(bitmap);
            Log.d(TAG,"set bitmap");
        }else {
            Log.d(TAG,"bitmap is null");
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
