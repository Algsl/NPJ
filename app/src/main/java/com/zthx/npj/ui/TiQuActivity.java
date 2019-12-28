package com.zthx.npj.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.TiQuAdapter;
import com.zthx.npj.net.been.TiQuResponseBean;
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

public class TiQuActivity extends ActivityBase {
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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    String begin_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-1";
    String end_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-30";
    @BindView(R.id.ac_incomeLog_tv_chooseTime)
    TextView acIncomeLogTvChooseTime;
    @BindView(R.id.ac_incomeLoge_ll)
    LinearLayout acIncomeLogeLl;
    @BindView(R.id.ac_incomeLog_tv_ioMoney)
    TextView acIncomeLogTvIoMoney;
    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiqu);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "已提取金额");

        initList();
        acIncomeLogTvChooseTime.setText(new SimpleDateFormat("yyyy月MM日").format(new Date()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getTiQuInfo();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

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
                //showToast(errorMsg);
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
                getTiQuInfo();
            }
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }

    private void setTiQuInfo(String result) {
        TiQuResponseBean bean = GsonUtils.fromJson(result, TiQuResponseBean.class);
        if (bean.getData().size() == 0 || bean.getData() == null) {
            acTiquRvMingxi.setVisibility(View.GONE);
        } else {
            acTiquRvMingxi.setVisibility(View.VISIBLE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acTiquRvMingxi.setLayoutManager(layoutManager);
        TiQuAdapter adapter = new TiQuAdapter(this, bean.getData());
        acTiquRvMingxi.setItemAnimator(new DefaultItemAnimator());
        acTiquRvMingxi.setAdapter(adapter);
    }

    @OnClick(R.id.ac_incomeLoge_ll)
    public void onViewClicked() {
        showCityPicker();
    }
}
