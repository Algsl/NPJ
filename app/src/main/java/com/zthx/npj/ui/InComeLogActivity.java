package com.zthx.npj.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zthx.npj.R;
import com.zthx.npj.adapter.InComeLogAdapter;
import com.zthx.npj.net.been.InComeLogResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InComeLogActivity extends ActivityBase {
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

    String begin_time = "";
    String end_time = "";
    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_log);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "收益明细");
        initList();
        getIncomeLog();
    }

    private void getIncomeLog() {
        SetSubscribe.inComeLog(user_id, token,begin_time,end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                InComeLogResponseBean bean=GsonUtils.fromJson(result,InComeLogResponseBean.class);
                ArrayList<InComeLogResponseBean.DataBean> data=bean.getData();
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(InComeLogActivity.this);
                acIncomeLogRvMingxi.setLayoutManager(layoutManager);
                InComeLogAdapter adapter=new InComeLogAdapter(InComeLogActivity.this,data);
                acIncomeLogRvMingxi.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void initList() {
        for(int i=0;i<12;i++){
            options1Items1.add(2018+i+"");
            options1Items2.add(i+1+"");
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
                getIncomeLog();
            }
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }

    @OnClick(R.id.ac_incomeLog_tv_chooseTime)
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
