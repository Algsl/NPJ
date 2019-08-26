package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.TiQuAdapter;
import com.zthx.npj.net.been.TiQuResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TiQuActivity extends ActivityBase{
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_tiqu_rv_mingxi)
    RecyclerView acTiquRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_back)
    ImageView titleBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiqu);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"已提取金额");

        getTiQuInfo();
    }

    private void getTiQuInfo() {
        SetSubscribe.tiqu(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setTiQuInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setTiQuInfo(String result) {
        TiQuResponseBean bean = GsonUtils.fromJson(result, TiQuResponseBean.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acTiquRvMingxi.setLayoutManager(layoutManager);
        TiQuAdapter adapter = new TiQuAdapter(this, bean.getData());
        acTiquRvMingxi.setItemAnimator(new DefaultItemAnimator());
        acTiquRvMingxi.setAdapter(adapter);
    }
}
