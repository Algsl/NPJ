package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.GradViewAdapter;
import com.zthx.npj.net.been.BankCardResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SYWithDrawActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_withdraw_tv_money)
    TextView acWithdrawTvMoney;
    @BindView(R.id.ac_withdraw_et_drawMoney)
    EditText acWithdrawEtDrawMoney;
    @BindView(R.id.ac_withdraw_btn_allMoney)
    Button acWithdrawBtnAllMoney;
    @BindView(R.id.ac_withdraw_btn_draw)
    Button acWithdrawBtnDraw;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);

    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sywithdraw);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "收益提取");

        String balance = getIntent().getStringExtra("key0");
        acWithdrawTvMoney.setText(balance);
    }

    @OnClick({R.id.ac_withdraw_btn_allMoney, R.id.ac_withdraw_btn_draw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_withdraw_btn_allMoney:
                acWithdrawEtDrawMoney.setText(acWithdrawTvMoney.getText().toString().trim());
                break;
            case R.id.ac_withdraw_btn_draw:
                if (acWithdrawEtDrawMoney.getText().toString().equals("")) {
                    showToast("请输入提取金额");
                } else if(Double.parseDouble(acWithdrawEtDrawMoney.getText().toString().trim())<10){
                   showToast("提取收益金额至少10元");
                }else{
                    //showPublishPopwindow();
                    String money = acWithdrawEtDrawMoney.getText().toString();
                    SetSubscribe.tqIncome(user_id, token,  money, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("收益提取成功");
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == 0) {

                }
                break;
        }
    }

    public void showPublishPopwindow() {
        position = 0;
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_mima, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
        // 设置PopupWindow的背景
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        GridView gv = contentView.findViewById(R.id.pw_mima_gv);
        final String[] strs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "确定", "0", "删除"};
        GradViewAdapter adapter = new GradViewAdapter(this, strs);
        gv.setAdapter(adapter);

        TextView tv1 = contentView.findViewById(R.id.tv1);
        TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv3 = contentView.findViewById(R.id.tv3);
        TextView tv4 = contentView.findViewById(R.id.tv4);
        TextView tv5 = contentView.findViewById(R.id.tv5);
        TextView tv6 = contentView.findViewById(R.id.tv6);
        final TextView[] tvs = new TextView[]{tv1, tv2, tv3, tv4, tv5, tv6};

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < 11 && i != 9) {
                    if (position < 6) {
                        tvs[position].setText(strs[i]);
                        position += 1;
                    }
                } else if (i == 11) {
                    if (position > 0) {
                        position--;
                        tvs[position].setText("");
                    }
                }
                //空按钮
                if (i == 9) {
                    String password = "";
                    for (int j = 0; j < 6; j++) {
                        password += tvs[j].getText().toString().trim();
                    }
                    if (password.equals("123456")) {
                        String money = acWithdrawEtDrawMoney.getText().toString();
                        SetSubscribe.tqIncome(user_id, token,  money, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                showToast("收益提取成功");
                                finish();
                            }

                            @Override
                            public void onFault(String errorMsg) {
                                //showToast(errorMsg);
                            }
                        }));
                    } else {
                        showToast("密码不正确");
                        position = 0;
                        for (int j = 0; j < 6; j++) {
                            tvs[j].setText("");
                        }
                    }
                }

            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

}
