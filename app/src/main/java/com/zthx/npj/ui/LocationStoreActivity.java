package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.LocationStoreAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.GlideImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.at_location_store_locate)
    RelativeLayout atLocationStoreLocate;
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
    @BindView(R.id.ac_localtionStore_tv_address)
    TextView acLocaltionStoreTvAddress;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String type = "1";


    private String keyword = "";
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String level = SharePerferenceUtils.getUserLevel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "附近商家");

        atLocationStoreTvRuzhu.setText("我的店铺");
        acLocaltionStoreTvAddress.setSelected(true);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getLocalStore(type);
                initBanner();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        initBanner();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                atLocationStoreRv.setItemAnimator(new DefaultItemAnimator());
                atLocationStoreRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }, LocationStoreActivity.this));
    }

    @OnClick({R.id.ac_locationStore_tv_type1, R.id.ac_locationStore_tv_type2, R.id.ac_locationStore_tv_type3,
            R.id.ac_locationStore_tv_type4, R.id.at_location_store_et_search, R.id.at_location_store_locate,
            R.id.at_location_store_tv_ruzhu})
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
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            getSearchStore();
                        }
                        return true;
                    }
                });
                break;
            case R.id.at_location_store_locate:
                Intent intent = new Intent(this, MapAddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.at_location_store_tv_ruzhu:
                if(user_id.equals("")){
                    CommonDialog dialog=new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                startActivity(new Intent(LocationStoreActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                }else{
                    getMyStoreType();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            acLocaltionStoreTvAddress.setText(data.getStringExtra("addressDetail"));
        }
    }

    private void getSearchStore() {
        String lng = SharePerferenceUtils.getLng(this);
        String lat = SharePerferenceUtils.getLat(this);
        keyword = atLocationStoreEtSearch.getText().toString().trim();
        MainSubscribe.searchStore(lng, lat, keyword, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSearchStore(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setSearchStore(String result) {
        LocalStoreResponseBean bean = GsonUtils.fromJson(result, LocalStoreResponseBean.class);
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
        atLocationStoreRv.setItemAnimator(new DefaultItemAnimator());
        atLocationStoreRv.setAdapter(mAdapter);
    }

    private void initBanner() {
        MainSubscribe.getMainBanner("3", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                final BannerResponseBean bean = GsonUtils.fromJson(result, BannerResponseBean.class);
                ArrayList<BannerResponseBean.DataBean> data = bean.getData();
                ArrayList<Uri> list = new ArrayList<>();
                ArrayList<String> list2 = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(Uri.parse(data.get(i).getImg()));
                    list2.add(data.get(i).getTitle());
                }
                //设置banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(list);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置标题集合（当banner样式有显示title时）
                banner.setBannerTitles(list2);
                //设置轮播时间
                banner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                //设置banner点击事件
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(LocationStoreActivity.this, BannerActivity.class);
                        intent.putExtra("title", bean.getData().get(position).getTitle());
                        intent.putExtra("type", bean.getData().get(position).getType());
                        intent.putExtra("id", bean.getData().get(position).getId()+"");
                        startActivity(intent);
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    public void getMyStoreType() {
        if (level.equals("0")) {
            Toast toast = Toast.makeText(LocationStoreActivity.this, "成为农品街代言人，才可使用线下门店的功能哦", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    startActivity(new Intent(LocationStoreActivity.this, EditMyOfflineStoreActivity.class));
                    /*Log.e("测试", "onSuccess: " + result);
                    try {
                        JSONObject object = new JSONObject(result);
                        String code = object.getString("code") + "";
                        if (code.equals("1")) {
                            startActivity(new Intent(LocationStoreActivity.this, EditMyOfflineStoreActivity.class));
                        } else if (code.equals("2")) {
                            Toast.makeText(LocationStoreActivity.this, "线下门店审核中", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }

                @Override
                public void onFault(String errorMsg) {
                    try {
                        JSONObject object = new JSONObject(errorMsg);
                        String code = object.getString("code") + "";
                        if (code.equals("-2")) {
                            startActivity(new Intent(LocationStoreActivity.this, StoreManagerActivity.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

}
