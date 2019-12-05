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
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.been.ZiZhi2Bean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
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

public class ZiZhiInfoActivity extends ActivityBase {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_zizhi_et_name)
    EditText atZizhiEtName;
    /*@BindView(R.id.at_zizhi_et_type)
    EditText atZizhiTvType;*/
    @BindView(R.id.at_zizhi_ll_zizhipic)
    LinearLayout atZizhiLlZizhipic;
    @BindView(R.id.at_zizhi_btn_confirm)
    Button atZizhiBtnConfirm;
    @BindView(R.id.at_zizhi_tv_realName)
    TextView atZizhiTvRealName;
    @BindView(R.id.at_zizhi_tv_mobile)
    TextView atZizhiTvMobile;
    @BindView(R.id.at_zizhi_tv_type)
    TextView atZizhiTvType;
    @BindView(R.id.at_zizhi_ll_zizhipic1)
    LinearLayout atZizhiLlZizhipic1;
    @BindView(R.id.at_zizhi_ll_zizhipic2)
    LinearLayout atZizhiLlZizhipic2;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private String cert_id = "";
    private String path = "";

    private int index = 0;

    private String business_license = "";//营业执照
    private String authorization="";//资质证书
    private String  other_img="";//其他资料

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhi2);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "资质认证");

        String real_name = getIntent().getStringExtra(Const.PERSON_CERT_NAME);
        String mobile = getIntent().getStringExtra(Const.PERSON_CERT_PHONE);
        atZizhiTvRealName.setText(real_name);
        atZizhiTvMobile.setText(mobile);


        if (getIntent().getStringExtra("key0") != null) {
            cert_id = getIntent().getStringExtra("key0");
        }
    }

    @OnClick({R.id.at_zizhi_ll_zizhipic, R.id.at_zizhi_btn_confirm, R.id.at_zizhi_tv_type,
            R.id.at_zizhi_ll_zizhipic1,R.id.at_zizhi_ll_zizhipic2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_zizhi_ll_zizhipic:
                showBottomDialog(atZizhiLlZizhipic);
                break;
            case R.id.at_zizhi_ll_zizhipic1:
                showBottomDialog(atZizhiLlZizhipic1);
                break;
            case R.id.at_zizhi_ll_zizhipic2:
                showBottomDialog(atZizhiLlZizhipic2);
                break;
            case R.id.at_zizhi_btn_confirm:
                if (atZizhiEtName.getText().toString().trim().equals("")) {
                    showToast("请填写企业全称");
                } else if (atZizhiTvType.getText().toString().trim().equals("")) {
                    showToast("请选择许可资质类型");
                } else if (business_license == null || business_license.equals("")) {
                    showToast("请上传营业执照");
                }else if (authorization == null || authorization.equals("")) {
                    showToast("请上传资质证书");
                } else {
                    uploadData();
                    /*HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UpLoadPicResponseBean bean = GsonUtils.fromJson(response.body().string(), UpLoadPicResponseBean.class);
                            UpLoadPicResponseBean.DataBean data = bean.getData();
                            business_license = data.getSrc();
                            Log.e("测试", "onResponse: " + business_license);
                            uploadData();
                        }
                    });*/
                }
                break;
            case R.id.at_zizhi_tv_type:
                showBottomDialog1();
                break;
        }
    }

    private void uploadData() {
        atZizhiBtnConfirm.setClickable(false);
        ZiZhi2Bean bean = new ZiZhi2Bean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCompany_name(atZizhiEtName.getText().toString());
        bean.setCompany_type(atZizhiTvType.getText().toString());
        bean.setBusiness_license(business_license);
        bean.setAuthorization(authorization);
        bean.setOther_img(other_img);
        if (!cert_id.equals("")) {
            bean.setCert_id(cert_id);
            CertSubscribe.zizhi3(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atZizhiBtnConfirm.setClickable(true);
                    openActivity(ConfirmAttestationSuccessActivity.class);
                }

                @Override
                public void onFault(String errorMsg) {
                    atZizhiBtnConfirm.setClickable(true);
                    //showToast(errorMsg);
                }
            }));
        } else {
            CertSubscribe.zizhi2(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    atZizhiBtnConfirm.setClickable(true);
                    openActivity(ConfirmAttestationSuccessActivity.class);
                }

                @Override
                public void onFault(String errorMsg) {
                    atZizhiBtnConfirm.setClickable(true);
                    showToast(errorMsg);
                }
            }));
        }
    }

    private void showBottomDialog1() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_zizhi_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_zizhi_tv_pesticide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atZizhiTvType.setText("农药经营许可证");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_zizhi_tv_seed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atZizhiTvType.setText("种子经营许可证");
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_zizhi_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /*private void showBottomDialog() {
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
        //从相册中选取
        dialog.findViewById(R.id.dl_photo_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                dialog.dismiss();
            }
        });
        //拍摄照片
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
                    imageUri = FileProvider.getUriForFile(ZiZhiInfoActivity.this,
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
        dialog.findViewById(R.id.dl_photo_cancel)
                .setOnClickListener(new View.OnClickListener() {
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
                        atZizhiLlZizhipic.setBackground(new BitmapDrawable(bitmap));
                        path = getExternalCacheDir() + "/output_image.jpg";

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
                        path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        atZizhiLlZizhipic.setBackground(new BitmapDrawable(bitmap));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }*/
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
                if (v.getId() == R.id.at_zizhi_ll_zizhipic) {
                    index = 0;
                } else if (v.getId() == R.id.at_zizhi_ll_zizhipic1) {
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
                if (v.getId() == R.id.at_zizhi_ll_zizhipic) {
                    index = 0;
                } else if (v.getId() == R.id.at_zizhi_ll_zizhipic1) {
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
                    imageUri = FileProvider.getUriForFile(ZiZhiInfoActivity.this,
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
                        if (index == 0) {
                            atZizhiLlZizhipic.setBackground(new BitmapDrawable(bitmap));
                        } else if (index == 1) {
                            atZizhiLlZizhipic1.setBackground(new BitmapDrawable(bitmap));
                        } else {
                            atZizhiLlZizhipic2.setBackground(new BitmapDrawable(bitmap));
                        }
                        String filePath = getExternalCacheDir() + "/output_image.jpg";
                        if (index == 0) {
                            showToast("图片上传中，请稍等...");
                        } else if (index == 1) {
                            showToast("图片上传中，请稍等...");
                        } else {
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
                                if (index == 0) {
                                    business_license = data.getSrc();
                                } else if (index == 1) {
                                    authorization = data.getSrc();
                                } else {
                                    other_img = data.getSrc();
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
                        if (index == 0) {
                            showToast("图片上传中，请稍等...");
                            atZizhiLlZizhipic.setBackground(new BitmapDrawable(bitmap));
                        } else if (index == 1) {
                            showToast("图片上传中，请稍等...");
                            atZizhiLlZizhipic1.setBackground(new BitmapDrawable(bitmap));
                        } else {
                            showToast("图片上传中，请稍等...");
                            atZizhiLlZizhipic2.setBackground(new BitmapDrawable(bitmap));
                        }
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                if (index == 0) {
                                    business_license = data.getSrc();
                                } else if (index == 1) {
                                    authorization = data.getSrc();
                                } else {
                                    other_img = data.getSrc();
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
