package com.zthx.npj.ui;

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
    TextView atOrderApplyRefundTvOrderPrice;
    @BindView(R.id.at_order_applyRefund_tv_ship)
    TextView atOrderApplyRefundTvShip;
    @BindView(R.id.ac_order_applyRefund_et_reason)
    EditText acOrderApplyRefundEtReason;
    @BindView(R.id.ac_order_applyRefund_iv_img)
    ImageView acOrderApplyRefundIvImg;
    @BindView(R.id.ac_order_applyRefund_btn_confirm)
    Button acOrderApplyRefundBtnConfirm;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MySupplyOrderConfirmResponseBean.DataBean data;
    private static final int CHOOSE_PHOTO = 1;
    @BindView(R.id.title_back)
    ImageView titleBack;
    private String img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_applyrefund);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"申请退款");

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
        atOrderApplyRefundTvOrderPrice.setText("￥ " + data.getOrder_price());
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
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    acOrderApplyRefundIvImg.setImageBitmap(bitmap);
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

    @OnClick({R.id.at_order_applyRefund_tv_state, R.id.at_order_applyRefund_tv_reason, R.id.ac_order_applyRefund_iv_img, R.id.ac_order_applyRefund_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_order_applyRefund_tv_state:
                break;
            case R.id.at_order_applyRefund_tv_reason:
                break;
            case R.id.ac_order_applyRefund_iv_img:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                break;
            case R.id.ac_order_applyRefund_btn_confirm:
                MySupplyOrderRefundBean bean = new MySupplyOrderRefundBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(getIntent().getStringExtra("order_id"));
                bean.setRefund_state("0");
                bean.setRefund_reason("颜色不对");
                bean.setRefund_price(data.getOrder_price());
                bean.setRefund_desc(acOrderApplyRefundEtReason.getText().toString().trim());
                bean.setRefund_img(img);
                SetSubscribe.mySupplyOrderRefund(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
