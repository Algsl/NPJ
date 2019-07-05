package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity {
    private String is_default="0";

    @BindView(R.id.ac_title)
    TextView  mAcTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView  mAtLocationStoreTvRuzhu;
    @BindView(R.id.ac_address_et_consignee)
    EditText  mAcAddressEtConsignee;
    @BindView(R.id.ac_address_et_mobile)
    EditText  mAcAddressEtMobile;
    @BindView(R.id.ac_address_et_address)
    EditText  mAcAddressEtAddress;
    @BindView(R.id.ac_address_et_houseNumber)
    EditText  mAcAddressEtHouseNumber;
    @BindView(R.id.ac_address_iv_isDefault)
    ImageView mAcAddressIvIsDefault;
    @BindView(R.id.ac_address_btn_save)
    Button    mAcAddressBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        mAcTitle.setText("添加地址");

        mAcAddressIvIsDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAcAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
                is_default="1";
            }
        });
    }


    @OnClick(R.id.ac_address_btn_save)
    public void onViewClicked() {
        String user_id= SharePerferenceUtils.getUserId(this);
        String token="1f27405d66fa30be262785b395b622a6";
        //String token=SharePerferenceUtils.getToken(this);
        String consignee    = mAcAddressEtConsignee.getText().toString();
        String mobile       = mAcAddressEtMobile.getText().toString();
        String address      = mAcAddressEtAddress.getText().toString();
        String house_number = mAcAddressEtHouseNumber.getText().toString();
        SetSubscribe.addAddress(user_id,token,consignee,mobile,address,house_number,is_default,
                                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
