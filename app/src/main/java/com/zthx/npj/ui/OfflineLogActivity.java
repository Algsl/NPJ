package com.zthx.npj.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.InComeLogAdapter;
import com.zthx.npj.adapter.OfflineLogAdapter;
import com.zthx.npj.net.been.InComeLogResponseBean;
import com.zthx.npj.net.been.OfflineLogBean;
import com.zthx.npj.net.been.OfflineLogResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfflineLogActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.ac_incomeLog_tv_chooseTime)
    TextView acIncomeLogTvChooseTime;
    @BindView(R.id.ac_incomeLog_tv_ioMoney)
    TextView acIncomeLogTvIoMoney;
    @BindView(R.id.ac_incomeLog_rv_mingxi)
    RecyclerView acIncomeLogRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    String begin_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-1";
    String end_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-30";
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_incomeLoge_ll)
    LinearLayout acIncomeLogeLl;
    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_log);
        ButterKnife.bind(this);

        acIncomeLogTvChooseTime.setText(new SimpleDateFormat("yyyy月MM日").format(new Date()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getOfflineLog();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        back(titleBack);
        changeTitle(acTitle, "线下门店收益");
        initList();
        getOfflineLog();
    }

    private void getOfflineLog() {
        OfflineLogBean bean=new OfflineLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType("9");
        bean.setBegin_time(begin_time);
        bean.setEnd_time(end_time);
        SetSubscribe.offlineLog(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OfflineLogResponseBean bean=GsonUtils.fromJson(result,OfflineLogResponseBean.class);
                ArrayList<OfflineLogResponseBean.DataBean.Mingxi> data=bean.getData().getMingxi();
                acIncomeLogTvIoMoney.setText("当月收益 ￥"+bean.getData().getShouyi());
                if (data.size() == 0 || data == null) {
                    acIncomeLogRvMingxi.setVisibility(View.GONE);
                } else {
                    acIncomeLogRvMingxi.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OfflineLogActivity.this);
                acIncomeLogRvMingxi.setLayoutManager(layoutManager);
                OfflineLogAdapter adapter = new OfflineLogAdapter(OfflineLogActivity.this, data);
                acIncomeLogRvMingxi.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    private void initList() {
        for (int i = 0; i < 12; i++) {
            options1Items1.add(2019 + i + "");
            options1Items2.add(i + 1 + "");
        }
    }

    private void showCityPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options, View v) {
                //返回的分别是三个级别的选中位置
                //acMyWalletTvChooseTime.setText();
                acIncomeLogTvChooseTime.setText(options1Items1.get(options1) + "年" + options1Items2.get(options2) + "月");
                begin_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-1";
                end_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-30";
                getOfflineLog();
            }
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }

    @OnClick(R.id.ac_incomeLoge_ll)
    public void onViewClicked() {
        showCityPicker();
    }

}
