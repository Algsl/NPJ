package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AgricultureKnowledgeAdatper;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
import com.zthx.npj.net.been.DiscoverSolutionListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.AgricultureKnowledgeActivity;
import com.zthx.npj.ui.BannerActivity;
import com.zthx.npj.ui.DefaultPageActivity;
import com.zthx.npj.ui.SystemSolutionActivity;
import com.zthx.npj.ui.WebViewActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.view.GlideImageLoader;

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
public class DiscverServiceFragment extends Fragment {

    private static DiscverServiceFragment mFragment;
    @BindView(R.id.fg_discover_ll_agriculture_knowledge)
    LinearLayout fgDiscoverLlAgricultureKnowledge;
    Unbinder unbinder;
    @BindView(R.id.banner_discover_service)
    Banner bannerDiscoverService;
    @BindView(R.id.fg_discover_ll_information)
    LinearLayout fgDiscoverLlInformation;
    @BindView(R.id.fg_discover_ll_auction)
    LinearLayout fgDiscoverLlAuction;
    @BindView(R.id.fg_discover_ll_goods_for_goods)
    LinearLayout fgDiscoverLlGoodsForGoods;
    @BindView(R.id.fg_discover_ll_loan)
    LinearLayout fgDiscoverLlLoan;
    @BindView(R.id.fg_discover_service_rv)
    RecyclerView fgDiscoverServiceRv;
    @BindView(R.id.fg_discoverService_iv_search)
    ImageView fgDiscoverServiceIvSearch;
    @BindView(R.id.fg_discover_et_search)
    EditText fgDiscoverEtSearch;
    @BindView(R.id.fg_discover_ll)
    LinearLayout fgDiscoverLl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;

    private Intent intent1 = null;
    private boolean flag = false;
    private AgricultureKnowledgeAdatper mAdapter;
    private View view=null;


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
        }else{
            view = inflater.inflate(R.layout.fragment_discver_service, container, false);
        }
        unbinder = ButterKnife.bind(this, view);

        intent1 = new Intent(getContext(), DefaultPageActivity.class);

        mRecyclerView = view.findViewById(R.id.fg_discover_service_rv);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getDataList();
                initBanner();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }
        });

        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        getDataList();
        initBanner();
        return view;
    }

    private void getDataList() {

        DiscoverSubscribe.getSolutionList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                getResult(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getSearchSolution(String str) {
        DiscoverSubscribe.searchSolution(str, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                getResult(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getResult(String result) {
        DiscoverSolutionListResponseBean discoverSolutionListResponseBean = GsonUtils.fromJson(result, DiscoverSolutionListResponseBean.class);
        final ArrayList<DiscoverSolutionListResponseBean.DataBean> data = discoverSolutionListResponseBean.getData();
        if (data.size() <= 0) {
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        //初始化适配器
        mAdapter = new AgricultureKnowledgeAdatper(data, getActivity());
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new AgricultureKnowledgeAdatper.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), SystemSolutionActivity.class);
                intent.putExtra(Const.VIDEO_ID, data.get(position).getId() + "");
                intent.putExtra("title", data.get(position).getTitle());
                startActivity(intent);
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
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


    @OnClick({R.id.fg_discover_ll_agriculture_knowledge, R.id.fg_discover_ll_information,
            R.id.fg_discover_ll_auction, R.id.fg_discover_ll_goods_for_goods,
            R.id.fg_discover_ll_loan, R.id.fg_discoverService_iv_search, R.id.fg_discover_et_search})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.fg_discover_ll_agriculture_knowledge:
                startActivity(new Intent(getActivity(), AgricultureKnowledgeActivity.class));
                break;
            case R.id.fg_discover_ll_information:
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("discover_url", "http://nc.mofcom.gov.cn/channel/jghq2017/index.shtml");
                startActivity(intent);
                break;
            case R.id.fg_discover_ll_auction:
                intent1.putExtra("type","1");
                startActivity(intent1);
                break;
            case R.id.fg_discover_ll_goods_for_goods:
                intent1.putExtra("type","2");
                startActivity(intent1);
                break;
            case R.id.fg_discover_ll_loan:
                intent1.putExtra("type","3");
                startActivity(intent1);
                break;
            case R.id.fg_discoverService_iv_search:
                toggle();
                break;
            case R.id.fg_discover_et_search:
                fgDiscoverEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            String str = fgDiscoverEtSearch.getText().toString().trim();
                            getSearchSolution(str);
                            fgDiscoverEtSearch.setText("");
                            InputMethodManager imm1 = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        return false;
                    }
                });
                break;
        }
    }

    private void toggle() {
        flag = !flag;
        if (flag) {
            fgDiscoverLl.setVisibility(View.VISIBLE);
        } else {
            fgDiscoverLl.setVisibility(View.GONE);
            getDataList();
        }
    }

    private void initBanner() {
        MainSubscribe.getMainBanner("4", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                bannerDiscoverService.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                bannerDiscoverService.setIndicatorGravity(BannerConfig.CENTER);
                //设置图片加载器
                bannerDiscoverService.setImageLoader(new GlideImageLoader());
                //设置图片集合
                bannerDiscoverService.setImages(list);
                //设置banner动画效果
                bannerDiscoverService.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                bannerDiscoverService.isAutoPlay(true);
                //设置标题集合（当banner样式有显示title时）
                bannerDiscoverService.setBannerTitles(list2);
                //设置轮播时间
                bannerDiscoverService.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                bannerDiscoverService.setIndicatorGravity(BannerConfig.RIGHT);
                //设置banner点击事件
                bannerDiscoverService.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(getContext(), BannerActivity.class);
                        intent.putExtra("title", bean.getData().get(position).getTitle());
                        intent.putExtra("type", bean.getData().get(position).getType());
                        intent.putExtra("id", bean.getData().get(position).getId()+"");
                        startActivity(intent);
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                bannerDiscoverService.start();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
