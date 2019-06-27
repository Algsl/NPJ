package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BuyVideoResponseBean;
import com.zthx.npj.net.been.VideoInfoResponseBean;
import com.zthx.npj.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoBuyConfirmActivity extends AppCompatActivity {

    @BindView(R.id.at_video_buy_confirm_rl_title)
    RelativeLayout atVideoBuyConfirmRlTitle;
    @BindView(R.id.at_video_buy_confirm_tv_name)
    TextView atVideoBuyConfirmTvName;
    @BindView(R.id.at_video_buy_confirm_img)
    ImageView atVideoBuyConfirmImg;
    @BindView(R.id.at_video_buy_confirm_tv_title)
    TextView atVideoBuyConfirmTvTitle;
    @BindView(R.id.at_video_buy_confirm_tv_teacher)
    TextView atVideoBuyConfirmTvTeacher;
    @BindView(R.id.at_video_buy_confirm_tv_type)
    TextView atVideoBuyConfirmTvType;
    @BindView(R.id.at_video_buy_confirm_tv_sale_price)
    TextView atVideoBuyConfirmTvSalePrice;
    @BindView(R.id.at_video_buy_confirm_iv_my_col)
    ImageView atVideoBuyConfirmIvMyCol;
    @BindView(R.id.at_video_buy_confirm_iv_my_wechat)
    ImageView atVideoBuyConfirmIvMyWechat;
    @BindView(R.id.at_video_buy_confirm_iv_alipay)
    ImageView atVideoBuyConfirmIvAlipay;
    @BindView(R.id.at_video_buy_confirm_tv_jin)
    TextView atVideoBuyConfirmTvJin;
    @BindView(R.id.at_video_buy_confirm_tv_money)
    TextView atVideoBuyConfirmTvMoney;
    @BindView(R.id.at_video_buy_confirm_btn_pay)
    Button atVideoBuyConfirmBtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_buy_confim);
        ButterKnife.bind(this);

        String info = getIntent().getStringExtra(Const.VIDEO_BUY_INFO);
        setData(info);

    }

    private void setData(String info) {
        BuyVideoResponseBean buyVideoResponseBean = GsonUtils.fromJson(info, BuyVideoResponseBean.class);
        atVideoBuyConfirmTvName.setText(buyVideoResponseBean.getData().getNick_name());
        Glide.with(this).load(buyVideoResponseBean.getData().getImg()).into(atVideoBuyConfirmImg);
        atVideoBuyConfirmTvTitle.setText(buyVideoResponseBean.getData().getTitle());
        atVideoBuyConfirmTvTeacher.setText("主讲: " + buyVideoResponseBean.getData().getTeacher());
        atVideoBuyConfirmTvType.setText("类型: " + buyVideoResponseBean.getData().getName_type());
        atVideoBuyConfirmTvSalePrice.setText(buyVideoResponseBean.getData().getPrice());
        atVideoBuyConfirmTvMoney.setText(buyVideoResponseBean.getData().getPrice());
    }
}
