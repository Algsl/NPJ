package com.zthx.npj.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsAdapter;
import com.zthx.npj.adapter.StoreGoodsSearchAdapter;
import com.zthx.npj.net.been.SearchStoreGoodsResponseBean;
import com.zthx.npj.net.been.StoreGoodsListResponseBean;
import com.zthx.npj.net.been.StoreInfoResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.ImageCircleConner;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreActivity extends ActivityBase {


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
    @BindView(R.id.ac_store_iv_back)
    ImageView acStoreIvBack;
    @BindView(R.id.ac_store_iv_img)
    ImageView acStoreIvImg;
    @BindView(R.id.ac_store_tv_tag1)
    TextView acStoreTvTag1;
    @BindView(R.id.ac_store_tv_tag2)
    TextView acStoreTvTag2;
    @BindView(R.id.at_store_et_search)
    EditText atStoreEtSearch;
    @BindView(R.id.ac_store_ll_classify)
    LinearLayout acStoreLlClassify;
    @BindView(R.id.ac_store_iv_message)
    ImageView acStoreIvMessage;
    @BindView(R.id.ac_store_ll_tuijian)
    LinearLayout acStoreLlTuijian;
    @BindView(R.id.ac_store_ll_search)
    LinearLayout acStoreLlSearch;
    @BindView(R.id.ac_store_rv1)
    RecyclerView acStoreRv1;
    @BindView(R.id.ac_store_tv_noResult)
    TextView acStoreTvNoResult;

    private boolean saleFlag = false;
    private boolean collectFlag;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String store_id = "";
    private String type = "1";
    private long level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        back(acStoreIvBack);

        store_id = getIntent().getStringExtra("key0");
        getStoreInfo();
        getStoreGoodsList();
    }

    //获取商品列表信息
    private void getStoreGoodsList() {
        acStoreLlTuijian.setVisibility(View.VISIBLE);
        acStoreLlSearch.setVisibility(View.GONE);
        MainSubscribe.storeGoodsList(store_id, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoodsList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreGoodsList(String result) {
        StoreGoodsListResponseBean bean = GsonUtils.fromJson(result, StoreGoodsListResponseBean.class);
        ArrayList<StoreGoodsListResponseBean.DataBean> data = bean.getData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        acStoreRv.setLayoutManager(layoutManager);
        StoreGoodsAdapter adapter = new StoreGoodsAdapter(this, data, level);
        acStoreRv.setAdapter(adapter);
    }

    //获取店铺信息
    private void getStoreInfo() {
        MainSubscribe.storeInfo(store_id, "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreInfo(String result) {
        StoreInfoResponseBean bean = GsonUtils.fromJson(result, StoreInfoResponseBean.class);
        StoreInfoResponseBean.DataBean data = bean.getData();

        level = data.getLevel();
        Glide.with(this).load(Uri.parse(data.getStore_img())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                acStoreIvImg.setImageBitmap(ImageCircleConner.toRoundCorner(resource, 16));
            }
        });
        acStoreTvStoreName.setText(data.getStore_name());
        acStoreTvFans.setText(data.getAtt_num() == null ? "0" : data.getAtt_num());
        acStoreTvGoodsNum.setText(data.getGoods_num());
        MyCustomUtils.showLevelImg((int) data.getLevel(), acStoreIvLevel);
        collectFlag = data.getIs_shoucang().equals("0") ? false : true;
        if (collectFlag) {
            acStoreIvCollect.setImageResource(R.drawable.collect_star);
        } else {
            acStoreIvCollect.setImageResource(R.drawable.uncollect_star);
        }
    }


    @OnClick({R.id.ac_store_iv_collect, R.id.ac_store_tv_tuijian, R.id.ac_store_tv_goods, R.id.ac_store_rl,
            R.id.at_store_et_search,R.id.ac_store_iv_message,R.id.ac_store_ll_classify})
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
                type = "1";
                getStoreGoodsList();
                break;
            case R.id.ac_store_tv_goods:
                acStoreTvGoods.setTextColor(getResources().getColor(R.color.app_theme));
                acStoreIvSort.setImageResource(R.drawable.unselect);
                saleFlag = false;
                acStoreTvTuijian.setTextColor(getResources().getColor(R.color.text3));
                acStoreTvSaleNum.setTextColor(getResources().getColor(R.color.text3));
                type = "2";
                getStoreGoodsList();
                break;
            case R.id.ac_store_rl:
                acStoreTvSaleNum.setTextColor(getResources().getColor(R.color.app_theme));
                toggle();
                acStoreTvTuijian.setTextColor(getResources().getColor(R.color.text3));
                acStoreTvGoods.setTextColor(getResources().getColor(R.color.text3));
                break;
            case R.id.at_store_et_search:
                atStoreEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String keyword = atStoreEtSearch.getText().toString().trim();
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            if (keyword.equals("")) {
                                Toast.makeText(StoreActivity.this, "您忘记输入了!", Toast.LENGTH_SHORT).show();
                            } else {
                                searchStoreGoods(keyword);
                            }
                        }
                        return true;
                    }
                });
                break;
            case R.id.ac_store_iv_message:
                openActivity(MessageCenterActivity.class);
                break;
            case R.id.ac_store_ll_classify:
                openActivity(StoreGoodsClassifyActivity.class);
                break;
        }
    }

    private void searchStoreGoods(String str) {
        acStoreLlTuijian.setVisibility(View.GONE);
        acStoreLlSearch.setVisibility(View.VISIBLE);
        MainSubscribe.searchStoreGoods(store_id, str, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSearchStoreGoods(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSearchStoreGoods(String result) {
        SearchStoreGoodsResponseBean bean = GsonUtils.fromJson(result, SearchStoreGoodsResponseBean.class);
        if(bean!=null){
            ArrayList<SearchStoreGoodsResponseBean.DataBean> data = bean.getData();
            acStoreRv1.setVisibility(View.VISIBLE);
            acStoreTvNoResult.setVisibility(View.GONE);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            acStoreRv1.setLayoutManager(layoutManager);
            StoreGoodsSearchAdapter adapter = new StoreGoodsSearchAdapter(this, data, level);
            acStoreRv1.setAdapter(adapter);
        }else{
            acStoreRv1.setVisibility(View.GONE);
            acStoreTvNoResult.setVisibility(View.VISIBLE);
        }
    }


    private void collectToggle() {
        collectFlag = !collectFlag;
        if (collectFlag) {
            SetSubscribe.addCollection(user_id, token, store_id, "2", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    acStoreIvCollect.setImageResource(R.drawable.collect_star);
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        } else {
            acStoreIvCollect.setImageResource(R.drawable.uncollect_star);
        }
    }

    private void toggle() {
        saleFlag = !saleFlag;
        if (saleFlag) {
            acStoreIvSort.setImageResource(R.drawable.select1);
            type = "3";
        } else {
            acStoreIvSort.setImageResource(R.drawable.select2);
            type = "4";
        }
        getStoreGoodsList();
    }
}
