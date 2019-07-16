package com.zthx.npj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.SecKillAdpter;
import com.zthx.npj.net.been.SecKillTodayResponseBean;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SeckillListFragment extends Fragment {
    @BindView(R.id.fg_seckill_list_rv)
    RecyclerView fgSeckillListRv;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_seckill_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    public Fragment newIntent(String type){
        SeckillListFragment fragment=new SeckillListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        getSeckillMsg();
    }

    private void getSeckillMsg() {
        switch (getArguments().getString("type")){
            case "1":
                getSeckillOver();
                break;
            case "2":
                getSeckillGoing();
                break;
            case "3":
                getSeckillStart();
                break;
        }
    }

    private void getSeckillStart() {
        SecKillSubscribe.getSecKillOverList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSeckillStart(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSeckillStart(String result) {
        SecKillTodayResponseBean bean=GsonUtils.fromJson(result,SecKillTodayResponseBean.class);
        ArrayList<SecKillTodayResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter=new SecKillAdpter(getContext(),data,getArguments().getString("type"));
        fgSeckillListRv.setAdapter(adapter);
    }

    private void getSeckillGoing() {
        SecKillSubscribe.getSecKillTodayList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSeckillGoing(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSeckillGoing(String result) {
        SecKillTodayResponseBean bean=GsonUtils.fromJson(result,SecKillTodayResponseBean.class);
        ArrayList<SecKillTodayResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter=new SecKillAdpter(getContext(),data,getArguments().getString("type"));
        fgSeckillListRv.setAdapter(adapter);
    }

    private void getSeckillOver() {
        SecKillSubscribe.getSecKillOverList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSeckillOver(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setSeckillOver(String result) {
        SecKillTodayResponseBean bean=GsonUtils.fromJson(result,SecKillTodayResponseBean.class);
        ArrayList<SecKillTodayResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter=new SecKillAdpter(getContext(),data,getArguments().getString("type"));
        fgSeckillListRv.setAdapter(adapter);
    }
}
