package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsBillAdapter;
import com.zthx.npj.net.been.KuaiDiResponseBean;
import com.zthx.npj.net.been.MyOrderListResponseBean;
import com.zthx.npj.net.been.RefundResponseBean;
import com.zthx.npj.net.been.ShipBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreGoodsBillActivity extends ActivityBase {

    @BindView(R.id.at_store_goods_bill_rv)
    RecyclerView atStoreGoodsBillRv;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String[] item;
    String storeGoodsExExpressId = "1";
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_storeGoodsBill_tv_toShare)
    TextView acStoreGoodsBillTvToShare;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods_bill);
        ButterKnife.bind(this);
        getMyStoreOrderList();
        getKuaiDiList();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMyStoreOrderList();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "商品订单");
    }

    private void getKuaiDiList() {
        SetSubscribe.getKuaiDiList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setKuaiDiList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getMyStoreOrderList() {
        SetSubscribe.myOrderList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyStoreOrderList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyStoreOrderList(String result) {
        MyOrderListResponseBean bean = GsonUtils.fromJson(result, MyOrderListResponseBean.class);
        final ArrayList<MyOrderListResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            atStoreGoodsBillRv.setVisibility(View.GONE);
        } else {
            atStoreGoodsBillRv.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atStoreGoodsBillRv.setLayoutManager(manager);
        StoreGoodsBillAdapter mAdapter = new StoreGoodsBillAdapter(this, data);
        mAdapter.setOnItemClickListener(new StoreGoodsBillAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(StoreGoodsBillActivity.this, MyStoreOrderDetailActivity.class));
            }

            @Override
            public void onSendClick(int position) {
                showPublishPopwindow(data.get(position).getId() + "");
            }

            @Override
            public void onDrawBackClick(int position) {
                final String order_id = data.get(position).getId() + "";
                final String goods_name = data.get(position).getGoods_name();
                SetSubscribe.refund2(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        RefundResponseBean bean = GsonUtils.fromJson(result, RefundResponseBean.class);
                        RefundResponseBean.DataBean data = bean.getData();
                        showRefundPopwindow(order_id, data, goods_name);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));

            }
        });
        atStoreGoodsBillRv.setItemAnimator(new DefaultItemAnimator());
        atStoreGoodsBillRv.setAdapter(mAdapter);
    }

    public void showPublishPopwindow(final String order_id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_360));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        ;
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final EditText storeGoodsEtExpressNumber = contentView.findViewById(R.id.pw_storeGoods_et_expressNumber);
        Spinner spinner = contentView.findViewById(R.id.pw_storeGoods_s_expressName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
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
                String express_number = storeGoodsEtExpressNumber.getText().toString().trim();
                ShipBean bean = new ShipBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setExpress_id(storeGoodsExExpressId);
                bean.setExpress_number(express_number);
                SetSubscribe.ship(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                        window.dismiss();
                        backgroundAlpha(1f);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window.dismiss();
                backgroundAlpha(1f);
            }
        });
    }

    private void setKuaiDiList(String result) {
        KuaiDiResponseBean bean = GsonUtils.fromJson(result, KuaiDiResponseBean.class);
        ArrayList<KuaiDiResponseBean.DataBean> data = bean.getData();
        item = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            item[i] = data.get(i).getExpress_name();
        }
    }

    public void showRefundPopwindow(final String order_id, RefundResponseBean.DataBean data, String goods_name) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill_refund, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_500));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_320));
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView goodsName = contentView.findViewById(R.id.pw_storeGoods_tv_goodsName);
        TextView goodsPrice = contentView.findViewById(R.id.pw_storeGoods_tv_goodsPrice);
        TextView goodsState = contentView.findViewById(R.id.pw_storeGoods_tv_goodsState);
        TextView refundReason = contentView.findViewById(R.id.pw_storeGoods_tv_refundReason);
        TextView refundDesc = contentView.findViewById(R.id.pw_storeGoods_tv_refundDesc);
        ImageView goodsImg = contentView.findViewById(R.id.pw_storeGoods_iv_goodsImg);
        goodsName.setText(goods_name);
        goodsPrice.setText("￥" + data.getRefund_price());
        switch ((int) data.getRefund_state()) {
            case 0:
                goodsState.setText("已收货");
                break;
            case 1:
                goodsState.setText("未收货");
                break;
        }
        refundReason.setText(data.getRefund_reason());
        refundDesc.setText(data.getRefund_desc() == null ? "无" : data.getRefund_desc());
        Glide.with(this).load(Uri.parse("http://app.npj-vip.com" + data.getRefund_img())).into(goodsImg);
        Button refund = contentView.findViewById(R.id.pw_storeGoods_btn_refund);
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSubscribe.refund(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
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

    @OnClick(R.id.ac_storeGoodsBill_tv_toShare)
    public void onViewClicked() {
        openActivity(StoreGoodsListActivity.class);
    }
}
