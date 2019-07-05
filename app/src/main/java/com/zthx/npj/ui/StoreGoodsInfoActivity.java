package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.EditGoodsBean;
import com.zthx.npj.net.been.GoodsInfoResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreGoodsInfoActivity extends AppCompatActivity {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_storeGoodsInfo_et_goodsName)
    EditText acStoreGoodsInfoEtGoodsName;
    @BindView(R.id.ac_storeGoodsInfo_et_goodsDesc)
    EditText acStoreGoodsInfoEtGoodsDesc;
    @BindView(R.id.ac_storeGoodsInfo_iv_goodsImg)
    ImageView acStoreGoodsInfoIvGoodsImg;
    @BindView(R.id.ac_storeGoodsInfo_iv_goodsContent)
    ImageView acStoreGoodsInfoIvGoodsContent;
    @BindView(R.id.ac_storeGoodsInfo_et_platformPrice)
    EditText acStoreGoodsInfoEtPlatformPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_memberPrice)
    EditText acStoreGoodsInfoEtMemberPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_marketPrice)
    EditText acStoreGoodsInfoEtMarketPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_inventory)
    EditText acStoreGoodsInfoEtInventory;
    @BindView(R.id.ac_storeGoodsInfo_tv_cateName)
    TextView acStoreGoodsInfoTvCateName;
    @BindView(R.id.ac_storeGoodsInfo_et_isFreeShipping)
    EditText acStoreGoodsInfoEtIsFreeShipping;
    @BindView(R.id.publish_goods_iv)
    ImageView publishGoodsIv;
    @BindView(R.id.ac_storeGoodsInfo_tv_goodsType)
    TextView acStoreGoodsInfoTvGoodsType;
    @BindView(R.id.ac_storeGoodsInfo_btn_pulish)
    Button acStoreGoodsInfoBtnPulish;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String goods_id = getIntent().getStringExtra("goods_id");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goodsInfo);
        ButterKnife.bind(this);

        getStoreGoodsInfo();
    }

    private void getStoreGoodsInfo() {
        SetSubscribe.goodsInfo(user_id, token, goods_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoodsInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreGoodsInfo(String result) {
        GoodsInfoResponseBean bean = GsonUtils.fromJson(result, GoodsInfoResponseBean.class);
        GoodsInfoResponseBean.DataBean data = bean.getData();
        acStoreGoodsInfoEtGoodsName.setText(data.getGoods_name());
        acStoreGoodsInfoEtGoodsDesc.setText(data.getGoods_desc());
        Glide.with(this).load(Uri.parse(data.getGoods_img().get(0))).into(acStoreGoodsInfoIvGoodsImg);
        Glide.with(this).load(Uri.parse(data.getGoods_content().get(0))).into(acStoreGoodsInfoIvGoodsContent);
        acStoreGoodsInfoEtPlatformPrice.setText(data.getPlatform_price());
        acStoreGoodsInfoEtMemberPrice.setText(data.getMember_price());
        acStoreGoodsInfoEtMarketPrice.setText(data.getMarket_price());
        acStoreGoodsInfoEtInventory.setText(data.getInventory() + "");
        acStoreGoodsInfoTvCateName.setText(data.getCate_name());
        if (data.getIs_free_shipping() == 0) {
            acStoreGoodsInfoEtIsFreeShipping.setText("包邮");
        } else {
            acStoreGoodsInfoEtIsFreeShipping.setText("不包邮");
        }
        acStoreGoodsInfoTvGoodsType.setText(data.getGoods_type() + "");
    }

    @OnClick({R.id.title_theme_back, R.id.ac_storeGoodsInfo_iv_goodsImg, R.id.ac_storeGoodsInfo_iv_goodsContent, R.id.ac_storeGoodsInfo_btn_pulish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_theme_back:
                break;
            case R.id.ac_storeGoodsInfo_iv_goodsImg:
                break;
            case R.id.ac_storeGoodsInfo_iv_goodsContent:
                break;
            case R.id.ac_storeGoodsInfo_btn_pulish:
                editStoreGoodsInfo();
                break;
        }
    }

    private void editStoreGoodsInfo() {
        EditGoodsBean bean=new EditGoodsBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goods_id);
        bean.setGoods_desc(getEtString(acStoreGoodsInfoEtGoodsDesc));
        bean.setGoods_img("");
        bean.setPlatform_price(getEtString(acStoreGoodsInfoEtPlatformPrice));
        bean.setMarket_price(getEtString(acStoreGoodsInfoEtMarketPrice));
        bean.setMember_price(getEtString(acStoreGoodsInfoEtMemberPrice));
        bean.setInventory(getEtString(acStoreGoodsInfoEtInventory));
        bean.setCate_id("584");
        bean.setIs_free_shipping("0");
        SetSubscribe.editGoods(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
    public String getEtString(EditText et){
        return et.getText().toString().trim();
    }
}
