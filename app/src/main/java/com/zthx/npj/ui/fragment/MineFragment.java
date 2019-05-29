package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommenGoodsAdatper;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.HelpActivity;
import com.zthx.npj.ui.MainActivity;
import com.zthx.npj.ui.MyAttestationActivity;
import com.zthx.npj.ui.MyCollectActivity;
import com.zthx.npj.ui.MyCouponActivity;
import com.zthx.npj.ui.MyOrderActivity;
import com.zthx.npj.ui.MyStoreActivity;
import com.zthx.npj.ui.MySupplyActivity;
import com.zthx.npj.ui.MyWalletActivity;
import com.zthx.npj.ui.SettingsActivity;
import com.zthx.npj.ui.SpokesmanRightsActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.view.MyCircleView;

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
public class MineFragment extends BaseFragment {

    @BindView(R.id.fg_mine_iv_head_pic)
    MyCircleView fgMineIvHeadPic;
    @BindView(R.id.fg_mine_tv_name)
    TextView fgMineTvName;
    @BindView(R.id.fg_mine_tv_tel)
    TextView fgMineTvTel;
    @BindView(R.id.fg_mine_tv_word)
    TextView fgMineTvWord;
    @BindView(R.id.fg_mine_iv_settings)
    ImageView fgMineIvSettings;
    @BindView(R.id.fg_mine_iv_message)
    ImageView fgMineIvMessage;
    @BindView(R.id.fg_mine_iv_people_right)
    ImageView fgMineIvPeopleRight;
    @BindView(R.id.fg_mine_ll_collect)
    LinearLayout fgMineLlCollect;
    @BindView(R.id.fg_mine_ll_my_coin)
    LinearLayout fgMineLlMyCoin;
    @BindView(R.id.fg_mine_ll_coupon)
    LinearLayout fgMineLlCoupon;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_jiantou)
    ImageView ivJiantou;
    @BindView(R.id.fg_mine_ll_wait_pay)
    LinearLayout fgMineLlWaitPay;
    @BindView(R.id.fg_mine_ll_wait_delivery)
    LinearLayout fgMineLlWaitDelivery;
    @BindView(R.id.fg_mine_ll_wait_take_delivery)
    LinearLayout fgMineLlWaitTakeDelivery;
    @BindView(R.id.fg_mine_ll_wait_evaluate)
    LinearLayout fgMineLlWaitEvaluate;
    @BindView(R.id.fg_mine_ll_custom_service)
    LinearLayout fgMineLlCustomService;
    @BindView(R.id.fg_mine_rv_like)
    RecyclerView fgMineRvLike;
    Unbinder unbinder;
    @BindView(R.id.fg_mine_tv_all_order)
    TextView fgMineTvAllOrder;
    @BindView(R.id.fg_mine_ll_my_wallet)
    LinearLayout fgMineLlMyWallet;
    @BindView(R.id.fg_mine_ll_my_store)
    LinearLayout fgMineLlMyStore;
    @BindView(R.id.fg_mine_ll_my_attestation)
    LinearLayout fgMineLlMyAttestation;
    @BindView(R.id.fg_mine_ll_help)
    LinearLayout fgMineLlHelp;
    @BindView(R.id.fg_mine_ll_my_supply)
    LinearLayout fgMineLlMySupply;

    public MineFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        fgMineRvLike.setLayoutManager(layoutManager);
        //初始化适配器
        List<CommentGoodsBeen> list3 = new ArrayList<>();
        CommentGoodsBeen HomeGoodsBeen = new CommentGoodsBeen();
        HomeGoodsBeen.setGoodsPic("123");
        HomeGoodsBeen.setGoodsTitle("1231245124");
        HomeGoodsBeen.setGoodsSellNum("123");
        HomeGoodsBeen.setGoodsPrice("1333");
        for (int i = 0; i < 20; i++) {
            list3.add(HomeGoodsBeen);
        }
        CommenGoodsAdatper mAdapter = new CommenGoodsAdatper(getActivity(), list3);
        mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_LONG).show();
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        fgMineRvLike.setAdapter(mAdapter);

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

    @OnClick({R.id.fg_mine_iv_settings, R.id.fg_mine_ll_my_supply, R.id.fg_mine_ll_help, R.id.fg_mine_ll_my_store, R.id.fg_mine_ll_my_attestation, R.id.fg_mine_iv_people_right, R.id.fg_mine_ll_collect, R.id.fg_mine_ll_coupon, R.id.fg_mine_ll_my_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_mine_iv_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.fg_mine_iv_people_right:
//                startActivity(new Intent(getActivity(), SpokesmanRightsNoPermissionActivity.class));
                startActivity(new Intent(getActivity(), SpokesmanRightsActivity.class));
                break;
            case R.id.fg_mine_ll_collect:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.fg_mine_tv_all_order:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_coupon:
                startActivity(new Intent(getActivity(), MyCouponActivity.class));
                break;
            case R.id.fg_mine_ll_my_wallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.fg_mine_ll_my_store:
                startActivity(new Intent(getActivity(), MyStoreActivity.class));
                break;
            case R.id.fg_mine_ll_my_attestation:
                startActivity(new Intent(getActivity(), MyAttestationActivity.class));
                break;
            case R.id.fg_mine_ll_help:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
            case R.id.fg_mine_ll_my_supply:
                startActivity(new Intent(getActivity(), MySupplyActivity.class));
                break;
        }
    }
}
