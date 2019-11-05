package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.utils.ImageCircleConner;
import com.zthx.npj.utils.QRCodeUtil;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreManagerQRCodeActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_storeManager_et_receiveName)
    EditText acStoreManagerEtReceiveName;
    @BindView(R.id.ac_storeManager_iv_min)
    ImageView acStoreManagerIvMin;
    @BindView(R.id.ac_storeManager_pb)
    ProgressBar acStoreManagerPb;
    @BindView(R.id.ac_storeManager_iv_add)
    ImageView acStoreManagerIvAdd;
    @BindView(R.id.ac_storeManager_tv_hint)
    TextView acStoreManagerTvHint;
    @BindView(R.id.ac_storeManager_save)
    Button acStoreManagerSave;
    @BindView(R.id.ac_storeManager_iv_QRCode)
    ImageView acStoreManagerIvQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager_qrcode);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的收款码");
        changeRightText(titleThemeTvRight, "账单", StoreManagerBillActivity.class, null);

        //String img=getIntent().getStringExtra("img");
        if(getIntent()!=null){
            final String store_id=getIntent().getStringExtra("store_id");
            String offer=getIntent().getStringExtra("offer");
            acStoreManagerPb.setProgress((int)Double.parseDouble(offer));
            acStoreManagerTvHint.setText("优惠比率" + offer + "%，用户支付时葫芦币抵扣" + offer + "%消费金额");

            Glide.with(this).load(Uri.parse(SharePerferenceUtils.getHeadPic(this))).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    acStoreManagerIvQRCode.setImageBitmap(QRCodeUtil.createQRCodeBitmap("http://game.npj-vip.com/h5/jumpApp.html?page=payStore&id="+store_id,(int)getResources().getDimension(R.dimen.dp_220),
                            ImageCircleConner.toRoundCorner(resource,220),0.3f));
                }
            });
        }else{
            Glide.with(this).load(Uri.parse(SharePerferenceUtils.getHeadPic(this))).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    acStoreManagerIvQRCode.setImageBitmap(QRCodeUtil.createQRCodeBitmap("http://game.npj-vip.com/h5/jumpApp.html?page=payStore&id="+null,(int)getResources().getDimension(R.dimen.dp_220),
                            ImageCircleConner.toRoundCorner(resource,220),0.3f));
                }
            });
        }
    }

    @OnClick({R.id.ac_storeManager_iv_min, R.id.ac_storeManager_iv_add, R.id.ac_storeManager_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_storeManager_iv_min:
                if (acStoreManagerPb.getProgress() - 10 >= 0) {
                    acStoreManagerPb.setProgress(acStoreManagerPb.getProgress() - 10);
                    acStoreManagerTvHint.setText("优惠比率" + acStoreManagerPb.getProgress() + "%，用户支付时葫芦币抵扣" + acStoreManagerPb.getProgress() + "%消费金额");
                } else {
                    Toast.makeText(StoreManagerQRCodeActivity.this, "不能再减了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ac_storeManager_iv_add:
                if (acStoreManagerPb.getProgress() + 10 <= 100) {
                    acStoreManagerPb.setProgress(acStoreManagerPb.getProgress() + 10);
                    acStoreManagerTvHint.setText("优惠比率" + acStoreManagerPb.getProgress() + "%，用户支付时葫芦币抵扣" + acStoreManagerPb.getProgress() + "%消费金额");
                } else {
                    Toast.makeText(StoreManagerQRCodeActivity.this, "再加就赔了！！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ac_storeManager_save:
                Intent intent = getIntent();
                intent.putExtra("offer", acStoreManagerPb.getProgress()+"");
                setResult(1, intent);
                finish();
                break;
        }
    }
}
