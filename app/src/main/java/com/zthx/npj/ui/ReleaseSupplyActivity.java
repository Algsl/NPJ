package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.ui.SupplyMessageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseSupplyActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.fg_discover_supply_ll_caigou)
    LinearLayout fgDiscoverSupplyLlCaigou;
    @BindView(R.id.fg_discover_supply_ll_gongying)
    LinearLayout fgDiscoverSupplyLlGongying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_supply);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"发布供求");
    }

    @OnClick({R.id.fg_discover_supply_ll_caigou, R.id.fg_discover_supply_ll_gongying})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_discover_supply_ll_caigou:
                Intent intent = new Intent(this, SupplyMessageActivity.class);
                intent.putExtra(Const.SUPPLY_TYPE, 1);
                startActivity(intent);
                break;
            case R.id.fg_discover_supply_ll_gongying:
                Intent intent1 = new Intent(this, SupplyMessageActivity.class);
                intent1.putExtra(Const.SUPPLY_TYPE, 2);
                startActivity(intent1);
                break;
        }
    }
}
