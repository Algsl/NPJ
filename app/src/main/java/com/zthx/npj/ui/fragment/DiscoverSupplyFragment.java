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
import android.widget.LinearLayout;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverSupplyAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.ui.SupplyProductsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class DiscoverSupplyFragment extends Fragment {

    private static DiscoverSupplyFragment mFragment;
    @BindView(R.id.fg_discover_supply_rv)
    RecyclerView fgDiscoverSupplyRv;
    Unbinder unbinder;
    @BindView(R.id.fg_discover_supply_ll_caigou)
    LinearLayout fgDiscoverSupplyLlCaigou;
    @BindView(R.id.fg_discover_supply_ll_gongying)
    LinearLayout fgDiscoverSupplyLlGongying;

    public DiscoverSupplyFragment() {

    }

    /**
     * 获取对象实例
     *
     * @return
     */
    public static DiscoverSupplyFragment getInstance() {
        if (mFragment == null) {
            mFragment = new DiscoverSupplyFragment();
        }
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_supply, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fgDiscoverSupplyRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        DiscoverSupplyAdapter mAdapter = new DiscoverSupplyAdapter(getActivity(), list);
        mAdapter.setOnItemClickListener(new DiscoverSupplyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), SupplyProductsActivity.class));
            }
        });
        fgDiscoverSupplyRv.setAdapter(mAdapter);
        unbinder = ButterKnife.bind(this, view);
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

    @OnClick({R.id.fg_discover_supply_ll_caigou, R.id.fg_discover_supply_ll_gongying})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_discover_supply_ll_caigou:
                startActivity(new Intent(getActivity(), SupplyMessageActivity.class));
                break;
            case R.id.fg_discover_supply_ll_gongying:
                startActivity(new Intent(getActivity(), SupplyMessageActivity.class));
                break;
        }
    }
}
