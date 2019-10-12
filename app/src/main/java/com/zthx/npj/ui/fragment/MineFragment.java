package com.zthx.npj.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.MyTeamResponseBean;
import com.zthx.npj.net.been.OrderResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.NetUtil;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.MessageCenterActivity;
import com.zthx.npj.ui.EditMyOfflineStoreActivity;
import com.zthx.npj.ui.GoodsDetailActivity;
import com.zthx.npj.ui.HelpActivity;
import com.zthx.npj.ui.InputInvitationCodeActivity;
import com.zthx.npj.ui.LoginActivity;
import com.zthx.npj.ui.MembershipPackageActivity;

import com.zthx.npj.ui.MyAttestationActivity;
import com.zthx.npj.ui.MyCollectActivity;
import com.zthx.npj.ui.MyCouponActivity;
import com.zthx.npj.ui.MyOrderActivity;
import com.zthx.npj.ui.MyStoreActivity;
import com.zthx.npj.ui.MyStoreOrderDetailActivity;
import com.zthx.npj.ui.MySupplyActivity;
import com.zthx.npj.ui.MyTeamActivity;
import com.zthx.npj.ui.MyWalletActivity;
import com.zthx.npj.ui.SettingsActivity;
import com.zthx.npj.ui.SpokesmanRightsNoPermissionActivity;
import com.zthx.npj.ui.StoreManagerActivity;
import com.zthx.npj.ui.UserMsgActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import org.json.JSONException;
import org.json.JSONObject;

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
public class MineFragment
        extends BaseFragment {

    @BindView(R.id.fg_mine_iv_head_pic)
    ImageView fgMineIvHeadPic;
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
    @BindView(R.id.fg_mine_tv_collectionNum)
    TextView fgMineTvCollectionNum;
    @BindView(R.id.fg_mine_tv_gourdCoin)
    TextView fgMineTvGourdCoin;
    @BindView(R.id.fg_mine_tv_couponNum)
    TextView fgMineTvCouponNum;
    @BindView(R.id.fg_mine_iv_levelimg)
    ImageView fgMineIvLevelimg;
    @BindView(R.id.fg_mine_ll_my_offlinestore)
    LinearLayout fgMineLlMyOfflinestore;
    @BindView(R.id.fg_mine_ll)
    LinearLayout fgMineLl;
    @BindView(R.id.fg_mine_iv_jihuo)
    ImageView fgMineIvJihuo;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.fg_mine_tv_payMsg)
    TextView fgMineTvPayMsg;
    @BindView(R.id.fg_mine_tv_sendMsg)
    TextView fgMineTvSendMsg;
    @BindView(R.id.fg_mine_tv_receiveMsg)
    TextView fgMineTvReceiveMsg;
    @BindView(R.id.fg_mine_tv_commentMsg)
    TextView fgMineTvCommentMsg;
    @BindView(R.id.fg_mine_tv_rebackMsg)
    TextView fgMineTvRebackMsg;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.fg_mine_nsv)
    NestedScrollView fgMineNsv;
    @BindView(R.id.title_theme)
    LinearLayout titleTheme;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.seeMore)
    TextView seeMore;

    //private String level=SharePerferenceUtils.getLevel(getContext());
    private String user_id = SharePerferenceUtils.getUserId(getContext());
    private String token = SharePerferenceUtils.getToken(getContext());
    private String level = "";
    private String balance = "";
    private Intent intent;

    private int paySize = 0;
    private int sendSize = 0;
    private int receiveSize = 0;
    private int commentSize = 0;
    private int rebackSize = 0;

    public MineFragment() {

    }

    private int page = 1;
    private AlsoLikeAdatper adatper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("测试", "onCreate0: " + SharePerferenceUtils.getUserId(getContext()).equals(""));
        if (SharePerferenceUtils.getUserId(getContext()).equals("")) {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.e("测试", "onCreate1: " + SharePerferenceUtils.getUserId(getContext()).equals(""));
            if (SharePerferenceUtils.getUserId(getContext()).equals("")) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            } else if (!NetUtil.isNetworkConnected(getContext())) {
                Toast.makeText(getContext(), "请打开网络连接", Toast.LENGTH_SHORT).show();
            } else {
                getUserInfo();
                getAlsoLike();
                getOrderSize();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);

        titleThemeBack.setVisibility(View.GONE);
        titleThemeTitle.setText("个人中心");
        //titleTheme.setBackground(getResources().getDrawable(R.drawable.mine_title_gb));
        titleTheme.setAlpha(0f);
        titleThemeTvRight.setText("设置");
        titleThemeTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (adatper != null) {
                    adatper.clearData();
                }
                seeMore.setText("查看更多");
                getUserInfo();
                getAlsoLike();
                getOrderSize();
                refreshlayout.finishRefresh();
                refreshLayout.setLoadmoreFinished(false);
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                getAlsoLike();
                refreshlayout.finishLoadmore();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final int height = titleTheme.getLayoutParams().height;
            fgMineNsv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    float alpha = 0;
                    titleTheme.setAlpha(alpha);
                    if (i1 <= height) {
                        alpha = (float) ((i1 * 10) / height / 10.0);
                        // 随着滑动距离改变透明度
                        // Log.e("al=","="+alpha);
                        titleTheme.setAlpha(alpha);
                    } else {
                        titleTheme.setAlpha(1f);
                    }
                }
            });
        }

        getUserInfo();
        getAlsoLike();
        getOrderSize();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void getOrderSize() {
        SetSubscribe.myOrder(user_id, token, "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
                if (bean.getData() == null || bean.getData().size() == 0) {
                    fgMineTvPayMsg.setVisibility(View.GONE);
                } else {
                    fgMineTvPayMsg.setVisibility(View.VISIBLE);
                    fgMineTvPayMsg.setText(bean.getData().size() + "");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        SetSubscribe.myOrder(user_id, token, "2", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
                if (bean.getData() == null || bean.getData().size() == 0) {
                    fgMineTvSendMsg.setVisibility(View.GONE);
                } else {
                    fgMineTvSendMsg.setVisibility(View.VISIBLE);
                    fgMineTvSendMsg.setText(bean.getData().size() + "");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        SetSubscribe.myOrder(user_id, token, "3", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
                if (bean.getData() == null || bean.getData().size() == 0) {
                    fgMineTvReceiveMsg.setVisibility(View.GONE);
                } else {
                    fgMineTvReceiveMsg.setVisibility(View.VISIBLE);
                    fgMineTvReceiveMsg.setText(bean.getData().size() + "");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        SetSubscribe.myOrder(user_id, token, "4", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
                if (bean.getData() == null || bean.getData().size() == 0) {
                    fgMineTvCommentMsg.setVisibility(View.GONE);
                } else {
                    fgMineTvCommentMsg.setVisibility(View.VISIBLE);
                    fgMineTvCommentMsg.setText(bean.getData().size() + "");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        SetSubscribe.myOrder(user_id, token, "6", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderResponseBean bean = GsonUtils.fromJson(result, OrderResponseBean.class);
                if (bean.getData() == null || bean.getData().size() == 0) {
                    fgMineTvRebackMsg.setVisibility(View.GONE);
                } else {
                    fgMineTvRebackMsg.setVisibility(View.VISIBLE);
                    fgMineTvRebackMsg.setText(bean.getData().size() + "");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    private void getAlsoLike() {
        MainSubscribe.alsoLike(page+"", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean = GsonUtils.fromJson(result, AlsoLikeResponseBean.class);
                final ArrayList<AlsoLikeResponseBean.DataBean> data = bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                fgMineRvLike.setLayoutManager(layoutManager);
                if (adatper == null) {
                    adatper = new AlsoLikeAdatper(getContext(), data);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        adatper.addData(data);
                    }
                }
                //设置添加或删除item时的动画，这里使用默认动画
                fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
                fgMineRvLike.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<AlsoLikeResponseBean.DataBean> mList) {
                        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, mList.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getUserInfo() {
        String user_id = SharePerferenceUtils.getUserId(getContext());
        String token = SharePerferenceUtils.getToken(getContext());
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {
                SharePerferenceUtils.setUserId(getContext(), "");
            }
        }));
    }

    private void setUserInfo(String result) {
        Log.e("测试", "setUserInfo: " + result);
        UserResponseBean userResponseBean = GsonUtils.fromJson(result, UserResponseBean.class);
        UserResponseBean.DataBean data = userResponseBean.getData();
        level = data.getLevel() + "";
        SharePerferenceUtils.setUserLevel(getContext(), level);
        fgMineTvName.setText(data.getNick_name());
        fgMineTvTel.setText(data.getMobile());
        SharePerferenceUtils.setUserMobile(getContext(), data.getMobile());
        fgMineTvWord.setText(data.getSignature() == null ? "这个人很懒，什么也没有留下" : data.getSignature());
        fgMineTvGourdCoin.setText(String.valueOf(data.getGourd_coin()));
        fgMineTvCouponNum.setText(String.valueOf(data.getCoupon_num()));
        fgMineTvCollectionNum.setText(String.valueOf(data.getCollection_num()));
        balance = data.getBalance();
        SharePerferenceUtils.setBalance(getContext(), balance);
        Glide.with(getContext())
                .load(Uri.parse(data.getHead_img()))
                .into(fgMineIvHeadPic);
        SharePerferenceUtils.setHeadPic(getContext(), data.getHead_img());
        SharePerferenceUtils.setNickName(getContext(), data.getNick_name());
        SharePerferenceUtils.setReputation(getContext(), data.getReputation());
        SharePerferenceUtils.putString(getContext(), "level", String.valueOf(data.getLevel()));
        MyCustomUtils.showLevelImg(data.getLevel(), fgMineIvLevelimg);
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

    @OnClick({R.id.fg_mine_iv_settings,
            R.id.fg_mine_ll_my_supply,
            R.id.fg_mine_ll_help,
            R.id.fg_mine_ll_my_store,
            R.id.fg_mine_ll_my_attestation,
            R.id.fg_mine_iv_people_right,
            R.id.fg_mine_ll_collect,
            R.id.fg_mine_ll_coupon,
            R.id.fg_mine_ll_my_wallet,
            R.id.fg_mine_tv_all_order,
            R.id.fg_mine_ll_wait_pay,//待付款
            R.id.fg_mine_ll_wait_delivery,//待发货
            R.id.fg_mine_ll_wait_take_delivery,//待收货
            R.id.fg_mine_ll_wait_evaluate,//待评价
            R.id.fg_mine_ll_custom_service,//退款/售后
            R.id.fg_mine_ll_my_offlinestore,
            R.id.fg_mine_iv_head_pic,
            R.id.fg_mine_iv_levelimg,
            R.id.fg_mine_iv_jihuo,
            R.id.fg_mine_iv_message
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_mine_ll_wait_pay:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 1);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_wait_delivery:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 2);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_wait_take_delivery:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 3);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_wait_evaluate:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 4);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_custom_service:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 5);
                startActivity(intent);
                break;
            case R.id.fg_mine_iv_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.fg_mine_iv_people_right:
                startActivity(new Intent(getActivity(), SpokesmanRightsNoPermissionActivity.class));
                break;
            case R.id.fg_mine_ll_collect:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.fg_mine_tv_all_order:
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra("currentItem", 0);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_coupon:
                startActivity(new Intent(getActivity(), MyCouponActivity.class));
                break;
            case R.id.fg_mine_ll_my_wallet:
                Intent intent = new Intent(getContext(), MyWalletActivity.class);
                //intent.putExtra("balance", balance);
                startActivity(intent);
                break;
            case R.id.fg_mine_ll_my_store:
                if (level.equals("0")) {
                    CommonDialog dialog = new CommonDialog(getContext(), R.style.dialog, "您还不是代言人，暂不能开店", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(getContext(), MembershipPackageActivity.class));
                            }
                        }
                    });
                    dialog.setPositiveButton("成为代言人");
                    dialog.show();
                } else {
                    startActivity(new Intent(getContext(), MyStoreActivity.class));
                }
                break;
            case R.id.fg_mine_ll_my_attestation:
                startActivity(new Intent(getActivity(), MyAttestationActivity.class));
                break;
            case R.id.fg_mine_ll_help:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
            case R.id.fg_mine_ll_my_supply:
                /*if (level.equals("0")) {
                    Toast toast = Toast.makeText(getContext(), "成为农品街代言人，才可使用供求操作", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    startActivity(new Intent(getContext(), MySupplyActivity.class));
                }*/
                startActivity(new Intent(getContext(), MySupplyActivity.class));
                break;
            case R.id.fg_mine_ll_my_offlinestore:
                if (level.equals("0")) {
                    Toast toast = Toast.makeText(getContext(), "成为农品街代言人，才可使用线下门店的功能哦", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            startActivity(new Intent(getContext(), EditMyOfflineStoreActivity.class));
                            /*Log.e("测试", "onSuccess: " + result);
                            try {
                                JSONObject object = new JSONObject(result);
                                String code = object.getString("code") + "";
                                if (code.equals("1")) {

                                } else if (code.equals("2")) {
                                    Toast.makeText(getContext(), "线下门店审核中", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            try {
                                JSONObject object = new JSONObject(errorMsg);
                                String code = object.getString("code") + "";
                                if (code.equals("-2")) {
                                    startActivity(new Intent(getContext(), StoreManagerActivity.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }));
                }
                break;
            case R.id.fg_mine_iv_head_pic:
                Intent intent1 = new Intent(getContext(), UserMsgActivity.class);
                intent1.putExtra("key0", user_id);
                startActivity(intent1);
                break;
            case R.id.fg_mine_iv_jihuo:
                startActivity(new Intent(getContext(), MembershipPackageActivity.class));
                break;
            case R.id.fg_mine_iv_message:
                startActivity(new Intent(getContext(), MessageCenterActivity.class));
                break;
            case R.id.fg_mine_iv_levelimg:
                SetSubscribe.myTeam(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        MyTeamResponseBean bean = GsonUtils.fromJson(result, MyTeamResponseBean.class);
                        MyTeamResponseBean.DataBean data = bean.getData();
                        if ((int) data.getStatus() == 1) {
                            CommonDialog dialog = new CommonDialog(getContext(), R.style.dialog, "请先升级成为VIP代言人", new CommonDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm) {
                                        startActivity(new Intent(getContext(), MembershipPackageActivity.class));
                                    }
                                }
                            });
                            dialog.setPositiveButton("升级为代言人");
                            dialog.show();
                        } else if ((int) data.getStatus() == 2) {
                            CommonDialog dialog = new CommonDialog(getContext(), R.style.dialog, "请先绑定邀请人！", new CommonDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm) {
                                        startActivity(new Intent(getContext(), InputInvitationCodeActivity.class));
                                    }
                                }
                            });
                            dialog.setPositiveButton("绑定邀请人");
                            dialog.show();
                        } else {
                            startActivity(new Intent(getContext(), MyTeamActivity.class));
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                }));

                break;
        }
    }
}
