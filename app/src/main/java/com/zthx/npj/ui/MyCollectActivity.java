package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CollectionAdapter;
import com.zthx.npj.adapter.CollectionStoreAdapter;
import com.zthx.npj.adapter.CommenGoodsAdatper;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.net.been.CollectionResponseBean;
import com.zthx.npj.net.been.CollectionStoreResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectActivity extends AppCompatActivity {

    @BindView(R.id.at_my_collect_goods_rv)
    RecyclerView atMyCollectGoodsRv;
    @BindView(R.id.at_my_collect_store_rv)
    RecyclerView atMyCollectStoreRv;
    @BindView(R.id.at_my_collect_like_rv)
    RecyclerView atMyCollectLikeRv;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_myCollect_tv_goods)
    TextView acMyCollectTvGoods;
    @BindView(R.id.ac_myStore_tv_stores)
    TextView acMyStoreTvStores;

    private boolean flag = true;
    private String type="1";
    private String user_id= SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);

        acTitle.setText("收藏");
        getCollection();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        atMyCollectLikeRv.setLayoutManager(layoutManager);
        //初始化适配器
        List<CommentGoodsBeen> list3         = new ArrayList<>();
        CommentGoodsBeen       HomeGoodsBeen = new CommentGoodsBeen();
        HomeGoodsBeen.setGoodsPic("123");
        HomeGoodsBeen.setGoodsTitle("1231245124");
        HomeGoodsBeen.setGoodsSellNum("123");
        HomeGoodsBeen.setGoodsPrice("1333");
        for (int i = 0; i < 20; i++) {
            list3.add(HomeGoodsBeen);
        }
        CommenGoodsAdatper mAdapter = new CommenGoodsAdatper(this, list3);
        //设置适配器
        atMyCollectLikeRv.setAdapter(mAdapter);
    }

    private void getCollection() {
        SetSubscribe.collectionList(user_id,token,type,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setResult(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setResult(String result) {
        if(type.equals("1")){
            CollectionResponseBean bean= GsonUtils.fromJson(result,CollectionResponseBean.class);
            final ArrayList<CollectionResponseBean.DataBean> data=bean.getData();
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            atMyCollectGoodsRv.setLayoutManager(layoutManager);
            CollectionAdapter adapter=new CollectionAdapter(this,data);
            atMyCollectGoodsRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new CollectionAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }

                @Override
                public void onItemAddCart(int position) {

                }

                @Override
                public void onItemDelete(int position) {
                    SetSubscribe.delCollection(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
            });
        }else{
            CollectionStoreResponseBean bean=GsonUtils.fromJson(result,CollectionStoreResponseBean.class);
            final ArrayList<CollectionStoreResponseBean.DataBean> data=bean.getData();
            atMyCollectGoodsRv.setVisibility(View.GONE);
            atMyCollectStoreRv.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            atMyCollectStoreRv.setLayoutManager(layoutManager);
            CollectionStoreAdapter adapter=new CollectionStoreAdapter(this,data);
            atMyCollectStoreRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new CollectionStoreAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }

                @Override
                public void onDeleteClick(int position) {
                    SetSubscribe.delCollection(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
            });
        }
    }

    @OnClick({R.id.ac_myCollect_tv_goods, R.id.ac_myStore_tv_stores})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myCollect_tv_goods:
                    acMyCollectTvGoods.setTextColor(getResources().getColor(android.R.color.white));
                    acMyCollectTvGoods.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    acMyStoreTvStores.setTextColor(getResources().getColor(R.color.text3));
                    acMyStoreTvStores.setBackgroundColor(getResources().getColor(android.R.color.white));
                    type="1";
                    getCollection();
                break;
            case R.id.ac_myStore_tv_stores:
                    acMyStoreTvStores.setTextColor(getResources().getColor(android.R.color.white));
                    acMyStoreTvStores.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    acMyCollectTvGoods.setTextColor(getResources().getColor(R.color.text3));
                    acMyCollectTvGoods.setBackgroundColor(getResources().getColor(android.R.color.white));
                    type="2";
                    getCollection();
                break;
        }
    }
}
