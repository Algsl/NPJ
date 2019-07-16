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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.UpLoadFileBean;
import com.zthx.npj.net.been.UpLoadMyCertBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
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

import static com.zthx.npj.utils.NetUtils.context;

public class RealNameAuthentication2Activity extends AppCompatActivity {

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

    private File avatarFile;
    private Uri avatarUri;
    private int CAMERA_RESULT_CODE = 10;
    private int CROP_RESULT_CODE = 11;
    private int ALBUM_RESULT_CODE = 12;

    private int index = 0;

    private String UrlZheng;
    private String UrlFan;
    private String UrlQuan;
    private Uri uritempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_real_name_authentication2_ll_id_zheng, R.id.at_real_name_authentication2_ll_id_fan, R.id.at_real_name_authentication2_ll_id_quan})
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
                upLoadInformation();
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

        CertSubscribe.upLoadMyCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                startActivity(new Intent(RealNameAuthentication2Activity.this, ConfirmAttestationSuccessActivity.class));
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
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
                if (v.getId() == R.id.at_real_name_authentication2_ll_id_zheng) {
                    index = 0;
                } else if (v.getId() == R.id.at_real_name_authentication2_ll_id_fan) {
                    index = 1;
                } else {
                    index = 2;
                }
                openSysAlbum();
                dialog.dismiss();
            }
        });
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

    public void onCameraSelected() {
        if (PermissionChecker.checkSelfPermission(RealNameAuthentication2Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {//如果已经授予相机相关权限
            openCamera();
        } else {//如果相机权限并未被授予, 主动向用户请求该权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//Android 6.0+时, 动态申请权限
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_CONTACTS}, 1);
            } else {
//                IntentUtil.openAppPermissionPage(this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
//                    showPermissionDeniedDialog();
                }
                break;
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
        if (requestCode== CAMERA_RESULT_CODE) {
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
                            atRealNameAuthentication2LlIdZheng.setBackground(new BitmapDrawable(bitmap));
                            getPicUrl(atRealNameAuthentication2LlIdZheng, bitmap);
                        } else if (index == 1) {
                            atRealNameAuthentication2LlIdFan.setBackground(new BitmapDrawable(bitmap));
                        } else {
                            atRealNameAuthentication2LlIdQuan.setBackground(new BitmapDrawable(bitmap));
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
                Log.e("测试", "onSuccess: "+result);
                UpLoadPicResponseBean upLoadPicResponseBean = GsonUtils.fromJson(result, UpLoadPicResponseBean.class);
                if (view == atRealNameAuthentication2LlIdZheng) {
                    UrlZheng = upLoadPicResponseBean.getSrc();
                } else if (view == atRealNameAuthentication2LlIdFan) {
                    UrlFan = upLoadPicResponseBean.getSrc();
                } else {
                    UrlQuan = upLoadPicResponseBean.getSrc();
                }
            }

            @Override
            public void onFault(String errorMsg) {
                Log.e("测试", "onSuccess: "+errorMsg);
            }
        },this));
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

    /**
     * 7.0以上获取裁剪 Uri
     *
     * @param imageFile
     * @return
     */
    private Uri getImageContentUri(File imageFile) {

        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
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

    /**
     * 裁剪图片
     *
     * @param data
     */
    private void cropPic(Uri data) {
        if (data == null) {
            return;
        }
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(data, "image/*");

        // 开启裁剪：打开的Intent所显示的View可裁剪
        cropIntent.putExtra("crop", "true");
        // 裁剪宽高比
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        // 裁剪输出大小
        cropIntent.putExtra("outputX", 320);
        cropIntent.putExtra("outputY", 320);
        cropIntent.putExtra("scale", true);
        /**
         * return-data
         * 这个属性决定我们在 onActivityResult 中接收到的是什么数据，
         * 如果设置为true 那么data将会返回一个bitmap
         * 如果设置为false，则会将图片保存到本地并将对应的uri返回，当然这个uri得有我们自己设定。
         * 系统裁剪完成后将会将裁剪完成的图片保存在我们所这设定这个uri地址上。我们只需要在裁剪完成后直接调用该uri来设置图片，就可以了。
         */
        cropIntent.putExtra("return-data", true);
        // 当 return-data 为 false 的时候需要设置这句
//        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // 图片输出格式
//        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 头像识别 会启动系统的拍照时人脸识别
//        cropIntent.putExtra("noFaceDetection", true);
        startActivityForResult(cropIntent, CROP_RESULT_CODE);
    }
}
