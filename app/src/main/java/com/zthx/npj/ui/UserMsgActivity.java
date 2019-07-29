package com.zthx.npj.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.utils.SimpleUtil;
import com.zthx.npj.view.MyCircleView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserMsgActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_userMsg_mcv_headImg)
    MyCircleView acUserMsgMcvHeadImg;
    @BindView(R.id.ac_userMsg_tv_level)
    TextView acUserMsgTvLevel;
    @BindView(R.id.ac_userMsg_tv_nickName)
    TextView acUserMsgTvNickName;
    @BindView(R.id.ac_userMsg_tv_signature)
    TextView acUserMsgTvSignature;
    @BindView(R.id.ac_userMsg_tv_address)
    TextView acUserMsgTvAddress;
    @BindView(R.id.fg_mine_rv_like)
    RecyclerView fgMineRvLike;
    @BindView(R.id.ac_userMsg_sv)
    ScrollView acUserMsgSv;
    @BindView(R.id.ac_userMsg_iv_show)
    ImageView acUserMsgIvShow;

    private IWXAPI api;
    private Bitmap bmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");


        back(titleThemeBack);
        changeTitle(titleThemeTitle, "个人资料");
        titleTheme.setBackgroundColor(getResources().getColor(R.color.background_img));
        titleThemeImgRight.setImageResource(R.drawable.item_goods_more1);
    }

    @OnClick(R.id.title_theme_img_right)
    public void onViewClicked() {
        showItemPopwindow();
    }


    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_mine_menu, null);
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_135));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_100));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(false);
        window.setTouchable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 0, 0);
        TextView share = contentView.findViewById(R.id.pw_mineMenu_tv_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                bmp = SimpleUtil.shotScrollView(acUserMsgSv);
                showSingleBottomDialog();
                /*//构建文本信息的分享对象（其它的有WXVideoObject,WXImageObject等），内容为hello
                WXTextObject textObject = new WXTextObject();
                textObject.text = "hello";

                //将textObject分装到WXMediaMessage里
                WXMediaMessage mediaMessage = new WXMediaMessage();
                mediaMessage.mediaObject = textObject;
                mediaMessage.description = "hello";

                //构建发送请求
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                //设置发送场景为分享给微信好友
                req.scene = SendMessageToWX.Req.WXSceneSession;
                //设置该事务为唯一事务（因为时间只有一个）
                req.transaction = String.valueOf(System.currentTimeMillis());
                //将封装好的WXMediaMessage再封装给SendMessageToWX.Req
                req.message = mediaMessage;

                //通过IWXAPI发送请求
                api.sendReq(req);*/
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //两个选项的Dialog
    private void showSingleBottomDialog() {
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
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120,120, true);
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
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120,120, true);
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
}
