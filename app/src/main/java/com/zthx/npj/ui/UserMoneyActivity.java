package com.zthx.npj.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.zthx.npj.adapter.UserMoneyAdapter;
import com.zthx.npj.net.been.UserMoneyResponseBean;
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

public class UserMoneyActivity extends ActivityBase {

    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.ac_myWallet_tv_chooseTime)
    TextView acMyWalletTvChooseTime;
    @BindView(R.id.ac_myWallet_tv_ioMoney)
    TextView acMyWalletTvIoMoney;
    @BindView(R.id.ac_myWallet_rv_mingxi)
    RecyclerView acMyWalletRvMingxi;


    String type = "";
    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);

    String begin_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-1";
    String end_time = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-30";
    @BindView(R.id.ac_vipJL_tv_allType)
    TextView acVipJLTvAllType;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_myWallet_ll)
    LinearLayout acMyWalletLl;

    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet_income);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "钱包明细");

        acMyWalletTvChooseTime.setText(new SimpleDateFormat("yyyy年MM月").format(new Date()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getUserMoney();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
        getUserMoney();
        initList();
    }

    private void getUserMoney() {
        SetSubscribe.userMoney(user_id, token, type, begin_time, end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: " + result);
                setUserMoney(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.ac_myWallet_ll, R.id.ac_vipJL_tv_allType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myWallet_ll:
                showCityPicker();
                break;
            case R.id.ac_vipJL_tv_allType:
                showSingleBottomDialog();
                break;
        }
    }

    private void setUserMoney(String result) {
        Log.e("测试", "setUserMoney: "+result );
        UserMoneyResponseBean bean = GsonUtils.fromJson(result, UserMoneyResponseBean.class);
        UserMoneyResponseBean.DataBean data = bean.getData();
        acMyWalletTvIoMoney.setText("充值" + data.getRecharge() + "元，提现" + data.getWithdraw() + "元");
        ArrayList<UserMoneyResponseBean.DataBean.MingXi> mingXis = data.getMingxi();
        if (mingXis.size() == 0 || mingXis == null) {
            acMyWalletRvMingxi.setVisibility(View.GONE);
        } else {
            acMyWalletRvMingxi.setVisibility(View.VISIBLE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acMyWalletRvMingxi.setLayoutManager(layoutManager);
        UserMoneyAdapter adapter = new UserMoneyAdapter(this, mingXis);
        acMyWalletRvMingxi.setItemAnimator(new DefaultItemAnimator());
        acMyWalletRvMingxi.setAdapter(adapter);
    }

    private void showSingleBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_user_money_layout, null);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        TextView all, charge, widthdraw, pay, system, cancel;
        all = view.findViewById(R.id.dl_userMoney_tv_all);
        charge = view.findViewById(R.id.dl_userMoney_tv_charge);
        widthdraw = view.findViewById(R.id.dl_userMoney_tv_widthdraw);
        pay = view.findViewById(R.id.dl_userMoney_tv_pay);
        system = view.findViewById(R.id.dl_userMoney_tv_system);
        cancel = view.findViewById(R.id.dl_userMoney_tv_cancel);
        final TextView[] tvs = {all, charge, widthdraw, pay, system};
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
        }
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "";
                acVipJLTvAllType.setText("全部");
                getUserMoney();
                dialog.dismiss();
            }
        });
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                acVipJLTvAllType.setText("充值");
                getUserMoney();
                dialog.dismiss();
            }
        });
        widthdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                acVipJLTvAllType.setText("提现");
                getUserMoney();
                dialog.dismiss();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "3";
                acVipJLTvAllType.setText("支付");
                getUserMoney();
                dialog.dismiss();
            }
        });
        system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "4";
                acVipJLTvAllType.setText("系统");
                getUserMoney();
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

    private void initList() {
        for (int i = 0; i < 12; i++) {
            options1Items1.add(2018 + i + "");
            options1Items2.add(i + 1 + "");
        }
    }

    private void showCityPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options, View v) {
                //返回的分别是三个级别的选中位置
                //acMyWalletTvChooseTime.setText();
                acMyWalletTvChooseTime.setText(options1Items1.get(options1) + "年" + options1Items2.get(options2) + "月");
                begin_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-1";
                end_time = options1Items1.get(options1) + "-" + options1Items2.get(options2) + "-30";
                getUserMoney();
            }
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }

}
