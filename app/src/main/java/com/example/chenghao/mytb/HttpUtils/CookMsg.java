package com.example.chenghao.mytb.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenghao on 2016/11/8.
 */
public class CookMsg {

    private int cookId;
    private String cookTitle;           //菜名
    private String cookTage;            //标签
    private String cookImtro;           //简介
    private String ingredients;         //原料
    private String burden;              //调料
    private String cookImgUrl;          //图片

    private ArrayList<HashMap<String,String>> cookSteps;

    public int getCookID(){
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId=cookId;
    }

    public String getCookTitle(){
        return cookTitle;
    }

    public void setCookTitle(String cookTitle){
        this.cookTitle=cookTitle;
    }

    public String getCookTage(){
        return cookTage;
    }

    public void setCookTage(String cookTage){
        this.cookTage=cookTage;
    }
    public String getCookImtro(){
        return cookImtro;
    }

    public void setCookImtro(String cookImtro){
        this.cookImtro=cookImtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getCookImgUrl() {
        return cookImgUrl;
    }

    public void setCookImgUrl(String cookImgUrl) {
        this.cookImgUrl = cookImgUrl;
    }

    public ArrayList<HashMap<String, String>> getCookSteps() {
        return cookSteps;
    }

    public void setCookSteps(ArrayList<HashMap<String, String>> cookSteps) {
        this.cookSteps = cookSteps;
    }
}
