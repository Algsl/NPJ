package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.MySupplyListAdapter;
import com.zthx.npj.adapter.SupplyOrderAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.KuaiDiResponseBean;
import com.zthx.npj.net.been.MySupplyListResponseBean;
import com.zthx.npj.net.been.MySupplyOrderFahuoBean;
import com.zthx.npj.net.been.MySupplyOrderRefund3ResponseBean;
import com.zthx.npj.net.been.MySupplyOrderRefuseRefundBean;
import com.zthx.npj.net.been.SupplyOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyManagerActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.at_supply_manager_rv_supply_list1)
    RecyclerView atSupplyManagerRvSupplyList1;
    @BindView(R.id.at_supply_manager_ll_supply_list)
    LinearLayout atSupplyManagerLlSupplyList;
    @BindView(R.id.at_supply_manager_rv_supply_bill)
    RecyclerView atSupplyManagerRvSupplyBill;
    @BindView(R.id.at_supply_manager_ll_supply_bill)
    RelativeLayout atSupplyManagerLlSupplyBill;
    @BindView(R.id.ac_supplyManager_tv_supply)
    TextView acSupplyManagerTvSupply;
    @BindView(R.id.ac_supplyManager_tv_order)
    TextView acSupplyManagerTvOrder;
    @BindView(R.id.ac_supplyManager_tv_up)
    TextView acSupplyManagerTvUp;
    @BindView(R.id.ac_supplyManager_tv_down)
    TextView acSupplyManagerTvDown;
    @BindView(R.id.refreshLayout1)
    SmartRefreshLayout refreshLayout1;
    @BindView(R.id.refreshLayout2)
    SmartRefreshLayout refreshLayout2;
    @BindView(R.id.showHint)
    TextView showHint;
    @BindView(R.id.showHint1)
    TextView showHint1;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String type = "1";
    String storeGoodsExExpressId = "1";
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_manager);
        ButterKnife.bind(this);

        refreshLayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getSupply();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
        refreshLayout2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getOrder();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "供应管理");
        getSupply();
        getKuaiDiList();
    }

    private void getSupply() {
        atSupplyManagerLlSupplyList.setVisibility(View.VISIBLE);
        atSupplyManagerLlSupplyBill.setVisibility(View.GONE);
        SetSubscribe.mySupplyList(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMySupplyList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMySupplyList(String result) {
        MySupplyListResponseBean bean = GsonUtils.fromJson(result, MySupplyListResponseBean.class);
        final ArrayList<MySupplyListResponseBean.DataBean> data = bean.getData();
        if (data == null || data.size() == 0) {
            showHint.setVisibility(View.VISIBLE);
        } else {
            showHint.setVisibility(View.GONE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atSupplyManagerRvSupplyList1.setLayoutManager(layoutManager);
        MySupplyListAdapter adapter = new MySupplyListAdapter(this, data, type);
        atSupplyManagerRvSupplyList1.setItemAnimator(new DefaultItemAnimator());
        atSupplyManagerRvSupplyList1.setAdapter(adapter);
        adapter.setOnItemClickListener(new MySupplyListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SupplyManagerActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.SUPPLY_DETAIL);
                intent.putExtra("goods_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onSaleClick(int position) {
                if (type.equals("1")) {
                    SetSubscribe.mySupplyDown(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("供应商品下架成功");
                            getSupply();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                } else {
                    SetSubscribe.mySupplyUp(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("供应商品上架成功");
                            getSupply();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
            }


            @Override
            public void onSupplyEditClick(int position) {
                openActivity(SupplyMessageInfoActivity.class, data.get(position).getId(), "2");
            }

            @Override
            public void onSupplyDeleteClick(final int position) {
                CommonDialog dialog=new CommonDialog(SupplyManagerActivity.this, R.style.dialog, "商品删除后将无法找回，确定要删除吗？", true, new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            SetSubscribe.mySupplyDel(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    showToast("商品删除成功");
                                    getSupply();
                                }

                                @Override
                                public void onFault(String errorMsg) {

                                }
                            }));
                        }
                    }
                });
                dialog.setTitle("商品删除");
                dialog.show();
            }

            @Override
            public void onSupplyShareClick(int position) {
                Intent intent = new Intent(SupplyManagerActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.SUPPLY_DETAIL);
                intent.putExtra("goods_id", data.get(position).getId() + "");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.ac_supplyManager_tv_supply, R.id.ac_supplyManager_tv_order, R.id.ac_supplyManager_tv_up, R.id.ac_supplyManager_tv_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_supplyManager_tv_supply:
                acSupplyManagerTvSupply.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplyManagerTvSupply.setTextColor(getResources().getColor(android.R.color.white));
                acSupplyManagerTvOrder.setBackgroundColor(getResources().getColor(android.R.color.white));
                acSupplyManagerTvOrder.setTextColor(getResources().getColor(R.color.text3));
                getSupply();
                break;
            case R.id.ac_supplyManager_tv_order:
                acSupplyManagerTvOrder.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplyManagerTvOrder.setTextColor(getResources().getColor(android.R.color.white));
                acSupplyManagerTvSupply.setBackgroundColor(getResources().getColor(android.R.color.white));
                acSupplyManagerTvSupply.setTextColor(getResources().getColor(R.color.text3));
                getOrder();
                break;
            case R.id.ac_supplyManager_tv_up:
                acSupplyManagerTvUp.setTextColor(getResources().getColor(R.color.app_theme));
                acSupplyManagerTvDown.setTextColor(getResources().getColor(R.color.text3));
                type = "1";
                getSupply();
                break;
            case R.id.ac_supplyManager_tv_down:
                acSupplyManagerTvUp.setTextColor(getResources().getColor(R.color.text3));
                acSupplyManagerTvDown.setTextColor(getResources().getColor(R.color.app_theme));
                type = "2";
                getSupply();
                break;
        }
    }

    private void getOrder() {
        atSupplyManagerLlSupplyList.setVisibility(View.GONE);
        atSupplyManagerLlSupplyBill.setVisibility(View.VISIBLE);
        SetSubscribe.supplyOrder(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrder(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setOrder(String result) {
        SupplyOrderResponseBean bean = GsonUtils.fromJson(result, SupplyOrderResponseBean.class);
        final ArrayList<SupplyOrderResponseBean.DataBean> data = bean.getData();
        if (data == null || data.size() == 0) {
            showHint1.setVisibility(View.VISIBLE);
        } else {
            showHint1.setVisibility(View.GONE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atSupplyManagerRvSupplyBill.setLayoutManager(layoutManager);
        SupplyOrderAdapter adapter = new SupplyOrderAdapter(this, data);
        adapter.setOnItemClickListener(new SupplyOrderAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(SupplyManagerActivity.this, MySupplyOrderDetailActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                intent.putExtra("order_state",data.get(position).getOrder_state()+"");
                intent.putExtra("type","buss");
                startActivity(intent);
            }

            @Override
            public void onSendClick(int position) {
                showPublishPopwindow(data.get(position).getId() + "");
            }

            @Override
            public void onRefundClick(int position) {
                final String order_id = data.get(position).getId() + "";
                final String goods_name = data.get(position).getGoods_name();
                SetSubscribe.mySupplyOrderRefund3(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        MySupplyOrderRefund3ResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderRefund3ResponseBean.class);
                        MySupplyOrderRefund3ResponseBean.DataBean data = bean.getData();
                        showRefundPopwindow(order_id, data, goods_name);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        atSupplyManagerRvSupplyBill.setItemAnimator(new DefaultItemAnimator());
        atSupplyManagerRvSupplyBill.setAdapter(adapter);
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

    private void setKuaiDiList(String result) {
        KuaiDiResponseBean bean = GsonUtils.fromJson(result, KuaiDiResponseBean.class);
        ArrayList<KuaiDiResponseBean.DataBean> data = bean.getData();
        item = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            item[i] = data.get(i).getExpress_name();
        }
    }


    public void showPublishPopwindow(final String order_id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
        // 设置PopupWindow的背景
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

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
                MySupplyOrderFahuoBean bean = new MySupplyOrderFahuoBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setExpress_id(storeGoodsExExpressId);
                bean.setExpress_number(express_number);
                SetSubscribe.mySupplyOrderFahuo(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("发货成功");
                        getOrder();
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
                window.dismiss();
            }
        });
    }


    public void showRefundPopwindow(final String order_id, MySupplyOrderRefund3ResponseBean.DataBean data, String goods_name) {
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
        TextView refund = contentView.findViewById(R.id.pw_storeGoods_btn_refund);
        TextView refuse = contentView.findViewById(R.id.pw_storeGoods_btn_refuse);
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSubscribe.mySupplyOrderRefund2(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("退款成功");
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                showRefusePopwindow(order_id);
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    //拒绝退款弹窗
    public void showRefusePopwindow(final String order_id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill_refuse, null);
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
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final EditText etReason = contentView.findViewById(R.id.pw_storeGoodsBill_et_reason);
        TextView confirm = contentView.findViewById(R.id.pw_storeGoodsBill_tv_refuse);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySupplyOrderRefuseRefundBean bean = new MySupplyOrderRefuseRefundBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setJujue_yuanyin(etReason.getText().toString().trim());
                SetSubscribe.mySupplyOrderRefuseRefund(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("测试", "onSuccess: " + result);
                        showToast("拒绝退款成功");
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
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
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
