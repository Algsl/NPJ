package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
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
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.adapter.GoodsCateAdapter;
import com.zthx.npj.net.been.AddGoodsBean;
import com.zthx.npj.net.been.GoodsCateResponseBean;
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

public class PublishGoodsActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_pulishGoods_et_goodsName)
    EditText acPulishGoodsEtGoodsName;
    @BindView(R.id.ac_pulishGoods_et_goodsDesc)
    EditText acPulishGoodsEtGoodsDesc;
    @BindView(R.id.ac_pulishGoods_et_platformPrice)
    EditText acPulishGoodsEtPlatformPrice;
    @BindView(R.id.ac_pulishGoods_et_memberPrice)
    EditText acPulishGoodsEtMemberPrice;
    @BindView(R.id.ac_pulishGoods_et_marketPrice)
    EditText acPulishGoodsEtMarketPrice;
    @BindView(R.id.ac_pulishGoods_et_inventory)
    EditText acPulishGoodsEtInventory;
    @BindView(R.id.ac_pulishGoods_tv_cateId)
    TextView acPulishGoodsTvCateId;
    @BindView(R.id.ac_pulishGoods_tv_goodsType)
    TextView acPulishGoodsTvGoodsType;
    @BindView(R.id.ac_pulishGoods_btn_pulish)
    Button acPulishGoodsBtnPulish;
    @BindView(R.id.ac_pulishGoods_iv_goodsImg)
    ZzImageBox acPulishGoodsIvGoodsImg;
    @BindView(R.id.ac_pulishGoods_iv_goodsContent)
    ZzImageBox acPulishGoodsIvGoodsContent;


    private static final int CHOOSE_PHOTO1 = 1;
    private static final int CHOOSE_PHOTO2 = 2;
    @BindView(R.id.ac_publishGoods_iv_hint1)
    ImageView acPublishGoodsIvHint1;
    @BindView(R.id.ac_publishGoods_iv_hint2)
    ImageView acPublishGoodsIvHint2;
    @BindView(R.id.ac_publishGoods_iv_hint3)
    ImageView acPublishGoodsIvHint3;
    @BindView(R.id.ac_publishGoods_iv_hint4)
    ImageView acPublishGoodsIvHint4;
    @BindView(R.id.ac_pulishGoods_rl_cateId)
    RelativeLayout acPulishGoodsRlCateId;
    @BindView(R.id.ac_pulishGoods_rl_goodsType)
    RelativeLayout acPulishGoodsRlGoodsType;
    @BindView(R.id.at_publishGoods_iv_isTuiJian)
    ImageView atPublishGoodsIvIsTuiJian;

    private List<String> paths1 = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();
    private String requestUrl = "http://app.npj-vip.com/index.php/api/set/uploadimagegroup.html";
    private String goodsImg, goodsContent;
    private String str1 = "平台结算价是本商品售出并在买家确认收货后，平台结算给您的价格，不设账期，可立即提现。如果卖家不点击“确认收货”，则在签收后7日内自动结算。";
    private String str2 = "VIP代言价是代言人购买您商品时的价格，要求略高于结算价，平台将差价部分奖励给分享会员。\n" +
            "农品街设立“价高反馈功能，如果您发布的商品价格高于其他平台，用户通过该功能将直接下架您的商品，农品街不收取开店费，0佣金，0抽成,因此希望您按全网最低价销售商品。";
    private String str3 = "市场参考价是您发布的商品在市场上的公开价格，要求高于代言价和结算价。";
    private String str4 = "农品街-人人开店包邮原则，即：卖家承担物流运费。\n" +
            "卖家在设定商品价格时需要将运费考虑其中。";
    private String goods_type = "0";
    private String itemResult = "";
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String cate_id = "";
    private String cateName = "";
    private boolean isTuiJian;
    private String is_recommend2="0";

    private boolean isRenZheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulish_goods);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "发布商品");

        getGoodsCate();
        getZizhiRenzheng();

        acPulishGoodsIvGoodsImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths1.remove(position);
                acPulishGoodsIvGoodsImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO1);*/
                ImageSelectorUtils.openPhoto(PublishGoodsActivity.this,CHOOSE_PHOTO1,false,5-paths1.size());
            }
        });
        acPulishGoodsIvGoodsContent.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths2.remove(position);
                acPulishGoodsIvGoodsContent.removeImage(position);
            }

            @Override
            public void onAddClick() {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO2);*/
                ImageSelectorUtils.openPhoto(PublishGoodsActivity.this,CHOOSE_PHOTO2);
            }
        });
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


    public void publishImages() {
        HttpUtils.uploadMoreImg(requestUrl, paths1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                goodsImg = data.getImg();
                HttpUtils.uploadMoreImg(requestUrl, paths2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        goodsContent = data.getImg();
                        pulishGoods();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO1:
                if(resultCode!=0){
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i=0;i<images.size();i++){
                        paths1.add(compress(images.get(i)));
                        acPulishGoodsIvGoodsImg.addImage(compress(images.get(i)));
                    }
                }
                break;
            case CHOOSE_PHOTO2:
                if(resultCode!=0){
                    ArrayList<String> images1 = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i=0;i<images1.size();i++){
                        paths2.add(compress(images1.get(i)));
                        acPulishGoodsIvGoodsContent.addImage(compress(images1.get(i)));
                    }
                }
                break;
        }
    }

    @OnClick({R.id.ac_pulishGoods_btn_pulish, R.id.ac_publishGoods_iv_hint1,
            R.id.ac_publishGoods_iv_hint2, R.id.ac_publishGoods_iv_hint3, R.id.ac_publishGoods_iv_hint4,
            R.id.ac_pulishGoods_rl_cateId, R.id.ac_pulishGoods_rl_goodsType, R.id.at_publishGoods_iv_isTuiJian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_pulishGoods_rl_goodsType:
                showBottomDialog();
                break;
            case R.id.ac_pulishGoods_btn_pulish:
                if (acPulishGoodsEtGoodsName.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写商品名称", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsEtGoodsDesc.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写商品简介", Toast.LENGTH_SHORT).show();
                } else if (paths1.size() <= 1 || paths2.size() <= 1) {
                    Toast.makeText(PublishGoodsActivity.this, "请上传至少两张商品图片及详情图片", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsEtPlatformPrice.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写平台结算价格", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsEtMemberPrice.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写VIP会员价格", Toast.LENGTH_SHORT).show();
                }else if (Long.parseLong(acPulishGoodsEtMemberPrice.getText().toString().trim()) <= Long.parseLong(acPulishGoodsEtPlatformPrice.getText().toString().trim())){
                    Toast.makeText(PublishGoodsActivity.this, "VIP会员价格不得低于平台结算价格", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsEtMarketPrice.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写市场参考价格", Toast.LENGTH_SHORT).show();
                }else if (Long.parseLong(acPulishGoodsEtMarketPrice.getText().toString().trim()) <= Long.parseLong(acPulishGoodsEtMemberPrice.getText().toString().trim())) {
                    Toast.makeText(PublishGoodsActivity.this, "市场参考价格不得低于VIP会员价格", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsEtInventory.getText().toString().trim().equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请填写供应数量", Toast.LENGTH_SHORT).show();
                } else if (cate_id.equals("")) {
                    Toast.makeText(PublishGoodsActivity.this, "请选择商品分类", Toast.LENGTH_SHORT).show();
                } else if (acPulishGoodsTvGoodsType.getText().toString().trim().equals("请选择")) {
                    Toast.makeText(PublishGoodsActivity.this, "请选择商品显示类型", Toast.LENGTH_SHORT).show();
                } else {
                    acPulishGoodsBtnPulish.setClickable(false);
                    Toast.makeText(PublishGoodsActivity.this, "商品信息上传中，请等待", Toast.LENGTH_SHORT).show();
                    publishImages();
                }
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
            case R.id.ac_pulishGoods_rl_cateId:
                showItemPopwindow();
                break;
            case R.id.at_publishGoods_iv_isTuiJian:
                toggle();
                break;
        }
    }

    private void toggle() {
        isTuiJian=!isTuiJian;
        if(isTuiJian){
            is_recommend2="1";
            atPublishGoodsIvIsTuiJian.setImageResource(R.drawable.at_edit_address_selector);
        }else{
            is_recommend2="0";
            atPublishGoodsIvIsTuiJian.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }

    public String getEtString(EditText et) {
        return et.getText().toString().trim();
    }

    ;

    private void pulishGoods() {
        AddGoodsBean bean = new AddGoodsBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setGoods_name(getEtString(acPulishGoodsEtGoodsName));
        bean.setGoods_desc(getEtString(acPulishGoodsEtGoodsDesc));
        bean.setGoods_img(goodsImg);
        bean.setGoods_content(goodsContent);
        bean.setPlatform_price(getEtString(acPulishGoodsEtPlatformPrice));
        bean.setMember_price(getEtString(acPulishGoodsEtMemberPrice));
        bean.setMarket_price(getEtString(acPulishGoodsEtMarketPrice));
        bean.setInventory(getEtString(acPulishGoodsEtInventory));
        bean.setCate_id(cate_id);
        bean.setIs_free_shipping("0");
        bean.setGoods_type(goods_type);
        bean.setIs_recommend2(is_recommend2);

        SetSubscribe.addGoods(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(PublishGoodsActivity.this, "商品上传成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                //Toast.makeText(PublishGoodsActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
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
                acPulishGoodsTvGoodsType.setText("平台所有用户可显示购买");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_showType_tv_invite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_type = "1";
                acPulishGoodsTvGoodsType.setText("仅对直接邀请用户可显示购买");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_showType_tv_inviteAndIndirecct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_type = "2";
                acPulishGoodsTvGoodsType.setText("仅对直接和间接邀请用户可显示购买");
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
                    }else{
                        cate_id = id;
                        acPulishGoodsTvCateId.setText(cate_name);
                        backgroundAlpha(1f);
                        window.dismiss();
                    }
                }else{
                    cate_id = id;
                    acPulishGoodsTvCateId.setText(cate_name);
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

    /**
     * 图片压缩
     * @param path：原始图片路径
     * @return
     */
    public String compress(String path){
        File file=new File(path);
        Bitmap compressBitmap;
        /*if (file.length()>=2.5*1024*1024){//从相册中选择照片，2.5M以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 3;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else */if(file.length()>=600*1024){//从相册中选择照片，600k以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else{
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
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

        String bmString="";
        try {
            File bmFile=new File(getExternalCacheDir(), System.currentTimeMillis()+".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString=bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
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
}
