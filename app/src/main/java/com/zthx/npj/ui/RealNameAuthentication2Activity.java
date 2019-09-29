package com.zthx.npj.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UpLoadMyCertBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RealNameAuthentication2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_real_name_authentication2_btn_confirm)
    Button atRealNameAuthentication2BtnConfirm;
    @BindView(R.id.at_real_name_authentication2_et_name)
    EditText atRealNameAuthentication2EtName;
    @BindView(R.id.at_real_name_authentication2_et_id)
    EditText atRealNameAuthentication2EtId;
    @BindView(R.id.at_real_name_authentication2_ll_id_zheng)
    LinearLayout atRealNameAuthentication2LlIdZheng;
    @BindView(R.id.at_real_name_authentication2_ll_id_fan)
    LinearLayout atRealNameAuthentication2LlIdFan;
    @BindView(R.id.at_real_name_authentication2_ll_id_quan)
    LinearLayout atRealNameAuthentication2LlIdQuan;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;

    private int index = 0;

    private String UrlZheng;
    private String UrlFan;
    private String UrlQuan;
    private String cert_id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication2);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"实人认证");

        if(getIntent().getStringExtra("key0")!=null){
            cert_id=getIntent().getStringExtra("key0");
        }
    }

    @OnClick({R.id.at_real_name_authentication2_ll_id_zheng, R.id.at_real_name_authentication2_ll_id_fan, R.id.at_real_name_authentication2_ll_id_quan,R.id.at_real_name_authentication2_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_real_name_authentication2_ll_id_zheng:
                showBottomDialog(atRealNameAuthentication2LlIdZheng);
                break;
            case R.id.at_real_name_authentication2_ll_id_fan:
                showBottomDialog(atRealNameAuthentication2LlIdFan);
                break;
            case R.id.at_real_name_authentication2_ll_id_quan:
                showBottomDialog(atRealNameAuthentication2LlIdQuan);
                break;
            case R.id.at_real_name_authentication2_btn_confirm:
                if(atRealNameAuthentication2EtName.getText().toString().trim().equals("")){
                    showToast("请填写姓名");
                }else if(atRealNameAuthentication2EtId.getText().toString().trim().length()!=18){
                    showToast("请正确填写身份证号");
                }else if(UrlZheng==null || UrlZheng.equals("") ){
                    showToast("请上传身份证正面照");
                }else if(UrlFan==null || UrlFan.equals("")){
                    showToast("请上传身份证反面照");
                }else if(UrlQuan==null || UrlQuan.equals("")){
                    showToast("请上传手持身份证照");
                }else{
                    atRealNameAuthentication2BtnConfirm.setClickable(false);
                    upLoadInformation();
                }
                break;
        }
    }

    private void upLoadInformation() {
        UpLoadMyCertBean bean = new UpLoadMyCertBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(BaseConstant.TOKEN);
        bean.setName(atRealNameAuthentication2EtName.getText().toString().trim());
        bean.setIdentity_number(atRealNameAuthentication2EtId.getText().toString().trim());
        bean.setCard_face(UrlZheng);
        bean.setCard_back(UrlFan);
        bean.setCard_hand(UrlQuan);

        if(!cert_id.equals("")){
            bean.setCert_id(cert_id);
            CertSubscribe.upLoadMyCert3(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atRealNameAuthentication2BtnConfirm.setClickable(true);
                    SharePerferenceUtils.setUserName(RealNameAuthentication2Activity.this,atRealNameAuthentication2EtName.getText().toString().trim());
                    startActivity(new Intent(RealNameAuthentication2Activity.this, ConfirmAttestationSuccessActivity.class));
                }
                @Override
                public void onFault(String errorMsg) {
                    atRealNameAuthentication2BtnConfirm.setClickable(true);
                    showToast(errorMsg);
                }
            }));
        }else{
            CertSubscribe.upLoadMyCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atRealNameAuthentication2BtnConfirm.setClickable(true);
                    SharePerferenceUtils.setUserName(RealNameAuthentication2Activity.this,atRealNameAuthentication2EtName.getText().toString().trim());
                    startActivity(new Intent(RealNameAuthentication2Activity.this, ConfirmAttestationSuccessActivity.class));
                }

                @Override
                public void onFault(String errorMsg) {
                    atRealNameAuthentication2BtnConfirm.setClickable(true);
                    showToast(errorMsg);
                }
            }));
        }
    }

    private void showBottomDialog(final View v) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_photo_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //打开相册
        dialog.findViewById(R.id.dl_photo_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v.getId() == R.id.at_real_name_authentication2_ll_id_zheng) {
                    index = 0;
                } else if (v.getId() == R.id.at_real_name_authentication2_ll_id_fan) {
                    index = 1;
                } else {
                    index = 2;
                }
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                dialog.dismiss();
            }
        });

        //打开相机
        dialog.findViewById(R.id.dl_photo_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v.getId() == R.id.at_real_name_authentication2_ll_id_zheng) {
                    index = 0;
                } else if (v.getId() == R.id.at_real_name_authentication2_ll_id_fan) {
                    index = 1;
                } else {
                    index = 2;
                }
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(RealNameAuthentication2Activity.this,
                            "com.zthx.npj.file_provider",
                            outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        if(index==0){
                            atRealNameAuthentication2LlIdZheng.setBackground(new BitmapDrawable(bitmap));
                        }else if(index==1){
                            atRealNameAuthentication2LlIdFan.setBackground(new BitmapDrawable(bitmap));
                        }else{
                            atRealNameAuthentication2LlIdQuan.setBackground(new BitmapDrawable(bitmap));
                        }
                        String filePath = getExternalCacheDir() + "/output_image.jpg";
                        if(index==0){
                            showToast("图片上传中，请稍等...");
                        }else if(index==1){
                            showToast("图片上传中，请稍等...");
                        }else{
                            showToast("图片上传中，请稍等...");
                        }
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, filePath, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                if(index==0){
                                    UrlZheng=data.getSrc();
                                }else if(index==1){
                                    UrlFan=data.getSrc();
                                }else{
                                    UrlQuan=data.getSrc();
                                }
                            }
                        });
                        showToast("图片上传完成");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        final String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        if(index==0){
                            showToast("图片上传中，请稍等...");
                            atRealNameAuthentication2LlIdZheng.setBackground(new BitmapDrawable(bitmap));
                            UrlZheng=path;
                        }else if(index==1){
                            showToast("图片上传中，请稍等...");
                            atRealNameAuthentication2LlIdFan.setBackground(new BitmapDrawable(bitmap));
                        }else{
                            showToast("图片上传中，请稍等...");
                            atRealNameAuthentication2LlIdQuan.setBackground(new BitmapDrawable(bitmap));
                        }
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                if(index==0){
                                    UrlZheng=data.getSrc();
                                }else if(index==1){
                                    UrlFan=data.getSrc();
                                }else{
                                    UrlQuan=data.getSrc();
                                }
                            }
                        });
                        showToast("图片上传完成");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
