package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MyBillListAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.MySupplyOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

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
    private String order_state="100";

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
        ArrayList<MySupplyOrderResponseBean.DataBean> data=bean.getData();
        Log.e("测试", "setMySupplyOrder: "+data.get(0));
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
