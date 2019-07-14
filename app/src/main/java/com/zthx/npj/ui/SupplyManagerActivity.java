package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MySupplyListAdapter;
import com.zthx.npj.net.been.MySupplyListResponseBean;
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

public class SupplyManagerActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_manager);
        ButterKnife.bind(this);

        getSupply();
    }

    private void getSupply() {
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

                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }else{
                    SetSubscribe.mySupplyUp(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {

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

                break;
            case R.id.ac_supplyManager_tv_order:

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
}
