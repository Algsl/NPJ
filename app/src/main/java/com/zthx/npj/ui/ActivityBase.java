package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //去除半透明状态栏
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); //全屏显示
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        //getWindow().setStatusBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = this.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    //标题栏的返回按钮实现
    public void back(ImageView img){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //无参数的Activity跳转
    public void openActivity(Class mClass){
        Intent intent=new Intent(this,mClass);
        startActivity(intent);
    }

    //含一个或多个参数的Activity跳转
    public void openActivity(Class mClass,String ...value){
        Intent intent=new Intent(this,mClass);
        for(int i=0;i<value.length;i++){
            intent.putExtra("key"+i,value[i]);
        }
        startActivity(intent);
    }

    //改变标题栏的标题
    public void changeTitle(TextView tv,String str){
        tv.setText(str);
    }

    //标题栏右侧设置图片并包含点击事件
    public void changeRightImg(ImageView img, int resourceId, final Class mClass, final String id){
        img.setImageResource(resourceId);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(mClass,id);
            }
        });
    }

    //标题栏右侧设置文字并包含点击事件
    public void changeRightText(TextView tv, String str, final Class mClass, final String id){
        tv.setText(str);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(mClass,id);
            }
        });
    }

    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
