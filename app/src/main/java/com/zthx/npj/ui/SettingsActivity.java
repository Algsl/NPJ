package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity
        extends AppCompatActivity
{

    @BindView(R.id.at_settings_rl_head_pic)
    RelativeLayout atSettingsRlHeadPic;
    @BindView(R.id.at_settings_rl_clean_cash)
    RelativeLayout atSettingsRlCleanCash;
    @BindView(R.id.fg_setting_iv_headimg)
    ImageView      fg_setting_iv_headimg;
    @BindView(R.id.at_settings_rl_nickname)
    RelativeLayout atSettingsRlNickname;
    @BindView(R.id.at_settings_rl_signature)
    RelativeLayout atSettingsRlSignature;
    @BindView(R.id.ac_settings_tv_nickname)
    TextView       acSettingsTvNickname;
    @BindView(R.id.ac_settings_tv_signature)
    TextView       acSettingsTvSignature;
    @BindView(R.id.at_settings_rl_address)
    RelativeLayout mAtSettingsRlAddress;

    private              Uri imageUri;
    private static final int TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        //getUserInfo();
    }

    private void getUserInfo() {
        String user_id = SharePerferenceUtils.getUserId(this);
        //String token   = SharePerferenceUtils.getToken(getContext());
        String token = "1f27405d66fa30be262785b395b622a6";
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
                startActivity(new Intent(this,AddressListActivity.class));
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
        dialog.findViewById(R.id.dl_photo_take_pic)
              .setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      //String user_id= SharePerferenceUtils.getUserId(SettingsActivity.this);
                      //String token=SharePerferenceUtils.getToken(SettingsActivity.this);
                      String user_id = "23";
                      String token   = "1f27405d66fa30be262785b395b622a6";
                      String headimg = "/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg";
                      editHeadImg(user_id, token, headimg);
                      dialog.dismiss();
                  }
              });
        //拍摄照片
        dialog.findViewById(R.id.dl_photo_take_photo)
              .setOnClickListener(new View.OnClickListener() {
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
                                                                "com.example.cameraalbumtest.fileprovider",
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
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(
                                imageUri));
                        fg_setting_iv_headimg.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
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
        SetSubscribe.editHeadImg(user_id,
                                 token,
                                 head_img,
                                 new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                     @Override
                                     public void onSuccess(String result) {
                                         Glide.with(SettingsActivity.this)
                                              .load(Uri.parse(result))
                                              .into(fg_setting_iv_headimg);
                                     }

                                     @Override
                                     public void onFault(String errorMsg) {

                                     }
                                 }));
    }


}
