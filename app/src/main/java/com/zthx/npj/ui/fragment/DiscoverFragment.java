package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.MessageCenterActivity;

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
public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.fg_discover_main_tab)
    TabLayout fgDiscoverMainTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            view.requestApplyInsets();
        }
        view.setFitsSystemWindows(false);
        unbinder = ButterKnife.bind(this, view);
        List<String> list = new ArrayList<>();
        list.add("服务");
        list.add("供求");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(DiscverServiceFragment.getInstance());
        list2.add(DiscoverSupplyFragment.getInstance());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getChildFragmentManager(), getContext(), list, list2);
        viewPager.setAdapter(mAdapter);
        fgDiscoverMainTab.setTabMode(TabLayout.MODE_FIXED);
        fgDiscoverMainTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        fgDiscoverMainTab.setupWithViewPager(viewPager);
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
