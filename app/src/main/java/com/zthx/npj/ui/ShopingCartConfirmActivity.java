package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CartStoreAdapter;
import com.zthx.npj.adapter.LocalStoreAdapter;
import com.zthx.npj.net.been.AddressInfoResponseBean;
import com.zthx.npj.net.been.CartOrderOneBean;
import com.zthx.npj.net.been.CartOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

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
    private String cart_id="";
    private String address_id = "";
    private String pay_code="2";
    private HashMap<String,String> type=new HashMap<>();
    private HashMap<String,String> remark=new HashMap<>();
    private HashMap<String,String> ziti_id=new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cartorder);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"确认订单");

        String result = getIntent().getStringExtra("info");
        cart_id=getIntent().getStringExtra("cart_id");
        CartOrderResponseBean bean = GsonUtils.fromJson(result, CartOrderResponseBean.class);
        data = bean.getData();
        address_id = data.getAdd_id() + "";
        atConfirmCartOrderTvOrderPrice1.setText(SharePerferenceUtils.getTotlePrice());
        getAddress();
        setCartOrder();

    }

    private void setCartOrder() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acConfirmCartOrderRv.setLayoutManager(layoutManager);
        CartStoreAdapter adapter = new CartStoreAdapter(this, data.getList(),this);
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
                pay_code="3";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_select);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmCartOrder_payType2:
                pay_code="2";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_select);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmCartOrder_payType3:
                pay_code="1";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_select);
                break;
            case R.id.ac_confirmOrder_btn_pay:
                for(int i=0;i<data.getList().size();i++){
                    String store_id= data.getList().get(i).get(0).getStore_id()+"";
                    String result=data.getList().get(i).get(0).getRemark()+"";
                    String ztId=data.getList().get(i).get(0).getZiti_id();
                    String goods_type="1";
                    boolean flag=data.getList().get(i).get(0).isZiti();
                    if(!flag){
                        goods_type="1";
                        ztId ="";
                    }else{
                        goods_type="2";
                    }
                    type.put(store_id,goods_type);
                    remark.put(store_id,result);
                    ziti_id.put(store_id,ztId);
                    Log.e("测试", "onViewClicked: "+store_id+" "+result+" "+ztId+" "+goods_type);
                }

                CartOrderOneBean bean=new CartOrderOneBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setCart_id(cart_id);
                bean.setAddress_id(address_id);
                bean.setPay_code(pay_code);
                bean.setType(type);
                bean.setRemark(remark);
                bean.setZiti_id(ziti_id);
                SetSubscribe.cartOrderOne(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("测试", "onSuccess: "+result);
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Log.e("测试", "onFault: "+errorMsg);
                    }
                }));
                break;
        }
    }


}
