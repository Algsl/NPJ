package com.zthx.npj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BuyVideoResponseBean;
import com.zthx.npj.net.been.VideoOrderBean;
import com.zthx.npj.net.been.VideoOrderResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoBuyConfirmActivity extends ActivityBase {

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
    @BindView(R.id.ac_videoBuyConfirm_iv_payType3)
    ImageView acVideoBuyConfirmIvPayType3;
    @BindView(R.id.ac_videoBuyConfirm_iv_payType2)
    ImageView acVideoBuyConfirmIvPayType2;
    @BindView(R.id.ac_videoBuyConfirm_iv_payType1)
    ImageView acVideoBuyConfirmIvPayType1;
    @BindView(R.id.ac_videoBuyConfirm_et_remark)
    EditText acVideoBuyConfirmEtRemark;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;


    private String pay_code = "1";
    private String list_id = "";
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_buy_confim);
        ButterKnife.bind(this);

        back(titleBack);
        titleBack.setImageResource(R.drawable.goods_detial_back);
        changeTitle(acTitle,"课程购买");
        changeRightImg(acTitleIv,R.drawable.goods_detail_home,null,null);

        String info = getIntent().getStringExtra(Const.VIDEO_BUY_INFO);
        setData(info);

    }

    private void setData(String info) {
        BuyVideoResponseBean buyVideoResponseBean = GsonUtils.fromJson(info, BuyVideoResponseBean.class);
        list_id = buyVideoResponseBean.getData().getList_id();
        atVideoBuyConfirmTvName.setText(buyVideoResponseBean.getData().getNick_name());
        Glide.with(this).load(buyVideoResponseBean.getData().getImg()).into(atVideoBuyConfirmImg);
        atVideoBuyConfirmTvTitle.setText(buyVideoResponseBean.getData().getTitle());
        atVideoBuyConfirmTvTeacher.setText("主讲: " + buyVideoResponseBean.getData().getTeacher());
        atVideoBuyConfirmTvType.setText("类型: " + buyVideoResponseBean.getData().getName_type());
        atVideoBuyConfirmTvSalePrice.setText(buyVideoResponseBean.getData().getPrice());
        atVideoBuyConfirmTvMoney.setText(buyVideoResponseBean.getData().getPrice());
    }

    @OnClick({R.id.ac_videoBuyConfirm_iv_payType3, R.id.ac_videoBuyConfirm_iv_payType2, R.id.ac_videoBuyConfirm_iv_payType1, R.id.at_video_buy_confirm_btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_videoBuyConfirm_iv_payType3:
                acVideoBuyConfirmIvPayType3.setImageResource(R.drawable.confirm_select);
                acVideoBuyConfirmIvPayType2.setImageResource(R.drawable.confirm_unselect);
                acVideoBuyConfirmIvPayType1.setImageResource(R.drawable.confirm_unselect);
                pay_code = "3";
                break;
            case R.id.ac_videoBuyConfirm_iv_payType2:
                acVideoBuyConfirmIvPayType3.setImageResource(R.drawable.confirm_unselect);
                acVideoBuyConfirmIvPayType2.setImageResource(R.drawable.confirm_select);
                acVideoBuyConfirmIvPayType1.setImageResource(R.drawable.confirm_unselect);
                pay_code = "2";
                break;
            case R.id.ac_videoBuyConfirm_iv_payType1:
                acVideoBuyConfirmIvPayType3.setImageResource(R.drawable.confirm_unselect);
                acVideoBuyConfirmIvPayType2.setImageResource(R.drawable.confirm_unselect);
                acVideoBuyConfirmIvPayType1.setImageResource(R.drawable.confirm_select);
                pay_code = "1";
                break;
            case R.id.at_video_buy_confirm_btn_pay:
                VideoOrderBean bean = new VideoOrderBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setRemark(acVideoBuyConfirmEtRemark.getText().toString().trim());
                bean.setPay_code(pay_code);
                bean.setList_id(list_id);
                DiscoverSubscribe.videoOrder(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        VideoOrderResponseBean bean = GsonUtils.fromJson(result, VideoOrderResponseBean.class);
                        VideoOrderResponseBean.DataBean data = bean.getData();
                        DiscoverSubscribe.payVideo(data.getPay_code(), data.getOrder_sn(), data.getPay_money(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                finish();
                            }

                            @Override
                            public void onFault(String errorMsg) {

                            }
                        }));
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
