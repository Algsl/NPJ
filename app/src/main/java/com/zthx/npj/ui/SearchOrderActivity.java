package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MySupplyOrderAdapter;
import com.zthx.npj.adapter.OrderListAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.MySupplyOrderResponseBean;
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

public class SearchOrderActivity extends ActivityBase {

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

    private String type;
    private static final String TAG = "测试";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_store_order);
        ButterKnife.bind(this);

        type=getIntent().getStringExtra("key0");

        back(atHomeSearchIvBack);

    }

    private void getSearchStoreOrder(String str) {
        SetSubscribe.searchOrder(user_id,token,str,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSearchStoreOrder(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSearchStoreOrder(String result) {
        OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
        final ArrayList<OrderResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            acSearchStoreOrderRv.setVisibility(View.GONE);
        } else {
            acSearchStoreOrderRv.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(SearchOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        acSearchStoreOrderRv.setLayoutManager(manager);
        final OrderListAdapter mAdapter = new OrderListAdapter(SearchOrderActivity.this, data);

        mAdapter.setOnItemClickListener(new OrderListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (data.get(position).getOrder_state().equals("6") || data.get(position).getOrder_state().equals("7") || data.get(position).getOrder_state().equals("8")) {
                    Intent intent = new Intent(SearchOrderActivity.this, StoreOrderRefuseActivity.class);
                    intent.putExtra("order_id", data.get(position).getId() + "");
                    intent.putExtra("order_state", data.get(position).getOrder_state() + "");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SearchOrderActivity.this, MyStoreOrderDetailActivity.class);
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
                CommonDialog dialog = new CommonDialog(SearchOrderActivity.this, R.style.dialog, "订单删除后将无法找回，确定要删除？", true, new CommonDialog.OnCloseListener() {
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
                Intent intent = new Intent(SearchOrderActivity.this, ConfirmMyOrderActivity.class);
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
                Intent intent = new Intent(SearchOrderActivity.this, KuaiDiDetailActivity.class);
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
                Intent intent = new Intent(SearchOrderActivity.this, GoodsDetailActivity.class);
                intent.setAction("Goods");
                intent.putExtra(Const.GOODS_ID, data.get(position).getGoods_id() + "");
                startActivity(intent);
            }

            //评论订单
            @Override
            public void onCommentClick(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, CommentActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                intent.putExtra("order_type",data.get(position).getOrder_type()+"");
                startActivity(intent);
            }

            //退货
            @Override
            public void onGoodsReturn(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, ApplyRefundActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onGoStore(int position) {

            }

            @Override
            public void onComment2Click(int position) {
                Intent intent=new Intent(SearchOrderActivity.this,AddToCommentActivity.class);
                startActivity(intent);
            }
        });
        acSearchStoreOrderRv.setItemAnimator(new DefaultItemAnimator());
        acSearchStoreOrderRv.setAdapter(mAdapter);
    }



    private void getSearchSupplyOrder(String str) {
        SetSubscribe.searchSupplyOrder(user_id, token, str, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMySupplyOrder(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        Log.e(TAG, "getSearchSupplyOrder: "+str );
    }

    private void setMySupplyOrder(String result) {
        MySupplyOrderResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderResponseBean.class);
        final ArrayList<MySupplyOrderResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            acSearchStoreOrderRv.setVisibility(View.GONE);
        } else {
            acSearchStoreOrderRv.setVisibility(View.VISIBLE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchOrderActivity.this);
        acSearchStoreOrderRv.setLayoutManager(layoutManager);
        MySupplyOrderAdapter adapter = new MySupplyOrderAdapter(SearchOrderActivity.this, data);
        acSearchStoreOrderRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MySupplyOrderAdapter.ItemClickListener() {
            //查看详细信息
            @Override
            public void onItemClick(int position) {
                if(data.get(position).getOrder_state()==6 || data.get(position).getOrder_state()==7 || data.get(position).getOrder_state()==8){
                    Intent intent=new Intent(SearchOrderActivity.this, MySupplyOrderRefuseActivity.class);
                    intent.putExtra("order_id",data.get(position).getId()+"");
                    intent.putExtra("order_state",data.get(position).getOrder_state()+"");
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(SearchOrderActivity.this, MySupplyOrderDetailActivity.class);
                    intent.putExtra("order_id",data.get(position).getId()+"");
                    intent.putExtra("order_state",data.get(position).getOrder_state()+"");
                    intent.putExtra("type","mine");
                    startActivity(intent);
                }

            }

            //取消订单
            @Override
            public void onCancelClick(int position) {
                String order_id = data.get(position).getId() + "";
                SetSubscribe.mySupplyOrderCancel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //删除订单
            @Override
            public void onDeleteClick(final int position) {
                CommonDialog dialog=new CommonDialog(SearchOrderActivity.this, R.style.dialog, "订单删除后将无法找回，确定要删除吗？", true, new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            String order_id = data.get(position).getId() + "";
                            SetSubscribe.mySupplyOrderDel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
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

            //去支付
            @Override
            public void onPayClick(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, ConfirmMySupplyOrderActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //催单
            @Override
            public void onCuiDanClick(int position) {
                Toast toast=Toast.makeText(SearchOrderActivity.this,"已通知商家发货,请耐心等待",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();
                SetSubscribe.reminders(user_id,token,data.get(position).getId()+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("已通知商家发货,请耐心等待");
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //查询物流
            @Override
            public void onQueryClick(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, KuaiDiDetailActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                intent.putExtra("type","supply");
                startActivity(intent);
            }

            //确认收货
            @Override
            public void onConfirmClick(int position) {
                order_id=data.get(position).getId()+"";
                //showPublishPopwindow();
                SetSubscribe.mySupplyGoodsConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                Intent intent = new Intent(SearchOrderActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            //评价
            @Override
            public void onCommentClick(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, MySupplyOrderCommentActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //退款
            @Override
            public void onGoodsReturn(int position) {
                Intent intent = new Intent(SearchOrderActivity.this, MySupplyOrderRefundActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onAddCommentClick(int position) {

            }
        });

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
                            if(type.equals("supply")){
                                getSearchSupplyOrder(searchStr);
                            }else{
                                getSearchStoreOrder(searchStr);
                            }
                        }
                        return true;
                    }
                });
                break;
            case R.id.at_home_search_tv_search:
                searchStr = atHomeSearchEtSearch.getText().toString().trim();
                if(type.equals("supply")){
                    getSearchSupplyOrder(searchStr);
                }else{
                    getSearchStoreOrder(searchStr);
                }
                break;
        }
    }
}
