package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreManagerActivity extends AppCompatActivity {

    @BindView(R.id.at_store_manager_tv_code)
    TextView atStoreManagerTvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_store_manager_tv_code)
    public void onViewClicked() {
        startActivity(new Intent(StoreManagerActivity.this, StoreManagerQRCodeActivity.class));
    }
}
