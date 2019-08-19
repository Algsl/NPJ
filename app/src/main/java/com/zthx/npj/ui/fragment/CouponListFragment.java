package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MyCouponAdapter;
import com.zthx.npj.adapter.MyCouponListAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.MsgCodeResponseBeen;
import com.zthx.npj.net.been.MyCouponBean;
import com.zthx.npj.net.been.MyCouponResponseBean;
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
public class CouponListFragment extends Fragment {

    @BindView(R.id.fg_coupon_list_rv)
    RecyclerView fgCouponListRv;
    Unbinder unbinder;

    private String user_id=SharePerferenceUtils.getUserId(getContext());
    private String token=SharePerferenceUtils.getToken(getContext());

    public CouponListFragment() {
        // Required empty public constructor
    }


    public Fragment getNewOnce(String status){
        CouponListFragment fragment=new CouponListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("status",status);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        getCouponList();
        return view;
    }

    private void getCouponList() {
        SetSubscribe.myCoupon(user_id,token,getArguments().getString("status"),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setCoupon(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setCoupon(String result) {
        String test="{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"status\": 3,\n" +
                "            \"title\": \"订单满299元使用\",\n" +
                "            \"money\": \"5.00\",\n" +
                "            \"type\": 1,\n" +
                "            \"condition\": \"299.00\",\n" +
                "            \"begin_time\": 1559318400,\n" +
                "            \"end_time\": 1560009599,\n" +
                "            \"coup_status\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"msg\": \"加载成功\"\n" +
                "}";
        MyCouponResponseBean bean=GsonUtils.fromJson(test,MyCouponResponseBean.class);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        fgCouponListRv.setLayoutManager(layoutManager);
        MyCouponAdapter adapter=new MyCouponAdapter(getContext(),bean.getData(),getArguments().getString("status"));
        fgCouponListRv.setItemAnimator(new DefaultItemAnimator());
        fgCouponListRv.setAdapter(adapter);
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
