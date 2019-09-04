package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.ReferrerResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReferrerActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_referrer_mcv_headImg)
    MyCircleView acReferrerMcvHeadImg;
    @BindView(R.id.ac_referrer_tv_nickName)
    TextView acReferrerTvNickName;
    @BindView(R.id.rl_title)
    LinearLayout rlTitle;
    @BindView(R.id.ac_referrer_tv_mobile)
    TextView acReferrerTvMobile;
    @BindView(R.id.ac_referrer_tv_chat)
    TextView acReferrerTvChat;
    @BindView(R.id.ac_referrer_tv_change)
    TextView acReferrerTvChange;
    @BindView(R.id.ac_referrer_tv_level)
    TextView acReferrerTvLevel;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String mobile="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrer);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的上级");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyReferrer();
    }

    private void getMyReferrer() {
        GiftSubscribe.referrer(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: "+result );
                setMyReferrer(result);
            }

            @Override
            public void onFault(String errorMsg) {
                CommonDialog dialog=new CommonDialog(ReferrerActivity.this, R.style.dialog, "您还没有推荐人", new CommonDialog.OnCloseListener() {
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
    }

    private void setMyReferrer(String result) {
        ReferrerResponseBean bean = GsonUtils.fromJson(result, ReferrerResponseBean.class);
        Glide.with(this).load(Uri.parse(bean.getData().getHead_img())).into(acReferrerMcvHeadImg);
        acReferrerTvNickName.setText(bean.getData().getNick_name());
        acReferrerTvMobile.setText("手机号" + bean.getData().getMobile());
        switch ((int) bean.getData().getLevel()){
            case 0:acReferrerTvLevel.setText("普通会员");break;
            case 1:acReferrerTvLevel.setText("VIP代言人");break;
            case 2:acReferrerTvLevel.setText("天使代言人");break;
            case 3:acReferrerTvLevel.setText("金牌代言人");break;
            case 4:acReferrerTvLevel.setText("钻石代言人");break;
            case 5:acReferrerTvLevel.setText("首席代言人");break;
            case 6:acReferrerTvLevel.setText("天使股东代言人");break;
            case 7:acReferrerTvLevel.setText("金牌股东代言人");break;
            case 8:acReferrerTvLevel.setText("钻石股东代言人");break;
            case 9:acReferrerTvLevel.setText("首席股东代言人");break;
            case 10:acReferrerTvLevel.setText("城市代言人");break;
        }
        mobile=bean.getData().getMobile();
    }

    @OnClick({R.id.ac_referrer_tv_chat, R.id.ac_referrer_tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_referrer_tv_chat:
                acReferrerTvChat.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acReferrerTvChat.setTextColor(getResources().getColor(R.color.white));
                acReferrerTvChange.setBackgroundColor(getResources().getColor(R.color.line));
                acReferrerTvChange.setTextColor(getResources().getColor(R.color.text3));
                Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+mobile));
                startActivity(intent);
                break;
            case R.id.ac_referrer_tv_change:
                acReferrerTvChange.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acReferrerTvChange.setTextColor(getResources().getColor(R.color.white));
                acReferrerTvChat.setBackgroundColor(getResources().getColor(R.color.line));
                acReferrerTvChat.setTextColor(getResources().getColor(R.color.text3));
                new CommonDialog(ReferrerActivity.this, R.style.dialog, "每位用户仅有一次修改代言人机会，您确定要修改吗？", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            showReferrerPopwindow();
                        }
                    }
                }).show();
                break;
        }
    }

    public void showReferrerPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_referrer, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_200));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_300));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        //window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final EditText input = contentView.findViewById(R.id.pw_referrer_et_input);
        Button confirm = contentView.findViewById(R.id.pw_referrer_btn_comfirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hongbaoMa = input.getText().toString().trim();
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
