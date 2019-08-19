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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SecKillTodayResponseBean;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SecKillFragment extends Fragment {
    @BindView(R.id.fg_sec_kill_rv)
    RecyclerView fgSecKillRv;
    Unbinder unbinder;

    public SecKillFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sec_kill, container, false);
        unbinder = ButterKnife.bind(this, v);

        SecKillSubscribe.getSecKillTodayList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SecKillTodayResponseBean secKillTodayResponseBean = GsonUtils.fromJson(result, SecKillTodayResponseBean.class);
                final ArrayList<SecKillTodayResponseBean.DataBean> data = secKillTodayResponseBean.getData();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                fgSecKillRv.setLayoutManager(linearLayoutManager);
                SecKillAdpter adapter = new SecKillAdpter(getContext(),data,"1");
                adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.setAction("miaosha");
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId());
                        intent.putExtra(Const.SECKILL_STATUS, Const.SECKILL_DOING);
                        startActivity(intent);
                    }
                });
                fgSecKillRv.setItemAnimator(new DefaultItemAnimator());
                fgSecKillRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, getActivity()));
        return v;
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
