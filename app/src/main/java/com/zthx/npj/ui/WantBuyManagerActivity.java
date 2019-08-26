package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.BaojiaListAdapter;
import com.zthx.npj.adapter.MySupplyListAdapter;
import com.zthx.npj.adapter.PurchaseListAdapter;
import com.zthx.npj.net.been.BaojiaListResponseBean;
import com.zthx.npj.net.been.PurchaseListResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WantBuyManagerActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.at_want_buy_manager_rv_supply_list1)
    RecyclerView atWantBuyManagerRvSupplyList1;
    @BindView(R.id.at_swant_buy_manager_rv_supply_list2)
    RecyclerView atSwantBuyManagerRvSupplyList2;
    @BindView(R.id.at_want_buy_manager_ll_supply_list)
    LinearLayout atWantBuyManagerLlSupplyList;
    @BindView(R.id.at_want_buy_manager_rv_supply_bill)
    RecyclerView atWantBuyManagerRvSupplyBill;
    @BindView(R.id.at_want_buy_manager_ll_supply_bill)
    LinearLayout atWantBuyManagerLlSupplyBill;
    @BindView(R.id.ac_wantBuy_tv_wantBuy)
    TextView acWantBuyTvWantBuy;
    @BindView(R.id.ac_wantBuy_tv_baojia)
    TextView acWantBuyTvBaojia;
    @BindView(R.id.ac_wantBuy_tv_issue)
    TextView acWantBuyTvIssue;
    @BindView(R.id.ac_wantBuy_tv_unIssue)
    TextView acWantBuyTvUnIssue;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String type = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_buy_manager);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"供应管理");
        getWantBuy();
    }

    private void getWantBuy() {
        atWantBuyManagerLlSupplyList.setVisibility(View.VISIBLE);
        atWantBuyManagerLlSupplyBill.setVisibility(View.GONE);
        SetSubscribe.purchaseList(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyWantBuy(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setMyWantBuy(String result) {
        PurchaseListResponseBean bean = GsonUtils.fromJson(result, PurchaseListResponseBean.class);
        final ArrayList<PurchaseListResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atWantBuyManagerRvSupplyList1.setLayoutManager(layoutManager);
        PurchaseListAdapter adapter = new PurchaseListAdapter(this,data,type);
        atWantBuyManagerRvSupplyList1.setItemAnimator(new DefaultItemAnimator());
        atWantBuyManagerRvSupplyList1.setAdapter(adapter);
        adapter.setOnItemClickListener(new PurchaseListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSaleClick(int position) {
                if(type.equals("1")){
                    SetSubscribe.purchaseDown(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getWantBuy();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            showToast(errorMsg);
                        }
                    }));
                }else{
                    SetSubscribe.purchaseUp(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getWantBuy();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            showToast(errorMsg);
                        }
                    }));
                }
            }

            @Override
            public void onSupplyEditClick(int position) {

            }

            @Override
            public void onSupplyDeleteClick(int position) {
                SetSubscribe.purchaseDel(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getWantBuy();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
            }

            @Override
            public void onSupplyShareClick(int position) {

            }
        });
    }

    @OnClick({R.id.ac_wantBuy_tv_wantBuy, R.id.ac_wantBuy_tv_baojia, R.id.ac_wantBuy_tv_issue, R.id.ac_wantBuy_tv_unIssue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //求购列表
            case R.id.ac_wantBuy_tv_wantBuy:
                acWantBuyTvWantBuy.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvWantBuy.setTextColor(getResources().getColor(android.R.color.white));
                acWantBuyTvBaojia.setBackgroundColor(getResources().getColor(android.R.color.white));
                acWantBuyTvBaojia.setTextColor(getResources().getColor(R.color.text3));
                getWantBuy();
                break;
                //报价列表
            case R.id.ac_wantBuy_tv_baojia:
                acWantBuyTvBaojia.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvBaojia.setTextColor(getResources().getColor(android.R.color.white));
                acWantBuyTvWantBuy.setBackgroundColor(getResources().getColor(android.R.color.white));
                acWantBuyTvWantBuy.setTextColor(getResources().getColor(R.color.text3));
                getBaojia();
                break;
            case R.id.ac_wantBuy_tv_issue:
                acWantBuyTvIssue.setTextColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvUnIssue.setTextColor(getResources().getColor(R.color.text3));
                type = "1";
                getWantBuy();
                break;
            case R.id.ac_wantBuy_tv_unIssue:
                acWantBuyTvIssue.setTextColor(getResources().getColor(R.color.text3));
                acWantBuyTvUnIssue.setTextColor(getResources().getColor(R.color.app_theme));
                type = "2";
                getWantBuy();
                break;
        }
    }

    private void getBaojia() {
        atWantBuyManagerLlSupplyList.setVisibility(View.GONE);
        atWantBuyManagerLlSupplyBill.setVisibility(View.VISIBLE);
        SetSubscribe.baojiaList(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setBaojia(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setBaojia(String result) {
        BaojiaListResponseBean bean=GsonUtils.fromJson(result,BaojiaListResponseBean.class);
        final ArrayList<BaojiaListResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        atWantBuyManagerRvSupplyBill.setLayoutManager(layoutManager);
        BaojiaListAdapter adapter=new BaojiaListAdapter(this,data);
        atWantBuyManagerRvSupplyBill.setItemAnimator(new DefaultItemAnimator());
        atWantBuyManagerRvSupplyBill.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaojiaListAdapter.ItemClickListener() {
            @Override
            public void onSeeClick(int position) {
                Intent intent=new Intent(WantBuyManagerActivity.this,BaojiaUserListActivity.class);
                intent.putExtra("baojia_id",data.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }
}
