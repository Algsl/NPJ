package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.LocalSpokesmanAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalSpokesmanActivity extends ActivityBase {

    @BindView(R.id.at_local_spokesman_rv)
    RecyclerView atLocalSpokesmanRv;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_spokesman);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"附近代言人");
        requestLocalSpokesman();


        List<CommentGoodsBeen> list = new ArrayList<>();


    }

    private void requestLocalSpokesman() {
        LoginSubscribe.getLocalSpokesman(SharePerferenceUtils.getLat(this),SharePerferenceUtils.getLng(this), new OnSuccessAndFaultSub(
                new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        LocalSpokesmanResponseBean bean = GsonUtils.fromJson(result, LocalSpokesmanResponseBean.class);
                        final ArrayList<LocalSpokesmanResponseBean.LocalSpokesmanDetail> list = bean.getList();
                        if (list != null && list.size() > 0) {
                            LinearLayoutManager manager = new LinearLayoutManager(LocalSpokesmanActivity.this, LinearLayoutManager.VERTICAL, false);
                            atLocalSpokesmanRv.setLayoutManager(manager);
                            LocalSpokesmanAdapter mAdapter = new LocalSpokesmanAdapter(LocalSpokesmanActivity.this, list);
                            mAdapter.setOnItemClickListener(new LocalSpokesmanAdapter.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                }

                                @Override
                                public void onChooseClick(int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra("code", list.get(position).getMobile());
                                    setResult(100,intent);
                                    finish();
                                }
                            });
                            atLocalSpokesmanRv.setItemAnimator(new DefaultItemAnimator());
                            atLocalSpokesmanRv.setAdapter(mAdapter);
                        }

                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
    }
}
