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
import com.zthx.npj.adapter.SecKillAdpter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.ui.GoodsDetailActivity;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        fgSecKillRv.setLayoutManager(linearLayoutManager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        CommentGoodsBeen commentGoodsBeen = new CommentGoodsBeen();
        commentGoodsBeen.setGoodsSellNum("20");
        commentGoodsBeen.setGoodsOldPrice("200");
        commentGoodsBeen.setTotalNum("100");
        commentGoodsBeen.setGoodsPrice("100");
        commentGoodsBeen.setGoodsTitle("asdawweae");
        commentGoodsBeen.setGoodsLeb("23335ddddd");
        for (int i =0;i<12;i++) {
            list.add(commentGoodsBeen);
        }
        SecKillAdpter adapter = new SecKillAdpter(getActivity(),list);
        adapter.setOnItemClickListener(new SecKillAdpter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.setAction("miaosha");
                startActivity(intent);
            }
        });
        fgSecKillRv.setAdapter(adapter);
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
