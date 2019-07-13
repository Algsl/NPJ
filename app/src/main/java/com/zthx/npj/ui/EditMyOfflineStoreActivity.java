package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.EditOfflineStoreBean;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class EditMyOfflineStoreActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
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

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MyOfflineStoreResponseBean.DataBean data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);
        acTitle.setText("编辑线下门店");

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
        acStoreManagerTvOffer.setText(data.getOffer());
        acStoreManagerEtRelife.setText(data.getRelief());
    }

    private String getEtToString(EditText et){return et.getText().toString().trim();}

    @OnClick(R.id.ac_storeManager_btn_ruzhu)
    public void onViewClicked() {
        EditOfflineStoreBean bean=new EditOfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(data.getId()+"");
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
        SetSubscribe.editOfflineStore(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
