package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.LocationStoreAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationStoreActivity extends AppCompatActivity {

    @BindView(R.id.at_location_store_rv)
    RecyclerView atLocationStoreRv;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_store);
        ButterKnife.bind(this);

        getLocalStore();

    }

    private void getLocalStore() {
        LocalStoreBean bean = new LocalStoreBean();
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        bean.setPage("1");
        bean.setType("1");
        MainSubscribe.getLocalStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LocalStoreResponseBean localStoreResponseBean = GsonUtils.fromJson(result, LocalStoreResponseBean.class);
                final ArrayList<LocalStoreResponseBean.DataBean> data = localStoreResponseBean.getData();

                LinearLayoutManager manager = new LinearLayoutManager(LocationStoreActivity.this, LinearLayoutManager.VERTICAL, false);
                atLocationStoreRv.setLayoutManager(manager);
                LocationStoreAdapter mAdapter = new LocationStoreAdapter(LocationStoreActivity.this, data);
                mAdapter.setOnItemClickListener(new LocationStoreAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(LocationStoreActivity.this, StoreDetailActivity.class);
                        intent.putExtra(Const.STORE_ID,data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                atLocationStoreRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },LocationStoreActivity.this));
    }

    @OnClick(R.id.at_location_store_tv_ruzhu)
    public void onViewClicked() {
        startActivity(new Intent(LocationStoreActivity.this, StoreManagerActivity.class));
    }
}
