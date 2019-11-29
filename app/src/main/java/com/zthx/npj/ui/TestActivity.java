package com.zthx.npj.ui;

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


    @BindView(R.id.less)
    TextView less;
    @BindView(R.id.many)
    TextView many;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<LocalStoreResponseBean.DataBean> lists;
    private TestAdapter adapter;

    private String result = "";
    private int PAGE_COUNT=10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        getTest();


        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
                updateRecycler(adapter.getItemCount(),adapter.getItemCount()+PAGE_COUNT);
            }
        });

        result = "{\"code\":1,\"data\":[" +
                "{\"id\":9,\"store_name\":\"测试\"}," +
                "{\"id\":7,\"store_name\":\"心灵小花店\"}" +
                "],\"msg\":\"加载成功\"}";

        getLocalStore("1");
    }

    private void getTest() {
        HttpUtil.sendHttpRequest("http://106.13.228.156/index.php/admin/index/index.html", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("测试", "onResponse: "+response.body().string() );
            }
        });
    }

    private void getLocalStore(String type) {

    }

    private void setLocalStore(String result) {
        LocalStoreResponseBean bean=GsonUtils.fromJson(result,LocalStoreResponseBean.class);
        lists=bean.getData();

        if(lists.size()<PAGE_COUNT){
            adapter=new TestAdapter(this,lists);
        }else{
            adapter=new TestAdapter(this,getData(0,PAGE_COUNT));
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    public ArrayList<LocalStoreResponseBean.DataBean> getData(int start, int end) {
        ArrayList<LocalStoreResponseBean.DataBean> reList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (i < lists.size()) {
                reList.add(lists.get(i));
            }
        }
        return reList;
    }


    public void updateRecycler(int start, int end) {
        ArrayList<LocalStoreResponseBean.DataBean> newData = getData(start, end);
        if (newData != null) {
            adapter.updateList(newData);
        } else {
            adapter.updateList(null);
        }
    }

    @OnClick({R.id.less, R.id.many})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.less:
                result="{\"code\":1,\"data\":[" +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}" +
                        "],\"msg\":\"加载成功\"}";
                setLocalStore(result);
                break;
            case R.id.many:
                result="{\"code\":1,\"data\":[" +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}," +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}," +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}," +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}," +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}," +
                        "{\"id\":9,\"store_name\":\"测试\"}," +
                        "{\"id\":7,\"store_name\":\"心灵小花店\"}" +
                        "],\"msg\":\"加载成功\"}";
                setLocalStore(result);
                break;
        }
    }
}
