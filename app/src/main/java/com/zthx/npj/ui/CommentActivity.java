package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.been.OrderCommentBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
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

public class CommentActivity extends ActivityBase {
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
    ZzImageBox acOrderCommentIvImg;
    @BindView(R.id.ac_orderComment_rb_logisticsStar)
    RatingBar acOrderCommentRbLogisticsStar;
    @BindView(R.id.ac_orderComment_tv_logistics)
    TextView acOrderCommentTvLogistics;
    @BindView(R.id.ac_orderComment_rb_serviceStar)
    RatingBar acOrderCommentRbServiceStar;
    @BindView(R.id.ac_orderComment_tv_service)
    TextView acOrderCommentTvService;

    String goods_start="5";
    String logistics_start="5";
    String service_start="5";

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.ac_orderComment_iv_goodsImg)
    ImageView acOrderCommentIvGoodsImg;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_orderComment_iv_isHint)
    ImageView acOrderCommentIvIsHint;

    private List<String> paths = new ArrayList<>();
    private static final int CHOOSE_PHOTO = 1;
    private String img;
    private boolean isHint;

    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
        getOrderDetail();

        back(titleBack);
        changeTitle(acTitle, "发表评价");
        acTitle.setText("发表评价");
        atLocationStoreTvRuzhu.setText("提交");

        type=getIntent().getStringExtra("order_type");

        acOrderCommentIvImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                acOrderCommentIvImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });

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
        SetSubscribe.myOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setOrderDetail(String result) {
        MyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acOrderCommentIvGoodsImg);
    }

    public void commentConfirm() {
        String order_id = getIntent().getStringExtra("order_id");
        OrderCommentBean bean = new OrderCommentBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        bean.setContent(acOrderCommentEtContent.getText().toString().trim());
        bean.setImg(img);
        bean.setGoods_star(goods_start);
        bean.setLogistics_star(logistics_start);
        bean.setService_star(service_start);
        bean.setType(type);
        SetSubscribe.orderComment(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("评价成功");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.at_location_store_tv_ruzhu,R.id.ac_orderComment_iv_isHint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                if(acOrderCommentEtContent.getText().toString().trim().equals("")){
                    showToast("请填写您对商品的评价");
                }else if(paths==null || paths.size()<1){
                    showToast("您还没有上传图片呢！");
                }else{
                    HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                            UploadPicsResponseBean.DataBean data = bean.getData();
                            img = data.getImg();
                            commentConfirm();
                        }
                    });
                }
                break;
            case R.id.ac_orderComment_iv_isHint:
                toggle();
                break;
        }
    }

    private void toggle() {
        isHint=!isHint;
        if(isHint){
            acOrderCommentIvIsHint.setImageResource(R.drawable.confirm_select);
        }else{
            acOrderCommentIvIsHint.setImageResource(R.drawable.confirm_unselect);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        paths.add(path);
                        acOrderCommentIvImg.addImage(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

}
