package com.zthx.npj.ui;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.entity.PaoMaBean;
import com.zthx.npj.ui.fragment.HomeFragment;
import com.zthx.npj.view.MyPaoMaView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends ActivityBase {
    private List<String> datas=new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*ArrayList<PaoMaBean> mList = new ArrayList<>();
        mList.add(new PaoMaBean(BitmapFactory.decodeResource(getResources(), R.drawable.logo), "国货PK美国货,结果让人震惊"));
        mList.add(new PaoMaBean(BitmapFactory.decodeResource(getResources(), R.drawable.test), "这次XiPhone,可能让你迷路"));
        mList.add(new PaoMaBean(BitmapFactory.decodeResource(getResources(), R.drawable.game), "为什么吉普,奥巴马都爱钓鱼"));
        mList.add(new PaoMaBean(BitmapFactory.decodeResource(getResources(), R.drawable.jianshe), "虽然我字难看,但我钢笔好看啊"));

        acTestMpmText.setFrontColor(Color.RED);
        acTestMpmText.setBackColor(Color.BLACK);
        acTestMpmText.setmTexts(mList);
        acTestMpmText.setmDuration(500);
        acTestMpmText.setmInterval(2000);
        acTestMpmText.setOnClickLitener(new MyPaoMaView.onClickLitener() {
            @Override
            public void onClick(String mUrl) {
                Toast.makeText(TestActivity.this, "点击了" + mUrl, Toast.LENGTH_LONG).show();
            }
        });*/
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tas);
        ViewPager viewPager= (ViewPager) findViewById(R.id.vp);

        datas.add("推荐");
        datas.add("要闻");
        datas.add("娱乐");
        datas.add("科技");
        datas.add("汽车");
        datas.add("体育");
        datas.add("图片");
        datas.add("动漫");
        datas.add("社会");
        datas.add("游戏");

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        //进行关联
        tabLayout.setupWithViewPager(viewPager);
    }
    class MyAdapter extends FragmentPagerAdapter {

        //带参的构造方法
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //返回选项卡的文本 ，，，添加选项卡
        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment f = new HomeFragment();

            return f;
        }
        @Override public int getCount() {
            return datas.size();//返回选项卡的数量
        }
    }
}