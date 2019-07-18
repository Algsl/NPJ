package com.zthx.npj.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyStoreResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyStoreActivity extends ActivityBase {

    @BindView(R.id.at_my_store_push_goods)
    LinearLayout atMyStorePushGoods;
    @BindView(R.id.at_my_store_goods_bill)
    LinearLayout atMyStoreGoodsBill;
    @BindView(R.id.at_my_store_goods_list)
    LinearLayout atMyStoreGoodsList;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_myStore_mcv_storeImg)
    MyCircleView acMyStoreMcvStoreImg;
    @BindView(R.id.ac_myStore_tv_storeName)
    TextView acMyStoreTvStoreName;
    @BindView(R.id.ac_myStore_tv_reputation)
    TextView acMyStoreTvReputation;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_back)
    ImageView titleBack;

    private MyStoreActivity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);
        ButterKnife.bind(this);
        getMyStore();

        back(titleBack);
        changeTitle(acTitle,"我的店铺");
        atLocationStoreTvRuzhu.setText("设置店铺");
    }

    private void getMyStore() {
        SetSubscribe.myStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyStore(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyStore(String result) {
        MyStoreResponseBean bean = GsonUtils.fromJson(result, MyStoreResponseBean.class);
        MyStoreResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getStore_img())).into(acMyStoreMcvStoreImg);
        acMyStoreTvStoreName.setText(data.getStore_name());
        acMyStoreTvReputation.setText("信誉分：" + data.getReputation());
    }

    @OnClick({R.id.at_my_store_push_goods, R.id.at_my_store_goods_bill, R.id.at_my_store_goods_list, R.id.at_location_store_tv_ruzhu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_store_push_goods:
                startActivity(new Intent(this, PublishGoodsActivity.class));
                break;
            case R.id.at_my_store_goods_bill:
                startActivity(new Intent(this, StoreGoodsBillActivity.class));
                break;
            case R.id.at_my_store_goods_list:
                startActivity(new Intent(this, StoreGoodsListActivity.class));
                break;
            case R.id.at_location_store_tv_ruzhu:
                backgroundAlpha(0.5f);
                showPublishPopwindow();
                break;
        }
    }

    public void showPublishPopwindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_edit, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_360));
        window.setWidth((int)getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final MyCircleView pwStoreEditMCVStoreImg = contentView.findViewById(R.id.pw_storEdit_mcv_storeImg);
        final TextView pwStoreEditTvStoreName = contentView.findViewById(R.id.pw_storEdit_et_storeName);
        Button pwStoreEditBtnCommit = contentView.findViewById(R.id.pw_storEdit_btn_commit);
        pwStoreEditBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String store_img = "/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg";
                String store_name = pwStoreEditTvStoreName.getText().toString().trim();
                SetSubscribe.setStore(user_id, token, store_name, store_img, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !window.isFocusable()) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    backgroundAlpha(1f);
                    return false;
                }else{
                    //否则default，往下dispatch事件:关掉popupWindow，
                    return true;
                }
            }
        });
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
