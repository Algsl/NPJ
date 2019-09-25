package com.zthx.npj.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.zthx.npj.adapter.VipJLAdapter;
import com.zthx.npj.net.been.VipJLResponseBean;
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

public class VipJLActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.ac_vipJL_tv_chooseTime)
    TextView acVipJLTvChooseTime;
    @BindView(R.id.ac_vipJL_tv_ioMoney)
    TextView acVipJLTvIoMoney;
    @BindView(R.id.ac_vipJL_rv_mingxi)
    RecyclerView acVipJLRvMingxi;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String type = "";
    String begin_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-1";
    String end_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-30";
    @BindView(R.id.ac_vipJL_tv_allType)
    TextView acVipJLTvAllType;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_vipJL_ll)
    LinearLayout acVipJLLl;

    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_jl);
        ButterKnife.bind(this);

        acVipJLTvChooseTime.setText(new SimpleDateFormat("yyyy年MM月").format(new Date()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getVipJLInfo();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        initList();
        getVipJLInfo();
        back(titleBack);
        changeTitle(acTitle, "代言奖励");
    }

    private void getVipJLInfo() {
        SetSubscribe.vipJL(user_id, token, type, begin_time, end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setVipJL(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setVipJL(String result) {
        VipJLResponseBean bean = GsonUtils.fromJson(result, VipJLResponseBean.class);
        if (bean.getData().size() == 0 || bean.getData() == null) {
            acVipJLRvMingxi.setVisibility(View.GONE);
        } else {
            acVipJLRvMingxi.setVisibility(View.VISIBLE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acVipJLRvMingxi.setLayoutManager(layoutManager);
        VipJLAdapter adapter = new VipJLAdapter(this, bean.getData());
        acVipJLRvMingxi.setItemAnimator(new DefaultItemAnimator());
        acVipJLRvMingxi.setAdapter(adapter);
    }

    private void showSingleBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_vipjl_layout, null);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        TextView all, city, dInvation, iInvation, dSell, iSell, store, other, cancel;
        all = view.findViewById(R.id.dl_vipjl_tv_all);
        city = view.findViewById(R.id.dl_vipjl_tv_city);
        dInvation = view.findViewById(R.id.dl_vipjl_tv_dInvitation);
        iInvation = view.findViewById(R.id.dl_vipjl_tv_iInvitation);
        dSell = view.findViewById(R.id.dl_vipjl_tv_dSell);
        iSell = view.findViewById(R.id.dl_vipjl_tv_iSell);
        store = view.findViewById(R.id.dl_vipjl_tv_store);
        other = view.findViewById(R.id.dl_vipjl_tv_other);
        cancel = view.findViewById(R.id.dl_vipjl_tv_cancel);
        store.setVisibility(View.GONE);
        final TextView[] tvs = {all, city, dInvation, iInvation, dSell, iSell, other};
        switch (type) {
            case "":
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
            case "4":
                changeColor(tvs, 4);
                break;
            case "5":
                changeColor(tvs, 5);
                break;
            case "6":
                changeColor(tvs, 6);
                break;
        }
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "";
                acVipJLTvAllType.setText("全部");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                acVipJLTvAllType.setText("城市分红奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        dInvation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                acVipJLTvAllType.setText("直接邀请奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        iInvation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "3";
                acVipJLTvAllType.setText("间接邀请奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        dSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "4";
                acVipJLTvAllType.setText("直接销售奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        iSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "5";
                acVipJLTvAllType.setText("间接销售奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "6";
                acVipJLTvAllType.setText("其他代言人奖励");
                getVipJLInfo();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void changeColor(TextView[] tvs, int v) {
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

    @OnClick({R.id.ac_vipJL_tv_allType, R.id.ac_vipJL_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_vipJL_tv_allType:
                showSingleBottomDialog();
                break;
            case R.id.ac_vipJL_ll:
                showDataPicker();
                break;
        }
    }

    private void initList() {
        for (int i = 0; i < 12; i++) {
            options1Items1.add(2018 + i + "");
            options1Items2.add(i + 1 + "");
        }
    }

    private void showDataPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options, View v) {
                //返回的分别是三个级别的选中位置
                //acMyWalletTvChooseTime.setText();
                acVipJLTvChooseTime.setText(options1Items1.get(options1) + "年" + options1Items2.get(options2) + "月");
                begin_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-1";
                end_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-30";
                getVipJLInfo();
            }
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }
}
