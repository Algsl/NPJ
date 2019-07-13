package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InComeLogActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;

    @BindView(R.id.ac_incomeLog_tv_chooseTime)
    TextView acIncomeLogTvChooseTime;
    @BindView(R.id.ac_incomeLog_tv_ioMoney)
    TextView acIncomeLogTvIoMoney;
    @BindView(R.id.ac_incomeLog_rv_mingxi)
    RecyclerView acIncomeLogRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String type = "100";
    @BindView(R.id.ac_vipJL_tv_allType)
    TextView acVipJLTvAllType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_log);
        ButterKnife.bind(this);

        acTitle.setText("收益明细");
        getIncomeLog();
    }

    private void getIncomeLog() {
        SetSubscribe.inComeLog(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
