package com.zthx.npj.ui;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zthx.npj.R;

public class BaseActivity extends AppCompatActivity {

    protected ImmersionBar mImmersionBar;
    protected boolean ISShowToolBar = true;
    public Toolbar toolbar;

    public TextView mTvBaseTitle;
    public TextView mTvBaseRight;
    public ImageView mImgBaseRight;
    public ImageView mImgBaseRight2;
    public ImageView mImgBaseBack;
    public TextView mTvBaseBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化沉浸式状态栏
        //initToolbar();
    }

    //初始化控件，改变中间标题
    public void init_title(String title) {
        changeTitle(title);
    }

    //初始化控件，改变中间和右侧标题
    public void init_title(String title, String name) {
        changeTitle(title, name);
    }

    // 沉浸状态栏
    public void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    //改变中间标题
    public void changeTitle(String title) {
        if (null == mTvBaseTitle) {
            mTvBaseTitle = (TextView) findViewById(R.id.tv_base_title);
        }
        mTvBaseTitle.setText(title);
    }

    //改变中间和右侧标题
    public void changeTitle(String title, String name) {
        changeTitle(title);
        if (mTvBaseRight == null)
            mTvBaseRight = (TextView) findViewById(R.id.tv_base_right);
        if (name == null) {
            assert mTvBaseRight != null;
            mTvBaseRight.setVisibility(View.INVISIBLE);
        } else {
            assert mTvBaseRight != null;
            mTvBaseRight.setVisibility(View.VISIBLE);
            mTvBaseRight.setText(name);
        }
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        setWhiteBar();
    }

    // 沉浸状态栏，设置Toolbar是否可见
    public void transparentStatusBar(boolean isVisible) {
        transparentStatusBar();
        setToolbarVisibility(isVisible);
    }

    //设置Toolbar是否可见
    public void setToolbarVisibility(boolean isVisible) {
        if (toolbar != null)
            toolbar.setVisibility(isVisible ? View.VISIBLE : View.GONE);

    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 白色 顶部的时候 设置
     */
    private void setWhiteBar() {
        if (ISShowToolBar) {
            mImmersionBar.titleBar(toolbar)
                    .navigationBarColor(R.color.black)
                    .statusBarColor(R.color.colorPrimary)
                    .init();
        }
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        super.onDestroy();
    }
}
