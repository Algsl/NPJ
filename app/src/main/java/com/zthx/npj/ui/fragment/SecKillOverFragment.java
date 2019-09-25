package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.SecKillAdpter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.SecKillTodayResponseBean;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class SecKillOverFragment extends Fragment {
    @BindView(R.id.fg_sec_kill_over_rv)
    RecyclerView fgSecKillOverRv;
    Unbinder unbinder;
    // TODO: Rename parameter arguments, choose names that match

    public SecKillOverFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sec_kill_over, container, false);
        unbinder = ButterKnife.bind(this, view);

        SecKillSubscribe.getSecKillOverList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                SecKillTodayResponseBean secKillTodayResponseBean = GsonUtils.fromJson(result, SecKillTodayResponseBean.class);
                final ArrayList<SecKillTodayResponseBean.DataBean> data = secKillTodayResponseBean.getData();

                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                fgSecKillOverRv.setLayoutManager(layoutManager);
                SecKillAdpter adapter = new SecKillAdpter(getContext(),data,"2");
                adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.setAction("miaosha");
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId());
                        intent.putExtra(Const.SECKILL_STATUS, Const.SECKILL_DONE);
                        startActivity(intent);
                    }

                    @Override
                    public void onBuyClick(int position) {

                    }
                });
                fgSecKillOverRv.setItemAnimator(new DefaultItemAnimator());
                fgSecKillOverRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, getActivity()));
        return view;
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
