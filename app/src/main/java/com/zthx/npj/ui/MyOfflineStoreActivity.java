package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOfflineStoreActivity extends ActivityBase {


    @BindView(R.id.at_store_detail_name)
    TextView atStoreDetailName;
    @BindView(R.id.at_store_detail_iv_star1)
    ImageView atStoreDetailIvStar1;
    @BindView(R.id.at_store_detail_iv_star2)
    ImageView atStoreDetailIvStar2;
    @BindView(R.id.at_store_detail_iv_star3)
    ImageView atStoreDetailIvStar3;
    @BindView(R.id.at_store_detail_iv_star4)
    ImageView atStoreDetailIvStar4;
    @BindView(R.id.at_store_detail_iv_star5)
    ImageView atStoreDetailIvStar5;
    @BindView(R.id.at_store_detail_ll_star)
    LinearLayout atStoreDetailLlStar;
    @BindView(R.id.at_store_detail_tv_popularity)
    TextView atStoreDetailTvPopularity;
    @BindView(R.id.at_store_detail_tv_open_time)
    TextView atStoreDetailTvOpenTime;
    @BindView(R.id.at_store_detail_tv_consumption)
    TextView atStoreDetailTvConsumption;
    @BindView(R.id.at_store_detail_btn_pay)
    Button atStoreDetailBtnPay;
    @BindView(R.id.at_store_detail_tv_address)
    TextView atStoreDetailTvAddress;
    @BindView(R.id.at_store_detail_tv_address2)
    TextView atStoreDetailTvAddress2;
    @BindView(R.id.at_store_detail_iv_call)
    ImageView atStoreDetailIvCall;
    @BindView(R.id.at_store_detail_tv_offer)
    TextView atStoreDetailTvOffer;
    @BindView(R.id.item_location_store_ll_dikou)
    LinearLayout itemLocationStoreLlDikou;
    @BindView(R.id.at_store_detail_tv_relief)
    TextView atStoreDetailTvRelief;
    @BindView(R.id.item_location_store_ll_dikou2)
    LinearLayout itemLocationStoreLlDikou2;
    @BindView(R.id.at_store_detail_rv)
    RecyclerView atStoreDetailRv;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.banner_discover_service)
    Banner bannerDiscoverService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"线下门店详情");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyOfflineStore();
    }

    private void getMyOfflineStore() {
        SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOfflineStore(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyOfflineStore(String result) {
        MyOfflineStoreResponseBean bean = GsonUtils.fromJson(result, MyOfflineStoreResponseBean.class);
        MyOfflineStoreResponseBean.DataBean data = bean.getData();

    }

    @OnClick(R.id.at_location_store_tv_ruzhu)
    public void onViewClicked() {
        startActivity(new Intent(this, EditMyOfflineStoreActivity.class));
    }
}
