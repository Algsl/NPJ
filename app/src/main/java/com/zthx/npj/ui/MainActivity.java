package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zthx.npj.R;
import com.zthx.npj.ui.fragment.DiscoverFragment;
import com.zthx.npj.ui.fragment.GameFragment;
import com.zthx.npj.ui.fragment.HomeFragment;
import com.zthx.npj.ui.fragment.MineFragment;
import com.zthx.npj.ui.fragment.ShoppingCartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


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
    private ShoppingCartFragment mShoppingCartFragment;
    private MineFragment mMineFragment;
    private GameFragment mGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        // Example of a call to a native method
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
                    break;
                case 1:
                    mDiscoverFragment = new DiscoverFragment();
                    mFragments[1] = mDiscoverFragment;
                    break;
                case 2:
                    mGameFragment = new GameFragment();
                    mFragments[2] = mGameFragment;
                    acMainRg.clearCheck();
                    break;
                case 3:
                    mShoppingCartFragment = new ShoppingCartFragment();
                    mFragments[3] = mShoppingCartFragment;
                    break;
                case 4:
                    mMineFragment = new MineFragment();
                    mFragments[4] = mMineFragment;
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
        mShoppingCartFragment = new ShoppingCartFragment();
        mMineFragment = new MineFragment();
        mGameFragment = new GameFragment();
        //添加到数组
        mFragments = new Fragment[]{mHomeFragment, mDiscoverFragment, mGameFragment, mShoppingCartFragment, mMineFragment};
        //开启事务
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
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
                setIndexSelected(2);
                break;
            case R.id.ll_main_check_04:
                setIndexSelected(3);
                break;
            case R.id.ll_main_check_05:
                setIndexSelected(4);
                break;
        }
    }
}
