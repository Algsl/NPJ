package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.adapter.BaojiaListAdapter;
import com.zthx.npj.adapter.PurchaseListAdapter;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BaojiaListResponseBean;
import com.zthx.npj.net.been.IsTopBean;
import com.zthx.npj.net.been.IsTopResponseBean;
import com.zthx.npj.net.been.MySupplyListResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.PurchaseListResponseBean;
import com.zthx.npj.net.been.UploadChengxinCertResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WantBuyManagerActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.at_want_buy_manager_rv_supply_list1)
    RecyclerView atWantBuyManagerRvSupplyList1;
    @BindView(R.id.at_want_buy_manager_ll_supply_list)
    LinearLayout atWantBuyManagerLlSupplyList;
    @BindView(R.id.at_want_buy_manager_rv_supply_bill)
    RecyclerView atWantBuyManagerRvSupplyBill;
    @BindView(R.id.at_want_buy_manager_ll_supply_bill)
    RelativeLayout atWantBuyManagerLlSupplyBill;
    @BindView(R.id.ac_wantBuy_tv_wantBuy)
    TextView acWantBuyTvWantBuy;
    @BindView(R.id.ac_wantBuy_tv_baojia)
    TextView acWantBuyTvBaojia;
    @BindView(R.id.ac_wantBuy_tv_issue)
    TextView acWantBuyTvIssue;
    @BindView(R.id.ac_wantBuy_tv_unIssue)
    TextView acWantBuyTvUnIssue;
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

    private String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx1Lq1TU+c8jDT\n" +
            "NEU5up1siPOXKJBU0ypde7oPfm9gyy2ajgcw6v3KF2ryjot5AKlBED6qdQPRa5Sk\n" +
            "jIf8ZE1W+x8CVOvEC2m1lCglpm5zbAw2EGXdE4NNH6D0tcxIHza94RFkVilx1rjc\n" +
            "5hQ1OnwnLCDWN2UbOBl6jyom+iqUWSnFu7pEm5J8ZlNyr654LmDvCQXoPio28VSk\n" +
            "uedjQLdM+OkQZdidUYMaKsWmc6Xy6qmRqrgfUjvArjCZb0MKaNMq7bm7K9tr9dmJ\n" +
            "cj4X1WSm7txR81FkDDREEYiwFBoocm/G3wqUDIho0vT++kMlz0tnoPEx4q339eJL\n" +
            "t9pkdQvFAgMBAAECggEAUzoMZ+3W5M00rKQ6Adqk8rblykjhw9FQcpAFdFroJZTx\n" +
            "svPlya8xN/PdyceM3wTAMgM4UO6S6uA+oQRkYGtRBvRgfubfsNDmmGTOpVBPQRXA\n" +
            "YU0rX1xShzXWTrEG+nohVJyRVzQ8EVs9CaVkr8S/dlXgyGEEoMiQpBt8zuEmLGcG\n" +
            "MTK/OyIbHQKtjfRoXfL8xfW2bifFhg4fGlmgbO2DfiljpRwEY3mU9cSLH3oYWr8y\n" +
            "z3rSWlFCtV5v8syHNCZ+2+DTfcAMw1kPk/g16u/KPHA7duvnWLwGDm6Ktv14CMM2\n" +
            "mq9SpeFl3uicBKIcdK2I5k0lCJY/8aik/f1d0BNpwQKBgQDlz0keZqzY4itvNOpe\n" +
            "McJzzNyok6+rl42mDBJwb7QD+9X9ZtFhsYbdSgXWLbjILNpFKcZK9PGMuMXj8Cx7\n" +
            "C26W6zH/zQWsypLT940owzjNy0eckFH8BFm4UQHinc7GILbuUtAJNBBCdKKIdep6\n" +
            "/4zQl35v62wAa+ijy2Lox1fOzwKBgQDGGPXghnOMEm+vntbjay81cEZNReHLSzo9\n" +
            "rr/QGktC6SIaroYYpQgBsgScX/srDdi5wAy9pgHkywCxeZ2jzOset2O6NZCHAcFp\n" +
            "Wb4BrG9Gf0nWVP/DG0uqvEVBmgPEa6lZaIZH13jFFVH8P+Vwn26zza46lW0gD2Kn\n" +
            "z4ClplGBKwKBgHskvjuqLUjyuO+YXVYoN9ixmDRFH0dFqMOniGHzmXThB+QHqn89\n" +
            "D9WYitQgH/oz/qo9HmKgKqeLg48G7e7pS1NXqK04Aah7zH4FEwEay1+LZE5DD4uK\n" +
            "EUGxNt9mTJzifuPqQEwOOABEW6vf88wBEEXeSARVFMSNDlZm8BNobmcFAoGAMT4V\n" +
            "KLnjUSdoEezXF/MV6h+9qgm8Bg/uK1UcIzvWB4zySFWnycqEQf+he8m0ItCvVgUy\n" +
            "ZZY1lE0OIA/OKuCOdbU6mhgklBrQnEKNo9bcVlbf4OKCLVrEpW1lfdguJY5pq2r7\n" +
            "LjKWt88D8UNk4mkPWKzBKZjpZnXMnVBMd2Dvk78CgYB8DC/wQzY/0ibckmXnqE9e\n" +
            "hjBuRaG3964je78O5JaCEIVXUNX6nn5TMTK+uWrQNyqgXs92kw98Xi4ZSuER3zXk\n" +
            "Vmc1SOW4LjF98RAFdVMct8fP2u9xZ2zHV/SZ/ot0D1Bmz+P0dQL38+kSJ4w1N1rz\n" +
            "HCGInP32FNZD8bmcY+gFXw==";
    public static final String APPID = "2019062565701049";
    public static IWXAPI api;
    private static final int SDK_PAY_FLAG = 1001;
    private String payMoney = "1";
    private String payType;
    private UploadChengxinCertResponseBean.DataBean data1;
    private ArrayList<PurchaseListResponseBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_buy_manager);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");

        refreshLayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getWantBuy();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        refreshLayout2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getBaojia();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "求购管理");
        getWantBuy();
    }

    private void getWantBuy() {
        atWantBuyManagerLlSupplyList.setVisibility(View.VISIBLE);
        atWantBuyManagerLlSupplyBill.setVisibility(View.GONE);
        SetSubscribe.purchaseList(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyWantBuy(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setMyWantBuy(String result) {
        PurchaseListResponseBean bean = GsonUtils.fromJson(result, PurchaseListResponseBean.class);
        data = bean.getData();
        if (data == null || data.size() == 0) {
            showHint.setVisibility(View.VISIBLE);
        } else {
            showHint.setVisibility(View.GONE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atWantBuyManagerRvSupplyList1.setLayoutManager(layoutManager);
        atWantBuyManagerRvSupplyList1.setLayoutManager(layoutManager);
        PurchaseListAdapter adapter = new PurchaseListAdapter(this, data, type);
        atWantBuyManagerRvSupplyList1.setItemAnimator(new DefaultItemAnimator());
        atWantBuyManagerRvSupplyList1.setAdapter(adapter);
        adapter.setOnItemClickListener(new PurchaseListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(WantBuyManagerActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.NEED_DETAIL);
                intent.putExtra("goods_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onSaleClick(int position) {
                if (type.equals("1")) {
                    SetSubscribe.purchaseDown(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("求购商品下架成功");
                            getWantBuy();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                } else {
                    SetSubscribe.purchaseUp(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("求购商品上架成功");
                            getWantBuy();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
            }

            @Override
            public void onSupplyEditClick(int position) {
                openActivity(SupplyMessageInfoActivity.class, data.get(position).getId() + "", "1");
            }

            @Override
            public void onSupplyDeleteClick(final int position) {
                CommonDialog dialog=new CommonDialog(WantBuyManagerActivity.this, R.style.dialog, "商品删除后将无法找回，确定要删除吗？", true, new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            SetSubscribe.purchaseDel(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    showToast("商品删除成功");
                                    getWantBuy();
                                }

                                @Override
                                public void onFault(String errorMsg) {
                                    //showToast(errorMsg);
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
                if(data.get(position).getIs_top().equals("0")){
                    showPublishPopwindow1(data.get(position).getId()+"");
                }else{
                    Intent intent = new Intent(WantBuyManagerActivity.this, SupplyProductsActivity.class);
                    intent.setAction(Const.NEED_DETAIL);
                    intent.putExtra("goods_id", data.get(position).getId() + "");
                    startActivity(intent);
                }
            }
        });
    }

    @OnClick({R.id.ac_wantBuy_tv_wantBuy, R.id.ac_wantBuy_tv_baojia, R.id.ac_wantBuy_tv_issue, R.id.ac_wantBuy_tv_unIssue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //求购列表
            case R.id.ac_wantBuy_tv_wantBuy:
                acWantBuyTvWantBuy.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvWantBuy.setTextColor(getResources().getColor(android.R.color.white));
                acWantBuyTvBaojia.setBackgroundColor(getResources().getColor(android.R.color.white));
                acWantBuyTvBaojia.setTextColor(getResources().getColor(R.color.text3));
                getWantBuy();
                break;
            //报价列表
            case R.id.ac_wantBuy_tv_baojia:
                acWantBuyTvBaojia.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvBaojia.setTextColor(getResources().getColor(android.R.color.white));
                acWantBuyTvWantBuy.setBackgroundColor(getResources().getColor(android.R.color.white));
                acWantBuyTvWantBuy.setTextColor(getResources().getColor(R.color.text3));
                getBaojia();
                break;
            case R.id.ac_wantBuy_tv_issue:
                acWantBuyTvIssue.setTextColor(getResources().getColor(R.color.app_theme));
                acWantBuyTvUnIssue.setTextColor(getResources().getColor(R.color.text3));
                type = "1";
                getWantBuy();
                break;
            case R.id.ac_wantBuy_tv_unIssue:
                acWantBuyTvIssue.setTextColor(getResources().getColor(R.color.text3));
                acWantBuyTvUnIssue.setTextColor(getResources().getColor(R.color.app_theme));
                type = "2";
                getWantBuy();
                break;
        }
    }

    private void getBaojia() {
        atWantBuyManagerLlSupplyList.setVisibility(View.GONE);
        atWantBuyManagerLlSupplyBill.setVisibility(View.VISIBLE);
        SetSubscribe.baojiaList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setBaojia(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setBaojia(String result) {
        BaojiaListResponseBean bean = GsonUtils.fromJson(result, BaojiaListResponseBean.class);
        final ArrayList<BaojiaListResponseBean.DataBean> data = bean.getData();
        if(data==null || data.size()==0){
            showHint1.setVisibility(View.VISIBLE);
        }else{
            showHint1.setVisibility(View.GONE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atWantBuyManagerRvSupplyBill.setLayoutManager(layoutManager);
        BaojiaListAdapter adapter = new BaojiaListAdapter(this, data);
        atWantBuyManagerRvSupplyBill.setItemAnimator(new DefaultItemAnimator());
        atWantBuyManagerRvSupplyBill.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaojiaListAdapter.ItemClickListener() {
            @Override
            public void onSeeClick(int position) {
                Intent intent = new Intent(WantBuyManagerActivity.this, BaojiaUserListActivity.class);
                intent.putExtra("baojia_id", data.get(position).getId() + "");
                startActivity(intent);
            }
        });
    }


    public void showPublishPopwindow1(final String id) {
       /* View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_zhiding, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_450));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_275));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);*/
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.popupwindow_zhiding, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        ImageView cancel = view.findViewById(R.id.pw_zhiding_iv_cancel);
        final TextView item1 = view.findViewById(R.id.pw_zhiding_tv_item1);
        final TextView item2 = view.findViewById(R.id.pw_zhiding_tv_item2);
        final TextView item3 = view.findViewById(R.id.pw_zhiding_tv_item3);
        final TextView item4 = view.findViewById(R.id.pw_zhiding_tv_item4);
        final TextView money = view.findViewById(R.id.pw_zhiding_tv_payMoney);
        TextView confirm = view.findViewById(R.id.pw_zhiding_tv_confirm);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item1.setTextColor(getResources().getColor(R.color.white));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "1";
                money.setText("￥1.00");
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.white));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "5";
                money.setText("￥5.00");
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.white));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "10";
                money.setText("￥10.00");
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.white));
                payMoney = "30";
                money.setText("￥30.00");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog(id);
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

    private void showBottomDialog(final String id) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_pay_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_pay_yue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "3";
                uploadData(id);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_ali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "1";
                uploadData(id);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "2";
                uploadData(id);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData(String id) {
        IsTopBean bean = new IsTopBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setList_id(id);
        bean.setPay_code(payType);
        bean.setTop_days(payMoney);
        bean.setTop_price(payMoney);
        bean.setType("2");
        SetSubscribe.isTop(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                Log.e("测试", "onSuccess: "+result);
                IsTopResponseBean.DataBean data1=GsonUtils.fromJson(result,IsTopResponseBean.class).getData();
                if(data1.getStatus()==1){
                    showToast("支付成功");
                }else if(data1.getStatus()==3) {
                    DiscoverSubscribe.supplyPay(data1.getPay_code(), data1.getOrder_sn(), data1.getPay_money(), "6", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            if (payType.equals("1")) {
                                setPayResult(result);
                            } else if (payType.equals("2")) {
                                setWXResult(result);
                            } else {
                                finish();
                            }
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    private void setWXResult(String result) {
        PayResponse1Bean bean = GsonUtils.fromJson(result, PayResponse1Bean.class);
        PayResponse1Bean.DataBean data = bean.getData();
        PayReq req = new PayReq();
        req.appId = data.getAppid();//你的微信appid
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp();//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign = data.getSign();//签名
        //req.extData			= "app data";
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }


    private void setPayResult(String result) {
        PayResponseBean bean = GsonUtils.fromJson(result, PayResponseBean.class);
        PayResponseBean.DataBean data = bean.getData();
        alipay(data.getAlipay());
    }

    public void alipay(String str) {
        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = str;
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask(WantBuyManagerActivity.this);
                //获取支付结果
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(WantBuyManagerActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WantBuyManagerActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
