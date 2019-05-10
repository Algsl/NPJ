package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommenGoodsAdatper;
import com.zthx.npj.adapter.MyCollectGoodsAdapter;
import com.zthx.npj.adapter.MyCollectStoreAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectActivity extends AppCompatActivity {

    @BindView(R.id.at_my_collect_goods_rv)
    RecyclerView atMyCollectGoodsRv;
    @BindView(R.id.at_my_collect_store_rv)
    RecyclerView atMyCollectStoreRv;
    @BindView(R.id.at_my_collect_like_rv)
    RecyclerView atMyCollectLikeRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager manager2 = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
        atMyCollectGoodsRv.setLayoutManager(manager);
        atMyCollectStoreRv.setLayoutManager(manager);
        atMyCollectLikeRv.setLayoutManager(manager2);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        MyCollectGoodsAdapter mAdapter1 = new MyCollectGoodsAdapter(this,list);
        MyCollectStoreAdapter mAdapter2 = new MyCollectStoreAdapter(this,list);
        CommenGoodsAdatper mAdapter3 = new CommenGoodsAdatper(this,list);
        atMyCollectGoodsRv.setAdapter(mAdapter1);
        atMyCollectStoreRv.setAdapter(mAdapter2);
        atMyCollectGoodsRv.setAdapter(mAdapter3);
    }
}
