package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class AddressListActivity extends ActivityBase {

    @BindView(R.id.ac_address_addAddress)
    Button acAddressAddAddress;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_address_recycle)
    RecyclerView mAcAddressRecycle;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_back)
    ImageView titleBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresslist);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"选择收货地址");

        getAddressList();
    }

    private void getAddressList() {

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
        AddressListResponseBean bean = GsonUtils.fromJson(result, AddressListResponseBean.class);
        final ArrayList<AddressListResponseBean.DataBean> dataList = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAcAddressRecycle.setLayoutManager(layoutManager);
        AddressAdapter addressAdapter = new AddressAdapter(this, dataList);
        addressAdapter.setItemClickListener(new AddressAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String key0=getIntent().getStringExtra("key0");
                /*if(key0.equals("1")){
                    openActivity(SupplyBillActivity.class,dataList.get(position).getId()+"");
                }*/
                Intent intent=new Intent();
                intent.putExtra("address_id",dataList.get(position).getId()+"");
                intent.putExtra("address",dataList.get(position).getAlladdress());
                setResult(0,intent);
                finish();
            }

            @Override
            public void onEditClick(int position) {
                openActivity(EditAddressActivity.class,dataList.get(position).getId() + "");
            }

            @Override
            public void onDeleteClick(int position) {
                String address_id = dataList.get(position).getId() + "";
                SetSubscribe.delAddress(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getAddressList();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        mAcAddressRecycle.setAdapter(addressAdapter);
    }

    @OnClick(R.id.ac_address_addAddress)
    public void onViewClicked() {
        startActivity(new Intent(this, AddAddressActivity.class));
    }
}
