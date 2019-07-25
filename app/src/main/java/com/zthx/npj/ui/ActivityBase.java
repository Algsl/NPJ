package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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
}
