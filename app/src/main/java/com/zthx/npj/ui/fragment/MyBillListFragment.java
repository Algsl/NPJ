package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.GradViewAdapter;
import com.zthx.npj.adapter.MySupplyOrderAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.MySupplyOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ConfirmMySupplyOrderActivity;
import com.zthx.npj.ui.KuaiDiDetailActivity;
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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String user_id = SharePerferenceUtils.getUserId(getContext());
    private String token = SharePerferenceUtils.getToken(getContext());
    private int position=0;
    private String order_id="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bill_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMySupplyOrder();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fgWantBuyManagerList.setLayoutManager(manager);
        getMySupplyOrder();
        return view;
    }

    private void getMySupplyOrder() {
        SetSubscribe.mySupplyOrder(user_id, token, getArguments().getString("order_state"), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MySupplyOrderResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderResponseBean.class);
        final ArrayList<MySupplyOrderResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            fgWantBuyManagerList.setVisibility(View.GONE);
        } else {
            fgWantBuyManagerList.setVisibility(View.VISIBLE);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fgWantBuyManagerList.setLayoutManager(layoutManager);
        MySupplyOrderAdapter adapter = new MySupplyOrderAdapter(getContext(), data);
        fgWantBuyManagerList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MySupplyOrderAdapter.ItemClickListener() {
            //查看详细信息
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getContext(), MySupplyOrderDetailActivity.class);
                intent.putExtra("order_id",data.get(position).getId()+"");
                intent.putExtra("order_state",data.get(position).getOrder_state()+"");
                startActivity(intent);
            }

            //取消订单
            @Override
            public void onCancelClick(int position) {
                String order_id = data.get(position).getId() + "";
                SetSubscribe.mySupplyOrderCancel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                String order_id = data.get(position).getId() + "";
                SetSubscribe.mySupplyOrderDel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                Intent intent = new Intent(getActivity(), ConfirmMySupplyOrderActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //催单
            @Override
            public void onCuiDanClick(int position) {
                /*Toast toast=Toast.makeText(getContext(),"已通知商家发货,请耐心等待",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();*/
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
                Intent intent = new Intent(getContext(), KuaiDiDetailActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
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
                Intent intent = new Intent(getContext(), SupplyProductsActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            //评价
            @Override
            public void onCommentClick(int position) {
                Intent intent = new Intent(getContext(), MySupplyOrderCommentActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            //退款
            @Override
            public void onGoodsReturn(int position) {
                Intent intent = new Intent(getContext(), MySupplyOrderRefundActivity.class);
                intent.putExtra("order_id", data.get(position).getId() + "");
                startActivity(intent);
            }
        });

    }


    public Fragment newInstence(String order_state) {
        MyBillListFragment fragment = new MyBillListFragment();
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



    public void showPublishPopwindow() {
        position=0;
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_mima, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
        // 设置PopupWindow的背景
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        GridView gv=contentView.findViewById(R.id.pw_mima_gv);
        final String[] strs=new String[]{"1","2","3","4","5","6","7","8","9","确定","0","删除"};
        GradViewAdapter adapter=new GradViewAdapter(getContext(),strs);
        gv.setAdapter(adapter);

        TextView tv1=contentView.findViewById(R.id.tv1);
        TextView tv2=contentView.findViewById(R.id.tv2);
        TextView tv3=contentView.findViewById(R.id.tv3);
        TextView tv4=contentView.findViewById(R.id.tv4);
        TextView tv5=contentView.findViewById(R.id.tv5);
        TextView tv6=contentView.findViewById(R.id.tv6);
        final TextView[] tvs=new TextView[]{tv1,tv2,tv3,tv4,tv5,tv6};

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < 11 &&i!=9) {
                    if(position<6){
                        tvs[position].setText(strs[i]);
                        position+=1;
                    }
                }else if(i == 11) {
                    if(position>0){
                        position--;
                        tvs[position].setText("");
                    }
                }
                //空按钮
                if(i==9){
                    String password="";
                    for(int j=0;j<6;j++){
                        password+=tvs[j].getText().toString().trim();
                    }
                    if(password.equals("123456")){
                        SetSubscribe.mySupplyGoodsConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                showToast("确认收货成功");
                                backgroundAlpha(1f);
                                window.dismiss();
                            }

                            @Override
                            public void onFault(String errorMsg) {

                            }
                        }));
                    }else{
                        showToast("密码不正确");
                        position=0;
                        for(int j=0;j<6;j++){
                            tvs[j].setText("");
                        }
                    }
                }

            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

    public void showToast(String str){
        Toast toast=Toast.makeText(getContext(),str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
        getMySupplyOrder();
    }
}
