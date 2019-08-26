package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.StoreDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayToStoreActivity extends ActivityBase {

    @BindView(R.id.at_pay_to_store_iv_pic)
    ImageView atPayToStoreIvPic;
    @BindView(R.id.ac_payToStore_tv_storeName)
    TextView acPayToStoreTvStoreName;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_to_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"付款");

        String store_id = getIntent().getStringExtra("key0");
        getStoreDetail(store_id);
    }

    private void getStoreDetail(String store_id) {
        MainSubscribe.getStoreDetail(store_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setStoreDetail(String result) {
        StoreDetailResponseBean bean = GsonUtils.fromJson(result, StoreDetailResponseBean.class);
        //Glide.with(this).load(Uri.parse(bean.getData().getStore_img().get(0))).into(atPayToStoreIvPic);
        Glide.with(this).load(Uri.parse(bean.getData().getStore_img().get(0))).into(atPayToStoreIvPic);
        acPayToStoreTvStoreName.setText(bean.getData().getStore_name());
    }
}
