package com.zthx.npj.ui;

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
import android.view.WindowManager;
import android.widget.Button;
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
import com.zthx.npj.net.been.UserResponseBean;
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

public class MySupplyActivity extends ActivityBase {

    @BindView(R.id.at_my_supply_ll_my_bill)
    LinearLayout atMySupplyLlMyBill;
    @BindView(R.id.at_my_supply_ll_supply_manager)
    LinearLayout atMySupplyLlSupplyManager;
    @BindView(R.id.at_my_supply_ll_want_buy_manager)
    LinearLayout atMySupplyLlWantBuyManager;
    @BindView(R.id.at_my_supply_ll_publish_supply)
    LinearLayout atMySupplyLlPublishSupply;
    @BindView(R.id.ac_mySupply_mcv_headImg)
    ImageView acMySupplyMcvHeadImg;
    @BindView(R.id.ac_mySupply_tv_nickName)
    TextView acMySupplyTvNickName;
    @BindView(R.id.ac_mySupply_tv_reputation)
    TextView acMySupplyTvReputation;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    private static final int CHOOSE_PHOTO = 1;
    private String store_img;
    private String store_name="";
    private MyCircleView pwStoreEditMCVStoreImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_supply);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"我的供求");
        if(!SharePerferenceUtils.getHeadPic(MySupplyActivity.this).equals("")){
            Glide.with(MySupplyActivity.this).load(Uri.parse(SharePerferenceUtils.getHeadPic(MySupplyActivity.this))).into(acMySupplyMcvHeadImg);
            acMySupplyTvNickName.setText(SharePerferenceUtils.getNickName(MySupplyActivity.this));
            acMySupplyTvReputation.setText("信誉分："+SharePerferenceUtils.getReputation(MySupplyActivity.this));
        }
    }

    @OnClick({R.id.at_my_supply_ll_my_bill, R.id.at_my_supply_ll_supply_manager, R.id.at_my_supply_ll_want_buy_manager, R.id.at_my_supply_ll_publish_supply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_supply_ll_my_bill:
                startActivity(new Intent(this, MyBillActivity.class));
                break;
            case R.id.at_my_supply_ll_supply_manager:
                startActivity(new Intent(this, SupplyManagerActivity.class));
                break;
            case R.id.at_my_supply_ll_want_buy_manager:
                startActivity(new Intent(this, WantBuyManagerActivity.class));
                break;
            case R.id.at_my_supply_ll_publish_supply:
                startActivity(new Intent(this, ReleaseSupplyActivity.class));
                break;
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
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
}
