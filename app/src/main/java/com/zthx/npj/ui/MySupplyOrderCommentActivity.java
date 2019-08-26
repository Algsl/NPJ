package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.MySupplyOrderCommentBean;
import com.zthx.npj.net.been.MySupplyOrderConfirmResponseBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MySupplyOrderCommentActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_orderComment_rb_goodsStar)
    RatingBar acOrderCommentRbGoodsStar;
    @BindView(R.id.ac_orderComment_tv_goods)
    TextView acOrderCommentTvGoods;
    @BindView(R.id.ac_orderComment_et_content)
    EditText acOrderCommentEtContent;
    @BindView(R.id.ac_orderComment_iv_img)
    ImageView acOrderCommentIvImg;
    @BindView(R.id.ac_orderComment_rb_logisticsStar)
    RatingBar acOrderCommentRbLogisticsStar;
    @BindView(R.id.ac_orderComment_tv_logistics)
    TextView acOrderCommentTvLogistics;
    @BindView(R.id.ac_orderComment_rb_serviceStar)
    RatingBar acOrderCommentRbServiceStar;
    @BindView(R.id.ac_orderComment_tv_service)
    TextView acOrderCommentTvService;
    @BindView(R.id.ac_orderComment_iv_goodsImg)
    ImageView acOrderCommentIvGoodsImg;

    String goods_start;
    String logistics_start;
    String service_start;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    private static final int CHOOSE_PHOTO = 1;
    @BindView(R.id.title_back)
    ImageView titleBack;
    private String img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
        getOrderDetail();

        back(titleBack);
        changeTitle(acTitle,"发表评价");
        atLocationStoreTvRuzhu.setText("提交");

        acOrderCommentRbGoodsStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch (String.valueOf(v)) {
                    case "1.0":
                        acOrderCommentTvGoods.setText("差");
                        break;
                    case "2.0":
                        acOrderCommentTvGoods.setText("较差");
                        break;
                    case "3.0":
                        acOrderCommentTvGoods.setText("一般");
                        break;
                    case "4.0":
                        acOrderCommentTvGoods.setText("好");
                        break;
                    case "5.0":
                        acOrderCommentTvGoods.setText("很好");
                        break;
                }
                goods_start = String.valueOf(v);
            }
        });
        acOrderCommentRbLogisticsStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch (String.valueOf(v)) {
                    case "1.0":
                        acOrderCommentTvLogistics.setText("差");
                        break;
                    case "2.0":
                        acOrderCommentTvLogistics.setText("较差");
                        break;
                    case "3.0":
                        acOrderCommentTvLogistics.setText("一般");
                        break;
                    case "4.0":
                        acOrderCommentTvLogistics.setText("好");
                        break;
                    case "5.0":
                        acOrderCommentTvLogistics.setText("很好");
                        break;
                }
                logistics_start = String.valueOf(v);
            }
        });
        acOrderCommentRbServiceStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch (String.valueOf(v)) {
                    case "1.0":
                        acOrderCommentTvService.setText("差");
                        break;
                    case "2.0":
                        acOrderCommentTvService.setText("较差");
                        break;
                    case "3.0":
                        acOrderCommentTvService.setText("一般");
                        break;
                    case "4.0":
                        acOrderCommentTvService.setText("好");
                        break;
                    case "5.0":
                        acOrderCommentTvService.setText("很好");
                        break;
                }
                service_start = String.valueOf(v);
            }
        });
    }

    private void getOrderDetail() {
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.mySupplyOrderConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setOrderDetail(String result) {
        MySupplyOrderConfirmResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderConfirmResponseBean.class);
        MySupplyOrderConfirmResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acOrderCommentIvGoodsImg);
    }


    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.ac_orderComment_iv_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                String order_id = getIntent().getStringExtra("order_id");
                MySupplyOrderCommentBean bean = new MySupplyOrderCommentBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setContent(acOrderCommentEtContent.getText().toString().trim());
                bean.setImg(img);
                bean.setGoods_star(goods_start);
                bean.setLogistics_star(logistics_start);
                bean.setService_star(service_start);

                SetSubscribe.mySupplyOrderComment(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.ac_orderComment_iv_img:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectImg = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectImg, filePath, null, null, null);
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(filePath[0]);
                    String path = cursor.getString(index);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    acOrderCommentIvImg.setImageBitmap(bitmap);
                    HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                            UpLoadPicResponseBean.DataBean data = bean.getData();
                            img = data.getSrc();
                        }
                    });
                }
                break;
        }
    }
}
