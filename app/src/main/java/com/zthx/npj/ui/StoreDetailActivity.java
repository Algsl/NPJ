package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.StoreDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreDetailActivity extends AppCompatActivity {

    @BindView(R.id.at_store_detail_rv)
    RecyclerView atStoreDetailRv;
    @BindView(R.id.at_store_detail_btn_pay)
    Button atStoreDetailBtnPay;
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
    @BindView(R.id.at_store_detail_tv_address)
    TextView atStoreDetailTvAddress;
    @BindView(R.id.at_store_detail_tv_address2)
    TextView atStoreDetailTvAddress2;
    @BindView(R.id.at_store_detail_iv_call)
    ImageView atStoreDetailIvCall;
    @BindView(R.id.item_location_store_ll_dikou)
    LinearLayout itemLocationStoreLlDikou;
    @BindView(R.id.item_location_store_ll_dikou2)
    LinearLayout itemLocationStoreLlDikou2;
    @BindView(R.id.at_store_detail_tv_offer)
    TextView atStoreDetailTvOffer;
    @BindView(R.id.at_store_detail_tv_relief)
    TextView atStoreDetailTvRelief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);
        getStoreDetail(getIntent().getStringExtra(Const.STORE_ID));
        getStoreComment(getIntent().getStringExtra(Const.STORE_ID));
    }

    private void getStoreComment(String id) {

        MainSubscribe.getStoreComment(id,"5",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setComment(String result) {

        CommentResponseBean commentResponseBean = GsonUtils.fromJson(result, CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data = commentResponseBean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atStoreDetailRv.setLayoutManager(manager);
        CommentAdapter mAdapter = new CommentAdapter(this, data);
        atStoreDetailRv.setAdapter(mAdapter);
    }

    private void getStoreDetail(String id) {

        MainSubscribe.getStoreDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setData(result);

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick(R.id.at_store_detail_btn_pay)
    public void onViewClicked() {

    }

    public void setData(String result) {

        StoreDetailResponseBean storeDetailResponseBean = GsonUtils.fromJson(result, StoreDetailResponseBean.class);
        StoreDetailResponseBean.DataBean data = storeDetailResponseBean.getData();
        atStoreDetailName.setText(data.getStore_name());
        switch (data.getPopularity()) {
            case 1:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 2:
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 3:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 4:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar4.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 5:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar4.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar5.setBackgroundResource(R.drawable.item_location_store_star);
        }
        atStoreDetailTvPopularity.setText(data.getPopularity() + "分");
        atStoreDetailTvOpenTime.setText(data.getBusiness_hours() + "营业");
        atStoreDetailTvConsumption.setText("人均消费¥ " + data.getConsumption());
        atStoreDetailTvOffer.setText("葫芦币折扣"+ data.getOffer() +"%现金");
        atStoreDetailTvRelief.setText("新会员在该商家成为代言人首单结算减免"+ data.getRelief() +"元现金");
        atStoreDetailTvAddress.setText(data.getAddress2());
        atStoreDetailTvAddress2.setText(data.getAddress());
    }
}
