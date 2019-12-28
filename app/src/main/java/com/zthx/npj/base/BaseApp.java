package com.zthx.npj.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.zthx.npj.baidumap.LocationService;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by huangxin on 2019/5/28.
 */

public class BaseApp extends Application {

    public static Context appContext;
    public static ArrayList<Activity> allActivities = new ArrayList<Activity>();
    public static BaseApp app;
    public LocationService locationService;

    public static final int SDKAPPID=1400269107;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        locationService = new LocationService(this);
        JPushInterface.init(this);
        //JMessageClient.registerEventReceiver(new GlobalEventListener(getApplicationContext()));
        app = this;

        TUIKitConfigs configs=TUIKitConfigs.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        TUIKit.init(this,SDKAPPID,configs);

        SDKInitializer.initialize(this);
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
                if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                    //获取信息后的操作
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    SharePerferenceUtils.setLat(getConText(), latitude + "");
                    SharePerferenceUtils.setLng(getConText(), longitude + "");
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


    private List<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }

}
