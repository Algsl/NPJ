package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AddGoodsBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class PublishGoodsActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_pulishGoods_et_goodsName)
    EditText acPulishGoodsEtGoodsName;
    @BindView(R.id.ac_pulishGoods_et_goodsDesc)
    EditText acPulishGoodsEtGoodsDesc;
    @BindView(R.id.ac_pulishGoods_iv_goodsContent)
    ImageView acPulishGoodsIvGoodsContent;
    @BindView(R.id.ac_pulishGoods_et_platformPrice)
    EditText acPulishGoodsEtPlatformPrice;
    @BindView(R.id.ac_pulishGoods_et_memberPrice)
    EditText acPulishGoodsEtMemberPrice;
    @BindView(R.id.ac_pulishGoods_et_marketPrice)
    EditText acPulishGoodsEtMarketPrice;
    @BindView(R.id.ac_pulishGoods_et_inventory)
    EditText acPulishGoodsEtInventory;
    @BindView(R.id.ac_pulishGoods_tv_cateId)
    TextView acPulishGoodsTvCateId;
    @BindView(R.id.ac_pulishGoods_et_isFreeShipping)
    EditText acPulishGoodsEtIsFreeShipping;
    @BindView(R.id.publish_goods_iv)
    ImageView publishGoodsIv;
    @BindView(R.id.ac_pulishGoods_tv_goodsType)
    TextView acPulishGoodsTvGoodsType;
    @BindView(R.id.ac_pulishGoods_btn_pulish)
    Button acPulishGoodsBtnPulish;
    @BindView(R.id.ac_pulishGoods_iv_goodsImg)
    ZzImageBox acPulishGoodsIvGoodsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulish_goods);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ac_pulishGoods_iv_goodsImg, R.id.ac_pulishGoods_iv_goodsContent, R.id.ac_pulishGoods_tv_goodsType, R.id.ac_pulishGoods_btn_pulish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_pulishGoods_iv_goodsImg:
                acPulishGoodsIvGoodsImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
                    @Override
                    public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

                    }

                    @Override
                    public void onDeleteClick(int position, String url, String realPath, int realType) {
                        acPulishGoodsIvGoodsImg.removeImage(position);
                    }

                    @Override
                    public void onAddClick() {
                        acPulishGoodsIvGoodsImg.addImage(null);
                    }
                });
                break;
            case R.id.ac_pulishGoods_iv_goodsContent:
                break;
            case R.id.ac_pulishGoods_tv_goodsType:
                break;
            case R.id.ac_pulishGoods_btn_pulish:
                pulishGoods();
                break;
        }
    }

    public String getEtString(EditText et) {
        return et.getText().toString().trim();
    }

    ;

    private void pulishGoods() {
        AddGoodsBean bean = new AddGoodsBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setGoods_name(getEtString(acPulishGoodsEtGoodsName));
        bean.setGoods_desc(getEtString(acPulishGoodsEtGoodsDesc));
        bean.setGoods_img("/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg,/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg,/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg");
        bean.setGoods_content("");
        bean.setPlatform_price(getEtString(acPulishGoodsEtPlatformPrice));
        bean.setMember_price(getEtString(acPulishGoodsEtMemberPrice));
        bean.setMarket_price(getEtString(acPulishGoodsEtMarketPrice));
        bean.setInventory(getEtString(acPulishGoodsEtInventory));
        bean.setCate_id("20");
        bean.setIs_free_shipping("0");
        bean.setGoods_type("1");
        SetSubscribe.addGoods(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
