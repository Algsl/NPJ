package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.PreSellAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.PreSellResponseBean;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreSellActivity extends AppCompatActivity {

    @BindView(R.id.at_pre_sell_iv)
    ImageView atPreSellIv;
    @BindView(R.id.at_pre_sell_rv)
    RecyclerView atPreSellRv;
    @BindView(R.id.at_pre_sell_tv_ing)
    TextView atPreSellTvIng;
    @BindView(R.id.at_pre_sell_tv_ed)
    TextView atPreSellTvEd;
    @BindView(R.id.at_pre_sell_rv2)
    RecyclerView atPreSellRv2;

    private boolean isIng = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sell);
        ButterKnife.bind(this);

        getPreSellList("0");
    }

    private void getPreSellList(final String type) {
        PreSellSubscribe.getPreSell(type, new OnSuccessAndFaultSub(
                new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        PreSellResponseBean preSellResponseBean = GsonUtils.fromJson(result, PreSellResponseBean.class);
                        final ArrayList<PreSellResponseBean.DataBean> data = preSellResponseBean.getData();
                        LinearLayoutManager ll = new LinearLayoutManager(PreSellActivity.this, LinearLayoutManager.VERTICAL, false);
                        if ("0".equals(type)) {
                            atPreSellRv2.setVisibility(View.GONE);
                            atPreSellRv.setVisibility(View.VISIBLE);
                            setView(data, ll, atPreSellRv);
                        } else {
                            atPreSellRv2.setVisibility(View.VISIBLE);
                            atPreSellRv.setVisibility(View.GONE);
                            setView(data, ll, atPreSellRv2);
                        }


                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }, this));
    }

    private void setView(final ArrayList<PreSellResponseBean.DataBean> data, LinearLayoutManager ll, RecyclerView rv) {
        rv.setLayoutManager(ll);
        PreSellAdapter adapter = new PreSellAdapter(PreSellActivity.this, data);
        adapter.setOnItemClickListener(new PreSellAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PreSellActivity.this, GoodsDetailActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);

            }
        });
        rv.setAdapter(adapter);
    }

    @OnClick({R.id.at_pre_sell_tv_ing, R.id.at_pre_sell_tv_ed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_pre_sell_tv_ing:
                if (!isIng) {
                    atPreSellTvIng.setTextColor(getResources().getColor(android.R.color.white));
                    atPreSellTvIng.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    atPreSellTvEd.setTextColor(getResources().getColor(R.color.text3));
                    atPreSellTvEd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isIng = true;
                    getPreSellList("0");
                }
                break;
            case R.id.at_pre_sell_tv_ed:
                if (isIng) {
                    atPreSellTvIng.setTextColor(getResources().getColor(R.color.text3));
                    atPreSellTvIng.setBackgroundColor(getResources().getColor(android.R.color.white));
                    atPreSellTvEd.setTextColor(getResources().getColor(android.R.color.white));
                    atPreSellTvEd.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    isIng = false;
                    getPreSellList("1");
                }
                break;
        }
    }
}
