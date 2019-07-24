package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CartStoreAdapter;
import com.zthx.npj.net.been.AddressInfoResponseBean;
import com.zthx.npj.net.been.CartOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopingCartConfirmActivity extends ActivityBase {
    @BindView(R.id.ac_confirm_cartOrder_rv)
    RecyclerView acConfirmCartOrderRv;
    @BindView(R.id.at_confirm_order_iv_my_hongbao)
    ImageView atConfirmOrderIvMyHongbao;
    @BindView(R.id.at_confirm_order_rl_hongbao)
    RelativeLayout atConfirmOrderRlHongbao;
    @BindView(R.id.at_confirm_order_iv_my_col)
    ImageView atConfirmOrderIvMyCol;
    @BindView(R.id.ac_confirmCartOrder_payType1)
    ImageView acConfirmCartOrderPayType1;
    @BindView(R.id.at_confirm_order_iv_my_wechat)
    ImageView atConfirmOrderIvMyWechat;
    @BindView(R.id.ac_confirmCartOrder_payType2)
    ImageView acConfirmCartOrderPayType2;
    @BindView(R.id.at_confirm_order_iv_alipay)
    ImageView atConfirmOrderIvAlipay;
    @BindView(R.id.ac_confirmCartOrder_payType3)
    ImageView acConfirmCartOrderPayType3;
    @BindView(R.id.at_confirm_order_tv_jin)
    TextView atConfirmOrderTvJin;
    @BindView(R.id.at_confirmCartOrder_tv_orderPrice1)
    TextView atConfirmCartOrderTvOrderPrice1;
    @BindView(R.id.ac_confirmOrder_btn_pay)
    Button acConfirmOrderBtnPay;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_cartConfirm_tv_userInfo)
    TextView acCartConfirmTvUserInfo;
    @BindView(R.id.ac_cartConfirm_tv_address)
    TextView acCartConfirmTvAddress;

    private CartOrderResponseBean.DataBean data = new CartOrderResponseBean().getData();
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String address_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cartorder);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"确认订单");

        String result = getIntent().getStringExtra("info");
        CartOrderResponseBean bean = GsonUtils.fromJson(result, CartOrderResponseBean.class);
        data = bean.getData();
        address_id = data.getAdd_id() + "";
        getAddress();
        setCartOrder();
    }

    private void setCartOrder() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acConfirmCartOrderRv.setLayoutManager(layoutManager);
        CartStoreAdapter adapter = new CartStoreAdapter(this, data.getList());
        acConfirmCartOrderRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new CartStoreAdapter.ItemClickListener() {
            @Override
            public void onZitiClick(int position) {

            }

            @Override
            public void onPeisongClick(int position) {

            }
        });
    }

    //获取收货地址
    private void getAddress() {
        SetSubscribe.getAddressInfo(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AddressInfoResponseBean bean = GsonUtils.fromJson(result, AddressInfoResponseBean.class);
                AddressInfoResponseBean.DataBean data=bean.getData();
                acCartConfirmTvUserInfo.setText(data.getConsignee()+" "+data.getMobile());
                acCartConfirmTvAddress.setText(data.getAddress()+data.getHouse_number());
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    @OnClick({R.id.ac_confirmCartOrder_payType1, R.id.ac_confirmCartOrder_payType2, R.id.ac_confirmCartOrder_payType3, R.id.ac_confirmOrder_btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_confirmCartOrder_payType1:
                break;
            case R.id.ac_confirmCartOrder_payType2:
                break;
            case R.id.ac_confirmCartOrder_payType3:
                break;
            case R.id.ac_confirmOrder_btn_pay:
                break;
        }
    }
}
