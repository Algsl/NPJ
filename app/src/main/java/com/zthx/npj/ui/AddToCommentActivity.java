package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class AddToCommentActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_addToComment_mcv_pic)
    MyCircleView acAddToCommentMcvPic;
    @BindView(R.id.ac_addToComment_tv_name)
    TextView acAddToCommentTvName;
    @BindView(R.id.ac_addToComment_iv_star1)
    ImageView acAddToCommentIvStar1;
    @BindView(R.id.ac_addToComment_iv_star2)
    ImageView acAddToCommentIvStar2;
    @BindView(R.id.ac_addToComment_iv_star3)
    ImageView acAddToCommentIvStar3;
    @BindView(R.id.ac_addToComment_iv_star4)
    ImageView acAddToCommentIvStar4;
    @BindView(R.id.ac_addToComment_iv_star5)
    ImageView acAddToCommentIvStar5;
    @BindView(R.id.ac_addToComment_ll_star)
    LinearLayout acAddToCommentLlStar;
    @BindView(R.id.ac_addToComment_tv_content)
    TextView acAddToCommentTvContent;
    @BindView(R.id.item_custom_comment_rv)
    RecyclerView itemCustomCommentRv;
    @BindView(R.id.ac_addToComment_tv_content1)
    TextView acAddToCommentTvContent1;
    @BindView(R.id.item_custom_comment_rv1)
    RecyclerView itemCustomCommentRv1;
    @BindView(R.id.item_custom_zp)
    LinearLayout itemCustomZp;
    @BindView(R.id.ac_addToComment_tv_date)
    TextView acAddToCommentTvDate;
    @BindView(R.id.ac_addToComment_tv_xiaofei)
    TextView acAddToCommentTvXiaofei;
    @BindView(R.id.ac_addToComment_et_content)
    EditText acAddToCommentEtContent;
    @BindView(R.id.ac_addToComment_iv_img)
    ZzImageBox acAddToCommentIvImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_comment);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"评价管理");


    }

    @OnClick(R.id.title_theme_tv_right)
    public void onViewClicked() {

    }
}
