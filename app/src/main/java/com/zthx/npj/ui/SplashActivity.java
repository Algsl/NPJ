package com.zthx.npj.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.NetUtil;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    LinearLayout atSplash;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                MainSubscribe.getRecommend(SharePerferenceUtils.getUserId(SplashActivity.this), "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        SharePerferenceUtils.setMainRecommend(SplashActivity.this, result);
                        mHandler.sendEmptyMessage(2);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            } else if (msg.what == 2) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handPermission();

        BaseConstant.TOKEN = SharePerferenceUtils.getToken(this);



    }

    private void handPermission() {
        //需要动态获取的权限组
        String[] mPermissionGroup=new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        //过滤以获取的权限，得到未获取的权限
        List<String> mRequestPermission=new ArrayList<>();
        for(String permission :mPermissionGroup){
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                mRequestPermission.add(permission);
            }
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !mRequestPermission.isEmpty()){
            //请求授权
            ActivityCompat.requestPermissions(this,mRequestPermission.toArray(new String[mRequestPermission.size()]),100);
        }else{
            if (NetUtil.isNetworkConnected(this)) {
                getMainBannerAndList();
                BaseApp.getApp().locationService.registerListener(mListener);
                BaseApp.getApp().locationService.setLocationOption(BaseApp.getApp().locationService.getDefaultLocationClientOption());
                BaseApp.getApp().locationService.start();
            } else {
                Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT);
                finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (NetUtil.isNetworkConnected(this)) {
                        getMainBannerAndList();
                        BaseApp.getApp().locationService.registerListener(mListener);
                        BaseApp.getApp().locationService.setLocationOption(BaseApp.getApp().locationService.getDefaultLocationClientOption());
                        BaseApp.getApp().locationService.start();
                    } else {
                        Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT);
                        finish();
                    }
                }else{

                }
                break;
        }
    }

    private void getMainBannerAndList() {

        MainSubscribe.getMainBanner(Const.MAIN_BANNER_TYPE, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SharePerferenceUtils.setMainBanner(SplashActivity.this, result);
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                //获取信息后的操作
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                SharePerferenceUtils.setLat(SplashActivity.this, latitude+"");
                SharePerferenceUtils.setLng(SplashActivity.this,longitude+"");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApp.getApp().locationService.unregisterListener(mListener);
        BaseApp.getApp().locationService.stop();
    }
}
