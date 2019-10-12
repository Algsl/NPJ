package com.zthx.npj.tencent.util;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    public static void sendHttpRequest(String address,String json, Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendHttpRequest(String address, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static String StrToJson(String userId,String name,String portraitUri){
        return "{'app_key':25wehl3u2gcdw,'app_secret':nKSBj7tSmW7b6,'userId':"+userId+",'name':"+name+",'portraitUri':"+portraitUri+"}";
    }

    public static String getAddress(String userId,String name,String portraitUri){
        return "http://api-cn.ronghub.com/user/getToken?userId="+userId+"&name="+name+"&portraitUri="+portraitUri;
    }
}
