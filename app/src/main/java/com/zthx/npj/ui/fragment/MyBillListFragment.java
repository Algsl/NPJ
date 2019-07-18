package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MySupplyOrderAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.MySupplyOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ApplyRefundActivity;
import com.zthx.npj.ui.CommentActivity;
import com.zthx.npj.ui.ConfirmMyOrderActivity;
import com.zthx.npj.ui.ConfirmMySupplyOrderActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.KuaiDiDetailActivity;
import com.zthx.npj.ui.MyStoreOrderDetailActivity;
import com.zthx.npj.ui.MySupplyOrderCommentActivity;
import com.zthx.npj.ui.MySupplyOrderDetailActivity;
import com.zthx.npj.ui.MySupplyOrderRefundActivity;
import com.zthx.npj.ui.SupplyProductsActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class MyBillListFragment extends Fragment {


    @BindView(R.id.fg_want_buy_manager_list)
    RecyclerView fgWantBuyManagerList;
    Unbinder unbinder;

    private String user_id=SharePerferenceUtils.getUserId(getContext());
    private String token=SharePerferenceUtils.getToken(getContext());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bill_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        fgWantBuyManagerList.setLayoutManager(manager);
        getMySupplyOrder();
        return view;
    }

    private void getMySupplyOrder() {
        SetSubscribe.mySupplyOrder(user_id,token,getArguments().getString("order_state"),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMySupplyOrder(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMySupplyOrder(String result) {
        MySupplyOrderResponseBean bean=GsonUtils.fromJson(result,MySupplyOrderResponseBean.class);
        final ArrayList<MySupplyOrderResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        fgWantBuyManagerList.setLayoutManager(layoutManager);
        MySupplyOrderAdapter adapter=new MySupplyOrderAdapter(getContext(),data);
        fgWantBuyManagerList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MySupplyOrderAdapter.ItemClickListener() {
            //查看详细信息
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getContext(), MySupplyOrderDetailActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                startActivity(intent);
            }

            //取消订单
            @Override
            public void onCancelClick(int position) {
                String order_id=data.get(position).getId()+"";
                SetSubscribe.mySupplyOrderCancel(user_id,token,order_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getMySupplyOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //删除订单
            @Override
            public void onDeleteClick(int position) {
                String order_id=data.get(position).getId()+"";
                SetSubscribe.mySupplyOrderDel(user_id,token,order_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getMySupplyOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //去支付
            @Override
            public void onPayClick(int position) {
                Intent intent=new Intent(getActivity(), ConfirmMySupplyOrderActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                startActivity(intent);
            }

            //催单
            @Override
            public void onCuiDanClick(int position) {

            }

            //查询物流
            @Override
            public void onQueryClick(int position) {
                Intent intent=new Intent(getContext(), KuaiDiDetailActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                startActivity(intent);
            }

            //确认收货
            @Override
            public void onConfirmClick(int position) {
                String order_id=data.get(position).getId()+"";
                SetSubscribe.mySupplyGoodsConfirm(user_id,token,order_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getMySupplyOrder();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            //再来一单
            @Override
            public void onAgainClick(int position) {
                Intent intent=new Intent(getContext(), SupplyProductsActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            //评价
            @Override
            public void onCommentClick(int position) {
                Intent intent=new Intent(getContext(),MySupplyOrderCommentActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                startActivity(intent);
            }

            //退款
            @Override
            public void onGoodsReturn(int position) {
                Intent intent=new Intent(getContext(), MySupplyOrderRefundActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                startActivity(intent);
            }
        });

    }


    public Fragment newInstence(String order_state){
        MyBillListFragment fragment=new MyBillListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("order_state",order_state);
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
}
