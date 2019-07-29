package com.zthx.npj.ui;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zthx.npj.R;
import com.zthx.npj.ui.fragment.DiscoverFragment;
import com.zthx.npj.ui.fragment.GameFragment;
import com.zthx.npj.ui.fragment.HomeFragment;
import com.zthx.npj.ui.fragment.MineFragment;
import com.zthx.npj.ui.fragment.ShoppingCart1Fragment;
import com.zthx.npj.ui.fragment.ShoppingCartFragment;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

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


    //fragment数组
    private Fragment[] mFragments;
    //fragment模块
    private HomeFragment mHomeFragment;
    private DiscoverFragment mDiscoverFragment;
    private ShoppingCart1Fragment mShoppingCartFragment;
    private MineFragment mMineFragment;
    //private GameFragment mGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initJPush();
        initFragment();
        // Example of a call to a native method
        setNotification();
        registerMessageReceiver();
        //Log.e("测试", "onCreate: "+SharePerferenceUtils.getToken(this));
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
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft =fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            fragmentManager.beginTransaction().remove(mFragments[index]).commit();
            switch (index) {
                case 0:
                    mHomeFragment = new HomeFragment();
                    mFragments[0] = mHomeFragment;
                    break;
                case 1:
                    mDiscoverFragment = new DiscoverFragment();
                    mFragments[1] = mDiscoverFragment;
                    break;
                /*case 2:
                    mGameFragment = new GameFragment();
                    mFragments[2] = mGameFragment;
                    acMainRg.clearCheck();
                    llMainCheck03.setOnApplyWindowInsetsListener(null);
                    startActivity(new Intent(this,GameActivity.class));
                    break;*/
                case 2:
                    mShoppingCartFragment = new ShoppingCart1Fragment();
                    mFragments[2] = mShoppingCartFragment;
                    break;
                case 3:
                    mMineFragment = new MineFragment();
                    mFragments[3] = mMineFragment;
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
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
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
                startActivity(new Intent(this,GameActivity.class));
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
            } catch (Exception e){
            }
        }
    }
}
