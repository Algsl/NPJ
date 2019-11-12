package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
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
import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverNeedAdapter;
import com.zthx.npj.adapter.DiscoverSupplyAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
import com.zthx.npj.net.been.NeedListResponseBean;
import com.zthx.npj.net.been.SupplyListBean;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.BannerActivity;
import com.zthx.npj.ui.SupplyMessageActivity;
import com.zthx.npj.ui.SupplyProductsActivity;
import com.zthx.npj.ui.SupplySearchActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;

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
    @BindView(R.id.fg_discover_supply_search)
    EditText fgDiscoverSupplySearch;
    @BindView(R.id.fg_discover_supply_tv_supply)
    TextView fgDiscoverSupplyTvSupply;
    @BindView(R.id.fg_discover_supply_tv_need)
    TextView fgDiscoverSupplyTvNeed;
    @BindView(R.id.fg_discover_supply_tv_company)
    TextView fgDiscoverSupplyTvCompany;
    @BindView(R.id.fg_discover_supply_tv_new)
    TextView fgDiscoverSupplyTvNew;
    @BindView(R.id.fg_discover_supply_tv_location)
    TextView fgDiscoverSupplyTvLocation;
    @BindView(R.id.fg_discover_supply_tv_sell_num)
    TextView fgDiscoverSupplyTvSellNum;
    @BindView(R.id.fg_discover_supply_tv_xinyong)
    TextView fgDiscoverSupplyTvXinyong;
    @BindView(R.id.fg_discover_supply_tv_price)
    TextView fgDiscoverSupplyTvPrice;

    @BindView(R.id.fg_discover_supply_ll_supply)
    LinearLayout fgDiscoverSupplyLlSupply;
    @BindView(R.id.fg_discover_need_tv_new)
    TextView fgDiscoverNeedTvNew;
    @BindView(R.id.fg_discover_need_tv_location)
    TextView fgDiscoverNeedTvLocation;
    @BindView(R.id.fg_discover_need_rv)
    RecyclerView fgDiscoverNeedRv;
    @BindView(R.id.fg_discover_supply_ll_need)
    LinearLayout fgDiscoverSupplyLlNeed;
    @BindView(R.id.fg_discover_btn_cancel)
    Button fgDiscoverBtnCancel;
    @BindView(R.id.fg_discover_btn_issue)
    Button fgDiscoverBtnIssue;
    @BindView(R.id.fg_discover_supply_ll)
    LinearLayout fgDiscoverSupplyLl;
    @BindView(R.id.fg_discover_supply_rv_search)
    RelativeLayout fgDiscoverSupplyRvSearch;
    @BindView(R.id.fg_discover_wv_business)
    WebView fgDiscoverWvBusiness;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.seeMore)
    TextView seeMore;
    @BindView(R.id.seeMore1)
    TextView seeMore1;


    private String type1 = "1";//供应的销量的类型
    private String type2 = "1";//求购的销量、距离类型
    private int itemType = 1;//供求类型

    private int page = 1;
    private int page2 = 1;
    private DiscoverSupplyAdapter mAdapter;
    private DiscoverNeedAdapter mAdapter2;
    private View view = null;

    public DiscoverSupplyFragment() {

    }

    /**
     * 获取对象实例
     *
     * @return
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_discover_supply, container, false);
        }

        unbinder = ButterKnife.bind(this, view);
        getQiYeList();
        getSupplyData(type1);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (itemType == 1) {
                    page = 1;
                    if (mAdapter != null) {
                        mAdapter.clearData();
                    }
                    seeMore.setText("查看更多");
                } else {
                    page2 = 1;
                    if (mAdapter2 != null) {
                        mAdapter2.clearData();
                    }
                    seeMore1.setText("查看更多");
                }
                //refreshLayout.setLoadmoreFinished(false);
                getSupplyData(type1);
                getNeedData(type2);
                initBanner();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (itemType == 1) {
                    page++;
                    getSupplyData(type1);
                } else {
                    page2++;
                    getNeedData(type2);
                }
                refreshlayout.finishLoadmore();
            }
        });

        initBanner();
        return view;
    }

    private void getSupplyData(String type) {
        SupplyListBean bean = new SupplyListBean();
        bean.setType(type);
        bean.setPage(page + "");
        bean.setLng(SharePerferenceUtils.getLng(getActivity()));
        bean.setLat(SharePerferenceUtils.getLat(getActivity()));

        DiscoverSubscribe.supplyList(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyListResponseBean supplyListResponseBean = GsonUtils.fromJson(result, SupplyListResponseBean.class);
                final ArrayList<SupplyListResponseBean.DataBean> data = supplyListResponseBean.getData();

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                fgDiscoverSupplyRv.setLayoutManager(manager);
                /*if (mAdapter != null) {
                    mAdapter.updateData(data);
                } else {}*/
                if (mAdapter == null) {
                    mAdapter = new DiscoverSupplyAdapter(getContext(), data, false);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            //refreshLayout.setLoadmoreFinished(true);
                        }
                        mAdapter.addData(data);
                    }
                }
                mAdapter.setOnItemClickListener(new DiscoverSupplyAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<SupplyListResponseBean.DataBean> list) {
                        Intent intent = new Intent(getActivity(), SupplyProductsActivity.class);
                        intent.setAction(Const.SUPPLY_DETAIL);
                        intent.putExtra("goods_id", list.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                fgDiscoverSupplyRv.setItemAnimator(new DefaultItemAnimator());
                fgDiscoverSupplyRv.setAdapter(mAdapter);

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getNeedData(String type) {
        SupplyListBean bean = new SupplyListBean();
        bean.setLat(SharePerferenceUtils.getLat(getActivity()));
        bean.setLng(SharePerferenceUtils.getLng(getActivity()));
        bean.setPage(page2 + "");
        bean.setType(type);
        DiscoverSubscribe.needList(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                final ArrayList<NeedListResponseBean.DataBean> data = GsonUtils.fromJson(result, NeedListResponseBean.class).getData();
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                fgDiscoverNeedRv.setLayoutManager(manager);
                if (mAdapter2 == null) {
                    mAdapter2 = new DiscoverNeedAdapter(getContext(), data, false);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore1.setText("没有更多了");
                            //refreshLayout.setLoadmoreFinished(true);
                        }
                        mAdapter2.addData(data);
                    }
                }
                mAdapter2.setOnItemClickListener(new DiscoverNeedAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<NeedListResponseBean.DataBean> list) {
                        Intent intent = new Intent(getActivity(), SupplyProductsActivity.class);
                        intent.setAction(Const.NEED_DETAIL);
                        intent.putExtra("goods_id", list.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                fgDiscoverNeedRv.setItemAnimator(new DefaultItemAnimator());
                fgDiscoverNeedRv.setAdapter(mAdapter2);
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


    @OnClick({R.id.fg_discover_need_tv_new, R.id.fg_discover_need_tv_location, R.id.fg_discover_supply_ll_caigou,
            R.id.fg_discover_supply_ll_gongying, R.id.fg_discover_supply_tv_supply, R.id.fg_discover_supply_tv_need,
            R.id.fg_discover_supply_tv_company, R.id.fg_discover_supply_tv_new, R.id.fg_discover_supply_tv_location,
            R.id.fg_discover_supply_tv_sell_num, R.id.fg_discover_supply_tv_xinyong, R.id.fg_discover_supply_tv_price,
            R.id.fg_discover_btn_cancel, R.id.fg_discover_btn_issue, R.id.fg_discover_supply_search
    })
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            //求购模块选择操作
            //最新
            case R.id.fg_discover_need_tv_new:
                mAdapter2.clearData();
                fgDiscoverNeedTvNew.setTextColor(getResources().getColor(R.color.app_theme));
                fgDiscoverNeedTvLocation.setTextColor(getResources().getColor(R.color.text3));
                type2 = "1";
                getNeedData(type2);
                break;
            //附近
            case R.id.fg_discover_need_tv_location:
                mAdapter2.clearData();
                fgDiscoverNeedTvLocation.setTextColor(getResources().getColor(R.color.app_theme));
                fgDiscoverNeedTvNew.setTextColor(getResources().getColor(R.color.text3));
                type2 = "2";
                getNeedData(type2);
                break;


            case R.id.fg_discover_supply_tv_supply://切换为供应模式
                mAdapter.clearData();
                page = 1;
                itemType = 1;
                fgDiscoverSupplyRvSearch.setVisibility(View.VISIBLE);
                fgDiscoverSupplyLl.setVisibility(View.GONE);
                changeButtonColor(1);
                break;
            case R.id.fg_discover_supply_tv_need://切换为求购模式
                page2 = 1;
                itemType = 2;
                if (mAdapter2 != null) {
                    mAdapter2.clearData();
                }
                fgDiscoverSupplyRvSearch.setVisibility(View.VISIBLE);
                fgDiscoverSupplyLl.setVisibility(View.GONE);
                changeButtonColor(2);
                break;
            case R.id.fg_discover_supply_tv_company:
                fgDiscoverSupplyRvSearch.setVisibility(View.VISIBLE);
                fgDiscoverSupplyLl.setVisibility(View.GONE);
                changeButtonColor(3);
                break;

            //供应模块选择操作
            case R.id.fg_discover_supply_tv_new:
                selectType("1");
                getSupplyData(type1);
                break;
            case R.id.fg_discover_supply_tv_location:
                selectType("2");
                getSupplyData(type1);
                break;
            case R.id.fg_discover_supply_tv_sell_num:
                selectType("3");
                getSupplyData(type1);
                break;
            case R.id.fg_discover_supply_tv_xinyong:
                selectType("4");
                getSupplyData(type1);
                break;
            case R.id.fg_discover_supply_tv_price:
                selectType("5");
                getSupplyData(type1);
                break;
            case R.id.fg_discover_supply_ll_caigou:
                intent = new Intent(getActivity(), SupplyMessageActivity.class);
                intent.putExtra(Const.SUPPLY_TYPE, 1);
                startActivity(intent);
                break;
            case R.id.fg_discover_supply_ll_gongying:
                intent = new Intent(getActivity(), SupplyMessageActivity.class);
                intent.putExtra(Const.SUPPLY_TYPE, 2);
                startActivity(intent);
                break;

            case R.id.fg_discover_btn_cancel:
                fgDiscoverSupplyRvSearch.setVisibility(View.VISIBLE);
                fgDiscoverSupplyLl.setVisibility(View.GONE);
                break;
            case R.id.fg_discover_btn_issue:
                if (SharePerferenceUtils.getUserId(getContext()).equals("")) {
                    Toast.makeText(getContext(), "用户未登录，暂不能发布供求", Toast.LENGTH_SHORT).show();
                } else {
                    fgDiscoverSupplyRvSearch.setVisibility(View.GONE);
                    fgDiscoverSupplyLl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.fg_discover_supply_search:
                startActivity(new Intent(getContext(), SupplySearchActivity.class));
                break;
        }
    }

    //最新、附近、销量、信用、价格之间进行切换
    private void selectType(String type) {
        fgDiscoverSupplyTvNew.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvLocation.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvSellNum.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvXinyong.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvPrice.setTextColor(getResources().getColor(R.color.text3));
        mAdapter.clearData();
        if ("1".equals(type)) {
            fgDiscoverSupplyTvNew.setTextColor(getResources().getColor(R.color.app_theme));
            type1 = "1";
        } else if ("2".equals(type)) {
            fgDiscoverSupplyTvLocation.setTextColor(getResources().getColor(R.color.app_theme));
            type1 = "2";
        } else if ("3".equals(type)) {
            fgDiscoverSupplyTvSellNum.setTextColor(getResources().getColor(R.color.app_theme));
            type1 = "3";
        } else if ("4".equals(type)) {
            fgDiscoverSupplyTvXinyong.setTextColor(getResources().getColor(R.color.app_theme));
            type1 = "4";
        } else {
            fgDiscoverSupplyTvPrice.setTextColor(getResources().getColor(R.color.app_theme));
            type1 = "5";
        }
    }

    //供应、求购、企业目录进行切换
    private void changeButtonColor(int position) {
        fgDiscoverSupplyTvSupply.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvSupply.setBackgroundColor(getResources().getColor(android.R.color.white));
        fgDiscoverSupplyTvNeed.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvNeed.setBackgroundColor(getResources().getColor(android.R.color.white));
        fgDiscoverSupplyTvCompany.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvCompany.setBackgroundColor(getResources().getColor(android.R.color.white));
        if (position == 1) {
            fgDiscoverSupplyTvSupply.setTextColor(getResources().getColor(android.R.color.white));
            fgDiscoverSupplyTvSupply.setBackgroundColor(getResources().getColor(R.color.app_theme));
            fgDiscoverNeedRv.setVisibility(View.GONE);
            fgDiscoverSupplyRv.setVisibility(View.VISIBLE);
            fgDiscoverSupplyLlSupply.setVisibility(View.VISIBLE);
            fgDiscoverSupplyLlNeed.setVisibility(View.GONE);
            fgDiscoverWvBusiness.setVisibility(View.GONE);
            getSupplyData(type1);
        } else if (position == 2) {
            fgDiscoverSupplyTvNeed.setTextColor(getResources().getColor(android.R.color.white));
            fgDiscoverSupplyTvNeed.setBackgroundColor(getResources().getColor(R.color.app_theme));
            fgDiscoverSupplyLlSupply.setVisibility(View.GONE);
            fgDiscoverSupplyLlNeed.setVisibility(View.VISIBLE);
            fgDiscoverNeedRv.setVisibility(View.VISIBLE);
            fgDiscoverSupplyRv.setVisibility(View.GONE);
            fgDiscoverWvBusiness.setVisibility(View.GONE);
            getNeedData(type2);
        } else {
            fgDiscoverSupplyTvCompany.setTextColor(getResources().getColor(android.R.color.white));
            fgDiscoverSupplyTvCompany.setBackgroundColor(getResources().getColor(R.color.app_theme));
            fgDiscoverSupplyLlSupply.setVisibility(View.GONE);
            fgDiscoverSupplyLlNeed.setVisibility(View.GONE);
            fgDiscoverWvBusiness.setVisibility(View.VISIBLE);
            getQiYeList();
        }
    }

    public void getQiYeList() {
        fgDiscoverWvBusiness.loadUrl("http://www.ynshangji.com/hb-25/");
        fgDiscoverWvBusiness.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initBanner() {
        MainSubscribe.getMainBanner("5", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                        Intent intent = new Intent(getContext(), BannerActivity.class);
                        intent.putExtra("title", bean.getData().get(position).getTitle());
                        intent.putExtra("type", bean.getData().get(position).getType());
                        intent.putExtra("id", bean.getData().get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
