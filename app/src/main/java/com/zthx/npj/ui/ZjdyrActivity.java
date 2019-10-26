package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.adapter.UserOneAdapter;
import com.zthx.npj.adapter.UserTwoAdapter;
import com.zthx.npj.net.been.UserOneResponseBean;
import com.zthx.npj.net.been.UserTwoResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZjdyrActivity extends ActivityBase {
    @BindView(R.id.ac_zjdyr_iv_back)
    ImageView acZjdyrIvBack;
    @BindView(R.id.ac_zjdyr_tv_title)
    TextView acZjdyrTvTitle;
    @BindView(R.id.ac_zjdyr_tv_content)
    TextView acZjdyrTvContent;
    @BindView(R.id.ac_zjdyr_rv)
    RecyclerView acZjdyrRv;

    private String type="";
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjdyr);
        ButterKnife.bind(this);

        back(acZjdyrIvBack);
        type=getIntent().getStringExtra("key0");
        if(type.equals("1")){
            acZjdyrTvTitle.setText("直推代言人");
            acZjdyrTvContent.setText("您每推荐一名VIP代言人，平台奖励给您100元现金，您将获得商品利润49%的佣金，可实时提现。");
            getZtDYR();
        }else{
            acZjdyrTvTitle.setText("间推代言人");
            acZjdyrTvContent.setText("您推荐的用户每推荐一名VIP会员，平台奖励您30元现金，被推荐者购物，您将获得商品利润14%的佣金，可实时提现。");
            getJtDYR();
        }

    }

    private void getJtDYR() {
        SetSubscribe.userTwo(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserTwoResponseBean bean=GsonUtils.fromJson(result,UserTwoResponseBean.class);
                ArrayList<UserTwoResponseBean.DataBean> data=bean.getData();
                if(data==null || data.size()==0){
                    acZjdyrRv.setVisibility(View.GONE);
                }else{
                    acZjdyrRv.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ZjdyrActivity.this);
                acZjdyrRv.setLayoutManager(layoutManager);
                UserTwoAdapter adapter=new UserTwoAdapter(ZjdyrActivity.this,data);
                acZjdyrRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getZtDYR() {
        SetSubscribe.userOne(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserOneResponseBean bean=GsonUtils.fromJson(result,UserOneResponseBean.class);
                ArrayList<UserOneResponseBean.DataBean> data=bean.getData();
                if(data==null || data.size()==0){
                    acZjdyrRv.setVisibility(View.GONE);
                }else{
                    acZjdyrRv.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ZjdyrActivity.this);
                acZjdyrRv.setLayoutManager(layoutManager);
                UserOneAdapter adapter=new UserOneAdapter(ZjdyrActivity.this,data);
                acZjdyrRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
