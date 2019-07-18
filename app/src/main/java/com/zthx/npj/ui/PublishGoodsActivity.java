package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AddGoodsBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

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

public class PublishGoodsActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
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
    @BindView(R.id.ac_pulishGoods_et_isFreeShipping)
    EditText acPulishGoodsEtIsFreeShipping;
    @BindView(R.id.publish_goods_iv)
    ImageView publishGoodsIv;
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
    private List<String> paths1 = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();
    private String requestUrl = "http://app.npj-vip.com/index.php/api/set/uploadimagegroup.html";
    private String goodsImg, goodsContent;
    private String str1="平台结算价是本商品售出并在买家确认收货后，平台结算给您的价格，不设账期，可立即提现。如果卖家不点击“确认收货”，则在签收后7日内自动结算。";
    private String str2="市场参考价是您发布的商品在市场上的公开价格，要求高于代言价和结算价。";
    private String str3="VIP代言价是代言人购买您商品时的价格，要求略高于结算价，平台将差价部分奖励给分享会员。\n" +
            "农品街设立“价高反馈功能，如果您发布的商品价格高于其他平台，用户通过该功能将直接下架您的商品，农品街不收取开店费，0佣金，0抽成,因此希望您按全网最低价销售商品。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulish_goods);
        ButterKnife.bind(this);

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
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO1);
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
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO2);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO1:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        paths1.add(path);
                        acPulishGoodsIvGoodsImg.addImage(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO2:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        paths2.add(path);
                        acPulishGoodsIvGoodsContent.addImage(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @OnClick({R.id.ac_pulishGoods_tv_goodsType, R.id.ac_pulishGoods_btn_pulish,R.id.ac_publishGoods_iv_hint1, R.id.ac_publishGoods_iv_hint2, R.id.ac_publishGoods_iv_hint3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_pulishGoods_tv_goodsType:
                HttpUtils.uploadMoreImg(requestUrl, paths1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        goodsImg = data.getImg();
                    }
                });
                HttpUtils.uploadMoreImg(requestUrl, paths2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        goodsContent = data.getImg();
                    }
                });
                break;
            case R.id.ac_pulishGoods_btn_pulish:
                pulishGoods();
                break;
            case R.id.ac_publishGoods_iv_hint1:
                showPublishPopwindow(str1);
                break;
            case R.id.ac_publishGoods_iv_hint2:
                showPublishPopwindow(str2);
                break;
            case R.id.ac_publishGoods_iv_hint3:
                showPublishPopwindow(str3);
                break;
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
        bean.setCate_id("20");
        bean.setIs_free_shipping("0");
        bean.setGoods_type("1");
        SetSubscribe.addGoods(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    public void showPublishPopwindow(String str) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_publish_goods, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_280));
        window.setHeight((int) getResources().getDimension(R.dimen.dp_240));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView tv=contentView.findViewById(R.id.pw_publishGoods_tv_content);
        Button btn=contentView.findViewById(R.id.pw_publishGoods_tv_know);
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
}
