package com.zthx.npj.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zthx.npj.baidumap.LocationService;

import java.util.ArrayList;

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
        app = this;
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
