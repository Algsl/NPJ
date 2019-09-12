package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithDrawActivity extends ActivityBase{
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_withdraw_tv_money)
    TextView acWithdrawTvMoney;
    @BindView(R.id.ac_withdraw_et_drawMoney)
    EditText acWithdrawEtDrawMoney;
    @BindView(R.id.ac_withdraw_btn_allMoney)
    Button acWithdrawBtnAllMoney;
    @BindView(R.id.ac_withdraw_btn_draw)
    Button acWithdrawBtnDraw;

    String user_id= SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"余额提现");

        String balance=getIntent().getStringExtra("key0");
        acWithdrawTvMoney.setText(balance);
    }

    @OnClick({R.id.ac_withdraw_btn_allMoney, R.id.ac_withdraw_btn_draw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_withdraw_btn_allMoney:
                acWithdrawEtDrawMoney.setText(acWithdrawTvMoney.getText().toString().trim());
                break;
            case R.id.ac_withdraw_btn_draw:
                String card_id="2";
                String money=acWithdrawEtDrawMoney.getText().toString();
                SetSubscribe.withdraw(user_id,token,card_id,money,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
        }
    }
}
