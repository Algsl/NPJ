package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsListAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.MyGoodsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreGoodsListActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;

    @BindView(R.id.ac_storeGoodsList_tv_sale)
    TextView acStoreGoodsListTvSale;
    @BindView(R.id.ac_storeGoodsList_tv_down)
    TextView acStoreGoodsListTvDown;
    @BindView(R.id.ac_storeGoodsList_rv)
    RecyclerView acStoreGoodsListRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String type = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods_list);
        ButterKnife.bind(this);

        getStoreGoods();

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "商品列表");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getStoreGoods();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });


    }

    @OnClick({R.id.ac_storeGoodsList_tv_sale, R.id.ac_storeGoodsList_tv_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_storeGoodsList_tv_sale:
                acStoreGoodsListTvSale.setTextColor(getResources().getColor(R.color.white));
                acStoreGoodsListTvSale.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acStoreGoodsListTvDown.setTextColor(getResources().getColor(R.color.text3));
                acStoreGoodsListTvDown.setBackgroundColor(getResources().getColor(R.color.white));
                type = "1";
                getStoreGoods();
                break;
            case R.id.ac_storeGoodsList_tv_down:
                acStoreGoodsListTvDown.setTextColor(getResources().getColor(R.color.white));
                acStoreGoodsListTvDown.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acStoreGoodsListTvSale.setTextColor(getResources().getColor(R.color.text3));
                acStoreGoodsListTvSale.setBackgroundColor(getResources().getColor(R.color.white));
                type = "2";
                getStoreGoods();
                break;
        }
    }


    private void getStoreGoods() {
        SetSubscribe.myGoods(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoods(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreGoods(String result) {
        MyGoodsResponseBean bean = GsonUtils.fromJson(result, MyGoodsResponseBean.class);
        final ArrayList<MyGoodsResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            acStoreGoodsListRv.setVisibility(View.GONE);
        } else {
            acStoreGoodsListRv.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        acStoreGoodsListRv.setLayoutManager(manager);
        StoreGoodsListAdapter adapter = new StoreGoodsListAdapter(this, data, type);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new StoreGoodsListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(StoreGoodsListActivity.this, GoodsDetailActivity.class);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onSaleClick(int position) {
                String goods_id = data.get(position).getId() + "";
                SetSubscribe.outGoods(user_id, token, goods_id, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        if (type.equals("1")) {
                            showToast("商品下架成功");
                        } else {
                            showToast("商品上架成功");
                        }
                        getStoreGoods();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            @Override
            public void onShareClick(int position) {
                Intent intent = new Intent(StoreGoodsListActivity.this, GoodsDetailActivity.class);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(StoreGoodsListActivity.this, StoreGoodsInfoActivity.class);
                intent.putExtra("goods_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onDelete(final int position) {
                CommonDialog dialog=new CommonDialog(StoreGoodsListActivity.this, R.style.dialog, "商品删除后将无法找回，确定要删除吗？", true, new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            String goods_id = data.get(position).getId() + "";
                            SetSubscribe.delGoods(user_id, token, goods_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    getStoreGoods();
                                    showToast("商品删除成功");
                                }

                                @Override
                                public void onFault(String errorMsg) {

                                }
                            }));
                        }
                    }
                });
                dialog.setTitle("商品删除");
                dialog.show();
            }
        });
        acStoreGoodsListRv.setItemAnimator(new DefaultItemAnimator());
        acStoreGoodsListRv.setAdapter(adapter);
    }
}
