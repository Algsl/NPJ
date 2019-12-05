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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.MySupplyOrderCommentBean;
import com.zthx.npj.net.been.MySupplyOrderConfirmResponseBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

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
    ZzImageBox acOrderCommentIvImg;
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
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_orderComment_iv_isHint)
    ImageView acOrderCommentIvIsHint;

    String goods_start="5";
    String logistics_start="5";
    String service_start="5";

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    private static final int CHOOSE_PHOTO = 1;
    private List<String> paths = new ArrayList<>();
    private String img;
    private boolean isHint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
        getOrderDetail();

        back(titleBack);
        changeTitle(acTitle, "发表评价");
        atLocationStoreTvRuzhu.setText("提交");

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
        SetSubscribe.mySupplyOrderConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MySupplyOrderConfirmResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderConfirmResponseBean.class);
        MySupplyOrderConfirmResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acOrderCommentIvGoodsImg);
    }


    @OnClick({R.id.at_location_store_tv_ruzhu,R.id.ac_orderComment_iv_isHint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                if(acOrderCommentEtContent.getText().toString().trim().equals("")){
                    showToast("请填写您对商品的评价");
                }/*else {
                    if(paths==null || paths.size()<1){
                        img="";
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
                }*/
                else if(paths==null || paths.size()<1){
                    showToast("请上传图片");
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

    private void commentConfirm() {
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
                //showToast(errorMsg);
            }
        }));
    }

    private void toggle() {
        isHint = !isHint;
        if (isHint) {
            acOrderCommentIvIsHint.setImageResource(R.drawable.confirm_select);
        } else {
            acOrderCommentIvIsHint.setImageResource(R.drawable.confirm_unselect);
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
                    cursor.close();
                    paths.add(compress(path));
                    acOrderCommentIvImg.addImage(compress(path));
                }
                break;
        }
    }

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
}
