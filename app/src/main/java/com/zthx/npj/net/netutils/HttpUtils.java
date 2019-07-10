package com.zthx.npj.net.netutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtils {
    static String result = "";
    static String userInfo="";
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static String getAccessToken(String code){

        sendOkHttpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx76500efa65d19915&secret=a42f02f8f260d62a636e23b1fa585c7a&code=" + code + "&grant_type=authorization_code", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText=response.body().string();
                try {
                    JSONObject obj=new JSONObject(responseText);
                    String access_token=obj.getString("access_token");
                    String openid=obj.getString("openid");
                    userInfo=getUserInfo(access_token,openid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return userInfo;
    }

    public static String getUserInfo(String access_token,String openid){
        sendOkHttpRequest("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result =response.body().string();
            }
        });
        return result;
    }
}
