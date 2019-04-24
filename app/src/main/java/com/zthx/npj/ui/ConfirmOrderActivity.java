package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.at_confirm_order_rl_ziti)
    RelativeLayout atConfirmOrderRlZiti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_confirm_order_rl_ziti)
    public void onViewClicked() {
        showPopupWindow();
    }

    private void showPopupWindow() {
    }
}
