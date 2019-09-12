package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.OrderListAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.OrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ApplyRefundActivity;
import com.zthx.npj.ui.ClassfiysActivity;
import com.zthx.npj.ui.CommentActivity;
import com.zthx.npj.ui.ConfirmMyOrderActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.KuaiDiDetailActivity;
import com.zthx.npj.ui.MyStoreOrderDetailActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class OrderListFragment extends Fragment {


    @BindView(R.id.fg_order_list)
    RecyclerView fgOrderList;
    Unbinder unbinder;

    String user_id = SharePerferenceUtils.getUserId(getContext());
    String token = SharePerferenceUtils.getToken(getContext());
    @BindView(R.id.fg_orderList_toMakeOrder)
    TextView fgOrderListToMakeOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getOrder();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getOrder();
    }

    private void getOrder() {
        String order_state = getArguments().getString("order_state");
        SetSubscribe.myOrder(user_id, token, order_state, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
        final ArrayList<OrderResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            fgOrderList.setVisibility(View.GONE);
        } else {
            fgOrderList.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fgOrderList.setLayoutManager(manager);
        final OrderListAdapter mAdapter = new OrderListAdapter(getActivity(), data);

        mAdapter.setOnItemClickListener(new OrderListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), MyStoreOrderDetailActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                intent.putExtra("order_state", data.get(position).getOrder_state() + "");
                startActivity(intent);
            }

            //取消订单
            @Override
            public void onCancelClick(int position) {
                String order_id = data.get(position).getId();
                SetSubscribe.cancelOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        mAdapter.notifyDataSetChanged();
                        getOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //删除订单
            @Override
            public void onDeleteClick(final int position) {
                String order_id = data.get(position).getId();
                SetSubscribe.delOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        mAdapter.notifyItemRemoved(position);
                        getOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //支付订单
            @Override
            public void onPayClick(int position) {
                Intent intent = new Intent(getActivity(), ConfirmMyOrderActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //催单
            @Override
            public void onCuiDanClick(int position) {
                Toast toast=Toast.makeText(getContext(),"已通知商家发货，请耐心等待",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();
            }

            //查询物流
            @Override
            public void onQueryClick(int position) {
                Intent intent = new Intent(getContext(), KuaiDiDetailActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //确认收货
            @Override
            public void onConfirmClick(int position) {
                String order_id = data.get(position).getId() + "";
                SetSubscribe.receiveConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Toast toast=Toast.makeText(getContext(),"确认收货成功",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();
                        getOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //再来一单
            @Override
            public void onAgainClick(int position) {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            //评论订单
            @Override
            public void onCommentClick(int position) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //退货
            @Override
            public void onGoodsReturn(int position) {
                Intent intent = new Intent(getContext(), ApplyRefundActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }
        });
        fgOrderList.setItemAnimator(new DefaultItemAnimator());
        fgOrderList.setAdapter(mAdapter);
    }


    public OrderListFragment newIntent(String order_state) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("order_state", order_state);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fg_orderList_toMakeOrder)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), ClassfiysActivity.class));
    }
}
