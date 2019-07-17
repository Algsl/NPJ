package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends ActivityBase {

    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_recharge_btn_recharge)
    Button acRechargeBtnRecharge;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String storeGoodsExExpressId = "1";
    String[] items = {"圆通", "汇通", "中通"};
    @BindView(R.id.title_back)
    ImageView titleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"钱包充值");

    }

    @OnClick(R.id.ac_recharge_btn_recharge)
    public void onViewClicked() {
        showPublishPopwindow();
    }

    public void showPublishPopwindow() {

        final View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);


        final EditText storeGoodsEtExpressNumber = contentView.findViewById(R.id.pw_storeGoods_et_expressNumber);
        Spinner spinner = contentView.findViewById(R.id.pw_storeGoods_s_expressName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeGoodsExExpressId = (i + 1) + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button commit = contentView.findViewById(R.id.pw_storeGoods_btn_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RechargeActivity.this, storeGoodsExExpressId, Toast.LENGTH_LONG).show();
            }
        });
    }
}
