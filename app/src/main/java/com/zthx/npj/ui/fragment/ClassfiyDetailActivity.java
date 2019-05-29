package com.zthx.npj.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ClassfiyDetailGoodsAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.ui.GoodsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassfiyDetailActivity extends AppCompatActivity {

    @BindView(R.id.at_classfiy_detail_rv)
    RecyclerView atClassfiyDetailRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy_detail);
        ButterKnife.bind(this);
        //通过findViewById拿到RecyclerView实例
        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL,false);
        atClassfiyDetailRv.setLayoutManager(layoutManager);
        //初始化适配器
        List<CommentGoodsBeen> list3 = new ArrayList<>();
        CommentGoodsBeen homeGoodsBeen = new CommentGoodsBeen();
        homeGoodsBeen.setGoodsSellNum("1222");
        homeGoodsBeen.setGoodsPic("sdsdsd");
        homeGoodsBeen.setGoodsTitle("水果送达大厦");
        homeGoodsBeen.setGoodsPrice("120");
        homeGoodsBeen.setGoodsOldPrice("200");
        for (int i = 0; i < 20; i++) {
            list3.add(homeGoodsBeen);
        }
        ClassfiyDetailGoodsAdapter mAdapter = new ClassfiyDetailGoodsAdapter(this, list3);
        mAdapter.setOnItemClickListener(new ClassfiyDetailGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(this, "position==" + position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ClassfiyDetailActivity.this, GoodsDetailActivity.class));
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        atClassfiyDetailRv.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        atClassfiyDetailRv.setAdapter(mAdapter);
    }
}
