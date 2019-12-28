package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
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
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetAddressUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    //@BindView(R.id.ac_userMsg_sv)
    //ScrollView acUserMsgSv;
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
    /*@BindView(R.id.ac_userMsg_tv_qyCert)
    TextView acUserMsgTvQyCert;
    @BindView(R.id.ac_userMsg_tv_sjCert)
    TextView acUserMsgTvSjCert;
    @BindView(R.id.ac_userMsg_iv_mdCert)
    TextView acUserMsgIvMdCert;
    @BindView(R.id.ac_userMsg_tv_smCert)
    TextView acUserMsgTvSmCert;*/
    @BindView(R.id.ac_userMsg_tv_goCert)
    TextView acUserMsgTvGoCert;
    @BindView(R.id.ac_userMsg_tv_tuijian)
    TextView acUserMsgTvTuijian;
    @BindView(R.id.ac_userMsg_tv_allGoods)
    TextView acUserMsgTvAllGoods;
    @BindView(R.id.ac_userMsg_tv_sellSort)
    TextView acUserMsgTvSellSort;
    /*@BindView(R.id.ac_userMsg_tv_sellOver)
    TextView acUserMsgTvSellOver;*/
    /*@BindView(R.id.ac_userMsg_tv_hReputation)
    TextView acUserMsgTvHReputation;*/
    @BindView(R.id.ac_userMsg_tv_hint)
    TextView acUserMsgTvHint;
    @BindView(R.id.ac_userMsg_tv_realName)
    TextView acUserMsgTvRealName;
    @BindView(R.id.ac_userMsg_tv_purchase)
    TextView acUserMsgTvPurchase;
    @BindView(R.id.ac_userMsg_tv_zizhi)
    TextView acUserMsgTvZizhi;
    @BindView(R.id.ac_userMsg_tv_enterprice)
    TextView acUserMsgTvEnterprice;
    @BindView(R.id.ac_userMsg_tv_trust)
    TextView acUserMsgTvTrust;
    @BindView(R.id.ac_userMsg_iv_sellSort)
    ImageView acUserMsgIvSellSort;
    @BindView(R.id.ac_userMsg_ll_sellSort)
    LinearLayout acUserMsgLlSellSort;
    @BindView(R.id.ac_userMsg_tv_isAttention)
    TextView acUserMsgTvIsAttention;
    @BindView(R.id.ac_userMsg_tv_zizhiName)
    TextView acUserMsgTvZizhiName;
    @BindView(R.id.ac_userMsg_sv)
    NestedScrollView acUserMsgSv;
    @BindView(R.id.title)
    RelativeLayout title;


    private IWXAPI api;
    private Bitmap bmp;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String level = SharePerferenceUtils.getUserLevel(this);
    private String att_user_id = "";
    private String type = "1";
    private String[] certStrs={"0","0","0","0","0"};

    private boolean flag;
    private boolean isAttention;


    private static final String TAG = "测试";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg);
        ButterKnife.bind(this);

        acUserMsgTvAddress.setSelected(true);

        att_user_id = getIntent().getStringExtra("key0");
        getLookUser();

        back(titleThemeBack);
        titleThemeImgRight.setImageResource(R.drawable.item_goods_more1);


        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        if (!att_user_id.equals(user_id)) {
            acUserMsgTvGoCert.setVisibility(View.GONE);
        }
        if (!level.equals("0")) {
            acUserMsgTvBeDYR.setVisibility(View.INVISIBLE);
        }

        LatLng latLng = new LatLng(Double.parseDouble(SharePerferenceUtils.getLat(this)), Double.parseDouble(SharePerferenceUtils.getLng(this)));
        getLocateinfo(latLng);
        getStoreGoodsList();
    }

    private void delAttention() {
        SetSubscribe.delAttention(user_id, token, att_user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                showToast("取消关注成功");
                getLookUser();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void attention() {
        DiscoverSubscribe.attention(user_id, token, att_user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AttentionResponseBean bean = GsonUtils.fromJson(result, AttentionResponseBean.class);
                switch (bean.getData().getStatus()) {
                    case 1:
                        showToast("关注成功");
                        break;
                    case 2:
                        showToast("关注失败");
                        break;
                    case 3:
                        showToast("已经关注过了");
                        break;
                }
                getLookUser();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    private void getStoreGoodsList() {
        MainSubscribe.storeGoodsList(user_id, att_user_id, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        if (data.size() == 0 || data == null) {
            fgMineRvLike.setVisibility(View.GONE);
            acUserMsgTvHint.setVisibility(View.VISIBLE);
        } else {
            fgMineRvLike.setVisibility(View.VISIBLE);
            acUserMsgTvHint.setVisibility(View.GONE);
        }
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

    private void getLookUser() {
        DiscoverSubscribe.lookUser(SharePerferenceUtils.getUserId(this), SharePerferenceUtils.getToken(this), att_user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        if (data.getHead_img() == null || data.getHead_img().equals("")) {
            acUserMsgMcvHeadImg.setImageResource(R.drawable.logo);
        } else {
            Glide.with(this).load(Uri.parse(data.getHead_img())).into(acUserMsgMcvHeadImg);
        }
        if (data.getNick_name().substring(0, 2).equals("用户")) {
            acUserMsgTvNickName.setText("农品街用户");
        } else {
            acUserMsgTvNickName.setText(data.getNick_name());
        }

        if (data.getIs_attention() == 0) {
            isAttention = false;
            acUserMsgTvIsAttention.setText("立即关注");
        } else {
            isAttention = true;
            acUserMsgTvIsAttention.setText("取消关注");
        }
        acUserMsgTvTrust.setText((int) Double.parseDouble(data.getBail()) + "元保证金");
        acUserMsgTvSignature.setText(data.getSignature() == null ? "这个人很懒，什么也没留下" : data.getSignature());
        MyCustomUtils.showLevelImg(data.getCity_level(), data.getBoss_level(), data.getTeam_level(), data.getLevel(), acUserMsgTvLevel);
        acUserMsgTvHits.setText(data.getHits() == null ? "0" : data.getHits());
        acUserMsgTvAttNum.setText(data.getAtt_num() == null ? "0" : data.getAtt_num());
        acUserMsgTvHistoryMoney.setText(data.getHistory_money());
        acUserMsgTvReputation.setText(data.getReputation() == null ? "0" : data.getReputation());
        acUserMsgTvAddress.setText(new GetAddressUtil(this).getAddress(Double.parseDouble(data.getLng()), Double.parseDouble(data.getLat())));

        /*if (Double.parseDouble(data.getHistory_money()) >= 10000.00) {
            acUserMsgTvSellOver.setVisibility(View.VISIBLE);
        } else {
            acUserMsgTvSellOver.setVisibility(View.GONE);
        }*/

        /*if (Double.parseDouble(data.getReputation()) >= 100) {
            acUserMsgTvHReputation.setVisibility(View.VISIBLE);
        } else {
            acUserMsgTvHReputation.setVisibility(View.GONE);
        }*/

        String[] strs = data.getCertification().split(",");
        for (String str : strs) {
            if (str.equals("1")) {
                certStrs[0]="1";
                acUserMsgTvRealName.setVisibility(View.VISIBLE);
            } else if (str.equals("2")) {
                certStrs[1]="2";
                acUserMsgTvEnterprice.setVisibility(View.VISIBLE);
            } else if (str.equals("3")) {
                certStrs[2]="3";
                acUserMsgTvPurchase.setVisibility(View.VISIBLE);
            } else if (str.equals("4")) {
                certStrs[3]="4";
                acUserMsgTvTrust.setVisibility(View.VISIBLE);
            } else if (str.equals("5")) {
                certStrs[4]="5";
                acUserMsgTvZizhi.setVisibility(View.VISIBLE);
                acUserMsgTvZizhiName.setVisibility(View.VISIBLE);
                acUserMsgTvZizhiName.setText(data.getCompany_type());
            }
        }

        if( !certStrs[0].equals("0") && !certStrs[1].equals("0") && !certStrs[2].equals("0") && !certStrs[3].equals("0") && !certStrs[4].equals("0")){
            acUserMsgTvGoCert.setVisibility(View.GONE);
        }else{
            acUserMsgTvGoCert.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.title_theme_img_right, R.id.ac_userMsg_tv_beDYR, R.id.ac_userMsg_tv_tuijian,
            R.id.ac_userMsg_tv_allGoods, R.id.ac_userMsg_ll_sellSort,
            R.id.ac_userMsg_tv_goCert, R.id.ac_userMsg_tv_isAttention})
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
                acUserMsgIvSellSort.setImageResource(R.drawable.unselect);
                type = "1";
                getStoreGoodsList();
                break;
            case R.id.ac_userMsg_tv_allGoods:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.app_theme));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgIvSellSort.setImageResource(R.drawable.unselect);
                type = "2";
                getStoreGoodsList();
                break;
            case R.id.ac_userMsg_ll_sellSort:
                toggle();
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.text6));
                break;
            /*case R.id.ac_userMsg_tv_priceSort:
                acUserMsgTvTuijian.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvAllGoods.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.text6));
                acUserMsgTvPriceSort.setTextColor(getResources().getColor(R.color.app_theme));
                type = "4";
                getStoreGoodsList();
                break;*/
            /*case R.id.ac_userMsg_tv_toShare:
                //bmp=SimpleUtil.createViewBitmap(acUserMsgLlShare);
                showSingleBottomDialog();
                break;*/
            case R.id.ac_userMsg_tv_goCert:
                startActivity(new Intent(UserMsgActivity.this, MyAttestationActivity.class));
                break;
            case R.id.ac_userMsg_tv_isAttention:
                attention_toggle();
                break;
        }
    }

    private void attention_toggle() {
        if (isAttention) {
            delAttention();
        } else {
            attention();
        }
    }

    private void toggle() {
        acUserMsgTvSellSort.setTextColor(getResources().getColor(R.color.app_theme));
        flag = !flag;
        if (flag) {
            acUserMsgIvSellSort.setImageResource(R.drawable.select1);
            type = "3";
        } else {
            acUserMsgIvSellSort.setImageResource(R.drawable.select2);
            type = "4";
        }
        getStoreGoodsList();
    }

    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_mine_menu, null);
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_45));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_100));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 0, 0);
        //TextView share = contentView.findViewById(R.id.pw_mineMenu_tv_share);
        TextView attention = contentView.findViewById(R.id.pw_mineMenu_tv_attention);
        TextView report = contentView.findViewById(R.id.pw_mineMenu_tv_report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                showPublishPopwindow();
            }
        });
        /*share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                acUserMsgLlShow.setVisibility(View.VISIBLE);
                //showSingleBottomDialog();
                //构建文本信息的分享对象（其它的有WXVideoObject,WXImageObject等），内容为hello
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
                api.sendReq(req);
            }
        });*/
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
                //acUserMsgLlShow.setVisibility(View.GONE);
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
                //acUserMsgLlShow.setVisibility(View.GONE);
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
                //acUserMsgLlShow.setVisibility(View.GONE);
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
                window.dismiss();
                backgroundAlpha(1f);
                openActivity(ReportActivity.class, "该账号存在欺骗诈钱行为", att_user_id);
            }
        });
        reason2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                openActivity(ReportActivity.class, "该账号存在其它违法行为", att_user_id);
            }
        });
        reason3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                openActivity(ReportActivity.class, "该账号侵犯他人权益", att_user_id);
            }
        });
        reason4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                openActivity(ReportActivity.class, "该账号存在虚假信息", att_user_id);
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
                backgroundAlpha(1f);
            }
        });
    }

    private void getLocateinfo(LatLng latLng) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                acUserMsgTvAddress.setText(reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
                //Log.e(TAG, "onGetReverseGeoCodeResult: "+ reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

}
