package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zthx.npj.R;
import com.zthx.npj.adapter.BuyGiftAdapter;
import com.zthx.npj.adapter.SpokesmanQuanLiAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GiftListResponseBean;
import com.zthx.npj.net.been.SpokesmanQuanLiResponsebean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MembershipPackageActivity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_membership_package_head_pic)
    ImageView atMembershipPackageHeadPic;
    @BindView(R.id.at_membership_package_tv_name)
    TextView atMembershipPackageTvName;
    @BindView(R.id.at_membership_package_tv_tuijian)
    TextView atMembershipPackageTvTuijian;
    @BindView(R.id.at_membership_package_tv_share)
    TextView atMembershipPackageTvShare;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_membership_iv_level)
    ImageView acMembershipIvLevel;

    @BindView(R.id.ac_membership_tv_buy)
    TextView acMembershipTvBuy;
    @BindView(R.id.ac_membership_tv_quanyi)
    TextView acMembershipTvQuanyi;
    @BindView(R.id.ac_membership_rv)
    RecyclerView acMembershipRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_package);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "会员礼包");

        if (user_id.equals("")) {
            CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        startActivity(new Intent(MembershipPackageActivity.this, LoginActivity.class));
                    }
                }
            });
            dialog.setTitle("提示");
            dialog.setPositiveButton("去登录");
            dialog.show();
        } else {
            getUserInfo();
            getGiftList();
        }

        atMembershipPackageTvName.setSelected(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                SharePerferenceUtils.setUserId(MembershipPackageActivity.this,"");
            }
        }));
    }

    private void setUserInfo(String result) {
        UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
        Glide.with(this).load(Uri.parse(bean.getData().getHead_img())).into(atMembershipPackageHeadPic);
        atMembershipPackageTvName.setText(bean.getData().getNick_name());
        level=bean.getData().getLevel()+"";
        MyCustomUtils.showLevelImg(bean.getData().getLevel(), acMembershipIvLevel);
    }

    @OnClick({R.id.at_membership_package_tv_tuijian, R.id.at_membership_package_tv_share,
            R.id.ac_membership_tv_buy, R.id.ac_membership_tv_quanyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_membership_package_tv_tuijian:
                GiftSubscribe.referrer(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        openActivity(ReferrerActivity.class);
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        CommonDialog dialog=new CommonDialog(MembershipPackageActivity.this, R.style.dialog, "您还没有推荐人", new CommonDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if(confirm){
                                    openActivity(InputInvitationCodeActivity.class);
                                }else{
                                    finish();
                                }
                            }
                        });
                        dialog.setPositiveButton("绑定邀请人");
                        dialog.show();
                    }
                }));
                break;
            case R.id.at_membership_package_tv_share:
                showPublishPopwindow();
                break;
            case R.id.ac_membership_tv_buy:
                acMembershipTvBuy.setTextColor(getResources().getColor(R.color.app_theme));
                acMembershipTvQuanyi.setTextColor(getResources().getColor(R.color.text3));
                getGiftList();
                break;
            case R.id.ac_membership_tv_quanyi:
                acMembershipTvBuy.setTextColor(getResources().getColor(R.color.text3));
                acMembershipTvQuanyi.setTextColor(getResources().getColor(R.color.app_theme));
                getData();
                break;
        }
    }

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_vip_hint, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_320));
        window.setHeight((int) getResources().getDimension(R.dimen.dp_400));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView tv = contentView.findViewById(R.id.pw_vipHint_tv_generateHB);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level.equals("0")) {
                    Toast.makeText(MembershipPackageActivity.this, "您不是农品街代言人，无法生成分享海报", Toast.LENGTH_LONG).show();
                } else {
                    openActivity(HaiBaoActivity.class);
                    backgroundAlpha(1f);
                    window.dismiss();
                }
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void getGiftList() {
        GiftSubscribe.getGiftList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GiftListResponseBean giftListResponseBean = GsonUtils.fromJson(result, GiftListResponseBean.class);
                final ArrayList<GiftListResponseBean.DataBean> data = giftListResponseBean.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MembershipPackageActivity.this);
                acMembershipRv.setLayoutManager(layoutManager);
                BuyGiftAdapter mAdapter = new BuyGiftAdapter(MembershipPackageActivity.this, data);
                mAdapter.setOnItemClickListener(new BuyGiftAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(MembershipPackageActivity.this, GiftActivity.class);
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                        startActivity(intent);
                    }

                    @Override
                    public void onBuyClick(int position) {
                        if(level.equals("0")){
                            Intent intent = new Intent(MembershipPackageActivity.this, ConfirmOrderActivity.class);
                            intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                            intent.setAction(Const.GIFT);
                            startActivity(intent);
                        }else{
                            Toast toast=Toast.makeText(MembershipPackageActivity.this,"您已经是代言人了，赶快去邀请好友加入农品街吧！",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                            toast.show();
                        }

                    }
                });
                acMembershipRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {
            }
        }));
    }

    private void getData() {
        GiftSubscribe.getSpokesmanQuan(user_id,token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SpokesmanQuanLiResponsebean spokesmanQuanLiResponsebean = GsonUtils.fromJson(result, SpokesmanQuanLiResponsebean.class);
                ArrayList<SpokesmanQuanLiResponsebean.DataBean> data = spokesmanQuanLiResponsebean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(MembershipPackageActivity.this,LinearLayoutManager.VERTICAL,false);
                acMembershipRv.setLayoutManager(manager);
                SpokesmanQuanLiAdapter mAdapter = new SpokesmanQuanLiAdapter(MembershipPackageActivity.this,data);
                acMembershipRv.setItemAnimator(new DefaultItemAnimator());
                acMembershipRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
