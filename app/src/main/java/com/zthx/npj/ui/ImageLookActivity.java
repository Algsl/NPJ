package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ImgLookAdapter;
import com.zthx.npj.ui.fragment.ImgFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageLookActivity extends ActivityBase {

    private static final String TAG = "测试";

    @BindView(R.id.ac_imgLook_vp)
    ViewPager acImgLookVp;
    @BindView(R.id.ac_imgLook_iv_back)
    ImageView acImgLookIvBack;
    @BindView(R.id.ac_imgLook_tv_index)
    TextView acImgLookTvIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_look);
        ButterKnife.bind(this);

        int position = getIntent().getIntExtra("position", 1);
        final ArrayList<String> imgs = getIntent().getStringArrayListExtra("imgs");

        back(acImgLookIvBack);
        acImgLookTvIndex.setText(position + "/" + imgs.size());

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            fragmentList.add(new ImgFragment().newIntent(imgs.get(i)));
        }

        ImgLookAdapter adapter=new ImgLookAdapter(getSupportFragmentManager(),this,fragmentList);
        acImgLookVp.setAdapter(adapter);
        acImgLookVp.setCurrentItem(position-1);

        acImgLookVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                acImgLookTvIndex.setText((i+1)+"/"+imgs.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
