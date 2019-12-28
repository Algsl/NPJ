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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.OfflineLogAdapter;
import com.zthx.npj.adapter.SupplyLogAdapter;
import com.zthx.npj.net.been.OfflineLogBean;
import com.zthx.npj.net.been.OfflineLogResponseBean;
import com.zthx.npj.net.been.SupplyLogBean;
import com.zthx.npj.net.been.SupplyLogResponseBean;
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

public class SupplyLogActivity extends ActivityBase {
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
                getSupplyLog();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        back(titleBack);
        changeTitle(acTitle, "供应商品收益");
        initList();
        getSupplyLog();
    }

    private void getSupplyLog() {
        SupplyLogBean bean=new SupplyLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType("10");
        //bean.setEnd_time(end_time);
        SetSubscribe.supplyLog(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: "+result );
                SupplyLogResponseBean bean=GsonUtils.fromJson(result,SupplyLogResponseBean.class);
                ArrayList<SupplyLogResponseBean.DataBean.Mingxi> data=bean.getData().getMingxi();
                acIncomeLogTvIoMoney.setText("当月收益 ￥"+bean.getData().getShouyi());
                if (data.size() == 0 || data == null) {
                    acIncomeLogRvMingxi.setVisibility(View.GONE);
                } else {
                    acIncomeLogRvMingxi.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SupplyLogActivity.this);
                acIncomeLogRvMingxi.setLayoutManager(layoutManager);
                SupplyLogAdapter adapter = new SupplyLogAdapter(SupplyLogActivity.this, data);
                acIncomeLogRvMingxi.setAdapter(adapter);
                /*InComeLogResponseBean bean = GsonUtils.fromJson(result, InComeLogResponseBean.class);
                ArrayList<InComeLogResponseBean.DataBean> data = bean.getData();
                if (data.size() == 0 || data == null) {
                    acIncomeLogRvMingxi.setVisibility(View.GONE);
                } else {
                    acIncomeLogRvMingxi.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OfflineLogActivity.this);
                acIncomeLogRvMingxi.setLayoutManager(layoutManager);
                InComeLogAdapter adapter = new InComeLogAdapter(OfflineLogActivity.this, data);
                acIncomeLogRvMingxi.setAdapter(adapter);*/
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
                getSupplyLog();
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

    /*private void showSingleBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_income_layout, null);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView all, store,vip,money,cancel;
        all=view.findViewById(R.id.dl_income_tv_all);
        store=view.findViewById(R.id.dl_income_tv_store);
        vip=view.findViewById(R.id.dl_income_tv_vip);
        money=view.findViewById(R.id.dl_income_tv_money);
        cancel=view.findViewById(R.id.dl_income_tv_cancel);
        final TextView[] tvs = {all, store,vip,money};
        switch (type) {
            case "100":
                changeColor(tvs, 0);
                break;
            case "1":
                changeColor(tvs, 1);
                break;
            case "2":
                changeColor(tvs, 2);
                break;
            case "3":
                changeColor(tvs, 3);
                break;
        }
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "100";
                acVipJLTvAllType.setText("全部明细");
                getIncomeLog();
                dialog.dismiss();
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                acVipJLTvAllType.setText("店铺明细");
                getIncomeLog();
                dialog.dismiss();
            }
        });
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                acVipJLTvAllType.setText("代言明细");
                getIncomeLog();
                dialog.dismiss();
            }
        });
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "3";
                acVipJLTvAllType.setText("提取金额明细");
                getIncomeLog();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }*/

    /*public void changeColor(TextView[] tvs, int v) {
        for (int i = 0; i < tvs.length; i++) {
            if (i == v) {
                tvs[i].setBackgroundColor(getResources().getColor(R.color.app_theme));
                tvs[i].setTextColor(getResources().getColor(R.color.white));
            } else {
                tvs[i].setBackgroundColor(getResources().getColor(R.color.white));
                tvs[i].setTextColor(getResources().getColor(R.color.text3));
            }
        }
    }

    @OnClick({R.id.ac_vipJL_tv_allType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_vipJL_tv_allType:
                showSingleBottomDialog();
                break;
        }
    }*/
}
