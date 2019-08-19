package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.zthx.npj.R;
import com.zthx.npj.adapter.MySupplyListAdapter;
import com.zthx.npj.adapter.SupplyOrderAdapter;
import com.zthx.npj.net.been.KuaiDiResponseBean;
import com.zthx.npj.net.been.MySupplyListResponseBean;
import com.zthx.npj.net.been.ShipBean;
import com.zthx.npj.net.been.SupplyOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.Set;

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
    @BindView(R.id.at_supply_manager_rv_supply_list2)
    RecyclerView atSupplyManagerRvSupplyList2;
    @BindView(R.id.at_supply_manager_ll_supply_list)
    LinearLayout atSupplyManagerLlSupplyList;
    @BindView(R.id.at_supply_manager_rv_supply_bill)
    RecyclerView atSupplyManagerRvSupplyBill;
    @BindView(R.id.at_supply_manager_ll_supply_bill)
    LinearLayout atSupplyManagerLlSupplyBill;
    @BindView(R.id.ac_supplyManager_tv_supply)
    TextView acSupplyManagerTvSupply;
    @BindView(R.id.ac_supplyManager_tv_order)
    TextView acSupplyManagerTvOrder;
    @BindView(R.id.ac_supplyManager_tv_up)
    TextView acSupplyManagerTvUp;
    @BindView(R.id.ac_supplyManager_tv_down)
    TextView acSupplyManagerTvDown;

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

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"供应管理");
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atSupplyManagerRvSupplyList1.setLayoutManager(layoutManager);
        MySupplyListAdapter adapter = new MySupplyListAdapter(this, data,type);
        atSupplyManagerRvSupplyList1.setItemAnimator(new DefaultItemAnimator());
        atSupplyManagerRvSupplyList1.setAdapter(adapter);
        adapter.setOnItemClickListener(new MySupplyListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSaleClick(int position) {
                if (type.equals("1")){
                    SetSubscribe.mySupplyDown(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getSupply();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }else{
                    SetSubscribe.mySupplyUp(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
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

            }

            @Override
            public void onSupplyDeleteClick(int position) {
                SetSubscribe.mySupplyDel(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getSupply();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            @Override
            public void onSupplyShareClick(int position) {

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
                type="1";
                getSupply();
                break;
            case R.id.ac_supplyManager_tv_down:
                acSupplyManagerTvUp.setTextColor(getResources().getColor(R.color.text3));
                acSupplyManagerTvDown.setTextColor(getResources().getColor(R.color.app_theme));
                type="2";
                getSupply();
                break;
        }
    }

    private void getOrder() {
        atSupplyManagerLlSupplyList.setVisibility(View.GONE);
        atSupplyManagerLlSupplyBill.setVisibility(View.VISIBLE);
        SetSubscribe.supplyOrder(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        SupplyOrderResponseBean bean=GsonUtils.fromJson(result,SupplyOrderResponseBean.class);
        final ArrayList<SupplyOrderResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        atSupplyManagerRvSupplyBill.setLayoutManager(layoutManager);
        SupplyOrderAdapter adapter=new SupplyOrderAdapter(this,data);
        adapter.setOnItemClickListener(new SupplyOrderAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSendClick(int position) {
                showPublishPopwindow(data.get(position).getId()+"");
            }

            @Override
            public void onRefundClick(int position) {

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
                ShipBean bean = new ShipBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setExpress_id(storeGoodsExExpressId);
                bean.setExpress_number(express_number);
                SetSubscribe.ship(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
    }


    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
