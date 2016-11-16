package com.example.chenghao.mytb.HttpUtils;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenghao on 2016/11/7.
 * 此类用于解析查询菜谱后返回的xml文件
 */
public class SAXForHandler extends DefaultHandler{

    private final static String TAG="Qin:SAXForHandler";
    private List<CookMsg> cookMsgs;
    private ArrayList<HashMap<String,String>>cookSteps;//记录菜谱所有步骤
    private HashMap<String,String> stepMsg;//记录步骤信息
    private String img;                    //记录步骤中的图片url
    private String step;                   //记录步骤中的操作
    private boolean isStep=false;          //由于item存在于两处，所以用于判断item是否为做菜的步骤
    CookMsg cookMsg;
    private String currentTag;             //记录当前节点
    @Override
    public void startDocument() throws SAXException {
        cookMsgs=new ArrayList<CookMsg>();
        Log.d(TAG,"startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Log.d(TAG,"Element:"+qName);
        currentTag=qName;
        if("item".equals(currentTag)&&(!isStep)){
            cookMsg=new CookMsg();
           // cookMsg.setCookId(Integer.parseInt(attributes.getValue(0)));
        }
        if("steps".equals(currentTag)){
            cookSteps=new ArrayList();//重置菜谱所有步骤
            stepMsg=new HashMap();
            isStep=true;              //开始记录做菜步骤
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data=new String(ch,start,length).trim();
        Log.d(TAG,"characters:"+data);
        switch (currentTag){
            case "title":
                cookMsg.setCookTitle(data);
                break;
            case "tags":
                cookMsg.setCookTage(data);
                break;
            case "imtro":
                cookMsg.setCookImtro(data);
                break;
            case "ingredients":
                cookMsg.setIngredients(data);
                break;
            case "burden":
                cookMsg.setBurden(data);
                break;
            case "albums":
                cookMsg.setCookImgUrl(data);
                break;
            case "item" :
                if (isStep) {
                    stepMsg = new HashMap();
                }
                break;
            case "img":
                img=data;
                break;
            case "step":
                step=data;
                break;
            default:break;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("item".equals(qName)&&isStep){
            Log.d(TAG,"record cook step:"+isStep);
            stepMsg.put(img,step);
            cookSteps.add(stepMsg);     //记录做菜步骤
        }
        if("steps".equals(qName)){
            isStep=false;               //停止记录做菜步骤
            Log.d(TAG,"stop cook step record"+isStep);
            cookMsg.setCookSteps(cookSteps);
        }
        if("item".equals(qName)&&(!isStep)){
            cookMsgs.add(cookMsg);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        Log.d(TAG,"endDocument");
    }

    public List<CookMsg> getCookMsgs(){
        return cookMsgs;
    }
}
