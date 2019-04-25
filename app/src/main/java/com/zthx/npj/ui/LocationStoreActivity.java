package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.LocationStoreAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

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

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atLocationStoreRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<CommentGoodsBeen>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        LocationStoreAdapter mAdapter = new LocationStoreAdapter(this, list);
        mAdapter.setOnItemClickListener(new LocationStoreAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(LocationStoreActivity.this, StoreDetailActivity.class));
            }
        });
        atLocationStoreRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.at_location_store_tv_ruzhu)
    public void onViewClicked() {
        startActivity(new Intent(LocationStoreActivity.this, StoreManagerActivity.class));
    }
}
