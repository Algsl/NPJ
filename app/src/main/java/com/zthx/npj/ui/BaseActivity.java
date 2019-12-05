package com.zthx.npj.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    private static ArrayList<Activity> allActivities=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allActivities.add(this);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        allActivities.remove(this);
    }

    public static void finishAll(){
        for(Activity activity:allActivities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            String user_id=getIntent().getStringExtra("user_id");
            String token=getIntent().getStringExtra("token");
            //id相同，token不同：用户二次登录
            //id不同，token不同：非同一用户登录
            //id同tokentong：
            /*if(user_id.equals("35") && token.equals("123456")){
                Log.e("测试", "onReceive: 判断结果为当前用户" );
            }else if(user_id.equals("35") && !token.equals("123456")){
                Log.e("测试", "onReceive: 判断结果为同一用户进行登录，需强制下线" );
            }else if(!user_id.equals("35") && !token.equals("123456")){
                Log.e("测试", "onReceive: 判断结果为非同一用户，不需要强制下线");
            }*/
            /*AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("您已被强制下线，请重新登录");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    BaseActivity.finishAll();
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();*/
        }
    }
}
