package com.zthx.npj.ui;

import android.app.Dialog;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.downapk.DownloadService;
import com.zthx.npj.net.been.CartListResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.util.HttpUtil;
import com.zthx.npj.tencent.util.TencentUtil;
import com.zthx.npj.ui.fragment.DiscoverFragment;
import com.zthx.npj.ui.fragment.HomeFragment;
import com.zthx.npj.ui.fragment.MineFragment;
import com.zthx.npj.ui.fragment.ShoppingCart1Fragment;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private String downloadUrl;

    static {
        System.loadLibrary("native-lib");
    }

    //当前tab栏序号
    public static int mIndex;
    @BindView(R.id.lay_frg_main)
    FrameLayout layFrgMain;
    @BindView(R.id.ll_main_check_01)
    RadioButton llMainCheck01;
    @BindView(R.id.ll_main_check_02)
    RadioButton llMainCheck02;
    @BindView(R.id.ll_main_check_03)
    LinearLayout llMainCheck03;
    @BindView(R.id.ll_main_check_04)
    RadioButton llMainCheck04;
    @BindView(R.id.ll_main_check_05)
    RadioButton llMainCheck05;
    @BindView(R.id.ac_main_rg)
    RadioGroup acMainRg;
    @BindView(R.id.ac_main_tv_shoppingCart)
    TextView acMainTvShoppingCart;


    //fragment数组
    private Fragment[] mFragments;
    //fragment模块
    private HomeFragment mHomeFragment;
    private DiscoverFragment mDiscoverFragment;
    private ShoppingCart1Fragment mShoppingCartFragment;
    private MineFragment mMineFragment;
    private Intent intent;
    //private GameFragment mGameFragment;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //去除半透明状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = this.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        setContentView(R.layout.activity_main);
        BaseApp.getApp().addActivity(this);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent); // 启动服务
        bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务


        getNewPackInfro("http://game.npj-vip.com/version/getversion.php");

        RadioButton llMainCheck01 = findViewById(R.id.ll_main_check_01);
        RadioButton llMainCheck02 = findViewById(R.id.ll_main_check_02);
        RadioButton llMainCheck04 = findViewById(R.id.ll_main_check_04);
        RadioButton llMainCheck05 = findViewById(R.id.ll_main_check_05);

        Drawable dra1 = getResources().getDrawable(R.drawable.activity_main_select_home);
        Drawable dra2 = getResources().getDrawable(R.drawable.activity_main_select_discover);
        Drawable dra3 = getResources().getDrawable(R.drawable.activity_main_select_shoppingcart);
        Drawable dra4 = getResources().getDrawable(R.drawable.activity_main_select_mine);
        dra1.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp_25), (int) getResources().getDimension(R.dimen.dp_25));
        dra2.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp_25), (int) getResources().getDimension(R.dimen.dp_25));
        dra3.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp_25), (int) getResources().getDimension(R.dimen.dp_25));
        dra4.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp_25), (int) getResources().getDimension(R.dimen.dp_25));
        llMainCheck01.setCompoundDrawables(null, dra1, null, null);
        llMainCheck02.setCompoundDrawables(null, dra2, null, null);
        llMainCheck04.setCompoundDrawables(null, dra3, null, null);
        llMainCheck05.setCompoundDrawables(null, dra4, null, null);

        ButterKnife.bind(this);

        getUserMsg();

        initJPush();
        initFragment();
        // Example of a call to a native method
        setNotification();
        registerMessageReceiver();

        getBrowserResult();

    }





    @Override
    protected void onResume() {
        super.onResume();
        getShoppingCartSize();
        if(!SharePerferenceUtils.getLat(this).equals("")){
            BaseApp.getApp().locationService.unregisterListener(BaseApp.getListener());
            BaseApp.getApp().locationService.stop();
        }
        MyCustomUtils.setSystemMsg(this,user_id,token);
        if(!SharePerferenceUtils.getUserId(this).equals("")){
            getUserInfo();
        }
    }

    private void getShoppingCartSize() {
        SetSubscribe.cartList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {//第一个OnSuccessAndFaultSub是设置是否显示进度条，第二个是引用接口
            @Override
            public void onSuccess(String result) {
                CartListResponseBean bean = GsonUtils.fromJson(result, CartListResponseBean.class);
                ArrayList<ArrayList<CartListResponseBean.DataBean>> data = bean.getData();
                if (data == null || data.size() == 0) {
                    acMainTvShoppingCart.setVisibility(View.GONE);
                } else {
                    acMainTvShoppingCart.setVisibility(View.VISIBLE);
                    int shoppingCartSize=0;
                    for(int i=0;i<data.size();i++){
                        shoppingCartSize+=data.get(i).size();
                    }
                    acMainTvShoppingCart.setText(shoppingCartSize+"");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getBrowserResult() {
        Intent mgetvalue = getIntent();
        String maction = mgetvalue.getAction();
        if (Intent.ACTION_VIEW.equals(maction)) {
            Uri uri = mgetvalue.getData();
            Log.e("测试", "getBrowserResult: "+uri );
            if (uri != null) {
                String type = uri.getQueryParameter("type");
                String id = uri.getQueryParameter("id");
                if (type.equals("qianggou")) {
                    intent = new Intent(this, GoodsDetailActivity.class);
                    intent.setAction("miaosha");
                    intent.putExtra("goods_id", id + "");
                } else if (type.equals("zhongchou")) {
                    intent = new Intent(this, GoodsDetailActivity.class);
                    intent.setAction("presell");
                    intent.putExtra("goods_id", id + "");
                } else if (type.equals("putong")) {
                    intent = new Intent(this, GoodsDetailActivity.class);
                    intent.setAction("Goods");
                    intent.putExtra("goods_id", id + "");
                }else if (type.equals("gongying")) {
                    intent = new Intent(this, SupplyProductsActivity.class);
                    intent.setAction(Const.SUPPLY_DETAIL);
                    intent.putExtra("goods_id", id+"");
                }else if (type.equals("qiugou")) {
                    intent = new Intent(this, SupplyProductsActivity.class);
                    intent.setAction(Const.NEED_DETAIL);
                    intent.putExtra("goods_id", id+"");
                }else if (type.equals("tuijian")) {
                    intent = new Intent(this, MembershipPackageActivity.class);
                }else if (type.equals("dianpu")) {
                    intent = new Intent(this, PayToStoreActivity.class);
                    intent.putExtra("key0", id);
                } else{
                    /*intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("discover_url", uri);*/
                    Toast.makeText(this,maction,Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        }
        /*if (maction != null) {
            Toast.makeText(this, "非农品街二维码不予识别", Toast.LENGTH_LONG).show();
        }*/
    }


    private void getUserMsg() {
        Log.e("测试", "getUserMsg: " + user_id + " " + token);

        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                SharePerferenceUtils.setUserLevel(MainActivity.this, bean.getData().getLevel() + "");
                BaseConstant.TOKEN = SharePerferenceUtils.getToken(MainActivity.this);
                loginIM(bean.getData().getMobile(), bean.getData().getMobile());
                TencentUtil.login(bean.getData().getMobile());
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                SharePerferenceUtils.setUserId(MainActivity.this,"");
            }
        }));
    }

    private void loginIM(final String name, String pwd) {
       /* JMessageClient.login(name, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });*/
    }


    private void initJPush() {
        JPushInterface.init(getApplicationContext());
    }

    private void setNotification() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
        builder.statusBarDrawable = R.drawable.logo;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }


    public void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            fragmentManager.beginTransaction().remove(mFragments[index]).commit();
            switch (index) {
                case 0:
                    mHomeFragment = new HomeFragment();
                    mFragments[0] = mHomeFragment;
                    //startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                    break;
                case 1:
                    mDiscoverFragment = new DiscoverFragment();
                    mFragments[1] = mDiscoverFragment;
                    //startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                    break;
                case 2:
                    mShoppingCartFragment = new ShoppingCart1Fragment();
                    mFragments[2] = mShoppingCartFragment;
                    break;
                case 3:
                    mMineFragment = new MineFragment();
                    mFragments[3] = mMineFragment;
                    //startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                    break;
                default:
                    break;
            }
            fragmentManager.beginTransaction().add(R.id.lay_frg_main, mFragments[index]).commit();
        } else {
            ft.show(mFragments[index]);
        }
        try {
            ft.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        ft.commitAllowingStateLoss();
        //再次赋值
        mIndex = index;
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mDiscoverFragment = new DiscoverFragment();
        mShoppingCartFragment = new ShoppingCart1Fragment();
        mMineFragment = new MineFragment();
        //mGameFragment = new GameFragment();
        //添加到数组
        mFragments = new Fragment[]{mHomeFragment, mDiscoverFragment, mShoppingCartFragment, mMineFragment};
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.lay_frg_main, mHomeFragment).commit();
        llMainCheck01.performClick();
    }

    @OnClick({R.id.ll_main_check_01, R.id.ll_main_check_02, R.id.ll_main_check_03, R.id.ll_main_check_04, R.id.ll_main_check_05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_check_01:
                setIndexSelected(0);
                break;
            case R.id.ll_main_check_02:
                setIndexSelected(1);
                break;
            case R.id.ll_main_check_03:
                if(SharePerferenceUtils.getUserId(this).equals("")){
                    Toast.makeText(this,"请先完成登录",Toast.LENGTH_SHORT).show();
                }else{
                    //startActivity(new Intent(this, GameActivity.class));
                }
                break;
            case R.id.ll_main_check_04:
                setIndexSelected(2);
                break;
            case R.id.ll_main_check_05:
                setIndexSelected(3);
                break;
        }
    }


    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                }
            } catch (Exception e) {
            }
        }
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

    /**
     * 请求服务器拿到新的app版本的相关信息
     *
     * @param updateURL
     */
    private void getNewPackInfro(final String updateURL) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url(updateURL)//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            String res = null;
                            try {
                                res = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            checkVersion(res);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkVersion(String res) {
        //拿到服务器返回的相关数据
        //进行版本比较是否进行升级
        try {
            JSONObject jsonObject = new JSONObject(res);
            final String version=jsonObject.getString("version");
            final String content=jsonObject.getString("content");
            final boolean forceUpdate=jsonObject.getBoolean("force_update");

            SharePerferenceUtils.setVersion(this,res);

            if(!version.equals(getPackageVersionName())){
                downloadUrl=jsonObject.getString("url");
                //服务器有新版本,可供下载
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //showDialog();
                        showAlertDialog(version,content,forceUpdate);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
                    Toast.makeText(MainActivity.this, "下载新版本失败请稍后重试", Toast.LENGTH_SHORT).show();
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(MainActivity.this,
                    "com.zthx.npj.file_provider",
                    file);
        } else {
            uri = Uri.fromFile(file);
        }
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
                //downloadBinder.startDownload(downloadUrl);
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://www.npj-vip.com/"));
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void getUserInfo() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {
                MyCustomUtils.showDialog(MainActivity.this);
            }
        }));
    }
}
