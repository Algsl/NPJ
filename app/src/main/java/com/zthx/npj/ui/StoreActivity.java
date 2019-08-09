package com.zthx.npj.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.utils.ImageCircleConner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreActivity extends ActivityBase {


    @BindView(R.id.ac_store_xcrriv_img)
    ImageView acStoreXcrrivImg;
    @BindView(R.id.ac_store_tv_storeName)
    TextView acStoreTvStoreName;
    @BindView(R.id.ac_store_iv_level)
    ImageView acStoreIvLevel;
    @BindView(R.id.ac_store_tv_fans)
    TextView acStoreTvFans;
    @BindView(R.id.ac_store_tv_goodsNum)
    TextView acStoreTvGoodsNum;
    @BindView(R.id.ac_store_iv_collect)
    ImageView acStoreIvCollect;
    @BindView(R.id.ac_store_tv_tuijian)
    TextView acStoreTvTuijian;
    @BindView(R.id.ac_store_tv_goods)
    TextView acStoreTvGoods;
    @BindView(R.id.ac_store_tv_saleNum)
    TextView acStoreTvSaleNum;
    @BindView(R.id.ac_store_rv)
    RecyclerView acStoreRv;
    @BindView(R.id.ac_store_iv_sort)
    ImageView acStoreIvSort;
    @BindView(R.id.ac_store_rl)
    RelativeLayout acStoreRl;

    private boolean saleFlag = false;
    private boolean collectFlag=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        acStoreXcrrivImg.setImageBitmap(ImageCircleConner.toRoundCorner(bitmap, 60));

    }

    @OnClick({R.id.ac_store_iv_collect, R.id.ac_store_tv_tuijian, R.id.ac_store_tv_goods, R.id.ac_store_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_store_iv_collect:
                collectToggle();
                break;
            case R.id.ac_store_tv_tuijian:
                acStoreTvTuijian.setTextColor(getResources().getColor(R.color.app_theme));
                acStoreIvSort.setImageResource(R.drawable.unselect);
                saleFlag = false;
                acStoreTvGoods.setTextColor(getResources().getColor(R.color.text3));
                acStoreTvSaleNum.setTextColor(getResources().getColor(R.color.text3));
                break;
            case R.id.ac_store_tv_goods:
                acStoreTvGoods.setTextColor(getResources().getColor(R.color.app_theme));
                acStoreIvSort.setImageResource(R.drawable.unselect);
                saleFlag = false;
                acStoreTvTuijian.setTextColor(getResources().getColor(R.color.text3));
                acStoreTvSaleNum.setTextColor(getResources().getColor(R.color.text3));
                break;
            case R.id.ac_store_rl:
                acStoreTvSaleNum.setTextColor(getResources().getColor(R.color.app_theme));
                toggle();
                acStoreTvTuijian.setTextColor(getResources().getColor(R.color.text3));
                acStoreTvGoods.setTextColor(getResources().getColor(R.color.text3));
                break;
        }
    }

    private void collectToggle() {
        collectFlag=!collectFlag;
        if(collectFlag){
            acStoreIvCollect.setImageResource(R.drawable.collect_star);
        }else{
            acStoreIvCollect.setImageResource(R.drawable.uncollect_star);
        }
    }

    private void toggle() {
        saleFlag = !saleFlag;
        if (saleFlag) {
            acStoreIvSort.setImageResource(R.drawable.select1);
        } else {
            acStoreIvSort.setImageResource(R.drawable.select2);
        }
    }
}
