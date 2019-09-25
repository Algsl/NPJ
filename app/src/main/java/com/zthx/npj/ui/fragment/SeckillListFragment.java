package com.zthx.npj.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.SecKillAdpter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.SecKillGoodsDetailResponseBean;
import com.zthx.npj.net.been.SecKillTodayResponseBean;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ConfirmOrderActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.PreSellActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SeckillListFragment extends Fragment {
    @BindView(R.id.fg_seckill_list_rv)
    RecyclerView fgSeckillListRv;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_seckill_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getSeckillMsg();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public Fragment newIntent(String type) {
        SeckillListFragment fragment = new SeckillListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
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
        switch (getArguments().getString("type")) {
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
        SecKillSubscribe.getSecKillStartList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSeckillStart(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    //限时抢购即将开始
    private void setSeckillStart(String result) {
        SecKillTodayResponseBean bean = GsonUtils.fromJson(result, SecKillTodayResponseBean.class);
        final ArrayList<SecKillTodayResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter = new SecKillAdpter(getContext(), data, getArguments().getString("type"));
        fgSeckillListRv.setItemAnimator(new DefaultItemAnimator());
        fgSeckillListRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.setAction("miaosha");
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                intent.putExtra(Const.SECKILL_STATUS, 3);
                startActivity(intent);
            }

            @Override
            public void onBuyClick(int position) {

            }
        });
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

    //限时抢购进行中
    private void setSeckillGoing(String result) {
        SecKillTodayResponseBean bean = GsonUtils.fromJson(result, SecKillTodayResponseBean.class);
        final ArrayList<SecKillTodayResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter = new SecKillAdpter(getContext(), data, getArguments().getString("type"));
        fgSeckillListRv.setItemAnimator(new DefaultItemAnimator());
        fgSeckillListRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.setAction("miaosha");
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                intent.putExtra(Const.SECKILL_STATUS, 2);
                startActivity(intent);
            }

            @Override
            public void onBuyClick(int position) {
                SecKillSubscribe.getSecKillGoodsDetail(data.get(position).getId()+"", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        SecKillGoodsDetailResponseBean secKillGoodsDetailResponseBean = GsonUtils.fromJson(result, SecKillGoodsDetailResponseBean.class);
                        SecKillGoodsDetailResponseBean.DataBean data = secKillGoodsDetailResponseBean.getData();
                        Intent intent = new Intent(getContext(), ConfirmOrderActivity.class);
                        intent.setAction("miaosha");
                        intent.putExtra("count", "1");
                        intent.putExtra(Const.GOODS_ID, data.getId()+"");
                        startActivity(intent);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
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

    //限时抢购已结束
    private void setSeckillOver(String result) {
        SecKillTodayResponseBean bean = GsonUtils.fromJson(result, SecKillTodayResponseBean.class);
        final ArrayList<SecKillTodayResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fgSeckillListRv.setLayoutManager(layoutManager);
        SecKillAdpter adapter = new SecKillAdpter(getContext(), data, getArguments().getString("type"));
        fgSeckillListRv.setItemAnimator(new DefaultItemAnimator());
        fgSeckillListRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.setAction("miaosha");
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                intent.putExtra(Const.SECKILL_STATUS, 1);
                startActivity(intent);
            }

            @Override
            public void onBuyClick(int position) {

            }
        });
    }
}
