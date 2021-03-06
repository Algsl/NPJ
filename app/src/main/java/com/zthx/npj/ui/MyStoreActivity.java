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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.MyStoreResponseBean;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyStoreActivity extends ActivityBase {

    @BindView(R.id.at_my_store_push_goods)
    LinearLayout atMyStorePushGoods;
    @BindView(R.id.at_my_store_goods_bill)
    LinearLayout atMyStoreGoodsBill;
    @BindView(R.id.at_my_store_goods_list)
    LinearLayout atMyStoreGoodsList;
    @BindView(R.id.ac_myStore_mcv_storeImg)
    ImageView acMyStoreMcvStoreImg;
    @BindView(R.id.ac_myStore_tv_storeName)
    TextView acMyStoreTvStoreName;
    @BindView(R.id.ac_myStore_tv_reputation)
    TextView acMyStoreTvReputation;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;

    private static final int CHOOSE_PHOTO = 1;
    private String store_img="";
    private String store_name="";
    private MyCircleView pwStoreEditMCVStoreImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);
        ButterKnife.bind(this);
        getMyStore();

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的店铺");
        titleThemeTvRight.setText("设置店铺");
    }

    private void getMyStore() {
        SetSubscribe.myStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyStore(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast("请先完善店铺信息");
                showPwUnCancel();
            }
        }));
    }

    private void setMyStore(String result) {
        MyStoreResponseBean bean = GsonUtils.fromJson(result, MyStoreResponseBean.class);
        MyStoreResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getStore_img())).into(acMyStoreMcvStoreImg);
        acMyStoreTvStoreName.setText(data.getStore_name());
        acMyStoreTvReputation.setText("信誉分：" + data.getReputation());
    }

    @OnClick({R.id.at_my_store_push_goods, R.id.at_my_store_goods_bill, R.id.at_my_store_goods_list, R.id.title_theme_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_store_push_goods:
                startActivity(new Intent(this, PublishGoodsActivity.class));
                break;
            case R.id.at_my_store_goods_bill:
                startActivity(new Intent(this, StoreGoodsBillActivity.class));
                break;
            case R.id.at_my_store_goods_list:
                startActivity(new Intent(this, StoreGoodsListActivity.class));
                break;
            case R.id.title_theme_tv_right:
                showPublishPopwindow();
                break;
        }
    }

    //修改店铺信息弹窗
    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_edit, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_360));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

         pwStoreEditMCVStoreImg= contentView.findViewById(R.id.pw_storEdit_mcv_storeImg);
        final TextView pwStoreEditTvStoreName = contentView.findViewById(R.id.pw_storEdit_et_storeName);
        Button pwStoreEditBtnCommit = contentView.findViewById(R.id.pw_storEdit_btn_commit);
        pwStoreEditMCVStoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
        pwStoreEditBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(store_img.equals("")){
                    showToast("请点击农品街图片上传店铺头像");
                }else if(pwStoreEditTvStoreName.getText().toString().trim().equals("")){
                    showToast("请填写店铺名称");
                }else{
                    store_name=pwStoreEditTvStoreName.getText().toString().trim();
                    SetSubscribe.setStore(user_id, token, store_name, store_img, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            window.dismiss();
                            backgroundAlpha(1f);
                            getMyStore();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
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
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri img = data.getData();
                    String[] fileColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(img, fileColumn, null, null, null);
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(fileColumn[0]);
                    String path = cursor.getString(index);
                    Bitmap bitmap=BitmapFactory.decodeFile(path);
                    pwStoreEditMCVStoreImg.setImageBitmap(bitmap);
                    HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                            store_img = bean.getData().getSrc();
                        }
                    });
                }
                break;
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    //添加店铺弹窗
    public void showPwUnCancel() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_edit, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_360));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        pwStoreEditMCVStoreImg = contentView.findViewById(R.id.pw_storEdit_mcv_storeImg);
        final TextView pwStoreEditTvStoreName = contentView.findViewById(R.id.pw_storEdit_et_storeName);
        Button pwStoreEditBtnCommit = contentView.findViewById(R.id.pw_storEdit_btn_commit);
        pwStoreEditMCVStoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
        pwStoreEditBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store_name = pwStoreEditTvStoreName.getText().toString().trim();
                SetSubscribe.setStore(user_id, token, store_name, store_img, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        window.dismiss();
                        backgroundAlpha(1f);
                        getMyStore();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        //showToast(errorMsg);
                    }
                }));
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
                finish();
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }
}
