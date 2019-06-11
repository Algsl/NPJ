package com.zthx.npj.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.zthx.npj.R;
import com.zthx.npj.net.been.PreSellDetailResponseBean;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GoodSizePopupwindow extends PopupWindow {

    private final LabelsView labelsView;
    private final RelativeLayout minusView;
    private final TextView secKillOldPrice;
    private final TextView OldPrice;
    private final RelativeLayout addView;
    private final Button mAddShoppingCar;
    private final Button mBuyNow;
    private Context mContext;
    private View view;

    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, boolean b, ArrayList<PreSellDetailResponseBean.DataBean.Value> data) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);
        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        secKillOldPrice = view.findViewById(R.id.pop_goods_size_tv_sec_old_price);
        OldPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);


        if (b) {
            secKillOldPrice.setVisibility(View.VISIBLE);
            OldPrice.setVisibility(View.GONE);
        } else {
            secKillOldPrice.setVisibility(View.GONE);
            OldPrice.setVisibility(View.VISIBLE);
        }
//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);

        ArrayList<String> label = new ArrayList<>();
        label.add("白色");
        label.add("黑色");
        label.add("图片色");
        label.add("藕粉色");
        label.add("卡其色");
        label.add("浅蓝色(比图片较深，介意勿拍)");
        label.add("粉色");
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
//        ======================================================
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());

        // 设置弹出窗体显示时的动画，从底部向上弹出
//        this.setAnimationStyle(R.style.Animation_Design_BottomSheetDialog);

    }
}
