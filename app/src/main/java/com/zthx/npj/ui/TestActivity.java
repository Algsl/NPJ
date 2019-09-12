package com.zthx.npj.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zthx.npj.R;
import com.zthx.npj.view.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends ActivityBase {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;

    private CommonDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                dialog=new CommonDialog(TestActivity.this, R.style.dialog, "测试1", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                });
                dialog.setPositiveButton("确定");
                dialog.show();
                break;
            case R.id.btn2:
                dialog=new CommonDialog(TestActivity.this, R.style.dialog, "测试2", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                });
                dialog.setPositiveButton("确定");
                dialog.setTitle("测试2");
                dialog.show();
                break;
            case R.id.btn3:
                dialog=new CommonDialog(TestActivity.this, R.style.dialog, "测试3", false,new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                });
                dialog.setPositiveButton("确定");
                dialog.setTitle("测试2");
                dialog.show();
                break;
            case R.id.btn4:
                dialog=new CommonDialog(TestActivity.this, R.style.dialog, "测试4", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                });
                dialog.setPositiveButton("确定");
                dialog.setTitle("测试2");
                dialog.setNegativeButton("删除");
                dialog.show();
                break;
            case R.id.btn5:
                new CommonDialog(TestActivity.this, R.style.dialog, "测试2", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                }).show();
                break;
        }
    }
}