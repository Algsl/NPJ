package com.zthx.npj.ui.fragment;

import android.app.Dialog;
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
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.adapter.CommenGoodsAdatper;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.been.RealNameResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.EditMyOfflineStoreActivity;
import com.zthx.npj.ui.EnterpriseCertificationActivity;
import com.zthx.npj.ui.GiftActivity;
import com.zthx.npj.ui.HelpActivity;
import com.zthx.npj.ui.MembershipPackageActivity;
import com.zthx.npj.ui.MessageCenterActivity;
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
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import org.json.JSONException;
import org.json.JSONObject;

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
public class MineFragment
        extends BaseFragment {

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

    //private String level=SharePerferenceUtils.getLevel(getContext());
    private String user_id = SharePerferenceUtils.getUserId(getContext());
    private String token = SharePerferenceUtils.getToken(getContext());
    private String level="";

    public MineFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
        getAlsoLike();
    }


    private void getAlsoLike() {
        MainSubscribe.alsoLike("1",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean=GsonUtils.fromJson(result,AlsoLikeResponseBean.class);
                ArrayList<AlsoLikeResponseBean.DataBean> data=bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                fgMineRvLike.setLayoutManager(layoutManager);
                AlsoLikeAdatper adatper=new AlsoLikeAdatper(getContext(),data);
                //设置添加或删除item时的动画，这里使用默认动画
                fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
                fgMineRvLike.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

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

            }
        }));
    }

    private void setUserInfo(String result) {
        UserResponseBean userResponseBean = GsonUtils.fromJson(result, UserResponseBean.class);
        UserResponseBean.DataBean data = userResponseBean.getData();
        level=data.getLevel()+"";
        fgMineTvName.setText(data.getNick_name());
        fgMineTvTel.setText(data.getMobile());
        fgMineTvWord.setText(data.getSignature());
        fgMineTvGourdCoin.setText(String.valueOf(data.getGourd_coin()));
        fgMineTvCouponNum.setText(String.valueOf(data.getCoupon_num()));
        fgMineTvCollectionNum.setText(String.valueOf(data.getCollection_num()));
        Glide.with(getContext())
                .load(Uri.parse(data.getHead_img()))
                .into(fgMineIvHeadPic);
        SharePerferenceUtils.putString(getContext(), "level", String.valueOf(data.getLevel()));

        switch (data.getLevel()) {
            case 0:
                fgMineIvLevelimg.setImageResource(R.drawable.level0);
                break;
            case 1:
                fgMineIvLevelimg.setImageResource(R.drawable.level1);
                break;
            case 2:
                fgMineIvLevelimg.setImageResource(R.drawable.level2);
                break;
            case 3:
                fgMineIvLevelimg.setImageResource(R.drawable.level3);
                break;
            case 4:
                fgMineIvLevelimg.setImageResource(R.drawable.level4);
                break;
            case 5:
                fgMineIvLevelimg.setImageResource(R.drawable.level5);
                break;
            case 6:
                fgMineIvLevelimg.setImageResource(R.drawable.level6);
                break;
            case 7:
                fgMineIvLevelimg.setImageResource(R.drawable.level7);
                break;
            case 8:
                fgMineIvLevelimg.setImageResource(R.drawable.level8);
                break;
            case 9:
                fgMineIvLevelimg.setImageResource(R.drawable.level9);
                break;
            case 10:
                fgMineIvLevelimg.setImageResource(R.drawable.level10);
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
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_wait_delivery:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_wait_take_delivery:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_wait_evaluate:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_custom_service:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
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
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.fg_mine_ll_coupon:
                startActivity(new Intent(getActivity(), MyCouponActivity.class));
                break;
            case R.id.fg_mine_ll_my_wallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.fg_mine_ll_my_store:
                if(level.equals("0")){
                    CommonDialog dialog=new CommonDialog(getContext(), R.style.dialog, "您还不是代言人，暂不能开店", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                startActivity(new Intent(getContext(),MembershipPackageActivity.class));
                            }
                        }
                    });
                    dialog.setPositiveButton("成为代言人");
                    dialog.show();
                 }else{
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
                if(level.equals("0")){
                    CommonDialog dialog1=new CommonDialog(getContext(), R.style.dialog, "您还不是代言人，暂不能开店", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                startActivity(new Intent(getContext(),MembershipPackageActivity.class));
                            }
                        }
                    });
                    dialog1.setPositiveButton("成为代言人");
                    dialog1.show();
                }else{
                    startActivity(new Intent(getContext(), MySupplyActivity.class));
                }

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
                            Log.e("测试", "onSuccess: "+result);
                            try {
                                JSONObject object=new JSONObject(result);
                                String code=object.getString("code")+"";
                                if(code.equals("1")){
                                    startActivity(new Intent(getContext(),EditMyOfflineStoreActivity.class));
                                }else if(code.equals("2")){
                                    Toast.makeText(getContext(),"线下门店审核中",Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFault(String errorMsg) {
                            try {
                                JSONObject object=new JSONObject(errorMsg);
                                String code=object.getString("code")+"";
                                if(code.equals("-2")){
                                    startActivity(new Intent(getContext(),StoreManagerActivity.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }));
                }
                break;
            case R.id.fg_mine_iv_head_pic:
                Intent intent=new Intent(getContext(),UserMsgActivity.class);
                intent.putExtra("key0",user_id);
                startActivity(intent);
                break;
            case R.id.fg_mine_iv_jihuo:
                startActivity(new Intent(getContext(),MembershipPackageActivity.class));
                break;
            case R.id.fg_mine_iv_message:
                startActivity(new Intent(getContext(),MessageCenterActivity.class));
                break;
            case R.id.fg_mine_iv_levelimg:
                startActivity(new Intent(getContext(),MyTeamActivity.class));
                break;
        }
    }
}
