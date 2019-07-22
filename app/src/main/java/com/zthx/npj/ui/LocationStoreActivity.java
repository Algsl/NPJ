package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.LocationStoreAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationStoreActivity extends ActivityBase {

    @BindView(R.id.at_location_store_rv)
    RecyclerView atLocationStoreRv;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_location_store_locate)
    ImageView atLocationStoreLocate;
    @BindView(R.id.at_location_store_et_search)
    EditText atLocationStoreEtSearch;
    @BindView(R.id.ac_locationStore_tv_type1)
    TextView acLocationStoreTvType1;
    @BindView(R.id.ac_locationStore_tv_type2)
    TextView acLocationStoreTvType2;
    @BindView(R.id.ac_locationStore_tv_type3)
    TextView acLocationStoreTvType3;
    @BindView(R.id.ac_locationStore_tv_type4)
    TextView acLocationStoreTvType4;
    private String type = "1";

    private String lng = SharePerferenceUtils.getLng(this);
    private String lat = SharePerferenceUtils.getLat(this);
    private String keyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "附近商家");
        changeRightText(atLocationStoreTvRuzhu, "立即入驻？", StoreManagerActivity.class, null);

        getLocalStore(type);

    }

    private void getLocalStore(String type) {
        LocalStoreBean bean = new LocalStoreBean();
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        bean.setPage("1");
        bean.setType(type);
        MainSubscribe.getLocalStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LocalStoreResponseBean localStoreResponseBean = GsonUtils.fromJson(result, LocalStoreResponseBean.class);
                final ArrayList<LocalStoreResponseBean.DataBean> data = localStoreResponseBean.getData();

                LinearLayoutManager manager = new LinearLayoutManager(LocationStoreActivity.this, LinearLayoutManager.VERTICAL, false);
                atLocationStoreRv.setLayoutManager(manager);
                LocationStoreAdapter mAdapter = new LocationStoreAdapter(LocationStoreActivity.this, data);
                mAdapter.setOnItemClickListener(new LocationStoreAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(LocationStoreActivity.this, StoreDetailActivity.class);
                        intent.putExtra(Const.STORE_ID, data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                atLocationStoreRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, LocationStoreActivity.this));
    }

    @OnClick({R.id.ac_locationStore_tv_type1, R.id.ac_locationStore_tv_type2, R.id.ac_locationStore_tv_type3, R.id.ac_locationStore_tv_type4,R.id.at_location_store_et_search})
    public void onViewClicked(View view) {
        acLocationStoreTvType1.setTextColor(getResources().getColor(R.color.text6));
        acLocationStoreTvType1.setBackground(getResources().getDrawable(R.drawable.stroke_white_2));
        acLocationStoreTvType2.setTextColor(getResources().getColor(R.color.text6));
        acLocationStoreTvType2.setBackground(getResources().getDrawable(R.drawable.stroke_white_2));
        acLocationStoreTvType3.setTextColor(getResources().getColor(R.color.text6));
        acLocationStoreTvType3.setBackground(getResources().getDrawable(R.drawable.stroke_white_2));
        acLocationStoreTvType4.setTextColor(getResources().getColor(R.color.text6));
        acLocationStoreTvType4.setBackground(getResources().getDrawable(R.drawable.stroke_white_2));
        switch (view.getId()) {
            case R.id.ac_locationStore_tv_type1:
                acLocationStoreTvType1.setTextColor(getResources().getColor(R.color.app_theme));
                acLocationStoreTvType1.setBackground(getResources().getDrawable(R.drawable.stroke_theme_2));
                type = "1";
                getLocalStore(type);
                break;
            case R.id.ac_locationStore_tv_type2:
                acLocationStoreTvType2.setBackgroundResource(R.drawable.stroke_app_theme);
                acLocationStoreTvType2.setBackground(getResources().getDrawable(R.drawable.stroke_theme_2));
                type = "2";
                getLocalStore(type);
                break;
            case R.id.ac_locationStore_tv_type3:
                acLocationStoreTvType3.setTextColor(getResources().getColor(R.color.app_theme));
                acLocationStoreTvType3.setBackground(getResources().getDrawable(R.drawable.stroke_theme_2));
                type = "3";
                getLocalStore(type);
                break;
            case R.id.ac_locationStore_tv_type4:
                acLocationStoreTvType4.setTextColor(getResources().getColor(R.color.app_theme));
                acLocationStoreTvType4.setBackground(getResources().getDrawable(R.drawable.stroke_theme_2));
                type = "4";
                getLocalStore(type);
                break;
            case R.id.at_location_store_et_search:
                atLocationStoreEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if(i==EditorInfo.IME_ACTION_SEARCH){
                            getSearchStore();
                        }
                        return true;
                    }
                });
                break;
        }
    }

    private void getSearchStore() {
        keyword=atLocationStoreEtSearch.getText().toString().trim();
        MainSubscribe.searchStore(lng,lat,keyword,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSearchStore(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSearchStore(String result) {
        LocalStoreResponseBean bean=GsonUtils.fromJson(result,LocalStoreResponseBean.class);
        final ArrayList<LocalStoreResponseBean.DataBean> data = bean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(LocationStoreActivity.this, LinearLayoutManager.VERTICAL, false);
        atLocationStoreRv.setLayoutManager(manager);
        LocationStoreAdapter mAdapter = new LocationStoreAdapter(LocationStoreActivity.this, data);
        mAdapter.setOnItemClickListener(new LocationStoreAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(LocationStoreActivity.this, StoreDetailActivity.class);
                intent.putExtra(Const.STORE_ID, data.get(position).getId() + "");
                startActivity(intent);
            }
        });
        atLocationStoreRv.setAdapter(mAdapter);
    }
}
