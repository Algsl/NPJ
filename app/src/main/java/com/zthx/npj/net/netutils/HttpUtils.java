package com.zthx.npj.net.netutils;

import android.util.Log;
import android.widget.Toast;

import com.zthx.npj.ui.SettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    public static void uploadImg(String address,String path,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        File file = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file));
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

    public static void uploadVideo(String address,List<String> paths,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        List<File> files=new ArrayList<>();
        for(String path:paths){
            File file=new File(path);
            files.add(file);
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for(File file:files){
            builder.addFormDataPart("images[]",file.getName(),RequestBody.create(MediaType.parse("application/octet-stream"),file));
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

    public static void uploadMoreImg(String address,List<String> paths,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        List<File> files=new ArrayList<>();
        for(String path:paths){
            File file=new File(path);
            files.add(file);
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for(File file:files){
            builder.addFormDataPart("images[]",file.getName(),RequestBody.create(MediaType.parse("image/png"),file));
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
}
