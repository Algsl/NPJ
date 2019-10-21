package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zthx.npj.R;
import com.zthx.npj.net.been.OrderPushBean;
import com.zthx.npj.net.been.OrderPushResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.marquee.LooperBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderFinishActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_orderFinish_riv_goodsImg)
    RoundedImageView acOrderFinishRivGoodsImg;
    @BindView(R.id.ac_orderFinish_tv_goodsName)
    TextView acOrderFinishTvGoodsName;
    @BindView(R.id.ac_orderFinish_tv_payMoney)
    TextView acOrderFinishTvPayMoney;
    @BindView(R.id.ac_orderFinish_tv_seeOrder)
    TextView acOrderFinishTvSeeOrder;
    @BindView(R.id.ac_orderFinish_tv_shopping)
    TextView acOrderFinishTvShopping;

    private ArrayList<LooperBean> looperBeans=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finish);
        ButterKnife.bind(this);

        getOrderPush();

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"订单支付成功");

        String goodsImg=getIntent().getStringExtra("key0");
        String goodsName=getIntent().getStringExtra("key1");
        String goodsPrice=getIntent().getStringExtra("key2");
        Glide.with(this).load(goodsImg).into(acOrderFinishRivGoodsImg);
        acOrderFinishTvGoodsName.setText(goodsName);
        acOrderFinishTvPayMoney.setText(goodsPrice);
    }

    private void getOrderPush() {
        Log.e("测试", "onSuccess: " );
        MainSubscribe.orderPush(new OrderPushBean(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderPushResponseBean bean = GsonUtils.fromJson(result, OrderPushResponseBean.class);
                for(int i=0;i<bean.getData().size();i++){
                    looperBeans.add(new LooperBean(bean.getData().get(i).getHead_img(),bean.getData().get(i).getTitle()));
                }
                SharePerferenceUtils.setLooperBeans(looperBeans);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.ac_orderFinish_tv_seeOrder, R.id.ac_orderFinish_tv_shopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_orderFinish_tv_seeOrder:
                Intent intent = new Intent(this, MyOrderActivity.class);
                intent.putExtra("currentItem", 2);
                startActivity(intent);
                break;
            case R.id.ac_orderFinish_tv_shopping:
                openActivity(ClassfiysActivity.class);
                break;
        }
    }
}
