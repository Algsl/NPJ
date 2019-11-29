package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;
import com.zthx.npj.R;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
import com.zthx.npj.net.been.OrderPushBean;
import com.zthx.npj.net.been.OrderPushResponseBean;
import com.zthx.npj.net.been.RecommendResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.NetUtil;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.MessageCenterActivity;
import com.zthx.npj.tencent.util.TencentUtil;
import com.zthx.npj.ui.BannerActivity;
import com.zthx.npj.ui.ClassfiysActivity;
import com.zthx.npj.ui.GameActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.HomeSearchActivity;
import com.zthx.npj.ui.LocationStoreActivity;
import com.zthx.npj.ui.MembershipPackageActivity;
import com.zthx.npj.ui.PayToStoreActivity;
import com.zthx.npj.ui.PreSellActivity;
import com.zthx.npj.ui.SecKillActivity;
import com.zthx.npj.ui.SupplyProductsActivity;
import com.zthx.npj.ui.TestActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.marquee.AppBus;
import com.zthx.npj.utils.marquee.LooperBean;
import com.zthx.npj.utils.marquee.LooperImageView;
import com.zthx.npj.utils.marquee.LooperTextView;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

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
    @BindView(R.id.fg_home_iv_message)
    RelativeLayout fgHomeIvMessage;
    @BindView(R.id.fg_home_ll_secKill)
    LinearLayout fgHomeLlSecKill;
    @BindView(R.id.fg_home_ll_presell)
    LinearLayout fgHomeLlPresell;
    @BindView(R.id.fg_home_ll_local)
    LinearLayout fgHomeLlLocal;
    @BindView(R.id.fg_home_ll_gift)
    LinearLayout fgHomeLlGift;
    //@BindView(R.id.fg_home_refresh)
    //SmartRefreshLayout fgHomeRefresh;
    @BindView(R.id.fg_home_rl_go_game)
    RelativeLayout fgHomeLlGoGame;
    @BindView(R.id.fg_home_ll_recommend)
    LinearLayout fgHomeLlRecommend;
    /*@BindView(R.id.fg_home_mcv_headImg)
    ImageView fgHomeMcvHeadImg;
    @BindView(R.id.fg_home_tv_title)
    TextView fgHomeTvTitle;*/
    @BindView(R.id.fg_home_ll_classify)
    LinearLayout fgHomeLlClassify;
    @BindView(R.id.fg_home_tv_myLower)
    TextView fgHomeTvMyLower;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.seeMore)
    TextView seeMore;

    @BindView(R.id.fg_home_mcv_headImg)
    LooperImageView fgHomeMcvHeadImg;
    @BindView(R.id.fg_home_tv_title)
    LooperTextView fgHomeTvTitle;
    @BindView(R.id.tv_unReadMsg)
    TextView tvUnReadMsg;


    private Unbinder unbinder;

    private static final int REQUEST_CODE_SCAN = 1;
    private boolean isChild;
    private ArrayList<RecommendResponseBean.DataBean> data;
    private int page = 1;
    private HomeGoodsAdapter mAdapter;
    private List<LooperBean> looperBeenList;
    private int position;
    private ArrayList<LooperBean> looperBeans = new ArrayList<>();



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //looperBeenList = generateTips();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        AppBus.getInstance().register(this);

        //initBanner();
        //getMainRecommed(page+"");
        getGroupHome(page + "");
        getBanner();

        getOrderPush();
        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        fgHomeShoppingCast.setLayoutManager(layoutManager);
        //初始化适配器

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (isChild) {
                    getChildHome(page + "");
                } else {
                    getGroupHome(page + "");
                }
                if (mAdapter != null) {
                    mAdapter.clearData();
                }
                seeMore.setText("查看更多");
                getBanner();
                getOrderPush();
                refreshLayout.setLoadmoreFinished(false);
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page += 1;
                if (isChild) {
                    getChildHome(page + "");
                } else {
                    getGroupHome(page + "");
                }
                refreshlayout.finishLoadmore();
            }
        });
        if(!SharePerferenceUtils.getUserId(getContext()).equals("")){
            if(!TencentUtil.getUnReadNum().equals("0")){
                tvUnReadMsg.setVisibility(View.VISIBLE);
                if(Long.parseLong(TencentUtil.getUnReadNum())>99){
                    tvUnReadMsg.setText("⋯");
                }else{
                    tvUnReadMsg.setText(TencentUtil.getUnReadNum()+"");
                }
            }else{
                tvUnReadMsg.setVisibility(View.GONE);
            }
        }
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!SharePerferenceUtils.getUserId(getContext()).equals("")){
            if(!TencentUtil.getUnReadNum().equals("0")){
                tvUnReadMsg.setVisibility(View.VISIBLE);
                if(Long.parseLong(TencentUtil.getUnReadNum())>99){
                    tvUnReadMsg.setText("⋯");
                }else{
                    tvUnReadMsg.setText(TencentUtil.getUnReadNum()+"");
                }
            }else{
                tvUnReadMsg.setVisibility(View.GONE);
            }
        }
    }


    private void getBanner() {
        MainSubscribe.getMainBanner(Const.MAIN_BANNER_TYPE, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                final BannerResponseBean bean = GsonUtils.fromJson(result, BannerResponseBean.class);
                ArrayList<BannerResponseBean.DataBean> data = bean.getData();
                ArrayList<Uri> list = new ArrayList<>();
                ArrayList<String> list2 = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(Uri.parse(data.get(i).getImg()));
                    list2.add(data.get(i).getTitle());
                }
                initBanner(bean, list, list2);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    @Override
    public void onResume() {
        super.onResume();

        if (!NetUtil.isNetworkConnected(getContext())) {
            Toast.makeText(getContext(), "请打开网络连接", Toast.LENGTH_SHORT).show();
        } else {
            //getOrderPush();
        }
        if(!SharePerferenceUtils.getUserId(getContext()).equals("")){
            if(!TencentUtil.getUnReadNum().equals("0")){
                tvUnReadMsg.setVisibility(View.VISIBLE);
                if(Long.parseLong(TencentUtil.getUnReadNum())>99){
                    tvUnReadMsg.setText("⋯");
                }else{
                    tvUnReadMsg.setText(TencentUtil.getUnReadNum()+"");
                }
            }else{
                tvUnReadMsg.setVisibility(View.GONE);
            }
        }
    }

    private void initBanner(final BannerResponseBean bean, ArrayList<Uri> list, ArrayList<String> list2) {

        //final String mainBanner = SharePerferenceUtils.getMainBanner(getActivity());

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            banner.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 14);
                }
            });
            banner.setClipToOutline(true);
        }
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(list2);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = null;
                String[] strs=bean.getData().get(position).getRemark().split(",");
                if(strs[0].equals("0")){
                    intent = new Intent(getContext(), BannerActivity.class);
                    intent.putExtra("title", bean.getData().get(position).getTitle());
                    intent.putExtra("url", strs[1]);
                }else if(strs[0].equals("1")){
                    intent = new Intent(getContext(), GoodsDetailActivity.class);
                    intent.setAction("goods");
                    intent.putExtra("goods_id", strs[1]);
                }

               /* if (bean.getData().get(position).getId() == 15) {

                } else if (bean.getData().get(position).getId() == 20) {
                    intent = new Intent(getContext(), BannerActivity.class);
                    intent.putExtra("title", bean.getData().get(position).getTitle());
                    intent.putExtra("id", "1");
                } else if (bean.getData().get(position).getId() == 24) {
                    intent = new Intent(getContext(), BannerActivity.class);
                    intent.putExtra("title", bean.getData().get(position).getTitle());
                    intent.putExtra("id", "2");
                }*/
                startActivity(intent);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick({R.id.fg_home_iv_scan, R.id.fg_home_et_search, R.id.fg_home_ll_classify,
            R.id.fg_home_iv_message, R.id.fg_home_ll_secKill, R.id.fg_home_ll_presell,
            R.id.fg_home_ll_local, R.id.fg_home_ll_gift, R.id.fg_home_rl_go_game,
            R.id.fg_home_ll_recommend})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fg_home_iv_scan://扫描功能
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
            case R.id.fg_home_et_search:
                intent = new Intent(getActivity(), HomeSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_ll_classify:
                intent = new Intent(getActivity(), ClassfiysActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_iv_message:
                if (SharePerferenceUtils.getUserId(getContext()).equals("")) {
                    Toast.makeText(getContext(), "请先完成登录", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getActivity(), MessageCenterActivity.class);
                    startActivity(intent);
                }
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
                intent = new Intent(getActivity(), MembershipPackageActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_rl_go_game:
                if (SharePerferenceUtils.getUserId(getContext()).equals("")) {
                    Toast.makeText(getContext(), "请先完成登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getContext(), GameActivity.class));
                }
                //startActivity(new Intent(getContext(),TestActivity.class));
                break;
            case R.id.fg_home_ll_recommend:
                toggle();
                break;
        }
    }

    private void toggle() {
        page = 1;
        isChild = !isChild;
        if (mAdapter != null) {
            mAdapter.clearData();
        }
        if (isChild) {
            fgHomeTvMyLower.setText("下级用户首页");
            getChildHome(page + "");
        } else {
            fgHomeTvMyLower.setText("精品推荐 好货不断");
            getGroupHome(page + "");
        }
    }

    private void getGroupHome(final String page) {
        MainSubscribe.getRecommend(SharePerferenceUtils.getUserId(getContext()), page, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                RecommendResponseBean bean = GsonUtils.fromJson(result, RecommendResponseBean.class);
                ArrayList<RecommendResponseBean.DataBean> data = bean.getData();
                if (mAdapter == null) {
                    mAdapter = new HomeGoodsAdapter(getActivity(), data);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        mAdapter.addData(data);
                    }
                }

                mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<RecommendResponseBean.DataBean> list) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, list.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                fgHomeShoppingCast.setItemAnimator(new DefaultItemAnimator());
                fgHomeShoppingCast.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getChildHome(final String page) {
        MainSubscribe.childHome(SharePerferenceUtils.getUserId(getContext()), page, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                RecommendResponseBean bean = GsonUtils.fromJson(result, RecommendResponseBean.class);
                ArrayList<RecommendResponseBean.DataBean> data = bean.getData();
                if (mAdapter == null) {
                    mAdapter = new HomeGoodsAdapter(getActivity(), data);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        mAdapter.addData(data);
                    }
                }

                mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<RecommendResponseBean.DataBean> list) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, list.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                fgHomeShoppingCast.setItemAnimator(new DefaultItemAnimator());
                fgHomeShoppingCast.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getOrderPush() {
        MainSubscribe.orderPush(new OrderPushBean(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderPushResponseBean bean = GsonUtils.fromJson(result, OrderPushResponseBean.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    looperBeans.add(new LooperBean(bean.getData().get(i).getHead_img(), bean.getData().get(i).getTitle()));
                }
                looperBeenList = looperBeans;
                onStartBanner();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void onStartBanner() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fgHomeMcvHeadImg.setTipList(looperBeenList, position);
                fgHomeTvTitle.setTipList(looperBeenList, position);
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == RESULT_OK) {
                    String context = data.getStringExtra(Constant.CODED_CONTENT);
                    Uri uri = Uri.parse(context);
                    String type = uri.getQueryParameter("type");
                    String id = uri.getQueryParameter("id");

                    if (type==null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW); //Intent.ACTION_VIEW固定写法
                        intent.setData(Uri.parse(context)); //url是你要跳转的网页地址
                        startActivity(intent);
                    } else {
                        Intent intent=null;
                        /*if (page.equals("goodsDetail")) {
                            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                            intent.setAction(type);
                            intent.putExtra("goods_id", id + "");
                            startActivity(intent);
                        } else if (page.equals("tuijian")) {
                            startActivity(new Intent(getContext(), MembershipPackageActivity.class));
                        } else if (page.equals("payStore")) {
                            Intent intent = new Intent(getContext(), PayToStoreActivity.class);
                            intent.putExtra("key0", id);
                            startActivity(intent);
                        }*/
                        if (type.equals("qianggou")) {
                            intent = new Intent(getContext(), GoodsDetailActivity.class);
                            intent.setAction("miaosha");
                            intent.putExtra("goods_id", id + "");
                        } else if (type.equals("zhongchou")) {
                            intent = new Intent(getContext(), GoodsDetailActivity.class);
                            intent.setAction("presell");
                            intent.putExtra("goods_id", id + "");
                        } else if (type.equals("putong")) {
                            intent = new Intent(getContext(), GoodsDetailActivity.class);
                            intent.setAction("Goods");
                            intent.putExtra("goods_id", id + "");
                        }else if (type.equals("gongying")) {
                            intent = new Intent(getContext(), SupplyProductsActivity.class);
                            intent.setAction(Const.SUPPLY_DETAIL);
                            intent.putExtra("goods_id", id+"");
                        }else if (type.equals("qiugou")) {
                            intent = new Intent(getContext(), SupplyProductsActivity.class);
                            intent.setAction(Const.NEED_DETAIL);
                            intent.putExtra("goods_id", id+"");
                        }else if (type.equals("tuijian")) {
                            intent = new Intent(getContext(), MembershipPackageActivity.class);
                        }else if (type.equals("dianpu")) {
                            intent = new Intent(getContext(), PayToStoreActivity.class);
                            intent.putExtra("key0", id);
                        } else{
                            /*intent = new Intent(this, WebViewActivity.class);
                            intent.putExtra("discover_url", uri);*/
                        }
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    private List<LooperBean> generateTips() {
        List<LooperBean> tips = SharePerferenceUtils.getLooperBeans();
        return tips;
    }



    /*@Override
    public void onStart() {
        super.onStart();

    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }
}
