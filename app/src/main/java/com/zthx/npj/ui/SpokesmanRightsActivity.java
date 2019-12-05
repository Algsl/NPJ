package com.zthx.npj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.net.been.InComeResponseBean;
import com.zthx.npj.net.been.OfflineLogBean;
import com.zthx.npj.net.been.OfflineLogResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.IOException;

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
    @BindView(R.id.ac_spokesman_ll_zt)
    LinearLayout acSpokesmanLlZt;
    @BindView(R.id.ac_spokesman_ll_jt)
    LinearLayout acSpokesmanLlJt;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_spokesman_tv_offLineMoney)
    TextView acSpokesmanTvOffLineMoney;
    @BindView(R.id.ac_spokesman_rl_offline)
    RelativeLayout acSpokesmanRlOffline;

    private String shouyiAmount;
    private String offlineResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spokesman_rights2);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "收益明细");

        getOfflineLog();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getInCome();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }

    private void getOfflineLog() {
        OfflineLogBean bean = new OfflineLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        SetSubscribe.offlineLog(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                offlineResult = result;
                OfflineLogResponseBean bean = GsonUtils.fromJson(result, OfflineLogResponseBean.class);
                OfflineLogResponseBean.DataBean data = bean.getData();
                acSpokesmanTvOffLineMoney.setText(data.getShouyi()+"元");
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
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
        shouyiAmount = data.getIncome_amount() + "";
        atSpokesmanRightTvMoney.setText(data.getIncome_amount() + "");
        acSpokesmanTvZtPush.setText(data.getZt_push() + "");
        acSpokesmanTvJtPush.setText(data.getJt_push() + "");
        acSpokesmanTvAllPush.setText(data.getAll_push() + "");
        acSpokesmanTvStoreMoney.setText(data.getStore_money() + "元");
        acSpokesmanTvReward.setText(data.getReward() + "元");
        acSpokesmanTvExtracted.setText(data.getExtracted() + "元");
    }

    @OnClick({R.id.at_spokesman_right_btn_tiqu, R.id.at_spokesman_rl_daiyanjiangli, R.id.ac_spokesman_rl_store,
            R.id.ac_spokesman_rl_tiqu, R.id.ac_spokesman_tv_mingxi, R.id.ac_spokesman_ll_myTeam,
            R.id.ac_spokesman_ll_zt, R.id.ac_spokesman_ll_jt,R.id.ac_spokesman_rl_offline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_spokesman_right_btn_tiqu:
                openActivity(SYWithDrawActivity.class, shouyiAmount);
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
            case R.id.ac_spokesman_ll_zt:
                openActivity(ZjdyrActivity.class, "1");
                break;
            case R.id.ac_spokesman_ll_jt:
                openActivity(ZjdyrActivity.class, "2");
                break;
            case R.id.ac_spokesman_rl_offline:
                openActivity(OfflineLogActivity.class);
                break;
        }
    }
}
