package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.GradViewAdapter;
import com.zthx.npj.net.been.InComeResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpokesmanRightsActivity extends ActivityBase {

    @BindView(R.id.at_spokesman_right_btn_tiqu)
    Button atSpokesmanRightBtnTiqu;
    @BindView(R.id.at_spokesman_rl_daiyanjiangli)
    RelativeLayout atSpokesmanRlDaiyanjiangli;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.at_spokesman_right_tv_jine)
    TextView atSpokesmanRightTvJine;
    @BindView(R.id.at_spokesman_right_tv_money)
    TextView atSpokesmanRightTvMoney;
    @BindView(R.id.at_spokesman_right_iv_go)
    ImageView atSpokesmanRightIvGo;
    @BindView(R.id.ac_spokesman_tv_ztPush)
    TextView acSpokesmanTvZtPush;
    @BindView(R.id.ac_spokesman_tv_jtPush)
    TextView acSpokesmanTvJtPush;
    @BindView(R.id.ac_spokesman_tv_allPush)
    TextView acSpokesmanTvAllPush;
    @BindView(R.id.ac_spokesman_tv_storeMoney)
    TextView acSpokesmanTvStoreMoney;
    @BindView(R.id.ac_spokesman_tv_reward)
    TextView acSpokesmanTvReward;
    @BindView(R.id.ac_spokesman_tv_extracted)
    TextView acSpokesmanTvExtracted;
    @BindView(R.id.ac_spokesman_rl_store)
    RelativeLayout acSpokesmanRlStore;
    @BindView(R.id.ac_spokesman_rl_tiqu)
    RelativeLayout acSpokesmanRlTiqu;
    @BindView(R.id.ac_spokesman_tv_mingxi)
    TextView acSpokesmanTvMingxi;
    @BindView(R.id.ac_spokesman_ll_myTeam)
    LinearLayout acSpokesmanLlMyTeam;

    private String shouyiAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spokesman_rights2);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "收益明细");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getInCome();
    }

    private void getInCome() {
        SetSubscribe.inCome(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setInCome(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setInCome(String result) {
        InComeResponseBean bean = GsonUtils.fromJson(result, InComeResponseBean.class);
        InComeResponseBean.DataBean data = bean.getData();
        shouyiAmount=data.getIncome_amount()+"";
        atSpokesmanRightTvMoney.setText(data.getIncome_amount() + "");
        acSpokesmanTvZtPush.setText(data.getZt_push() + "");
        acSpokesmanTvJtPush.setText(data.getJt_push() + "");
        acSpokesmanTvAllPush.setText(data.getAll_push() + "");
        acSpokesmanTvStoreMoney.setText(data.getStore_money() + "元");
        acSpokesmanTvReward.setText(data.getReward() + "元");
        acSpokesmanTvExtracted.setText(data.getExtracted() + "元");
    }

    @OnClick({R.id.at_spokesman_right_btn_tiqu, R.id.at_spokesman_rl_daiyanjiangli, R.id.ac_spokesman_rl_store, R.id.ac_spokesman_rl_tiqu, R.id.ac_spokesman_tv_mingxi,R.id.ac_spokesman_ll_myTeam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_spokesman_right_btn_tiqu:
                openActivity(SYWithDrawActivity.class,shouyiAmount);
                break;
            case R.id.at_spokesman_rl_daiyanjiangli:
                openActivity(VipJLActivity.class);
                break;
            case R.id.ac_spokesman_rl_store:
                openActivity(ShopLogActivity.class);
                break;
            case R.id.ac_spokesman_rl_tiqu:
                openActivity(TiQuActivity.class);
                break;
            case R.id.ac_spokesman_tv_mingxi:
                openActivity(InComeLogActivity.class);
                break;
            case R.id.ac_spokesman_ll_myTeam:
                openActivity(MyTeamActivity.class);
                break;
        }
    }
}
