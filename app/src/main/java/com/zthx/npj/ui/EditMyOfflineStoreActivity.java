package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.EditOfflineStoreBean;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class EditMyOfflineStoreActivity extends ActivityBase {
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
    @BindView(R.id.ac_store_manager_rv)
    RecyclerView acStoreManagerRv;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MyOfflineStoreResponseBean.DataBean data;
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
        changeRightText(atLocationStoreTvRuzhu,"管理",StoreManagerCenterActivity.class,null);

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
        getComment();
    }

    private String getEtToString(EditText et) {
        return et.getText().toString().trim();
    }

    @OnClick(R.id.ac_storeManager_btn_ruzhu)
    public void onViewClicked() {
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

            }
        }));
    }

    private void getComment() {
        MainSubscribe.getStoreComment(data.getId()+"","5",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setComment(String result) {
        String test="{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 50,\n" +
                "            \"user_id\": 25,\n" +
                "            \"goods_id\": 1,\n" +
                "            \"store_id\": 23,\n" +
                "            \"content\": \"商品质量非常好，期待下次合作\",\n" +
                "            \"img\": [\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\",\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\"\n" +
                "            ],\n" +
                "            \"status\": 0,\n" +
                "            \"create_time\": 1556095903,\n" +
                "            \"type\": 1,\n" +
                "            \"goods_star\": 5,\n" +
                "            \"logistics_star\": 5,\n" +
                "            \"service_star\": 5,\n" +
                "            \"nick_name\": \"用户15853102073\",\n" +
                "            \"head_img\": \"http://app.npj-vip.com/public/upload/20190711/80d23137c98abfb897db59eb918b892e.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"msg\": \"加载成功\"\n" +
                "}";
        CommentResponseBean bean=GsonUtils.fromJson(test,CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acStoreManagerRv.setLayoutManager(layoutManager);
        CommentAdapter adapter=new CommentAdapter(this,data);
        acStoreManagerRv.setAdapter(adapter);
    }
}
