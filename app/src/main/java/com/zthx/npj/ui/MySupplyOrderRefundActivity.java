package com.zthx.npj.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.MySupplyOrderConfirmResponseBean;
import com.zthx.npj.net.been.MySupplyOrderRefundBean;
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

public class MySupplyOrderRefundActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_order_applyRefund_iv_goodsImg)
    ImageView acOrderApplyRefundIvGoodsImg;
    @BindView(R.id.at_order_applyRefund_tv_goodsName)
    TextView atOrderApplyRefundTvGoodsName;
    @BindView(R.id.at_order_applyRefund_tv_goodsPrice)
    TextView atOrderApplyRefundTvGoodsPrice;
    @BindView(R.id.at_order_applyRefund_tv_goodsNum)
    TextView atOrderApplyRefundTvGoodsNum;
    @BindView(R.id.at_order_applyRefund_tv_state)
    TextView atOrderApplyRefundTvState;
    @BindView(R.id.at_order_applyRefund_tv_reason)
    TextView atOrderApplyRefundTvReason;
    @BindView(R.id.at_order_applyRefund_tv_orderPrice)
    EditText atOrderApplyRefundTvOrderPrice;
    @BindView(R.id.at_order_applyRefund_tv_ship)
    TextView atOrderApplyRefundTvShip;
    @BindView(R.id.ac_order_applyRefund_et_reason)
    EditText acOrderApplyRefundEtReason;
    @BindView(R.id.ac_order_applyRefund_iv_img)
    ZzImageBox acOrderApplyRefundIvImg;
    @BindView(R.id.ac_order_applyRefund_btn_confirm)
    Button acOrderApplyRefundBtnConfirm;
    @BindView(R.id.title_back)
    ImageView titleBack;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MySupplyOrderConfirmResponseBean.DataBean data;
    private static final int CHOOSE_PHOTO = 1;
    private String img;
    private List<String> paths = new ArrayList<>();
    private String refund_state="0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_applyrefund);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"申请退款");

        acOrderApplyRefundIvImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                acOrderApplyRefundIvImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });

        getOrderDetail();
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

            }
        }));
    }

    private void setOrderDetail(String result) {
        MySupplyOrderConfirmResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderConfirmResponseBean.class);
        data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acOrderApplyRefundIvGoodsImg);
        atOrderApplyRefundTvGoodsName.setText(data.getGoods_name());
        atOrderApplyRefundTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atOrderApplyRefundTvGoodsNum.setText("x " + data.getOrder_num());
        atOrderApplyRefundTvOrderPrice.setText( data.getOrder_price());
        atOrderApplyRefundTvShip.setText("最多" + data.getOrder_price() + "元，含运费￥" + data.getShipping_fee());
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
                    final String path = cursor.getString(index);
                    cursor.close();
                    paths.add(compress(path));
                    acOrderApplyRefundIvImg.addImage(compress(path));
                }
                break;
        }
    }

    @OnClick({R.id.at_order_applyRefund_tv_state, R.id.at_order_applyRefund_tv_reason,  R.id.ac_order_applyRefund_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_order_applyRefund_tv_state:
                final String[] str=new String[] {"请选择","未发货","已发货","已拒签","已签收"};
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        /*if(which==0){
                                            refund_state=which+"";
                                        }else{
                                            refund_state=(which+1)+"";
                                        }*/
                                        if(which==1){
                                            refund_state="0";
                                        }else{
                                            refund_state=which+"";
                                        }
                                        atOrderApplyRefundTvState.setText(str[which]);
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .show();
                break;
            case R.id.at_order_applyRefund_tv_reason:
                final String[] str1=new String[]{"请选择","不喜欢/效果不好","多拍/错拍/不想要","与商品描述不符","质量问题","卖家发错货"};
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(str1, 0,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        atOrderApplyRefundTvReason.setText(str1[which]);
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .show();
                break;
            case R.id.ac_order_applyRefund_btn_confirm:
                if(atOrderApplyRefundTvState.getText().toString().trim().equals("请选择")){
                    showToast("请选择货物状态");
                }else if(atOrderApplyRefundTvReason.getText().toString().trim().equals("请选择")){
                    showToast("请选择退款原因");
                }else if(atOrderApplyRefundTvOrderPrice.getText().toString().equals("")){
                    showToast("请填写退款金额");
                }else if(Double.parseDouble(atOrderApplyRefundTvOrderPrice.getText().toString().trim())>Double.parseDouble(data.getOrder_price())){
                    showToast("退款金额不能超过订单金额");
                }/*else if(paths==null || paths.size()==0){
                    showToast("请上传凭证");
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
                            uploadData();
                        }
                    });
                }*/
                else{
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
                                uploadData();
                            }
                        });
                    }
                }

                break;
        }
    }

    private void uploadData(){
        MySupplyOrderRefundBean bean = new MySupplyOrderRefundBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(getIntent().getStringExtra("order_id"));
        bean.setRefund_state(refund_state);
        bean.setRefund_reason(atOrderApplyRefundTvReason.getText().toString().trim());
        bean.setRefund_price(atOrderApplyRefundTvOrderPrice.getText().toString().trim());
        bean.setRefund_desc(acOrderApplyRefundEtReason.getText().toString().trim());
        bean.setRefund_img(img);
        SetSubscribe.mySupplyOrderRefund(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("退换申请已提交");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
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

}
