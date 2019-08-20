package com.zthx.npj.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zthx.npj.R;

public class LoadingActivity extends ActivityBase{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                //等待10000毫秒后销毁此页面，并提示登陆成功
                LoadingActivity.this.finish();
                Toast.makeText(getApplicationContext(), "加载完成", Toast.LENGTH_SHORT).show();
            }
        }, 10000);

    }
}
