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
import com.zthx.npj.adapter.SpokesmanQuanLiAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.SpokesmanQuanLiResponsebean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
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
public class SpokesmanFragment extends Fragment {
    @BindView(R.id.fg_spokesman_rv)
    RecyclerView fgSpokesmanRv;
    Unbinder unbinder;

    public SpokesmanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spokesman, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        return view;
    }

    private void getData() {

        GiftSubscribe.getSpokesmanQuan(SharePerferenceUtils.getUserId(getActivity()), SharePerferenceUtils.getToken(getActivity()), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                fgSpokesmanRv.setLayoutManager(manager);
                SpokesmanQuanLiResponsebean spokesmanQuanLiResponsebean = GsonUtils.fromJson(result, SpokesmanQuanLiResponsebean.class);
                ArrayList<SpokesmanQuanLiResponsebean.DataBean> data = spokesmanQuanLiResponsebean.getData();
                SpokesmanQuanLiAdapter mAdapter = new SpokesmanQuanLiAdapter(getActivity(),data);
                fgSpokesmanRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {
                String result="{\n" +
                        "    \"code\": 1,\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"id\": 1,\n" +
                        "            \"title\": \"终身免费领取面膜\",\n" +
                        "            \"description\": \"每天收取8到48克芦荟汁，也可以摘取好友的芦荟汁，达到298克即可免费兑换一盒价值298元的面膜！\",\n" +
                        "            \"img\": \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\",\n" +
                        "            \"remark\": \"mianmo\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 2,\n" +
                        "            \"title\": \"可以发布自己的礼包\",\n" +
                        "            \"description\": \"可以发布自己的礼包可以发布自己的礼包可以发布自己的礼包可以发布自己的礼包可以发布自己的礼包\",\n" +
                        "            \"img\": \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\",\n" +
                        "            \"remark\": \"libao\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"msg\": \"加载成功\"\n" +
                        "}";
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                fgSpokesmanRv.setLayoutManager(layoutManager);
                SpokesmanQuanLiResponsebean spokesmanQuanLiResponsebean = GsonUtils.fromJson(result, SpokesmanQuanLiResponsebean.class);
                ArrayList<SpokesmanQuanLiResponsebean.DataBean> data = spokesmanQuanLiResponsebean.getData();
                SpokesmanQuanLiAdapter mAdapter = new SpokesmanQuanLiAdapter(getContext(),data);
                fgSpokesmanRv.setAdapter(mAdapter);
            }
        }));
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
