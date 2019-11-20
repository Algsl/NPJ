package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.adapter.GoodsCateAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.EditGoodsBean;
import com.zthx.npj.net.been.GoodsCateResponseBean;
import com.zthx.npj.net.been.GoodsInfoResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.been.ZiZhiResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StoreGoodsInfoActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_storeGoodsInfo_et_goodsName)
    EditText acStoreGoodsInfoEtGoodsName;
    @BindView(R.id.ac_storeGoodsInfo_et_goodsDesc)
    EditText acStoreGoodsInfoEtGoodsDesc;
    @BindView(R.id.ac_storeGoodsInfo_et_platformPrice)
    EditText acStoreGoodsInfoEtPlatformPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_memberPrice)
    EditText acStoreGoodsInfoEtMemberPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_marketPrice)
    EditText acStoreGoodsInfoEtMarketPrice;
    @BindView(R.id.ac_storeGoodsInfo_et_inventory)
    EditText acStoreGoodsInfoEtInventory;
    @BindView(R.id.ac_storeGoodsInfo_tv_cateName)
    TextView acStoreGoodsInfoTvCateName;
    @BindView(R.id.ac_storeGoodsInfo_et_isFreeShipping)
    EditText acStoreGoodsInfoEtIsFreeShipping;
    @BindView(R.id.ac_storeGoodsInfo_tv_goodsType)
    TextView acStoreGoodsInfoTvGoodsType;
    @BindView(R.id.ac_storeGoodsInfo_btn_pulish)
    Button acStoreGoodsInfoBtnPulish;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.ac_storeGoodsInfo_iv_goodsImg)
    ZzImageBox acStoreGoodsInfoIvGoodsImg;
    @BindView(R.id.ac_storeGoodsInfo_iv_goodsContent)
    ZzImageBox acStoreGoodsInfoIvGoodsContent;

    private static final int CHOOSE_PHOTO1 = 1;
    private static final int CHOOSE_PHOTO2 = 2;
    @BindView(R.id.publish_goods_3)
    TextView publishGoods3;
    @BindView(R.id.ac_pulishGoods_rl_cateName)
    RelativeLayout acPulishGoodsRlCateName;
    @BindView(R.id.ac_pulishGoods_rl_goodsType)
    RelativeLayout acPulishGoodsRlGoodsType;
    @BindView(R.id.at_publishGoods_iv_isTuiJian)
    ImageView atPublishGoodsIvIsTuiJian;
    @BindView(R.id.ac_publishGoods_iv_hint1)
    ImageView acPublishGoodsIvHint1;
    @BindView(R.id.ac_publishGoods_iv_hint2)
    ImageView acPublishGoodsIvHint2;
    @BindView(R.id.ac_publishGoods_iv_hint3)
    ImageView acPublishGoodsIvHint3;
    @BindView(R.id.ac_publishGoods_iv_hint4)
    ImageView acPublishGoodsIvHint4;

    private List<String> paths1 = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();
    private List<String> paths3 = new ArrayList<>();
    private List<String> paths4 = new ArrayList<>();
    private List<String> paths5 = new ArrayList<>();
    private List<String> paths6 = new ArrayList<>();
    private String goodsImg;
    private String goodsContent;
    private String goods_type = "0";
    private String cate_id = "";
    private String itemResult = "";
    private boolean isTuiJian;
    private String is_recommend2 = "0";

    private String str1 = "平台结算价是本商品售出并在买家确认收货后，平台结算给您的价格，不设账期，可立即提现。如果卖家不点击“确认收货”，则在签收后7日内自动结算。";
    private String str2 = "VIP代言价是代言人购买您商品时的价格，要求略高于结算价，平台将差价部分奖励给分享会员。\n" +
            "农品街设立“价高反馈功能，如果您发布的商品价格高于其他平台，用户通过该功能将直接下架您的商品，农品街不收取开店费，0佣金，0抽成,因此希望您按全网最低价销售商品。";
    private String str3 = "市场参考价是您发布的商品在市场上的公开价格，要求高于代言价和结算价。";
    private String str4 = "农品街-人人开店包邮原则，即：卖家承担物流运费。\n" +
            "卖家在设定商品价格时需要将运费考虑其中。";

    private boolean isRenZheng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods_info);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "编辑商品");

        getStoreGoodsInfo();
        getGoodsCate();

        getZizhiRenzheng();

        acStoreGoodsInfoIvGoodsImg.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(StoreGoodsInfoActivity.this).load(url).into(iv);
            }
        });
        acStoreGoodsInfoIvGoodsContent.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(StoreGoodsInfoActivity.this).load(url).into(iv);
            }
        });

        acStoreGoodsInfoIvGoodsImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths1.remove(position);
                acStoreGoodsInfoIvGoodsImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO1);*/
                ImageSelectorUtils.openPhoto(StoreGoodsInfoActivity.this, CHOOSE_PHOTO1, false, 5 - paths1.size());
            }
        });

        acStoreGoodsInfoIvGoodsContent.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths2.remove(position);
                acStoreGoodsInfoIvGoodsContent.removeImage(position);
            }

            @Override
            public void onAddClick() {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO2);*/
                ImageSelectorUtils.openPhoto(StoreGoodsInfoActivity.this, CHOOSE_PHOTO2);
            }
        });

    }

    private void getStoreGoodsInfo() {
        SetSubscribe.goodsInfo(user_id, token, getIntent().getStringExtra("goods_id"), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoodsInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setStoreGoodsInfo(String result) {
        GoodsInfoResponseBean bean = GsonUtils.fromJson(result, GoodsInfoResponseBean.class);
        GoodsInfoResponseBean.DataBean data = bean.getData();
        acStoreGoodsInfoEtGoodsName.setText(data.getGoods_name());
        acStoreGoodsInfoEtGoodsDesc.setText(data.getGoods_desc());
        cate_id = data.getCate_id() + "";
        acStoreGoodsInfoEtPlatformPrice.setText(data.getPlatform_price());
        acStoreGoodsInfoEtMemberPrice.setText(data.getMember_price());
        acStoreGoodsInfoEtMarketPrice.setText(data.getMarket_price());
        acStoreGoodsInfoEtInventory.setText(data.getInventory() + "");
        acStoreGoodsInfoTvCateName.setText(data.getCate_name());

        for (int i = 0; i < data.getGoods_img().size(); i++) {
            acStoreGoodsInfoIvGoodsImg.addImageOnline(data.getGoods_img().get(i));
            paths1.add(data.getGoods_img().get(i));
        }
        for (int i = 0; i < data.getGoods_content().size(); i++) {
            acStoreGoodsInfoIvGoodsContent.addImageOnline(data.getGoods_content().get(i));
            paths2.add(data.getGoods_content().get(i));
        }

        if (data.getIs_free_shipping() == 0) {
            acStoreGoodsInfoEtIsFreeShipping.setText("包邮");
        } else {
            acStoreGoodsInfoEtIsFreeShipping.setText("不包邮");
        }
        if (data.getGoods_type() == 0) {
            acStoreGoodsInfoTvGoodsType.setText("平台所有用户可显示购买");
        } else if (data.getGoods_type() == 1) {
            acStoreGoodsInfoTvGoodsType.setText("仅对直接邀请用户可显示购买");
        } else {
            acStoreGoodsInfoTvGoodsType.setText("仅对直接和间接邀请用户可显示购买");
        }
    }


    private void getZizhiRenzheng() {
        CertSubscribe.zizhi(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ZiZhiResponseBean bean = GsonUtils.fromJson(result, ZiZhiResponseBean.class);
                if(bean.getData().getStatus()==3){
                    isRenZheng=true;
                }else{
                    isRenZheng=false;
                }
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.title_theme_back, R.id.ac_storeGoodsInfo_iv_goodsImg, R.id.ac_storeGoodsInfo_iv_goodsContent,
            R.id.ac_storeGoodsInfo_btn_pulish, R.id.ac_pulishGoods_rl_cateName, R.id.ac_pulishGoods_rl_goodsType,
            R.id.at_publishGoods_iv_isTuiJian,R.id.ac_publishGoods_iv_hint1, R.id.ac_publishGoods_iv_hint2,
            R.id.ac_publishGoods_iv_hint3, R.id.ac_publishGoods_iv_hint4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_theme_back:
                break;
            case R.id.ac_storeGoodsInfo_iv_goodsImg:
                break;
            case R.id.ac_storeGoodsInfo_iv_goodsContent:
                break;
            case R.id.ac_storeGoodsInfo_btn_pulish:
                //publishImages();
                getImgPath();
                break;
            case R.id.ac_pulishGoods_rl_cateName:
                showItemPopwindow();
                break;
            case R.id.ac_pulishGoods_rl_goodsType:
                showBottomDialog();
                break;
            case R.id.at_publishGoods_iv_isTuiJian:
                toggle();
                break;
            case R.id.ac_publishGoods_iv_hint1:
                showPublishPopwindow(str1, R.dimen.dp_210);
                break;
            case R.id.ac_publishGoods_iv_hint2:
                showPublishPopwindow(str2, R.dimen.dp_300);
                break;
            case R.id.ac_publishGoods_iv_hint3:
                showPublishPopwindow(str3, R.dimen.dp_174);
                break;
            case R.id.ac_publishGoods_iv_hint4:
                showPublishPopwindow(str4, R.dimen.dp_195);
                break;
        }
    }

    private void toggle() {
        isTuiJian = !isTuiJian;
        if (isTuiJian) {
            is_recommend2 = "1";
            atPublishGoodsIvIsTuiJian.setImageResource(R.drawable.at_edit_address_selector);
        } else {
            is_recommend2 = "0";
            atPublishGoodsIvIsTuiJian.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }

    public void publishImages() {
        HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                goodsImg = data.getImg();

                uploadGoodsContent();
            }
        });
    }


    public void uploadGoodsContent() {
        HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                goodsContent = data.getImg();
                //showToast("请等待信息上传");
                editStoreGoodsInfo();
            }
        });
    }

    private void editStoreGoodsInfo() {
        EditGoodsBean bean = new EditGoodsBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_name(getEtString(acStoreGoodsInfoEtGoodsName));
        bean.setGoods_id(getIntent().getStringExtra("goods_id"));
        bean.setGoods_desc(getEtString(acStoreGoodsInfoEtGoodsDesc));
        bean.setGoods_img(goodsImg);
        bean.setGoods_content(goodsContent);
        bean.setPlatform_price(getEtString(acStoreGoodsInfoEtPlatformPrice));
        bean.setMarket_price(getEtString(acStoreGoodsInfoEtMarketPrice));
        bean.setMember_price(getEtString(acStoreGoodsInfoEtMemberPrice));
        bean.setInventory(getEtString(acStoreGoodsInfoEtInventory));
        bean.setCate_id(cate_id);
        bean.setIs_free_shipping("0");
        bean.setIs_recommend2(is_recommend2);
        SetSubscribe.editGoods(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("信息修改成功");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    public String getEtString(EditText et) {
        return et.getText().toString().trim();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO1:
                if (resultCode != 0) {
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for (int i = 0; i < images.size(); i++) {
                        paths1.add(compress(images.get(i)));
                        acStoreGoodsInfoIvGoodsImg.addImage(compress(images.get(i)));
                    }
                }
                break;
            case CHOOSE_PHOTO2:
                if (resultCode != 0) {
                    ArrayList<String> images1 = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for (int i = 0; i < images1.size(); i++) {
                        paths2.add(compress(images1.get(i)));
                        acStoreGoodsInfoIvGoodsContent.addImage(compress(images1.get(i)));
                    }
                }
                break;
        }
    }

    private void getGoodsCate() {
        SetSubscribe.goodsCate(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                itemResult = result;
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_show_type_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.dl_showType_tv_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_type = "0";
                acStoreGoodsInfoTvGoodsType.setText("平台所有用户可显示购买");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_showType_tv_invite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_type = "1";
                acStoreGoodsInfoTvGoodsType.setText("仅对直接邀请用户可显示购买");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_showType_tv_inviteAndIndirecct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_type = "2";
                acStoreGoodsInfoTvGoodsType.setText("仅对直接和间接邀请用户可显示购买");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_showType_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_cartid, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_300));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
        final GoodsCateResponseBean bean = GsonUtils.fromJson(itemResult, GoodsCateResponseBean.class);
        final ExpandableListView elv = contentView.findViewById(R.id.pw_storeCartId_elv);
        final GoodsCateAdapter adapter = new GoodsCateAdapter(this, bean.getData());
        elv.setAdapter(adapter);
        elv.setDivider(null);
        elv.setGroupIndicator(null);
        int groupCount = elv.getCount();
        for (int i = 0; i < groupCount; i++) {
            elv.expandGroup(i);
        }
        adapter.setOnItemClickListener(new GoodsCateAdapter.ItemClickListener() {
            @Override
            public void groupMsg(String cate_id, String cate_name) {

            }

            @Override
            public void childMsg(String group_id, String id, String cate_name) {
                if(group_id.equals("22") || group_id.equals("107")){
                    if(!isRenZheng){
                        showDialog();
                    }
                }else{
                    cate_id = id;
                    acStoreGoodsInfoTvCateName.setText(cate_name);
                    backgroundAlpha(1f);
                    window.dismiss();
                }
            }

            /*@Override
            public void childMsg(String id, String cate_name) {
                cate_id = id;
                acStoreGoodsInfoTvCateName.setText(cate_name);
                backgroundAlpha(1f);
                window.dismiss();
            }*/
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }

    private void showDialog() {
        CommonDialog commonDialog = new CommonDialog(this, R.style.dialog, "该分类商品需进行资质认证才能发布",false, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    openActivity(MyAttestationActivity.class);
                }
            }
        });
        commonDialog.setPositiveButton("去资质认证");
        commonDialog.show();
    }

    /**
     * 图片压缩
     *
     * @param path：原始图片路径
     * @return
     */
    public String compress(String path) {
        File file = new File(path);
        Bitmap compressBitmap;
        /*if (file.length()>=2.5*1024*1024){//从相册中选择照片，2.5M以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 3;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else */
        if (file.length() >= 600 * 1024) {//从相册中选择照片，600k以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            compressBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        } else {
            compressBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        compressBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        int options1 = 90;
        while (bos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bos.reset(); // 重置baos即清空baos
            compressBitmap.compress(Bitmap.CompressFormat.JPEG, options1, bos);// 这里压缩options%，把压缩后的数据存放到baos中
            options1 -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(bos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        compressBitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        String bmString = "";
        try {
            File bmFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString = bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
    }


    public void getImgPath() {
        for (String str : paths1) {
            if (str.split("http://app.npj-vip.com").length == 1) {
                paths3.add(str);
            } else {//解析全链接
                paths4.add(str.split("http://app.npj-vip.com")[1]);
            }
        }
        if (paths3.size() == 0) {//没有上传新的图片
            String paths3Str = "";
            for (int i = 0; i < paths4.size(); i++) {
                if (i == paths4.size() - 1) {
                    paths3Str += paths4.get(i);
                } else {
                    paths3Str += paths4.get(i) + ",";
                }
            }
            goodsImg = paths3Str;
            getContentPath();
        } else {//上传了新的图片
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths3, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    String paths3Str = "";
                    for (String str : paths4) {
                        paths3Str += str + ",";
                    }
                    goodsImg = paths3Str + data.getImg();
                    getContentPath();
                }
            });
        }
    }

    public void getContentPath() {
        for (String str : paths2) {
            if (str.split("http://app.npj-vip.com").length == 1) {
                paths5.add(str);
            } else {//解析全链接
                paths6.add(str.split("http://app.npj-vip.com")[1]);
            }
        }
        if (paths5.size() == 0) {//没有上传新的图片
            String paths3Str = "";
            for (int i = 0; i < paths6.size(); i++) {
                if (i == paths6.size() - 1) {
                    paths3Str += paths6.get(i);
                } else {
                    paths3Str += paths6.get(i) + ",";
                }
            }
            goodsContent = paths3Str;
            editStoreGoodsInfo();
        } else {//上传了新的图片
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths5, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    String paths3Str = "";
                    for (String str : paths6) {
                        paths3Str += str + ",";
                    }
                    goodsContent = paths3Str + data.getImg();
                    editStoreGoodsInfo();
                }
            });
        }
    }

    public void showPublishPopwindow(String str, int id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_publish_goods, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_280));
        window.setHeight((int) getResources().getDimension(id));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView tv = contentView.findViewById(R.id.pw_publishGoods_tv_content);
        Button btn = contentView.findViewById(R.id.pw_publishGoods_tv_know);
        tv.setText(str);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }
}
