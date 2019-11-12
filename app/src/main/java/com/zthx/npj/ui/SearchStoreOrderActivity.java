package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.OrderListAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.OrderResponseBean;
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

public class SearchStoreOrderActivity extends ActivityBase {

    @BindView(R.id.ac_searchStoreOrder_rv)
    RecyclerView acSearchStoreOrderRv;
    @BindView(R.id.at_home_search_iv_back)
    ImageView atHomeSearchIvBack;
    @BindView(R.id.at_home_search_et_search)
    EditText atHomeSearchEtSearch;
    @BindView(R.id.at_home_search_tv_search)
    TextView atHomeSearchTvSearch;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String order_id;
    private String searchStr;


    private static final String TAG = "测试";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_store_order);
        ButterKnife.bind(this);

        back(atHomeSearchIvBack);

    }

    private void getSearchStoreOrder(String str) {
        //setSearchStoreOrder("");
        Log.e(TAG, "getSearchStoreOrder: "+str);
    }

    private void setSearchStoreOrder(String result) {
        OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
        final ArrayList<OrderResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            acSearchStoreOrderRv.setVisibility(View.GONE);
        } else {
            acSearchStoreOrderRv.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(SearchStoreOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        acSearchStoreOrderRv.setLayoutManager(manager);
        final OrderListAdapter mAdapter = new OrderListAdapter(SearchStoreOrderActivity.this, data);

        mAdapter.setOnItemClickListener(new OrderListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (data.get(position).getOrder_state().equals("6") || data.get(position).getOrder_state().equals("7") || data.get(position).getOrder_state().equals("8")) {
                    Intent intent = new Intent(SearchStoreOrderActivity.this, StoreOrderRefuseActivity.class);
                    intent.putExtra("order_id", data.get(position).getId() + "");
                    intent.putExtra("order_state", data.get(position).getOrder_state() + "");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SearchStoreOrderActivity.this, MyStoreOrderDetailActivity.class);
                    intent.putExtra("order_id", data.get(position).getId() + "");
                    intent.putExtra("order_state", data.get(position).getOrder_state() + "");
                    intent.putExtra("type", "mine");
                    startActivity(intent);
                }

            }

            //取消订单
            @Override
            public void onCancelClick(int position) {
                String order_id = data.get(position).getId() + "";
                SetSubscribe.cancelOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        mAdapter.notifyDataSetChanged();
                        getSearchStoreOrder(searchStr);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //删除订单
            @Override
            public void onDeleteClick(final int position) {
                CommonDialog dialog = new CommonDialog(SearchStoreOrderActivity.this, R.style.dialog, "订单删除后将无法找回，确定要删除？", true, new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            String order_id = data.get(position).getId() + "";
                            SetSubscribe.delOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    mAdapter.notifyItemRemoved(position);
                                    showToast("订单删除成功");
                                }

                                @Override
                                public void onFault(String errorMsg) {

                                }
                            }));
                        }
                    }
                });
                dialog.setTitle("订单删除");
                dialog.show();
            }

            //支付订单
            @Override
            public void onPayClick(int position) {
                Intent intent = new Intent(SearchStoreOrderActivity.this, ConfirmMyOrderActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //催单
            @Override
            public void onCuiDanClick(int position) {
                /*Toast toast=Toast.makeText(SearchStoreOrderActivity.this,"已通知商家发货，请耐心等待",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();*/
                SetSubscribe.reminders(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("已通知商家发货，请耐心等待");
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //查询物流
            @Override
            public void onQueryClick(int position) {
                Intent intent = new Intent(SearchStoreOrderActivity.this, KuaiDiDetailActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                intent.putExtra("type", "store");
                startActivity(intent);
            }

            //确认收货
            @Override
            public void onConfirmClick(int position) {
                order_id = data.get(position).getId() + "";
                //showPublishPopwindow();
                SetSubscribe.receiveConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("确认收货成功");
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //再来一单
            @Override
            public void onAgainClick(int position) {
                Intent intent = new Intent(SearchStoreOrderActivity.this, GoodsDetailActivity.class);
                intent.setAction("Goods");
                intent.putExtra(Const.GOODS_ID, data.get(position).getGoods_id() + "");
                startActivity(intent);
            }

            //评论订单
            @Override
            public void onCommentClick(int position) {
                Intent intent = new Intent(SearchStoreOrderActivity.this, CommentActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                intent.putExtra("order_type",data.get(position).getOrder_type()+"");
                startActivity(intent);
            }

            //退货
            @Override
            public void onGoodsReturn(int position) {
                Intent intent = new Intent(SearchStoreOrderActivity.this, ApplyRefundActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onGoStore(int position) {

            }
        });
        acSearchStoreOrderRv.setItemAnimator(new DefaultItemAnimator());
        acSearchStoreOrderRv.setAdapter(mAdapter);
    }

    @OnClick({R.id.at_home_search_et_search, R.id.at_home_search_tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_home_search_et_search:
                atHomeSearchEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            searchStr = atHomeSearchEtSearch.getText().toString().trim();
                            getSearchStoreOrder(searchStr);
                        }
                        return true;
                    }
                });
                break;
            case R.id.at_home_search_tv_search:
                searchStr = atHomeSearchEtSearch.getText().toString().trim();
                getSearchStoreOrder(searchStr);
                break;
        }
    }
}
