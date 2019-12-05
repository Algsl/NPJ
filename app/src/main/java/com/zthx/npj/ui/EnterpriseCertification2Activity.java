package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadCompanyBean;
import com.zthx.npj.net.been.UserBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EnterpriseCertification2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_enterprise_certification2_et_name)
    EditText atEnterpriseCertification2EtName;
    @BindView(R.id.at_enterprise_certification2_et_des)
    EditText atEnterpriseCertification2EtDes;
    @BindView(R.id.at_enterprise_certification2_et_type)
    TextView atEnterpriseCertification2EtType;
    @BindView(R.id.at_enterprise_certification2_ll_company_pic)
    LinearLayout atEnterpriseCertification2LlCompanyPic;
    @BindView(R.id.at_enterprise_certification2_ll_shouquan)
    LinearLayout atEnterpriseCertification2LlShouquan;
    @BindView(R.id.at_enterprise_certification2_tv_personname)
    TextView atEnterpriseCertification2TvPersonname;
    @BindView(R.id.at_enterprise_certification2_tv_phone)
    ImageView atEnterpriseCertification2TvPhone;
    @BindView(R.id.at_enterprise_certification2_btn_confirm)
    Button atEnterpriseCertification2BtnConfirm;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.at_enterprise_certification2_tv_personMobile)
    TextView atEnterpriseCertification2TvPersonMobile;

    private String path1="";
    private String path2;
    private String img1;
    private String img2;
    private Uri imageUri;
    private int index;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private String cert_id = "";

    private String name="";
    private String mobile="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification2);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle, "企业认证");

        name=getIntent().getStringExtra(Const.PERSON_CERT_NAME);
        mobile=getIntent().getStringExtra(Const.PERSON_CERT_PHONE);

        atEnterpriseCertification2TvPersonname.setText(name);
        atEnterpriseCertification2TvPersonMobile.setText(mobile);


        if (getIntent().getStringExtra("key0") != null) {
            cert_id = getIntent().getStringExtra("key0");
        }

    }

    @OnClick({R.id.at_enterprise_certification2_ll_company_pic, R.id.at_enterprise_certification2_ll_shouquan,
            R.id.at_enterprise_certification2_btn_confirm, R.id.at_enterprise_certification2_et_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_enterprise_certification2_ll_company_pic:
                showBottomDialog(atEnterpriseCertification2LlCompanyPic);
                break;
            case R.id.at_enterprise_certification2_ll_shouquan:
                showBottomDialog(atEnterpriseCertification2LlShouquan);
                break;
            case R.id.at_enterprise_certification2_btn_confirm:
                if(atEnterpriseCertification2EtName.getText().toString().trim().equals("")){
                    showToast("请填写企业全称");
                }else if(atEnterpriseCertification2EtDes.getText().toString().trim().equals("")){
                    showToast("请填写企业简介");
                }else if(atEnterpriseCertification2EtType.getText().toString().trim().equals("请选择企业类型")){
                    showToast("请选择企业类型");
                }else if(path1==null || path1.equals("")){
                    showToast("请上传营业执照");
                }else{
                    if(path1!=null){
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path1, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                atEnterpriseCertification2BtnConfirm.setClickable(true);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                                UpLoadPicResponseBean.DataBean data = bean.getData();
                                img1 = data.getSrc();
                                uploadData();
                            }
                        });
                    }else if(path2!=null){
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path2, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                atEnterpriseCertification2BtnConfirm.setClickable(true);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                                UpLoadPicResponseBean.DataBean data = bean.getData();
                                img2 = data.getSrc();
                                uploadData();
                            }
                        });
                    }else{
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path1, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                                UpLoadPicResponseBean.DataBean data = bean.getData();
                                img1 = data.getSrc();
                                HttpUtils.uploadImg(URLConstant.REQUEST_URL, path2, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                                        UpLoadPicResponseBean.DataBean data = bean.getData();
                                        img2 = data.getSrc();
                                        atEnterpriseCertification2BtnConfirm.setClickable(false);
                                        uploadData();
                                    }
                                });
                            }
                        });
                    }

                }
                break;
            case R.id.at_enterprise_certification2_et_type:
                showBottomDialog();
                break;
        }
    }

    private void uploadData() {
        atEnterpriseCertification2BtnConfirm.setClickable(false);
        UploadCompanyBean bean = new UploadCompanyBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setCompany_name(atEnterpriseCertification2EtName.getText().toString().trim());
        bean.setCompany_desc(atEnterpriseCertification2EtDes.getText().toString().trim());
        bean.setCompany_type(atEnterpriseCertification2EtType.getText().toString());
        bean.setBusiness_license(img1);
        bean.setAuthorization(img2);
        if (!cert_id.equals("")) {
            bean.setCert_id(cert_id);
            CertSubscribe.upLoadCompanyCert4(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atEnterpriseCertification2BtnConfirm.setClickable(true);
                    startActivity(new Intent(EnterpriseCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
                }

                @Override
                public void onFault(String errorMsg) {
                    atEnterpriseCertification2BtnConfirm.setClickable(true);
                }
            }));
        } else {
            CertSubscribe.upLoadCompanyCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atEnterpriseCertification2BtnConfirm.setClickable(true);
                    startActivity(new Intent(EnterpriseCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
                }

                @Override
                public void onFault(String errorMsg) {
                    atEnterpriseCertification2BtnConfirm.setClickable(true);
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
        dialog.findViewById(R.id.dl_photo_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                if (v.getId() == R.id.at_enterprise_certification2_ll_company_pic) {
                    index = 0;
                } else {
                    index = 1;
                }

                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    imageUri = FileProvider.getUriForFile(EnterpriseCertification2Activity.this,
                            "com.zthx.npj.file_provider",
                            outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                if (v.getId() == R.id.at_enterprise_certification2_ll_company_pic) {
                    index = 0;
                } else {
                    index = 1;
                }

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

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_company_type, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_companyType_chaoshicaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atEnterpriseCertification2EtType.setText("公司");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_companyType_jiagongcahngcaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atEnterpriseCertification2EtType.setText("合作社");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_companyType_chukoucaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atEnterpriseCertification2EtType.setText("家庭农场");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_companyType_canyinqiye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atEnterpriseCertification2EtType.setText("个体工商户");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_companyType_cancel).setOnClickListener(new View.OnClickListener() {
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
                        if (index == 0) {
                            atEnterpriseCertification2LlCompanyPic.setBackground(new BitmapDrawable(bitmap));
                            path1 = getExternalCacheDir() + "/output_image.jpg";
                        } else {
                            atEnterpriseCertification2LlShouquan.setBackground(new BitmapDrawable(bitmap));
                            path2 = getExternalCacheDir() + "/output_image.jpg";
                        }
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
                        if (index == 0) {
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            path1 = cursor.getString(columnIndex);  //获取照片路径
                            cursor.close();
                            Bitmap bitmap = BitmapFactory.decodeFile(path1);
                            atEnterpriseCertification2LlCompanyPic.setBackground(new BitmapDrawable(bitmap));
                        } else {
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            path2 = cursor.getString(columnIndex);  //获取照片路径
                            cursor.close();
                            Bitmap bitmap = BitmapFactory.decodeFile(path2);
                            atEnterpriseCertification2LlShouquan.setBackground(new BitmapDrawable(bitmap));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
