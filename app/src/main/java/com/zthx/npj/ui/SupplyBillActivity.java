package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.ConfirmSupplyResponseBean;
import com.zthx.npj.net.been.SupplyBuy2Bean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyBillActivity extends ActivityBase {

    @BindView(R.id.at_supply_bill_ll_choice_address)
    RelativeLayout atSupplyBillLlChoiceAddress;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_supply_bill_iv1)
    ImageView atSupplyBillIv1;
    @BindView(R.id.at_supply_bill_head_pic)
    MyCircleView atSupplyBillHeadPic;
    @BindView(R.id.at_supply_bill_tv_name)
    TextView atSupplyBillTvName;
    @BindView(R.id.at_supply_bill_iv_goods_pic)
    ImageView atSupplyBillIvGoodsPic;
    @BindView(R.id.at_supply_bill_tv_title)
    TextView atSupplyBillTvTitle;
    @BindView(R.id.at_supply_bill_tv_danjia)
    TextView atSupplyBillTvDanjia;
    @BindView(R.id.at_supply_bill_tv_unit)
    TextView atSupplyBillTvUnit;
    @BindView(R.id.at_supply_bill_tv_buy_num)
    TextView atSupplyBillTvBuyNum;
    @BindView(R.id.at_supply_bill_tv_zongjia)
    TextView atSupplyBillTvZongjia;
    @BindView(R.id.at_supply_bill_btn_buy)
    Button atSupplyBillBtnBuy;
    @BindView(R.id.at_supply_bill_ll_bottom)
    LinearLayout atSupplyBillLlBottom;
    @BindView(R.id.at_supply_bill_tv_price)
    TextView atSupplyBillTvPrice;
    @BindView(R.id.at_supply_bill_tv_gongji_goods)
    TextView atSupplyBillTvGongjiGoods;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_supply_bill_tv_address)
    TextView atSupplyBillTvAddress;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String goodsId;
    private String goods_num="1";
    private String pay_code="1";
    private String address_id="10";
    private String shipping_fee="1";
    private String remark="sdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_bill);
        ButterKnife.bind(this);


        back(titleBack);
        changeTitle(acTitle, "商品确认订单");

        goodsId = getIntent().getStringExtra("key0");

        getData(goodsId);
    }

    private void getData(String id) {
        DiscoverSubscribe.confirmSupply(SharePerferenceUtils.getUserId(this), id, SharePerferenceUtils.getToken(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        ConfirmSupplyResponseBean.DataBean data = GsonUtils.fromJson(result, ConfirmSupplyResponseBean.class).getData();
                        Glide.with(SupplyBillActivity.this).load(data.getHead_img()).into(atSupplyBillHeadPic);
                        atSupplyBillTvAddress.setText(data.getAddress());
                        atSupplyBillTvName.setText(data.getNick_name());
                        Glide.with(SupplyBillActivity.this).load(data.getGoods_img()).into(atSupplyBillIvGoodsPic);
                        atSupplyBillTvTitle.setText(data.getTitle());
                        atSupplyBillTvDanjia.setText("¥" + data.getPrice());
                        atSupplyBillTvUnit.setText(data.getGoods_unit());
                        atSupplyBillTvBuyNum.setText(data.getBuy_num());
                        atSupplyBillTvGongjiGoods.setText("共计" + data.getBuy_num() + "斤商品   小计：");
                        atSupplyBillTvPrice.setText("¥" + Integer.parseInt(data.getBuy_num()) * Float.parseFloat(data.getPrice()));
                        atSupplyBillTvZongjia.setText("¥" + Integer.parseInt(data.getBuy_num()) * Float.parseFloat(data.getPrice()));
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }, this));
    }

    @OnClick({R.id.at_supply_bill_ll_choice_address, R.id.at_supply_bill_btn_buy})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.at_supply_bill_ll_choice_address:
                Intent intent=new Intent(this,AddressListActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.at_supply_bill_btn_buy:
                SupplyBuy2Bean bean=new SupplyBuy2Bean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setGoods_id(goodsId);
                bean.setGoods_num(goods_num);
                bean.setPay_code(pay_code);
                bean.setAddress_id(address_id);
                bean.setShipping_fee(shipping_fee);
                bean.setRemark(remark);
                DiscoverSubscribe.supplyBuy2(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            address_id=data.getStringExtra("address_id");
            atSupplyBillTvAddress.setText(data.getStringExtra("address"));
        }
    }
}
