package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.TestAdapter;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.tencent.util.HttpUtil;
import com.zthx.npj.utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TestActivity extends ActivityBase {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void offline(View v){
        Intent intent=new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
        intent.putExtra("user_id","100");
        intent.putExtra("token","100");
        sendBroadcast(intent);
    }
}
