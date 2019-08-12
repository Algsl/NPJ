package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsBillAdapter;
import com.zthx.npj.net.been.KuaiDiResponseBean;
import com.zthx.npj.net.been.MyOrderListResponseBean;
import com.zthx.npj.net.been.ShipBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods_bill);
        ButterKnife.bind(this);
        getMyStoreOrderList();
        getKuaiDiList();

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"商品订单");
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
                String order_id = data.get(position).getId() + "";
                SetSubscribe.refund(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        atStoreGoodsBillRv.setAdapter(mAdapter);
    }

    public void showPublishPopwindow(final String order_id) {

        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
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
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
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

    public void showRefundPopwindow(String order_id) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill_refund, null);
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
    }
}
