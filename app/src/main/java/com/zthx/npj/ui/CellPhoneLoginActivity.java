package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.AuthLoginByMoBileBean;
import com.zthx.npj.net.been.AuthLoginByMobileResponseBean;
import com.zthx.npj.net.been.MsgCodeResponseBeen;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.been.PhoneLoginResponseBean;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CellPhoneLoginActivity extends ActivityBase {


    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_cellphone_login_iv_delete)
    ImageView atCellphoneLoginIvDelete;
    @BindView(R.id.at_cellphone_login_tv_get_code)
    TextView atCellphoneLoginTvGetCode;
    @BindView(R.id.at_cellphone_login_btn_next)
    Button atCellphoneLoginBtnNext;
    @BindView(R.id.at_cellphone_login_et_phone)
    EditText atCellphoneLoginEtPhone;
    @BindView(R.id.at_cellphone_login_et_code)
    EditText atCellphoneLoginEtCode;
    @BindView(R.id.at_cellphone_login_iv_head_pic)
    MyCircleView atCellphoneLoginIvHeadPic;
    @BindView(R.id.at_cellphone_login_tv_third_name)
    TextView atCellphoneLoginTvThirdName;
    @BindView(R.id.at_cellphone_login_tv_hint)
    TextView atCellphoneLoginTvHint;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private String mCodeId;//短信验证码随机数
    private boolean isThirdLogin = false;
    public Handler handler = new Handler();
    String result;
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    private int time=60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone_login);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"手机登录");
        isThirdLogin = getIntent().getBooleanExtra("flag", true);
        if (isThirdLogin) {
            Glide.with(this).load(getIntent().getStringExtra("headImg")).into(atCellphoneLoginIvHeadPic);
            atCellphoneLoginTvThirdName.setText(getIntent().getStringExtra("nickName"));
        } else {
            atCellphoneLoginTvHint.setVisibility(View.INVISIBLE);
            atCellphoneLoginTvThirdName.setText("用户登录");
            atCellphoneLoginIvHeadPic.setImageResource(R.drawable.logo);
            atCellphoneLoginIvHeadPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


    @OnClick({R.id.at_cellphone_login_iv_delete, R.id.at_cellphone_login_tv_get_code, R.id.at_cellphone_login_btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_cellphone_login_iv_delete:
                atCellphoneLoginEtPhone.setText("");
                break;
            case R.id.at_cellphone_login_tv_get_code:
                if (TextUtils.isEmpty(atCellphoneLoginEtPhone.getText().toString().trim())) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else if(!MyCustomUtils.isRegular(atCellphoneLoginEtPhone.getText().toString().trim(),"mobile")){
                    Toast.makeText(this, "请正确填写手机号", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(){
                        public void run(){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(time<=0){
                                        time=60;
                                        atCellphoneLoginTvGetCode.setText("重新获取");
                                        atCellphoneLoginTvGetCode.setBackgroundResource(R.drawable.stroke_theme_5);
                                        atCellphoneLoginTvGetCode.setTextColor(getResources().getColor(R.color.app_theme));
                                        atCellphoneLoginTvGetCode.setClickable(true);
                                    }else{
                                        atCellphoneLoginTvGetCode.setClickable(false);
                                        time--;
                                        atCellphoneLoginTvGetCode.setBackgroundResource(R.drawable.stroke_gray_2);
                                        atCellphoneLoginTvGetCode.setTextColor(getResources().getColor(R.color.text6));
                                        atCellphoneLoginTvGetCode.setText(time+"s后获取");
                                        handler.postDelayed(this,1000);
                                    }
                                }
                            });
                        }
                    }.start();
                    sendPhoneCode();
                }
                break;
            case R.id.at_cellphone_login_btn_next:
                if (TextUtils.isEmpty(atCellphoneLoginEtCode.getText().toString().trim())
                        || TextUtils.isEmpty(atCellphoneLoginEtPhone.getText().toString().trim())) {
                    Toast.makeText(this, "验证码或手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
        }
    }

    /**
     * 根据手机号验证码登录
     */
    private void login() {
        JPushInterface.setAliasAndTags(getApplicationContext(),atCellphoneLoginEtPhone.getText().toString().trim(), null, new TagAliasCallback() {
            @Override
            public void gotResult(int code, String alias, Set<String> set) {
                Log.e("测试", "gotResult: "+code+" "+alias+" "+set );
                switch (code) {
                    case 0:
                        //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                        //UserUtils.saveTagAlias(getHoldingActivity(), true);
                        Log.e("测试","Set tag and alias success极光推送别名设置成功");
                        break;
                    case 6002:
                        //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                        Log.e("测试","Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试");
                        break;
                    default:
                        Log.e("测试","极光推送设置失败，Failed with errorCode = " + code);
                        break;
                }
            }
        });
        if (isThirdLogin) {
            //第三方绑定手机号
            authLoginByMobile();
        } else {
            //用户登录
            userLogin();
        }
    }
    //手机号登录
    private void userLogin() {
        PhoneLoginBean bean = new PhoneLoginBean();
        bean.setCode(atCellphoneLoginEtCode.getText().toString().trim());
        bean.setMobile(atCellphoneLoginEtPhone.getText().toString().trim());
        bean.setSession_id(mCodeId);
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        //获取到user_id和token并保存
        LoginSubscribe.MobileLogin(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PhoneLoginResponseBean bean = GsonUtils.fromJson(result, PhoneLoginResponseBean.class);
                SharePerferenceUtils.setUserId(CellPhoneLoginActivity.this, bean.getData().getUser_id());
                SharePerferenceUtils.setToken(CellPhoneLoginActivity.this, bean.getData().getToken());
                if(bean.getData().getInviter()==null){
                    startActivity(new Intent(CellPhoneLoginActivity.this, InputInvitationCodeActivity.class));
                }else{
                    startActivity(new Intent(CellPhoneLoginActivity.this, MainActivity.class));
                }

            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                //Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
    //第三方登录
    private void authLoginByMobile() {
        AuthLoginByMoBileBean bean = new AuthLoginByMoBileBean();
        bean.setMobile(atCellphoneLoginEtPhone.getText().toString().trim());
        bean.setCode(atCellphoneLoginEtCode.getText().toString().trim());
        bean.setSession_id(mCodeId);

        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        LoginSubscribe.authLoginByMobile(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AuthLoginByMobileResponseBean bean = GsonUtils.fromJson(result, AuthLoginByMobileResponseBean.class);
                SharePerferenceUtils.setUserId(CellPhoneLoginActivity.this, bean.getData().getUser_id());
                SharePerferenceUtils.setToken(CellPhoneLoginActivity.this, bean.getData().getToken());
                if(bean.getData().getInviter()==null){
                    startActivity(new Intent(CellPhoneLoginActivity.this, InputInvitationCodeActivity.class));
                }else{
                    startActivity(new Intent(CellPhoneLoginActivity.this, MainActivity.class));
                }
                //SharePerferenceUtils.setIsBindWx(CellPhoneLoginActivity.this,"bind");
            }
            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                //Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    /**
     * 发送验证码
     */
    private void sendPhoneCode() {
        LoginSubscribe.getMobileCode(atCellphoneLoginEtPhone.getText().toString().trim(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MsgCodeResponseBeen bean = GsonUtils.fromJson(result, MsgCodeResponseBeen.class);
                mCodeId = bean.getData().getSession_id();
                Toast.makeText(CellPhoneLoginActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                //失败
                //Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
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
                    imageUri = FileProvider.getUriForFile(CellPhoneLoginActivity.this,
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
                        //fgSettingIvHeadimg.setImageBitmap(bitmap);
                        String filePath = getExternalCacheDir() + "/output_image.jpg";
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, filePath, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                               // editHeadImg(user_id, token, data.getSrc());
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
                        //fgSettingIvHeadimg.setImageBitmap(bitmap);
                        /*JMessageClient.updateUserAvatar(new File(path), new BasicCallback() {
                            @Override
                            public void gotResult(int i, String s) {
                                if (i == 0) {
                                    Log.e("测试", "gotResult: "+i+" "+s);
                                } else {
                                    Log.e("测试", "gotResult: "+i+" "+s);
                                }
                            }
                        });*/
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                //editHeadImg(user_id, token, data.getSrc());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
