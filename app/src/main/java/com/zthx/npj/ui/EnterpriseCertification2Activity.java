package com.zthx.npj.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadCompanyBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
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

public class EnterpriseCertification2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_enterprise_certification2_et_name)
    EditText atEnterpriseCertification2EtName;
    @BindView(R.id.at_enterprise_certification2_et_des)
    EditText atEnterpriseCertification2EtDes;
    @BindView(R.id.at_enterprise_certification2_et_type)
    EditText atEnterpriseCertification2EtType;
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

    private int index;
    private int ALBUM_RESULT_CODE = 10;
    private File avatarFile;
    private Uri avatarUri;
    private int CAMERA_RESULT_CODE = 11;
    private int CROP_RESULT_CODE = 12;
    private Uri uritempFile;
    private String urlShouQuan;
    private String urlPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification2);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"企业认证");
    }

    @OnClick({R.id.at_enterprise_certification2_ll_company_pic, R.id.at_enterprise_certification2_ll_shouquan, R.id.at_enterprise_certification2_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_enterprise_certification2_ll_company_pic:
                showBottomDialog(atEnterpriseCertification2LlCompanyPic);
                break;
            case R.id.at_enterprise_certification2_ll_shouquan:
                showBottomDialog(atEnterpriseCertification2LlShouquan);
                break;
            case R.id.at_enterprise_certification2_btn_confirm:
                uploadData();
                break;
        }
    }

    private void uploadData() {

        UploadCompanyBean bean = new UploadCompanyBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(BaseConstant.TOKEN);
        bean.setCompany_name(atEnterpriseCertification2EtName.getText().toString().trim());
        bean.setCompany_type(atEnterpriseCertification2EtType.getText().toString().trim());
        bean.setCompany_desc(atEnterpriseCertification2EtDes.getText().toString().trim());
        bean.setBussiness_license(urlPic);
        bean.setAuthorization(urlShouQuan);
        CertSubscribe.upLoadCompanyCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                startActivity(new Intent(EnterpriseCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }, this));
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
                if (v.getId() == R.id.at_enterprise_certification2_ll_company_pic) {
                    index = 0;
                } else {
                    index = 1;
                }
                openSysAlbum();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v.getId() == R.id.at_enterprise_certification2_ll_company_pic) {
                    index = 0;
                } else {
                    index = 1;
                }
                onCameraSelected();
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

    /**
     * 打开系统相册
     */
    private void openSysAlbum() {
//        Intent albumIntent = new Intent(Intent.ACTION_PICK);
//        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        startActivityForResult(albumIntent, ALBUM_RESULT_CODE);
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, ALBUM_RESULT_CODE);// //适用于4.4及以上android版本

    }

    public void onCameraSelected() {
        if (PermissionChecker.checkSelfPermission(EnterpriseCertification2Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {//如果已经授予相机相关权限
            openCamera();
        } else {//如果相机权限并未被授予, 主动向用户请求该权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//Android 6.0+时, 动态申请权限
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS}, 1);
            } else {
//                IntentUtil.openAppPermissionPage(this);
            }
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            avatarFile = createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (avatarFile != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                avatarUri = Uri.fromFile(avatarFile);
            } else {
                avatarUri = FileProvider.getUriForFile(this, getPackageName() + ".file_provider", avatarFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri);
            startActivityForResult(cameraIntent, CAMERA_RESULT_CODE);
        }
//        Intent openCameraIntent = new Intent(
//                MediaStore.ACTION_IMAGE_CAPTURE);
        avatarUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "image.jpg"));
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri);
        startActivityForResult(cameraIntent, CAMERA_RESULT_CODE);
    }

    private File createOriImageFile() throws IOException {
        String imgNameOri = "HomePic_" + new SimpleDateFormat(
                "yyyyMMdd_HHmmss").format(new Date());
        File pictureDirOri = new File(getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();
        }
        File image = File.createTempFile(
                imgNameOri,         /* prefix */
                ".jpg",             /* suffix */
                pictureDirOri       /* directory */
        );
        avatarUri = Uri.parse(image.getAbsolutePath());
        return image;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_RESULT_CODE) {
//            cropPic(getImageContentUri(avatarFile));
            startPhotoZoom(avatarUri); // 开始对图片进行裁剪处理
        } else if (requestCode == CROP_RESULT_CODE) {
            // 裁剪时,这样设置 cropIntent.putExtra("return-data", true); 处理方案如下
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
//                    Bitmap bitmap = bundle.getParcelable("data");
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                        if (index == 0) {
                            atEnterpriseCertification2LlCompanyPic.setBackground(new BitmapDrawable(bitmap));
                            getPicUrl(atEnterpriseCertification2LlCompanyPic, bitmap);
                        } else {
                            atEnterpriseCertification2LlShouquan.setBackground(new BitmapDrawable(bitmap));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }

            // 裁剪时,这样设置 cropIntent.putExtra("return-data", false); 处理方案如下
//                try {
//                    ivHead.setImageBitmap(BitmapFactory.decodeStream(
// getActivity().getContentResolver().openInputStream(imageUri)));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
        } else if (requestCode == ALBUM_RESULT_CODE) {
            if (data != null && data.getData() != null) {
                startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        avatarUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP_SMALL_PICTURE);
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //intent.putExtra("return-data", true);

        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CROP_RESULT_CODE);
    }

    private void getPicUrl(final LinearLayout view, Bitmap bitmap) {
        File file = bitmapToFile(bitmap);

        SetSubscribe.upLoadFile(file, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                UpLoadPicResponseBean bean = GsonUtils.fromJson(result, UpLoadPicResponseBean.class);
                UpLoadPicResponseBean.DataBean data = bean.getData();
                if (view == atEnterpriseCertification2LlCompanyPic) {
                    urlPic = data.getSrc();
                } else {
                    urlShouQuan = data.getSrc();
                }
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }, this));
    }

    private File bitmapToFile(Bitmap bitmap) {
        File file = new File(Environment.getDataDirectory() + "/1234");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
