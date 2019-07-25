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
import com.zthx.npj.net.been.UploadCaigouBean;
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

public class PurchaserCertification2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_purchaser_certification2_et_mobile)
    EditText atPurchaserCertification2EtMobile;
    @BindView(R.id.at_purchaser_certification2_et_id)
    TextView atPurchaserCertification2EtId;
    @BindView(R.id.at_purchaser_certification2_et_company_name)
    EditText atPurchaserCertification2EtCompanyName;
    @BindView(R.id.at_purchaser_certification2_et_address)
    EditText atPurchaserCertification2EtAddress;
    @BindView(R.id.at_purchaser_certification2_ll_name)
    LinearLayout atPurchaserCertification2LlName;
    @BindView(R.id.at_purchaser_certification2_et_dangkou_name)
    EditText atPurchaserCertification2EtDangkouName;
    @BindView(R.id.at_purchaser_certification2_et_shichang_name)
    EditText atPurchaserCertification2EtShichangName;
    @BindView(R.id.at_purchaser_certification2_et_dangkou_address)
    EditText atPurchaserCertification2EtDangkouAddress;
    @BindView(R.id.at_purchaser_certification2_ll_dangkou)
    LinearLayout atPurchaserCertification2LlDangkou;
    @BindView(R.id.at_purchaser_certification2_rl_pic)
    RelativeLayout atPurchaserCertification2RlPic;
    @BindView(R.id.at_purchaser_certification2_tv_dangkou_pic)
    TextView atPurchaserCertification2TvDangkouPic;
    @BindView(R.id.at_purchaser_certification2_tv_dangkou_id_card)
    TextView atPurchaserCertification2TvDangkouIdCard;
    @BindView(R.id.at_purchaser_certification2_ll_dangkou_pic)
    LinearLayout atPurchaserCertification2LlDangkouPic;
    @BindView(R.id.at_purchaser_certification2_rl_fei_dangkou_pic)
    RelativeLayout atPurchaserCertification2RlFeiDangkouPic;
    @BindView(R.id.at_purchaser_certification2_btn_confirm)
    Button atPurchaserCertification2BtnConfirm;
    @BindView(R.id.at_purchaser_certification2_tv_tuijian)
    TextView atPurchaserCertification2TvTuijian;
    @BindView(R.id.at_purchaser_certification2_tv_dianjishangchuan)
    TextView atPurchaserCertification2TvDianjishangchuan;
    @BindView(R.id.at_purchaser_certification2_et_name)
    EditText atPurchaserCertification2EtName;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private int type;
    private int picType;
    private int ALBUM_RESULT_CODE = 10;
    private File avatarFile;
    private Uri avatarUri;
    private int CAMERA_RESULT_CODE = 11;
    private int CROP_RESULT_CODE = 12;
    private Uri uritempFile;

    private String url_bussiness_license;
    private String url_dangkou_img;
    private String url_id_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_certification2);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"采购商认证");

    }

    @OnClick({R.id.at_purchaser_certification2_et_id, R.id.at_purchaser_certification2_btn_confirm, R.id.at_purchaser_certification2_rl_pic, R.id.at_purchaser_certification2_tv_dangkou_pic, R.id.at_purchaser_certification2_tv_dangkou_id_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_purchaser_certification2_rl_pic:
                picType = 0;
                showPicDialog();
                break;
            case R.id.at_purchaser_certification2_tv_dangkou_pic:
                picType = 1;
                atPurchaserCertification2TvTuijian.setText("上传档口照片进行认证");
                atPurchaserCertification2TvDianjishangchuan.setText("1.需要您与档口的合照\n" +
                        "2.确保图片中能清晰看到档口名称和档口正门\n");
                break;
            case R.id.at_purchaser_certification2_tv_dangkou_id_card:
                picType = 2;
                atPurchaserCertification2TvTuijian.setText("上传名片或工牌进行认证");
                atPurchaserCertification2TvDianjishangchuan.setText(
                        "1.图片中需要展示名片/工牌的全部信息，名片包含但不限于联系人、联系方式、企业/档口名称、地址等信息。工牌需包含但不限于联系人、企业名称、部门/职位等信息。\n" +
                                "2.图片上传完毕，请确保内容清晰，无阅读障碍，以保顺利通过。\n");
                break;
            case R.id.at_purchaser_certification2_btn_confirm:
                uploadData();
                break;
            case R.id.at_purchaser_certification2_et_id:
                showBottomDialog();
                break;
        }
    }

    private void uploadData() {
        UploadCaigouBean bean = new UploadCaigouBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(BaseConstant.TOKEN);
        bean.setName(atPurchaserCertification2EtName.getText().toString().trim());
        if (type == 1) {
            bean.setStall_name(atPurchaserCertification2EtDangkouName.getText().toString().trim());
            bean.setLocation(atPurchaserCertification2EtShichangName.getText().toString().trim());
            bean.setAddress(atPurchaserCertification2EtDangkouAddress.getText().toString().trim());

        } else {
            bean.setCompany_name(atPurchaserCertification2EtCompanyName.getText().toString().trim());
            bean.setLocation(atPurchaserCertification2EtAddress.getText().toString().trim());
        }
        if (picType == 0) {
            bean.setBusiness_license(url_bussiness_license);
        } else if (picType == 1) {
            bean.setStall_image(url_dangkou_img);
        } else {
            bean.setBusiness_card(url_id_card);
        }

        CertSubscribe.upLoadCaigouCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                startActivity(new Intent(PurchaserCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_dangkou_shenfen_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_dangkou_laoban).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("档口老板");
                type = 1;
                atPurchaserCertification2LlDangkou.setVisibility(View.VISIBLE);
                atPurchaserCertification2LlName.setVisibility(View.GONE);
                atPurchaserCertification2LlDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_chaoshicaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("超市采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_chukoucaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("出口采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_canyinqiye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("餐饮企业");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_jiagongcahngcaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("加工厂采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_qita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("其他");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void showPicDialog() {
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

                openSysAlbum();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
     * ---------------------相机相册调用----------------------
     */

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
        if (PermissionChecker.checkSelfPermission(PurchaserCertification2Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {//如果已经授予相机相关权限
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
                        if (type == 1) {
                            atPurchaserCertification2RlPic.setBackground(new BitmapDrawable(bitmap));

                        } else {
                            atPurchaserCertification2RlFeiDangkouPic.setBackground(new BitmapDrawable(bitmap));
                        }
                        getPicUrl(bitmap);
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

    private void getPicUrl(Bitmap bitmap) {
        File file = bitmapToFile(bitmap);

        SetSubscribe.upLoadFile(file, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                UpLoadPicResponseBean bean = GsonUtils.fromJson(result, UpLoadPicResponseBean.class);
                UpLoadPicResponseBean.DataBean data = bean.getData();
                if (picType == 0) {
                    url_bussiness_license = data.getSrc();
                } else if (picType == 1) {
                    url_dangkou_img = data.getSrc();
                } else {
                    url_id_card = data.getSrc();
                }
            }

            @Override
            public void onFault(String errorMsg) {

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
