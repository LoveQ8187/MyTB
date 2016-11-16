package com.example.chenghao.mytb.HttpUtils;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by chenghao on 2016/11/8.
 */
public class GetCookMsgs {
    private final static String TAG="Qin:GetCookMsgs";

    public static List<CookMsg> excuteForXML(String menu){
        String url=UnKManager.COOK_URL+"?menu="+menu+"&dtype=xml&pn=&rn=&albums=&=&key="+UnKManager.COOK_KEY;
        Log.d(TAG,url);
        InputStream getIn=PureNet.post(url,null,true);
        try {
            SAXForHandler saxForHandler = new SAXForHandler();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(getIn,saxForHandler);
            List<CookMsg>cookMsgs=saxForHandler.getCookMsgs();
            Log.d(TAG,"SAX finished");
            return cookMsgs;
        }catch (SAXException e){
            e.printStackTrace();
            Log.d(TAG,"SAXException");
        }catch (ParserConfigurationException p){
            p.printStackTrace();
            Log.d(TAG,"ParserConfigurationException");
        }catch (IOException i){
            i.printStackTrace();
            Log.d(TAG,"IOException");
        }finally {
            try {
                getIn.close();
                Log.d(TAG,"GetCookMsgs:InputStream has been closed");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<CookMsg> excuteForJson(String menu){
        String url=UnKManager.COOK_URL+"?menu="+menu+"&dtype=json&pn=&rn=&albums=&=&key="+UnKManager.COOK_KEY;
        String result=  PureNet.get(url);
        Log.d(TAG,"get cook json date success");
        return JsonForCookMsg.getCookMsgList(result);
    }
}
