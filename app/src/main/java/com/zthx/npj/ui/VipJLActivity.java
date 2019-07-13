package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.VipJLAdapter;
import com.zthx.npj.net.been.VipJLResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipJLActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;

    @BindView(R.id.ac_vipJL_tv_chooseTime)
    TextView acVipJLTvChooseTime;
    @BindView(R.id.ac_vipJL_tv_ioMoney)
    TextView acVipJLTvIoMoney;
    @BindView(R.id.ac_vipJL_rv_mingxi)
    RecyclerView acVipJLRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String type = "";
    String begin_time = "";
    String end_time = "";
    @BindView(R.id.ac_vipJL_tv_allType)
    TextView acVipJLTvAllType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_jl);
        ButterKnife.bind(this);

        acTitle.setText("代言奖励");
        getVipJLInfo();
    }

    private void getVipJLInfo() {
        SetSubscribe.vipJL(user_id, token, type, begin_time, end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setVipJL(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setVipJL(String result) {
        VipJLResponseBean bean = GsonUtils.fromJson(result, VipJLResponseBean.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acVipJLRvMingxi.setLayoutManager(layoutManager);
        VipJLAdapter adapter = new VipJLAdapter(this, bean.getData());
        acVipJLRvMingxi.setAdapter(adapter);
    }
}
