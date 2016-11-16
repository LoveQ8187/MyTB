package com.example.chenghao.mytb.HttpUtils;

/**
 * Created by chenghao on 2016/11/8.
 * 管理所有公共api接口以及所需的key
 */
public class UnKManager {

    final static String WEATHER_URL="http://v.juhe.cn/weather/index";               //聚合数据天气查询URL
    final static String WEATHER_KEY="ee84dcdce7b92d5de6501282e754ec0a";             //聚合数据查询天气所需的key

    final static String AIR_URL="http://web.juhe.cn:8080/environment/air/cityair";  //聚合数据空气质量查询URL
    final static String AIR_KEY="df6f48bda79a170b895adfed1d9940ae";                 //聚合数据查询空气质量所需的key

    final static String COOK_URL="http://apis.juhe.cn/cook/query.php";              //聚合数据菜谱查询URL
    final static String COOK_KEY="96aa16b7cc388ff36c8a5c2dcdc13667";                //聚合数据查询菜谱所需的key
}
