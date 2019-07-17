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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverNeedAdapter;
import com.zthx.npj.adapter.DiscoverSupplyAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.NeedListResponseBean;
import com.zthx.npj.net.been.SupplyListBean;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.SupplyProductsActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

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

    DiscoverSupplyAdapter mAdapter;
    DiscoverNeedAdapter mAdapter2;
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
    @BindView(R.id.fg_discoverSupply_ll_gong)
    LinearLayout fgDiscoverSupplyLlGong;


    private String type1="1";
    private String type2="2";



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
        unbinder = ButterKnife.bind(this, view);

        getSupplyData(type1);

        return view;
    }

    private void getSupplyData(String type) {
        SupplyListBean bean = new SupplyListBean();
        bean.setType(type);
        bean.setPage("1");
        bean.setLng(SharePerferenceUtils.getLng(getActivity()));
        bean.setLat(SharePerferenceUtils.getLat(getActivity()));

        DiscoverSubscribe.supplyList(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyListResponseBean supplyListResponseBean = GsonUtils.fromJson(result, SupplyListResponseBean.class);
                final ArrayList<SupplyListResponseBean.DataBean> data = supplyListResponseBean.getData();

                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                fgDiscoverSupplyRv.setLayoutManager(manager);
                if (mAdapter != null) {
                    mAdapter.updateData(data);
                } else {
                    mAdapter = new DiscoverSupplyAdapter(getActivity(), data);
                    mAdapter.setOnItemClickListener(new DiscoverSupplyAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), SupplyProductsActivity.class);
                            intent.setAction(Const.SUPPLY_DETAIL);
                            intent.putExtra(Const.SUPPLY_ID, data.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    fgDiscoverSupplyRv.setAdapter(mAdapter);
                }
                fgDiscoverSupplyLlNeed.setVisibility(View.GONE);
                fgDiscoverSupplyLlSupply.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, getActivity()));
    }

    private void getNeedData(String type) {
        SupplyListBean bean = new SupplyListBean();
        bean.setLat(SharePerferenceUtils.getLat(getActivity()));
        bean.setLng(SharePerferenceUtils.getLng(getActivity()));
        bean.setPage("1");
        bean.setType(type);
        DiscoverSubscribe.needList(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                final ArrayList<NeedListResponseBean.DataBean> data = GsonUtils.fromJson(result, NeedListResponseBean.class).getData();
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                fgDiscoverSupplyRv.setLayoutManager(manager);
                if (mAdapter != null) {
                    mAdapter2.updateData(data);
                } else {
                    mAdapter2 = new DiscoverNeedAdapter(getActivity(), data);
                    mAdapter2.setOnItemClickListener(new DiscoverNeedAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), SupplyProductsActivity.class);
                            intent.setAction(Const.NEED_DETAIL);
                            intent.putExtra(Const.SUPPLY_ID, data.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    fgDiscoverSupplyRv.setAdapter(mAdapter);
                }
                fgDiscoverSupplyLlNeed.setVisibility(View.VISIBLE);
                fgDiscoverSupplyLlSupply.setVisibility(View.GONE);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, getActivity()));
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


    @OnClick({R.id.fg_discover_need_tv_new, R.id.fg_discover_need_tv_location, R.id.fg_discover_supply_ll_caigou, R.id.fg_discover_supply_ll_gongying, R.id.fg_discover_supply_tv_supply, R.id.fg_discover_supply_tv_need, R.id.fg_discover_supply_tv_company, R.id.fg_discover_supply_tv_new, R.id.fg_discover_supply_tv_location, R.id.fg_discover_supply_tv_sell_num, R.id.fg_discover_supply_tv_xinyong, R.id.fg_discover_supply_tv_price})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fg_discover_need_tv_new:
                fgDiscoverNeedTvNew.setTextColor(getResources().getColor(R.color.app_theme));
                fgDiscoverNeedTvLocation.setTextColor(getResources().getColor(R.color.text3));
                getNeedData("1");
                break;
            case R.id.fg_discover_need_tv_location:
                fgDiscoverNeedTvLocation.setTextColor(getResources().getColor(R.color.app_theme));
                fgDiscoverNeedTvNew.setTextColor(getResources().getColor(R.color.text3));
                getNeedData("2");
                break;
            case R.id.fg_discover_supply_tv_supply:
                changeButtonColor(1);
                getSupplyData("1");
                break;
            case R.id.fg_discover_supply_tv_need:
                changeButtonColor(2);
                getNeedData("1");
                break;
            case R.id.fg_discover_supply_tv_company:
                changeButtonColor(3);
                break;
            case R.id.fg_discover_supply_tv_new:
                selectType("1");
                getSupplyData("1");
                break;
            case R.id.fg_discover_supply_tv_location:
                selectType("2");
                getSupplyData("2");
                break;
            case R.id.fg_discover_supply_tv_sell_num:
                selectType("3");
                getSupplyData("3");
                break;
            case R.id.fg_discover_supply_tv_xinyong:
                selectType("4");
                getSupplyData("4");
                break;
            case R.id.fg_discover_supply_tv_price:
                selectType("5");
                getSupplyData("5");
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
        }
    }

    private void selectType(String type) {
        fgDiscoverSupplyTvNew.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvLocation.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvSellNum.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvXinyong.setTextColor(getResources().getColor(R.color.text3));
        fgDiscoverSupplyTvPrice.setTextColor(getResources().getColor(R.color.text3));
        if ("1".equals(type)) {
            fgDiscoverSupplyTvNew.setTextColor(getResources().getColor(R.color.app_theme));
            type1="1";
        } else if ("2".equals(type)) {
            fgDiscoverSupplyTvLocation.setTextColor(getResources().getColor(R.color.app_theme));
            type1="2";
        } else if ("3".equals(type)) {
            fgDiscoverSupplyTvSellNum.setTextColor(getResources().getColor(R.color.app_theme));
            type1="3";
        } else if ("4".equals(type)) {
            fgDiscoverSupplyTvXinyong.setTextColor(getResources().getColor(R.color.app_theme));
            type1="4";
        } else {
            fgDiscoverSupplyTvPrice.setTextColor(getResources().getColor(R.color.app_theme));
            type1="5";
        }
    }

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
            fgDiscoverSupplyLlGong.setVisibility(View.GONE);
            fgDiscoverSupplyLlNeed.setVisibility(View.VISIBLE);
            getSupplyData("1");
        } else if (position == 2) {
            fgDiscoverSupplyTvNeed.setTextColor(getResources().getColor(android.R.color.white));
            fgDiscoverSupplyTvNeed.setBackgroundColor(getResources().getColor(R.color.app_theme));
            getNeedData("1");
        } else {
            fgDiscoverSupplyTvCompany.setTextColor(getResources().getColor(android.R.color.white));
            fgDiscoverSupplyTvCompany.setBackgroundColor(getResources().getColor(R.color.app_theme));
        }
    }
}
