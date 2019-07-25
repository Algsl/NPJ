package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaojiaUserDetailActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_baojia_detail_mv_headImg)
    MyCircleView acBaojiaDetailMvHeadImg;
    @BindView(R.id.ac_baojia_detail_tv_nickName)
    TextView acBaojiaDetailTvNickName;
    @BindView(R.id.ac_baojia_detail_tv_level)
    ImageView acBaojiaDetailTvLevel;
    @BindView(R.id.ac_baojia_detail_tv_day)
    TextView acBaojiaDetailTvDay;
    @BindView(R.id.item_quotation_people_ll)
    LinearLayout itemQuotationPeopleLl;
    @BindView(R.id.ac_baojia_detail_rv)
    RecyclerView acBaojiaDetailRv;

    String user_id=SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojia_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"商家报价");
        getBaojiaDetail();
    }

    private void getBaojiaDetail() {
        SetSubscribe.baojiaUserDetail(user_id,token,getIntent().getStringExtra("id"),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
