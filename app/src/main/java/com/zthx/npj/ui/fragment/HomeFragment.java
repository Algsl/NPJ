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

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ClassfiysActivity;
import com.zthx.npj.ui.GameActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.HomeSearchActivity;
import com.zthx.npj.ui.LocationStoreActivity;
import com.zthx.npj.ui.MembershipPackageActivity;
import com.zthx.npj.ui.MessageCenterActivity;
import com.zthx.npj.ui.PreSellActivity;
import com.zthx.npj.ui.SecKillActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

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
    ImageView fgHomeIvMessage;
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
    @BindView(R.id.fg_home_mcv_headImg)
    MyCircleView fgHomeMcvHeadImg;
    @BindView(R.id.fg_home_tv_title)
    TextView fgHomeTvTitle;
    @BindView(R.id.fg_home_ll_classify)
    LinearLayout fgHomeLlClassify;
    @BindView(R.id.fg_home_tv_myLower)
    TextView fgHomeTvMyLower;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;

    private static final int REQUEST_CODE_SCAN = 1;
    private boolean isChild;

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


        initBanner();

        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        fgHomeShoppingCast.setLayoutManager(layoutManager);
        //初始化适配器
        String mainRecommend = SharePerferenceUtils.getMainRecommend(getActivity());
        RecommendResponseBean bean = GsonUtils.fromJson(mainRecommend, RecommendResponseBean.class);
        final ArrayList<RecommendResponseBean.DataBean> data = bean.getData();

        final HomeGoodsAdapter mAdapter = new HomeGoodsAdapter(getActivity(), data);
        mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        fgHomeShoppingCast.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        fgHomeShoppingCast.setItemAnimator(new DefaultItemAnimator());
        fgHomeShoppingCast.setAdapter(mAdapter);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            if(isChild){
                getChildHome();
            }else{
                getGroupHome();
            }
            refreshlayout.finishRefresh();
            Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderPush();
    }


    private void getOrderPush() {
        MainSubscribe.orderPush(new OrderPushBean(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrderPush(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setOrderPush(String result) {
        OrderPushResponseBean bean = GsonUtils.fromJson(result, OrderPushResponseBean.class);
        Glide.with(getContext()).load(Uri.parse(bean.getData().get(0).getHead_img())).into(fgHomeMcvHeadImg);
        fgHomeTvTitle.setText(bean.getData().get(0).getTitle());
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {

        String mainBanner = SharePerferenceUtils.getMainBanner(getActivity());
        BannerResponseBean bean = GsonUtils.fromJson(mainBanner, BannerResponseBean.class);
        ArrayList<BannerResponseBean.DataBean> data = bean.getData();
        ArrayList<Uri> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(Uri.parse(data.get(i).getImg()));
            list2.add(data.get(i).getTitle());
        }
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
                Log.e("huang", "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick({R.id.fg_home_iv_scan, R.id.fg_home_et_search, R.id.fg_home_ll_classify, R.id.fg_home_iv_message, R.id.fg_home_ll_secKill, R.id.fg_home_ll_presell, R.id.fg_home_ll_local, R.id.fg_home_ll_gift, R.id.fg_home_rl_go_game, R.id.fg_home_ll_recommend})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fg_home_iv_scan:
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
                intent = new Intent(getActivity(), MembershipPackageActivity.class);
                startActivity(intent);
                break;
            case R.id.fg_home_rl_go_game:
                startActivity(new Intent(getContext(), GameActivity.class));
                break;
            case R.id.fg_home_ll_recommend:
                toggle();

                break;
        }
    }

    private void toggle() {
        isChild = !isChild;
        if (isChild) {
            fgHomeTvMyLower.setText("下级用户首页");
            getChildHome();
        } else {
            fgHomeTvMyLower.setText("精品推荐 好货不断");
            getGroupHome();
        }
    }

    private void getGroupHome() {
        MainSubscribe.getRecommend(SharePerferenceUtils.getUserId(getContext()), "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                RecommendResponseBean bean = GsonUtils.fromJson(result, RecommendResponseBean.class);
                final ArrayList<RecommendResponseBean.DataBean> data = bean.getData();
                HomeGoodsAdapter mAdapter = new HomeGoodsAdapter(getActivity(), data);
                mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
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

    private void getChildHome() {
        MainSubscribe.childHome(SharePerferenceUtils.getUserId(getContext()), "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: " + result);
                RecommendResponseBean bean = GsonUtils.fromJson(result, RecommendResponseBean.class);
                final ArrayList<RecommendResponseBean.DataBean> data = bean.getData();
                HomeGoodsAdapter mAdapter = new HomeGoodsAdapter(getActivity(), data);
                mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
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
                    Log.e("测试", "onActivityResult: "+context );
                    Uri uri = Uri.parse(context);
                    String page = uri.getQueryParameter("page");
                    String type = uri.getQueryParameter("type");
                    String id = uri.getQueryParameter("id");
                    if (page.equals("goodsDetail")) {
                        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                        intent.setAction(type);
                        intent.putExtra("goods_id", id + "");
                        startActivity(intent);
                    } else if (page.equals("tuijian")) {
                        startActivity(new Intent(getContext(), MembershipPackageActivity.class));
                    }
                }
                break;
        }
    }
}
