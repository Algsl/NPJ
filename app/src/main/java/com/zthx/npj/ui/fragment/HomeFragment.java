package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.net.been.HomeGoodsBeen;
import com.zthx.npj.ui.ClassfiyActivity;
import com.zthx.npj.ui.HomeSearchActivity;
import com.zthx.npj.ui.LocationStoreActivity;
import com.zthx.npj.ui.MessageCenterActivity;
import com.zthx.npj.ui.PreSellActivity;
import com.zthx.npj.ui.SecKillActivity;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fg_home_shopping_cast)
    RecyclerView fgHomeShoppingCast;
    @BindView(R.id.fg_home_iv_scan)
    ImageView fgHomeIvScan;
    @BindView(R.id.fg_home_et_search)
    EditText fgHomeEtSearch;
    @BindView(R.id.fg_home_iv_classify)
    ImageView fgHomeIvClassify;
    @BindView(R.id.fg_home_iv_message)
    ImageView fgHomeIvMessage;
    @BindView(R.id.fg_home_ll_secKill)
    LinearLayout fgHomeLlSecKill;
    @BindView(R.id.fg_home_ll_presell)
    LinearLayout fgHomeLlPresell;
    @BindView(R.id.fg_home_ll_local)
    LinearLayout fgHomeLlLocal;
    @BindView(R.id.fg_home_ll_gift)
    LinearLayout fgHomeLlGift;
//    @BindView(R.id.fg_home_refresh)
//    SmartRefreshLayout fgHomeRefresh;
    @BindView(R.id.fg_home_rl_go_game)
    RelativeLayout fgHomeLlGoGame;
    @BindView(R.id.fg_home_ll_recommend)
    LinearLayout fgHomeLlRecommend;
    private Unbinder unbinder;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //本地图片数据（资源文件）
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.ic_action011);
        list.add(R.mipmap.ic_action027);
        list.add(R.mipmap.ic_action029);
        initBanner(list);


        //通过findViewById拿到RecyclerView实例
        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL,false);
        fgHomeShoppingCast.setLayoutManager(layoutManager);
        //初始化适配器
        List<HomeGoodsBeen> list3 = new ArrayList<>();
        HomeGoodsBeen HomeGoodsBeen = new HomeGoodsBeen();
        HomeGoodsBeen.setGoodsNewPrice("1");
        HomeGoodsBeen.setGoodsOldPrice("2");
        HomeGoodsBeen.setGoodsPic("123");
        HomeGoodsBeen.setGoodsTitle("1231245124");
        HomeGoodsBeen.setMallName("ddddd");
        HomeGoodsBeen.setMallPic("ssadasd");
        for (int i = 0; i < 20; i++) {
            list3.add(HomeGoodsBeen);
        }
        HomeGoodsAdapter mAdapter = new HomeGoodsAdapter(getActivity(), list3);
        mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_LONG).show();
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        fgHomeShoppingCast.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        fgHomeShoppingCast.setAdapter(mAdapter);
        return view;
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

    @OnClick({R.id.fg_home_iv_scan, R.id.fg_home_et_search, R.id.fg_home_iv_classify, R.id.fg_home_iv_message, R.id.fg_home_ll_secKill, R.id.fg_home_ll_presell, R.id.fg_home_ll_local, R.id.fg_home_ll_gift,R.id.fg_home_rl_go_game, R.id.fg_home_ll_recommend})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fg_home_iv_scan:
                Toast.makeText(getActivity(), "erweima", Toast.LENGTH_LONG).show();
                break;
            case R.id.fg_home_et_search:
                intent = new Intent(getActivity(), HomeSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_iv_classify:
                intent = new Intent(getActivity(), ClassfiyActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_iv_message:
                intent = new Intent(getActivity(), MessageCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_ll_secKill:
                intent = new Intent(getActivity(), SecKillActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_ll_presell:
                intent = new Intent(getActivity(), PreSellActivity.class);
               startActivity(intent);
                break;
            case R.id.fg_home_ll_local:
                intent = new Intent(getActivity(), LocationStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_ll_gift:
                Toast.makeText(getActivity(), "libao", Toast.LENGTH_LONG).show();
                break;
            case R.id.fg_home_rl_go_game:
                Toast.makeText(getActivity(), "game", Toast.LENGTH_LONG).show();
                break;
            case R.id.fg_home_ll_recommend:
                Toast.makeText(getActivity(), "tuijian", Toast.LENGTH_LONG).show();
                break;
        }
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
