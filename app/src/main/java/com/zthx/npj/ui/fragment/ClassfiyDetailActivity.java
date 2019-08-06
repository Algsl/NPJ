package com.zthx.npj.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ClassifyDetailAdapter;
import com.zthx.npj.net.been.GoodsListResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ActivityBase;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassfiyDetailActivity extends ActivityBase {

    @BindView(R.id.at_classfiy_detail_rv)
    RecyclerView atClassfiyDetailRv;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy_detail);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,getIntent().getStringExtra("key1"));

        getGoodsList();

        //通过findViewById拿到RecyclerView实例
        //设置RecyclerView管理器



        //设置添加或删除item时的动画，这里使用默认动画

        //设置适配器

    }

    private void getGoodsList() {
        MainSubscribe.goodsList(getIntent().getStringExtra("key0"),"0",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setGoodsList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setGoodsList(String result) {
        GoodsListResponseBean bean= GsonUtils.fromJson(result,GoodsListResponseBean.class);
        final ArrayList<GoodsListResponseBean.DataBean> data=bean.getData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        atClassfiyDetailRv.setLayoutManager(layoutManager);
        ClassifyDetailAdapter adapter=new ClassifyDetailAdapter(this,data);
        atClassfiyDetailRv.setItemAnimator(new DefaultItemAnimator());
        atClassfiyDetailRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ClassifyDetailAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(ClassfiyDetailActivity.this,GoodsDetailActivity.class);
                intent.putExtra("goods_id",data.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }
}
