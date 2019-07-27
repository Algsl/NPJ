package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SettingsActivity extends ActivityBase {

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
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_setting_iv_msg)
    ImageView acSettingIvMsg;
    @BindView(R.id.ac_setting_tv_cache)
    TextView acSettingTvCache;
    @BindView(R.id.ac_setting_btn_loginOut)
    Button acSettingBtnLoginOut;


    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private boolean receiveMsg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);


        back(titleThemeBack);
        changeTitle(titleThemeTitle, "设置");

    }

    private void getUserInfo() {
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
        getUserInfo();
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
            R.id.at_settings_rl_address,
            R.id.ac_setting_iv_msg})
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
                openActivity(AddressListActivity.class);
                break;
            case R.id.ac_setting_iv_msg:
                toggle();
                break;
        }
    }

    private void toggle() {
        receiveMsg = !receiveMsg;
        if (receiveMsg) {
            acSettingIvMsg.setImageResource(R.drawable.at_edit_address_selector);
        } else {
            acSettingIvMsg.setImageResource(R.drawable.at_edit_address_not_selector);
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
                            acSettingTvCache.setText("0M");
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
                        String filePath = getExternalCacheDir() + "/output_image.jpg";
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, filePath, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                editHeadImg(user_id, token, data.getSrc());
                            }
                        });
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
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                editHeadImg(user_id, token, data.getSrc());
                            }
                        });
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
                        /*Intent intent = new Intent(SettingsActivity.this, EditNicknameActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);*/
                        openActivity(EditNicknameActivity.class, type);
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

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick(R.id.ac_setting_btn_loginOut)
    public void onViewClicked() {
        SharePerferenceUtils.setUserId(this,"");
        openActivity(SplashActivity.class);
    }
}
