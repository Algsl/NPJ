package com.zthx.npj.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.downapk.DownloadService;
import com.zthx.npj.media.Settings;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.util.TencentUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
    @BindView(R.id.ac_setting_iv_msg)
    ImageView acSettingIvMsg;
    @BindView(R.id.ac_setting_tv_cache)
    TextView acSettingTvCache;
    @BindView(R.id.ac_setting_btn_loginOut)
    Button acSettingBtnLoginOut;
    @BindView(R.id.term)
    RelativeLayout term;
    @BindView(R.id.ac_setting_tv_version)
    TextView acSettingTvVersion;
    @BindView(R.id.ac_setting_rl_check)
    RelativeLayout acSettingRlCheck;


    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private boolean receiveMsg = true;

    private String downloadUrl;

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent); // 启动服务
        bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务


        ButterKnife.bind(this);

        acSettingsTvSignature.setSelected(true);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "设置");

        getUserInfo();

        acSettingTvVersion.setText(getPackageVersionName());
    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setUserInfo(String result) {
        UserResponseBean userResponseBean = GsonUtils.fromJson(result, UserResponseBean.class);
        UserResponseBean.DataBean data = userResponseBean.getData();
        if(!data.getHead_img().equals("")){
            Glide.with(this).load(Uri.parse(data.getHead_img())).into(fgSettingIvHeadimg);
            acSettingsTvNickname.setText(data.getNick_name());
            acSettingsTvSignature.setText(data.getSignature());
        }
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
            R.id.ac_setting_iv_msg,
            R.id.term,
            R.id.ac_setting_rl_check})
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
            case R.id.term:
                openActivity(TermsOfServiceActivity.class);
                break;
            case R.id.ac_setting_rl_check:
                //getNewPackInfro("http://game.gesilaa6.club/project/getVersion.php");
                checkVersion(SharePerferenceUtils.getVersion(this));
                break;
        }
    }

    private void toggle() {
        receiveMsg = !receiveMsg;
        if (receiveMsg) {
            acSettingIvMsg.setImageResource(R.drawable.at_edit_address_selector);
        } else {
            acSettingIvMsg.setImageResource(R.drawable.at_edit_address_not_selector);
            @SuppressLint("WrongConstant") NotificationManager manager = (NotificationManager) SettingsActivity.this.getApplicationContext().getSystemService("notification");
            manager.cancelAll();
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
                        String compressPath = compress(filePath);
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, compressPath, new Callback() {
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
                        String compressPath = compress(path);
                        Bitmap bitmap=BitmapFactory.decodeFile(compressPath);
                        fgSettingIvHeadimg.setImageBitmap(bitmap);
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, compressPath, new Callback() {
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
            case 3:
                if (resultCode == 3) {
                    acSettingsTvNickname.setText(data.getStringExtra("content"));
                }
                break;
            case 4:
                Log.e("测试", "onActivityResult: " + resultCode);
                if (requestCode == 4) {
                    Log.e("测试", "onActivityResult: " + data.getStringExtra("content"));
                    acSettingsTvSignature.setText(data.getStringExtra("content"));
                }
                break;
            case 0:
                getUserInfo();
                break;
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
                        //openActivity(EditNicknameActivity.class, type);
                        Intent intent = new Intent(SettingsActivity.this, EditNicknameActivity.class);
                        intent.putExtra("key0", type);
                        if (type.equals("1")) {
                            startActivityForResult(intent, 3);
                        } else {
                            startActivityForResult(intent, 4);
                        }
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

    private void editHeadImg(String user_id, String token, String head_img) {
        SetSubscribe.editHeadImg(user_id, token, head_img, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @OnClick(R.id.ac_setting_btn_loginOut)
    public void onViewClicked() {
        TencentUtil.loginOut();
        SharePerferenceUtils.setUserId(this, "");
        openActivity(SplashActivity.class);
    }



    /**
     * 拿到versionName
     *
     * @return
     */
    public String getPackageVersionName() {
        //1.PackageManager 包的管理者对象
        PackageManager pm = getPackageManager();
        //2.获取应用的配置信息,在此处传递0获取的是基本信息(包名,版本名称,版本号)
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 拿到versionCode
     *
     * @return
     */
    public int getPackageVersionCode() {
        //1.PackageManager 包的管理者对象
        PackageManager pm = getPackageManager();
        //2.获取应用的配置信息,在此处传递0获取的是基本信息(包名,版本名称,版本号)
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void checkVersion(String res) {
        //拿到服务器返回的相关数据
        //进行版本比较是否进行升级
        String version="";
        String content="";
        boolean forceUpdate=false;
        try {
            JSONObject jsonObject = new JSONObject(res);
            version=jsonObject.getString("version");
            content=jsonObject.getString("content");
            downloadUrl=jsonObject.getString("url");
            forceUpdate=jsonObject.getBoolean("force_update");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(version.equals(getPackageVersionName())){
            showToast("当前已经是最新版本了");
        }else{
            //服务器有新版本,可供下载
            final String finalVersion = version;
            final String finalContent = content;
            final boolean finalForceUpdate = forceUpdate;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //showDialog();
                    showAlertDialog(finalVersion, finalContent, finalForceUpdate);
                }
            });
        }
    }


    /**
     * 下载新的apk文件
     */
    private void downloadApk(final ProgressBar pb, final TextView percent, final TextView progress) {
        //下载apk,并且指定放置下载文件的路径,sd卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建一个进度条对话框,用于显示下载进度
            /*progressDialog = new ProgressDialog(this);
            //默认情况下对话框进度条圆形转圈的
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.show();*/
            //手机的sd卡可用
            //sd卡存储文件的路径
            //final String path = getExternalCacheDir()+ "release.apk";
            //如何根据downloadUrl进行下载,okhttp下载
            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().get().url(downloadUrl).build();
            okHttpClient.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    //请求失败
                    Toast.makeText(SettingsActivity.this, "下载新版本失败请稍后重试", Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //请求成功,从服务器的响应对象中获取apk,流(服务器 输入流(提供数据)  本地 输出流(写入文件))
                    ResponseBody body = response.body();
                    //告知progressDialog总进度,不变
                    //progressDialog.setMax((int) body.contentLength());
                    final int allLength= (int) body.contentLength();
                    pb.setMax(allLength);
                    //inputStream就是服务器把需要下载的apk以流的形式提供给客户端
                    InputStream inputStream = body.byteStream();
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    int temp = 0;//用于记录目前下载的到的位置
                    while ((len = inputStream.read(bytes)) != -1) {
                        //将读过的数据写入文件中
                        fos.write(bytes, 0, len);
                        //告知progressDialog在下载过程中的当前进度
                        temp += len;
                        //将当前的下载位置,设置给progressDialog
                        //progressDialog.setProgress(temp);
                        //没下载一段数据,睡眠一段时间

                        final int finalTemp = temp;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress(finalTemp);
                                percent.setText((int)(finalTemp*100.0/allLength)+"%");
                                progress.setText(new DecimalFormat("#0.00").format(finalTemp/1000000.00)+"M/"+new DecimalFormat("#0.00").format(allLength/1000000.00)+"M");
                            }
                        });

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //一旦循环结束,则sd卡中就有一个从服务器下载下来的apk
                    //下载完成后,则隐藏对话框
                    //progressDialog.dismiss();
                    //安装apk,这个要装的apk在哪里
                    installApk(file);
                }
            });
        }
    }

    /**
     * 安装指定路径下的apk
     *
     * @param file 需要安装文件的路径
     */
    private void installApk(File file) {
        //找到系统的安装界面,把安装过程中要用到的东西传递进去,让系统帮助我们安装.
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri=null;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(SettingsActivity.this,
                    "com.zthx.npj.file_provider",
                    file);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri,"application/vnd.android.package-archive");
        //通过隐式意图开启系统的安装apk界面
        startActivity(intent);
    }


    private void showAlertDialog(String version,String content,boolean forceUpdate) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_update, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.CENTER);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout((int)getResources().getDimension(R.dimen.dp_300), (int)getResources().getDimension(R.dimen.dp_400));
        dialog.show();


        TextView tvVersion=view.findViewById(R.id.dg_update_tv_version);
        TextView tvContent=view.findViewById(R.id.dg_update_content);
        TextView runInBack=view.findViewById(R.id.dg_update_tv_runInBack);
        final TextView later=view.findViewById(R.id.dg_update_tv_later);
        final TextView percent=view.findViewById(R.id.dg_update_tv_percent);
        final TextView progress=view.findViewById(R.id.dg_update_tv_progress);
        final ProgressBar pb=view.findViewById(R.id.dg_update_pb);
        final Button update=view.findViewById(R.id.dg_update_btn_update);
        final LinearLayout layout=view.findViewById(R.id.dg_update_ll_download);


        tvVersion.setText("发现新版本"+version);
        tvContent.setText(content.replace("\\n","\n"));

        if(forceUpdate){
            later.setVisibility(View.GONE);
        }else{
            later.setVisibility(View.VISIBLE);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.setVisibility(View.GONE);
                later.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                downloadApk(pb,percent,progress);
            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        runInBack.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                downloadBinder.startDownload(downloadUrl);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public String compress(String path){
        File file=new File(path);
        Bitmap compressBitmap=BitmapFactory.decodeFile(file.getAbsolutePath());
        compressBitmap=centerSquareScaleBitmap(compressBitmap,200);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        compressBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        int options1 = 90;
        while (bos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bos.reset(); // 重置baos即清空baos
            compressBitmap.compress(Bitmap.CompressFormat.JPEG, options1, bos);// 这里压缩options%，把压缩后的数据存放到baos中
            options1 -= 10;// 每次都减少10
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(bos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        compressBitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        String bmString="";
        try {
            File bmFile=new File(getExternalCacheDir(), System.currentTimeMillis()+".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString=bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
    }



    public Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength)
    {
        if(null == bitmap || edgeLength <= 0)
        {
            return  null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if(widthOrg > edgeLength && heightOrg > edgeLength)
        {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int)(edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try{
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            }
            catch(Exception e){
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try{
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            }
            catch(Exception e){
                return null;
            }
        }
        return result;
    }
}
