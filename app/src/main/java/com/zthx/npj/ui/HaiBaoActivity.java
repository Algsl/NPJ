package com.zthx.npj.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.QRCodeUtil;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.SimpleUtil;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zthx.npj.ui.UserMsgActivity.bmpToByteArray;

public class HaiBaoActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_haibao_mcv_userImg)
    MyCircleView acHaibaoMcvUserImg;
    @BindView(R.id.ac_haibao_tv_userName)
    TextView acHaibaoTvUserName;
    @BindView(R.id.ac_haibao_iv_qrImg)
    ImageView acHaibaoIvQrImg;
    @BindView(R.id.ac_haibao_tv_generateHB)
    TextView acHaibaoTvGenerateHB;
    @BindView(R.id.ac_haibao_ll)
    LinearLayout acHaibaoLl;
    @BindView(R.id.ac_haibao_tv_changeQRCode)
    TextView acHaibaoTvChangeQRCode;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private IWXAPI api;
    private int i=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haibao);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        back(titleBack);
        changeTitle(acTitle, "邀请好友");

        getUserInfo();

    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                Glide.with(HaiBaoActivity.this).load(Uri.parse(bean.getData().getHead_img())).into(acHaibaoMcvUserImg);
                acHaibaoTvUserName.setText(bean.getData().getNick_name());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                //Bitmap bitmap1 = QRCodeUtil.createQRCodeBitmap("hello", 80, ImageCircleConner.toRoundCorner(bitmap, 190), 0.2f);
                Bitmap bitmap1 = QRCodeUtil.createQRCodeBitmap("http://game.npj-vip.com/h5/jumpApp.html?page=tuijian", 100, bitmap, 0.3f);
                acHaibaoIvQrImg.setImageBitmap(bitmap1);
            }

            @Override
            public void onFault(String errorMsg) {
                SharePerferenceUtils.setUserId(HaiBaoActivity.this,"");
            }
        }));
    }


    //两个选项的Dialog
    private void showSingleBottomDialog(final Bitmap bmp) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_share_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dialog_share_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);
                bmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmp, true);  // 设置所图；
                msg.title = "标题";
                msg.description = "内容";
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "img" + String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
                //  req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
                api.sendReq(req);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_share_pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);
                bmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmp, true);  // 设置所图；
                msg.title = "标题";
                msg.description = "内容";
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "img" + String.valueOf(System.currentTimeMillis());
                req.message = msg;
                //req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
                req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
                api.sendReq(req);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @OnClick({R.id.ac_haibao_tv_changeQRCode, R.id.ac_haibao_tv_generateHB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_haibao_tv_changeQRCode:

                break;
            case R.id.ac_haibao_tv_generateHB:
                showSingleBottomDialog(SimpleUtil.createViewBitmap(acHaibaoLl));
                break;
        }
    }
}
