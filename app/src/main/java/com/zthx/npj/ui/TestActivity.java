package com.zthx.npj.ui;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.egret.runtime.launcherInterface.INativePlayer;
import org.egret.egretnativeandroid.EgretNativeAndroid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//Android项目发布设置详见doc目录下的README_ANDROID.md

public class TestActivity extends Activity {
    private final String TAG = "测试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


}
