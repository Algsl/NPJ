package com.zthx.npj.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.zthx.npj.baidumap.LocationService;
import com.zthx.npj.services.GlobalEventListener;
import com.zthx.npj.ui.SplashActivity;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by huangxin on 2019/5/28.
 */

public class BaseApp extends Application {

    public static Context appContext;
    public static ArrayList<Activity> allActivities = new ArrayList<Activity>();
    public static BaseApp app;
    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        locationService = new LocationService(this);
        JPushInterface.init(this);
        JMessageClient.registerEventReceiver(new GlobalEventListener(getApplicationContext()));
        app = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BDAbstractLocationListener getListener(){
        BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                // TODO Auto-generated method stub
                Log.e("测试", "onReceiveLocation: ");
                if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                    //获取信息后的操作
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    SharePerferenceUtils.setLat(getConText(), latitude + "");
                    SharePerferenceUtils.setLng(getConText(), longitude + "");
                    Log.e("测试", "onReceiveLocation: " + latitude + " " + longitude);
                }
            }
        };
        return mListener;
    }

    public static Context getConText(){
        return appContext;
    }

    public static BaseApp getApp(){
        return app;
    }

    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void delActivity(Activity activity) {
        allActivities.remove(activity);
    }
}
