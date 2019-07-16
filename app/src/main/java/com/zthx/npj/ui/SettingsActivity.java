package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.net.netutils.UploadImg;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingsActivity
        extends AppCompatActivity {

    @BindView(R.id.at_settings_rl_head_pic)
    RelativeLayout atSettingsRlHeadPic;
    @BindView(R.id.at_settings_rl_clean_cash)
    RelativeLayout atSettingsRlCleanCash;
    @BindView(R.id.at_settings_rl_nickname)
    RelativeLayout atSettingsRlNickname;
    @BindView(R.id.at_settings_rl_signature)
    RelativeLayout atSettingsRlSignature;
    @BindView(R.id.ac_settings_tv_nickname)
    TextView acSettingsTvNickname;
    @BindView(R.id.ac_settings_tv_signature)
    TextView acSettingsTvSignature;
    @BindView(R.id.at_settings_rl_address)
    RelativeLayout mAtSettingsRlAddress;
    @BindView(R.id.fg_setting_iv_headimg)
    MyCircleView fgSettingIvHeadimg;


    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private static final MediaType MEDIA_TYPE_PNG=MediaType.parse("image/jpg");
    private File file;
    private String requestUrl="http://app.npj-vip.com/index.php/api/set/uploadimg.html";
    //private String requestUrl="https://haijiao.pw/H5/upload.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getUserInfo();
    }

    private void getUserInfo() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        SetSubscribe.getUserInfo(user_id,
                token,
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        setUserInfo(result);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
    }

    private void setUserInfo(String result) {
        UserResponseBean userResponseBean = GsonUtils.fromJson(result, UserResponseBean.class);
        UserResponseBean.DataBean data = userResponseBean.getData();
        Glide.with(this).load(Uri.parse(data.getHead_img())).into(fgSettingIvHeadimg);
        acSettingsTvNickname.setText(data.getNick_name());
        acSettingsTvSignature.setText(data.getSignature());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.at_settings_rl_head_pic,
            R.id.at_settings_rl_clean_cash,
            R.id.at_settings_rl_nickname,
            R.id.at_settings_rl_signature,
            R.id.at_settings_rl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_settings_rl_head_pic:
                showBottomDialog();
                break;
            case R.id.at_settings_rl_clean_cash:
                showCleanCashDialog();
                break;
            case R.id.at_settings_rl_nickname:
                showSingleBottomDialog("设置昵称", "1");
                break;
            case R.id.at_settings_rl_signature:
                showSingleBottomDialog("设置个性签名", "2");
                break;
            case R.id.at_settings_rl_address:
                startActivity(new Intent(this, AddressListActivity.class));
                break;
        }
    }

    private void showCleanCashDialog() {
        new CommonDialog(SettingsActivity.this,
                R.style.dialog,
                "您确定删除此信息？",
                new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Toast.makeText(SettingsActivity.this,
                                    "点击确定",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            dialog.dismiss();
                        }
                    }
                }).show();
    }

    private void showBottomDialog() {
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
                      /*String user_id= SharePerferenceUtils.getUserId(SettingsActivity.this);
                      String token=SharePerferenceUtils.getToken(SettingsActivity.this);
                      String headimg = "/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg";
                      editHeadImg(user_id, token, headimg);*/
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
                    imageUri = FileProvider.getUriForFile(SettingsActivity.this,
                            "com.zthx.npj.file_provider",
                            outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

                String filePath = imageUri.getPath();
                File file = new File(filePath);
                Log.e("测试", "onActivityResult: " + file);
                SetSubscribe.upLoadFile(file, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("测试", "onSuccess: " + "上传成功");
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Log.e("测试", "onSuccess: " + "上传失败");
                    }
                }));

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
                        fgSettingIvHeadimg.setImageBitmap(bitmap);
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
                        fgSettingIvHeadimg.setImageBitmap(bitmap);
                        Log.e("测试", "onActivityResult: "+path);
                        upImage();
                        Log.e("测试", " "+file);
                        /*SetSubscribe.upLoadFile(file,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("测试", "onSuccess: "+result);
                            }

                            @Override
                            public void onFault(String errorMsg) {
                                Log.e("测试", "onSuccess: "+errorMsg);
                            }
                        }));*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    //两个选项的Dialog
    private void showSingleBottomDialog(String str, final String type) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_nickname_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        TextView dl_nickname = view.findViewById(R.id.dl_nickname);
        dl_nickname.setText(str);
        dialog.findViewById(R.id.dl_nickname)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SettingsActivity.this, EditNicknameActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
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

    private void editHeadImg(String user_id, String token, String head_img) {
        SetSubscribe.editHeadImg(user_id, token, head_img, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                //Glide.with(SettingsActivity.this).load(Uri.parse(result)).into(fg_setting_iv_headimg);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
    private void upImage() {
        OkHttpClient mOkHttpClent = new OkHttpClient();
        File file = new File(Environment.getExternalStorageDirectory()+"/temp.jpg");
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "temp.jpg",
                        RequestBody.create(MediaType.parse("image/png"), file));

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(requestUrl)
                .post(requestBody)
                .build();
        Call call = mOkHttpClent.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("测试", "onFailure: "+e );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SettingsActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
