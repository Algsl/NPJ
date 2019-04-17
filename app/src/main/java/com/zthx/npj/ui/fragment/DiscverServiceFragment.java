package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.net.been.HomeGoodsBeen;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class DiscverServiceFragment extends Fragment {

    private static DiscverServiceFragment mFragment;
    public Banner banner;
    private RecyclerView mRecyclerView;
    public DiscverServiceFragment() {
        // Required empty public constructor
    }
    /**
     * 获取对象实例
     * @return
     */
    public static DiscverServiceFragment getInstance(){
        if(mFragment == null){
            mFragment = new DiscverServiceFragment();
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
        View View = inflater.inflate(R.layout.fragment_discver_service, container, false);
        banner = View.findViewById(R.id.banner_discover_service);
        mRecyclerView = View.findViewById(R.id.fg_discover_service_rv);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.ic_action011);
        list.add(R.mipmap.ic_action027);
        list.add(R.mipmap.ic_action029);
        initBanner(list);

        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        //初始化适配器
//        HomeGoodsAdapter mAdapter = new HomeGoodsAdapter(getActivity(), list3);
//        mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_LONG).show();
//            }
//        });
//        //设置添加或删除item时的动画，这里使用默认动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //设置适配器
//        mRecyclerView.setAdapter(mAdapter);

        return View;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 初始化轮播图
     *
     * @param list
     */
    private void initBanner(List<Integer> list) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.e("huang", "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
