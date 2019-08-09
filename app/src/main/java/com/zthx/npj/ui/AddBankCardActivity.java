package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AddBankCardBean;
import com.zthx.npj.net.been.BankResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddBankCardActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_addBankCard_et_cardholder)
    EditText acAddBankCardEtCardholder;
    @BindView(R.id.ac_addBankCard_et_cardNumber)
    EditText acAddBankCardEtCardNumber;
    @BindView(R.id.ac_addBankCard_btn_add)
    Button acAddBankCardBtnAdd;
    @BindView(R.id.ac_addBankCard_spinner)
    Spinner acAddBankCardSpinner;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
     String bank_id="1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"添加银行卡");

        getBankList();
    }



    private void getBankList() {
        SetSubscribe.bank(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setBankList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setBankList(String result) {

        BankResponseBean bean=GsonUtils.fromJson(result,BankResponseBean.class);
        ArrayList<BankResponseBean.DataBean> data=bean.getData();
        String[] items=new String[data.size()];
        for(int i=0;i<data.size();i++){
            items[i]=data.get(i).getBank_name();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acAddBankCardSpinner.setAdapter(adapter);
        acAddBankCardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bank_id=(i+1)+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.ac_addBankCard_btn_add)
    public void onViewClicked() {
        AddBankCardBean bean = new AddBankCardBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCardholder(acAddBankCardEtCardholder.getText().toString().trim());
        bean.setCard_number(acAddBankCardEtCardNumber.getText().toString().trim());
        bean.setBank_id(bank_id);
        SetSubscribe.addBankCard(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
