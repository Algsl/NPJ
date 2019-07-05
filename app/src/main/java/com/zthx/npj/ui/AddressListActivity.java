package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.AddressAdapter;
import com.zthx.npj.net.been.AddressListResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity
        extends AppCompatActivity
{
    @BindView(R.id.ac_address_addAddress)
    Button       acAddressAddAddress;
    @BindView(R.id.ac_title)
    TextView     acTitle;
    @BindView(R.id.ac_address_recycle)
    RecyclerView mAcAddressRecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        ButterKnife.bind(this);
        acTitle.setText("选择收货地址");
        getAddressList();
    }

    private void getAddressList() {
        String user_id = SharePerferenceUtils.getUserId(this);
        //String token=SharePerferenceUtils.getToken(this);
        String token = "1f27405d66fa30be262785b395b622a6";
        SetSubscribe.getAddressList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setAddressList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setAddressList(String result) {
        AddressListResponseBean bean= GsonUtils.fromJson(result,AddressListResponseBean.class);
        ArrayList<AddressListResponseBean.DataBean> dataList=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mAcAddressRecycle.setLayoutManager(layoutManager);
        AddressAdapter addressAdapter=new AddressAdapter(this,dataList);
        addressAdapter.setItemClickListener(new AddressAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(AddressListActivity.this,"点击事件",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onEditClick(int position) {
                Toast.makeText(AddressListActivity.this,"编辑",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(AddressListActivity.this,"删除",Toast.LENGTH_LONG).show();
            }
        });
        mAcAddressRecycle.setAdapter(addressAdapter);
    }

    @OnClick(R.id.ac_address_addAddress)
    public void onViewClicked() {
        startActivity(new Intent(this, AddAddressActivity.class));
    }
}
