package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.KuaiDiDetailAdapter;
import com.zthx.npj.net.been.LookKDResponseBean;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KuaiDiDetailActivity extends AppCompatActivity {
    @BindView(R.id.ac_kuaidi_iv_goodsImg)
    ImageView acKuaidiIvGoodsImg;
    @BindView(R.id.ac_kuaidi_tv_goodsState)
    TextView acKuaidiTvGoodsState;
    @BindView(R.id.ac_kuaidi_rv_item)
    RecyclerView acKuaidiRvItem;

    String user_id=SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        ButterKnife.bind(this);
        getOrderDetail();
    }


    private void getOrderDetail() {
        String order_id=getIntent().getStringExtra("order_id");
        SetSubscribe.myOrderDetail(user_id,token,order_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setOrderDetail(String result) {
        MyOrderDetailResponseBean bean= GsonUtils.fromJson(result,MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data=bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acKuaidiIvGoodsImg);
        //getKuaiDiDetail(data.getExpress_name(),data.getExpress_number());
        getKuaiDiDetail("shentong","6612854271764");
    }

    private void getKuaiDiDetail(String express_name, String express_number) {
        SetSubscribe.lookKD(express_name,express_number,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setKuaiDiDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setKuaiDiDetail(String result) {
        LookKDResponseBean bean=GsonUtils.fromJson(result,LookKDResponseBean.class);
        ArrayList<LookKDResponseBean.DataBean.DataBean1> data=bean.getData().getData();
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        acKuaidiRvItem.setLayoutManager(layoutManager);
        KuaiDiDetailAdapter adapter=new KuaiDiDetailAdapter(this,data);
        acKuaidiRvItem.setAdapter(adapter);
    }
}