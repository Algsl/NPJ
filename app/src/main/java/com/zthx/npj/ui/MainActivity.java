package com.zthx.npj.ui;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.net.been.CartListResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.fragment.DiscoverFragment;
import com.zthx.npj.ui.fragment.HomeFragment;
import com.zthx.npj.ui.fragment.MineFragment;
import com.zthx.npj.ui.fragment.ShoppingCart1Fragment;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class MainActivity extends AppCompatActivity {


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

        initJPush();
        initFragment();
        // Example of a call to a native method
        setNotification();
        registerMessageReceiver();
        getUserMsg();

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
    }

    private void getShoppingCartSize() {
        SetSubscribe.cartList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CartListResponseBean bean = GsonUtils.fromJson(result, CartListResponseBean.class);
                ArrayList<ArrayList<CartListResponseBean.DataBean>> data = bean.getData();
                if (data == null || data.size() == 0) {
                    acMainTvShoppingCart.setVisibility(View.GONE);
                } else {
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
            if (uri != null) {
                String page = uri.getQueryParameter("page");
                String type = uri.getQueryParameter("type");
                String id = uri.getQueryParameter("id");
                if (page.equals("goodsDetail")) {
                    intent = new Intent(this, GoodsDetailActivity.class);
                    intent.setAction(type);
                    intent.putExtra("goods_id", id + "");
                } else if (page.equals("tuijian")) {
                    startActivity(new Intent(this, MembershipPackageActivity.class));
                } else if (page.equals("payStore")) {
                    intent = new Intent(this, PayToStoreActivity.class);
                    intent.putExtra("key0", id);
                } else {
                    intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("discover_url", uri);
                }
                startActivity(intent);
            }
        }
        if (maction != null) {
            Toast.makeText(this, "非农品街二维码不予识别", Toast.LENGTH_LONG).show();
        }
    }


    private void getUserMsg() {
        Log.e("测试", "getUserMsg: " + user_id + " " + token);
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                SharePerferenceUtils.setUserLevel(MainActivity.this, bean.getData().getLevel() + "");
                loginIM(bean.getData().getMobile(), bean.getData().getMobile());
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void loginIM(final String name, String pwd) {
        JMessageClient.login(name, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
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
                //startActivity(new Intent(this,GameActivity.class));
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
}
