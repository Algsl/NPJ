package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AttentionResponseBean;
import com.zthx.npj.net.been.LookUserResponseBean;
import com.zthx.npj.net.been.StoreGoodsListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetAddressUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.QRCodeUtil;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.SimpleUtil;
import com.zthx.npj.view.MyCircleView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserMsgActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_userMsg_mcv_headImg)
    MyCircleView acUserMsgMcvHeadImg;
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
    @BindView(R.id.ac_userMsg_tv_level)
    ImageView acUserMsgTvLevel;
    @BindView(R.id.ac_userMsg_tv_hits)
    TextView acUserMsgTvHits;
    @BindView(R.id.ac_userMsg_tv_attNum)
    TextView acUserMsgTvAttNum;
    @BindView(R.id.ac_userMsg_tv_historyMoney)
    TextView acUserMsgTvHistoryMoney;
    @BindView(R.id.ac_userMsg_tv_reputation)
    TextView acUserMsgTvReputation;
    @BindView(R.id.ac_userMsg_tv_beDYR)
    TextView acUserMsgTvBeDYR;
    @BindView(R.id.ac_userMsg_tv_qyCert)
    TextView acUserMsgTvQyCert;
    @BindView(R.id.ac_userMsg_tv_sjCert)
    TextView acUserMsgTvSjCert;
    @BindView(R.id.ac_userMsg_iv_mdCert)
    TextView acUserMsgIvMdCert;
    @BindView(R.id.ac_userMsg_tv_smCert)
    TextView acUserMsgTvSmCert;
    @BindView(R.id.ac_userMsg_tv_goCert)
    TextView acUserMsgTvGoCert;
    @BindView(R.id.ac_userMsg_tv_tuijian)
    TextView acUserMsgTvTuijian;
    @BindView(R.id.ac_userMsg_tv_allGoods)
    TextView acUserMsgTvAllGoods;
    @BindView(R.id.ac_userMsg_tv_sellSort)
    TextView acUserMsgTvSellSort;
    @BindView(R.id.ac_userMsg_tv_priceSort)
    TextView acUserMsgTvPriceSort;
    @BindView(R.id.ac_userMsg_mcv_headImg1)
    MyCircleView acUserMsgMcvHeadImg1;
    @BindView(R.id.ac_userMsg_tv_level1)
    ImageView acUserMsgTvLevel1;
    @BindView(R.id.ac_userMsg_tv_beDYR1)
    TextView acUserMsgTvBeDYR1;
    @BindView(R.id.ac_userMsg_tv_nickName1)
    TextView acUserMsgTvNickName1;
    @BindView(R.id.ac_userMsg_tv_signature1)
    TextView acUserMsgTvSignature1;
    @BindView(R.id.ac_userMsg_tv_address1)
    TextView acUserMsgTvAddress1;
    @BindView(R.id.ac_userMsg_tv_hits1)
    TextView acUserMsgTvHits1;
    @BindView(R.id.ac_userMsg_tv_attNum1)
    TextView acUserMsgTvAttNum1;
    @BindView(R.id.ac_userMsg_tv_historyMoney1)
    TextView acUserMsgTvHistoryMoney1;
    @BindView(R.id.ac_userMsg_tv_reputation1)
    TextView acUserMsgTvReputation1;
    @BindView(R.id.ac_userMsg_tv_qyCert1)
    TextView acUserMsgTvQyCert1;
    @BindView(R.id.ac_userMsg_tv_sjCert1)
    TextView acUserMsgTvSjCert1;
    @BindView(R.id.ac_userMsg_iv_mdCert1)
    TextView acUserMsgIvMdCert1;
    @BindView(R.id.ac_userMsg_tv_smCert1)
    TextView acUserMsgTvSmCert1;
    @BindView(R.id.ac_userMsg_tv_goCert1)
    TextView acUserMsgTvGoCert1;
    @BindView(R.id.ac_userMsg_ll_share)
    LinearLayout acUserMsgLlShare;
    @BindView(R.id.ac_userMsg_ll_show)
    LinearLayout acUserMsgLlShow;
    @BindView(R.id.ac_userMsg_tv_toShare)
    TextView acUserMsgTvToShare;
    @BindView(R.id.ac_userMsg_iv_qrCode)
    ImageView acUserMsgIvQrCode;


    private IWXAPI api;
    private Bitmap bmp;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String att_user_id = "";
    private String type = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg);
        ButterKnife.bind(this);

        att_user_id = getIntent().getStringExtra("key0");
        getLookUser(att_user_id);


        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        if (!att_user_id.equals(user_id)) {
            acUserMsgTvGoCert.setVisibility(View.GONE);
        }

        back(titleThemeBack);

        getStoreGoodsList();
    }

    private void getStoreGoodsList() {
        MainSubscribe.storeGoodsList(att_user_id, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoodsList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreGoodsList(String result) {
        StoreGoodsListResponseBean bean = GsonUtils.fromJson(result, StoreGoodsListResponseBean.class);
        final ArrayList<StoreGoodsListResponseBean.DataBean> data = bean.getData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        fgMineRvLike.setLayoutManager(layoutManager);
        StoreGoodsAdapter adapter = new StoreGoodsAdapter(this, data);
        fgMineRvLike.setItemAnimator(new DefaultItemAnimator());
        fgMineRvLike.setAdapter(adapter);
        adapter.setOnItemClickListener(new StoreGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(UserMsgActivity.this, GoodsDetailActivity.class);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onShoppingCartClick(int position) {
                Intent intent = new Intent(UserMsgActivity.this, GoodsDetailActivity.class);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }
        });
    }

    private void getLookUser(String user_id) {
        DiscoverSubscribe.lookUser(user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setLookUser(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setLookUser(String result) {
        LookUserResponseBean bean = GsonUtils.fromJson(result, LookUserResponseBean.class);
        LookUserResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getHead_img())).into(acUserMsgMcvHeadImg);
        acUserMsgTvNickName.setText(data.getNick_name());
        acUserMsgTvSignature.setText(data.getSignature() == null ? "这个人很懒，什么也没留下" : data.getSignature());
        MyCustomUtils.showLevelImg((int) data.getLevel(), acUserMsgTvLevel);
        acUserMsgTvHits.setText(data.getHits() == null ? "0" : data.getHits());
        acUserMsgTvAttNum.setText(data.getAtt_num() == null ? "0" : data.getAtt_num());
        acUserMsgTvHistoryMoney.setText(data.getHistory_money());
        acUserMsgTvReputation.setText(data.getReputation() == null ? "0" : data.getReputation());
        acUserMsgTvAddress.setText(new GetAddressUtil(this).getAddress(Double.parseDouble(data.getLng()), Double.parseDouble(data.getLat())));

        Glide.with(this).load(Uri.parse(data.getHead_img())).into(acUserMsgMcvHeadImg1);
        acUserMsgTvNickName1.setText(data.getNick_name());
        acUserMsgTvSignature1.setText(data.getSignature() == null ? "这个人很懒，什么也没留下" : data.getSignature());
        MyCustomUtils.showLevelImg((int) data.getLevel(), acUserMsgTvLevel1);
        acUserMsgTvHits1.setText(data.getHits() == null ? "0" : data.getHits());
        acUserMsgTvAttNum1.setText(data.getAtt_num() == null ? "0" : data.getAtt_num());
        acUserMsgTvHistoryMoney1.setText(data.getHistory_money());
        acUserMsgTvReputation1.setText(data.getReputation() == null ? "0" : data.getReputation());
        acUserMsgTvAddress1.setText(new GetAddressUtil(this).getAddress(Double.parseDouble(data.getLng()), Double.parseDouble(data.getLat())));
        acUserMsgIvQrCode.setImageBitmap(QRCodeUtil.createQRCodeBitmap("hello world",80));
    }


    @OnClick({R.id.title_theme_img_right, R.id.ac_userMsg_tv_beDYR, R.id.ac_userMsg_tv_tuijian,
            R.id.ac_userMsg_tv_allGoods, R.id.ac_userMsg_tv_sellSort, R.id.ac_userMsg_tv_priceSort,
            R.id.ac_userMsg_tv_toShare})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_userMsg_tv_beDYR:
                openActivity(MembershipPackageActivity.class);
                break;
            case R.id.title_theme_img_right:
                showItemPopwindow();
                break;
            case R.id.ac_userMsg_tv_tuijian:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.app_theme));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvPriceSort.setTextColor(getResources().getColor(R.color.text6));
                type = "1";
                break;
            case R.id.ac_userMsg_tv_allGoods:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.app_theme));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvPriceSort.setTextColor(getResources().getColor(R.color.text6));
                type = "2";
                break;
            case R.id.ac_userMsg_tv_sellSort:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.app_theme));
                acUserMsgTvPriceSort.setTextColor(getResources().getColor(R.color.text6));
                type = "3";
                break;
            case R.id.ac_userMsg_tv_priceSort:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvPriceSort.setTextColor(getResources().getColor(R.color.app_theme));
                type = "4";
                break;
            case R.id.ac_userMsg_tv_toShare:
                bmp=SimpleUtil.createViewBitmap(acUserMsgLlShare);
                showSingleBottomDialog();
                break;
        }
    }

    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_mine_menu, null);
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_135));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_100));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 0, 0);
        TextView share = contentView.findViewById(R.id.pw_mineMenu_tv_share);
        TextView attention = contentView.findViewById(R.id.pw_mineMenu_tv_attention);
        TextView report = contentView.findViewById(R.id.pw_mineMenu_tv_report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                showPublishPopwindow();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                acUserMsgLlShow.setVisibility(View.VISIBLE);
                //showSingleBottomDialog();
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
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiscoverSubscribe.attention(user_id, token, att_user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        window.dismiss();
                        backgroundAlpha(1f);
                        AttentionResponseBean bean = GsonUtils.fromJson(result, AttentionResponseBean.class);
                        switch (bean.getData().getStatus()) {
                            case 1:
                                Toast.makeText(UserMsgActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(UserMsgActivity.this, "关注失败", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(UserMsgActivity.this, "已经关注过了", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
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
        dialog.setCancelable(true);
        dialog.findViewById(R.id.dialog_share_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acUserMsgLlShow.setVisibility(View.GONE);
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
                acUserMsgLlShow.setVisibility(View.GONE);
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
                acUserMsgLlShow.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_report, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_270));
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        RelativeLayout reason1 = contentView.findViewById(R.id.pw_report_rl_reason1);
        RelativeLayout reason2 = contentView.findViewById(R.id.pw_report_rl_reason2);
        RelativeLayout reason3 = contentView.findViewById(R.id.pw_report_rl_reason3);
        RelativeLayout reason4 = contentView.findViewById(R.id.pw_report_rl_reason4);
        reason1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ReportActivity.class, "该账号存在欺骗诈钱行为");
            }
        });
        reason2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ReportActivity.class, "该账号存在其它违法行为");
            }
        });
        reason3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ReportActivity.class, "该账号侵犯他人权益");
            }
        });
        reason4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ReportActivity.class, "该账号存在虚假信息");
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
}
