package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.net.been.AttentionResponseBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.EditOfflineStoreBean;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.SimpleUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class EditMyOfflineStoreActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_storeManager_et_storeName)
    EditText acStoreManagerEtStoreName;
    @BindView(R.id.ac_storeManager_et_consumption)
    EditText acStoreManagerEtConsumption;
    @BindView(R.id.ac_storeManager_tv_businessHours)
    TextView acStoreManagerTvBusinessHours;
    @BindView(R.id.ac_storeManager_et_contact)
    EditText acStoreManagerEtContact;
    @BindView(R.id.ac_storeManager_tv_address)
    TextView acStoreManagerTvAddress;
    @BindView(R.id.ac_storeManager_et_address2)
    EditText acStoreManagerEtAddress2;
    @BindView(R.id.iv_zhekou)
    ImageView ivZhekou;
    @BindView(R.id.tv_malv)
    TextView tvMalv;
    @BindView(R.id.ac_storeManager_tv_offer)
    TextView acStoreManagerTvOffer;
    @BindView(R.id.at_store_manager_tv_code)
    TextView atStoreManagerTvCode;
    @BindView(R.id.ac_storeManager_et_relife)
    EditText acStoreManagerEtRelife;
    @BindView(R.id.zz_image_box)
    ZzImageBox zzImageBox;
    @BindView(R.id.ac_storeManager_btn_ruzhu)
    Button acStoreManagerBtnRuzhu;
    @BindView(R.id.ac_store_manager_rv)
    RecyclerView acStoreManagerRv;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MyOfflineStoreResponseBean.DataBean data;
    private boolean isClose;


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"商家管理");
        //changeRightText(atLocationStoreTvRuzhu,"管理",StoreManagerCenterActivity.class,null);
        atLocationStoreTvRuzhu.setText("管理");
        getMyOfflineStore();
    }

    private void getMyOfflineStore() {
        SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOfflineStore(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setMyOfflineStore(String result) {
        MyOfflineStoreResponseBean bean = GsonUtils.fromJson(result, MyOfflineStoreResponseBean.class);
        data = bean.getData();
        acStoreManagerEtStoreName.setText(data.getStore_name());
        acStoreManagerEtConsumption.setText(data.getConsumption());
        acStoreManagerTvBusinessHours.setText(data.getBusiness_hours());
        acStoreManagerEtContact.setText(data.getContact());
        acStoreManagerTvAddress.setText(data.getAddress());
        acStoreManagerEtAddress2.setText(data.getAddress2());
        acStoreManagerTvOffer.setText(data.getOffer()+"%");
        acStoreManagerEtRelife.setText(data.getRelief());

        getComment();
    }

    private String getEtToString(EditText et) {
        return et.getText().toString().trim();
    }

    @OnClick({R.id.ac_storeManager_btn_ruzhu,R.id.at_store_manager_tv_code,R.id.at_location_store_tv_ruzhu})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.at_store_manager_tv_code:
                Intent intent1=new Intent(EditMyOfflineStoreActivity.this,StoreManagerQRCodeActivity.class);
                intent1.putExtra("img",data.getStore_img().get(0));
                startActivityForResult(intent1,3);
                break;
            case R.id.ac_storeManager_btn_ruzhu:
                EditOfflineStoreBean bean = new EditOfflineStoreBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setId(data.getId() + "");
                bean.setStore_name(getEtToString(acStoreManagerEtStoreName));
                bean.setConsumption(getEtToString(acStoreManagerEtConsumption));
                bean.setContact(getEtToString(acStoreManagerEtContact));
                bean.setAddress(data.getAddress());
                bean.setAddress2(getEtToString(acStoreManagerEtAddress2));
                bean.setOffer("10");
                bean.setRelief(getEtToString(acStoreManagerEtRelife));
                bean.setStore_img("");
                bean.setLng(SharePerferenceUtils.getLng(this));
                bean.setLat(SharePerferenceUtils.getLat(this));
                SetSubscribe.editOfflineStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.at_location_store_tv_ruzhu:
                showItemPopwindow();
                break;
        }
    }

    private void getComment() {
        MainSubscribe.getStoreComment(data.getId()+"","5",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setComment(String result) {
        CommentResponseBean bean=GsonUtils.fromJson(result,CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acStoreManagerRv.setLayoutManager(layoutManager);
        CommentAdapter adapter=new CommentAdapter(this,data);
        acStoreManagerRv.setItemAnimator(new DefaultItemAnimator());
        acStoreManagerRv.setAdapter(adapter);
    }

    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_manage, null);
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_90));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_100));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 0, 0);

        TextView manageCenter=contentView.findViewById(R.id.pw_storeManager_tv_managerCenter);
        final TextView openStore=contentView.findViewById(R.id.pw_storeManager_tv_openStore);

        manageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(StoreManagerCenterActivity.class);
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        if(isClose){
            openStore.setText("开启店铺");
        }else{
            openStore.setText("关闭店铺");
        }
        openStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClose=!isClose;
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
