package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AddressInfoResponseBean;
import com.zthx.npj.net.been.EditAddressBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAddressActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_editAddress_et_consignee)
    EditText acEditAddressEtConsignee;
    @BindView(R.id.ac_editAddress_et_mobile)
    EditText acEditAddressEtMobile;
    @BindView(R.id.ac_editAddress_et_address)
    EditText acEditAddressEtAddress;
    @BindView(R.id.ac_editAddress_et_houseNumber)
    EditText acEditAddressEtHouseNumber;
    @BindView(R.id.ac_editAddress_iv_isDefault)
    ImageView acEditAddressIvIsDefault;
    @BindView(R.id.ac_editAddress_btn_save)
    Button acEditAddressBtnSave;

    private boolean flag=false;
    private String isDefault="0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        getAddressInfo();
    }

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String address_id = getIntent().getStringExtra("address_id");
    private void getAddressInfo() {
        SetSubscribe.getAddressInfo(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setAddressInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setAddressInfo(String result) {
        AddressInfoResponseBean bean = GsonUtils.fromJson(result, AddressInfoResponseBean.class);
        AddressInfoResponseBean.DataBean data = bean.getData();
        acEditAddressEtConsignee.setText(data.getConsignee());
        acEditAddressEtMobile.setText(data.getMobile());
        acEditAddressEtAddress.setText(data.getAddress());
        acEditAddressEtHouseNumber.setText(data.getHouse_number());
        if (data.getIs_default() == 0) {
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_not_selector);
        } else {
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
        }
    }

    public String getEtString(EditText et){
        return et.getText().toString().trim();
    }
    @OnClick({R.id.ac_editAddress_iv_isDefault, R.id.ac_editAddress_btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_editAddress_iv_isDefault:
                toggle();
                break;
            case R.id.ac_editAddress_btn_save:
                EditAddressBean bean=new EditAddressBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setAddress_id(address_id);
                bean.setConsignee(getEtString(acEditAddressEtConsignee));
                bean.setAddress(getEtString(acEditAddressEtAddress));
                bean.setMobile(getEtString(acEditAddressEtMobile));
                bean.setHouse_number(getEtString(acEditAddressEtHouseNumber));
                bean.setIs_default(isDefault);
                SetSubscribe.editAddressInfo(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }

    private void toggle() {
        flag=!flag;
        if(flag){
            isDefault="1";
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
            SetSubscribe.defaultAddress(user_id,token,address_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }else{
            isDefault="0";
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }
}
