package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ShopLogAdapter;
import com.zthx.npj.net.been.ShopLogResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopLogActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_shopLog_tv_chooseTime)
    TextView acShopLogTvChooseTime;
    @BindView(R.id.ac_shopLog_tv_ioMoney)
    TextView acShopLogTvIoMoney;
    @BindView(R.id.ac_shopLog_rv_mingxi)
    RecyclerView acShopLogRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String begin_time = "2019-7-1";
    String end_time = "2019-7-31";
    @BindView(R.id.title_back)
    ImageView titleBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_log);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"店铺收益");
        getShopLog();
    }

    private void getShopLog() {
        SetSubscribe.shopLog(user_id, token, begin_time, end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setShopLog(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setShopLog(String result) {
        ShopLogResponseBean bean = GsonUtils.fromJson(result, ShopLogResponseBean.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acShopLogRvMingxi.setLayoutManager(layoutManager);
        ShopLogAdapter adapter = new ShopLogAdapter(this, bean.getData());
        acShopLogRvMingxi.setAdapter(adapter);
    }

    @OnClick(R.id.ac_shopLog_tv_chooseTime)
    public void onViewClicked() {

    }
}
