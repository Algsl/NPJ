package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.BaojiaUserListAdapter;
import com.zthx.npj.net.been.BaojiaUserListResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaojiaUserListActivity extends AppCompatActivity {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_baojia_userlist_rv)
    RecyclerView acBaojiaUserlistRv;

    String user_id=SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojia_userlist);
        ButterKnife.bind(this);

        titleThemeTitle.setText("报价商家列表");
        titleThemeTvRight.setVisibility(View.VISIBLE);
        titleThemeTvRight.setText("发布供求");

        getBaojiaUserList();
    }

    private void getBaojiaUserList() {
        String id=getIntent().getStringExtra("user_id");
        SetSubscribe.baojiaUserList(user_id,token,id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setBaojiaUserList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setBaojiaUserList(String result) {
        BaojiaUserListResponseBean bean=GsonUtils.fromJson(result,BaojiaUserListResponseBean.class);
        final ArrayList<BaojiaUserListResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acBaojiaUserlistRv.setLayoutManager(layoutManager);
        BaojiaUserListAdapter adapter=new BaojiaUserListAdapter(this,data);
        acBaojiaUserlistRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaojiaUserListAdapter.ItemClickListener() {
            @Override
            public void onSeeClick(int position) {
                Intent intent=new Intent(BaojiaUserListActivity.this,BaojiaUserDetailActivity.class);
                intent.putExtra("id",data.get(position).getId()+"");
                intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
                startActivity(intent);
            }
        });
    }
}
