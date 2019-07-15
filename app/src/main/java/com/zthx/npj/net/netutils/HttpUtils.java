package com.zthx.npj.net.netutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpUtils {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Request.Builder builder = new Request.Builder();

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpRequest(String address,File file,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

}
