package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyRefundActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_order_applyRefund_iv_goodsImg)
    ImageView acOrderApplyRefundIvGoodsImg;
    @BindView(R.id.at_order_applyRefund_tv_goodsName)
    TextView atOrderApplyRefundTvGoodsName;
    @BindView(R.id.at_order_applyRefund_tv_goodsPrice)
    TextView atOrderApplyRefundTvGoodsPrice;
    @BindView(R.id.at_order_applyRefund_tv_goodsNum)
    TextView atOrderApplyRefundTvGoodsNum;
    @BindView(R.id.ac_order_applyRefund_reason)
    EditText acOrderApplyRefundReason;
    @BindView(R.id.ac_order_applyRefund_img)
    ImageView acOrderApplyRefundImg;
    @BindView(R.id.ac_order_applyRefund_confirm)
    Button acOrderApplyRefundConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_applyrefund);
        ButterKnife.bind(this);
    }
}
