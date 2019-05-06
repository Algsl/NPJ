package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyProductsActivity extends AppCompatActivity {

    @BindView(R.id.at_supply_products_btn_buy_now)
    Button atSupplyProductsBtnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_products);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_supply_products_btn_buy_now)
    public void onViewClicked() {
//        showPopupWindow(atSupplyProductsBtnBuyNow);
        startActivity(new Intent(this, SupplyBillActivity.class));
    }

    private void showPopupWindow(View view) {
        // 用于PopupWindow的View
         View contentView=LayoutInflater.from(SupplyProductsActivity.this).inflate(R.layout.popupwindow_supply_producets, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window=new PopupWindow(contentView, 100, 100, true);
        // 设置PopupWindow的背景
         window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
         window.setOutsideTouchable(true);
         // 设置PopupWindow是否能响应点击事件
         window.setTouchable(true);
         // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
         window.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
