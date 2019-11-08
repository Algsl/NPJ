package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.zthx.npj.R;
import com.zthx.npj.adapter.HotSearchAdapter;
import com.zthx.npj.net.been.HistoryResponseBean;
import com.zthx.npj.net.been.HotSearchBean;
import com.zthx.npj.net.been.HotSearchResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends ActivityBase {

    @BindView(R.id.at_home_search_iv_back)
    ImageView atHomeSearchIvBack;
    @BindView(R.id.at_home_search_et_search)
    EditText atHomeSearchEtSearch;
    @BindView(R.id.at_home_search_tv_search)
    TextView atHomeSearchTvSearch;
    @BindView(R.id.ac_homeSearch_iv_delHistory)
    ImageView acHomeSearchIvDelHistory;
    @BindView(R.id.ac_homeSearch_ll1)
    LinearLayout acHomeSearchLl1;
    @BindView(R.id.ac_homeSearch_lv_history)
    LabelsView acHomeSearchLvHistory;
    @BindView(R.id.ac_homeSearch_rv_hot)
    LabelsView acHomeSearchRvHot;

    private String user_id = SharePerferenceUtils.getUserId(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        back(atHomeSearchIvBack);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSearchHistory();
        getHotSearch();
    }

    private void getHotSearch() {
        MainSubscribe.hotSearch(new HotSearchBean(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setHotSearch(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setHotSearch(String result) {
        final HotSearchResponseBean bean = GsonUtils.fromJson(result, HotSearchResponseBean.class);
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < bean.getData().size(); i++) {
            labels.add(bean.getData().get(i).getTitle());
        }
        acHomeSearchRvHot.setLabels(labels);
        acHomeSearchRvHot.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                openActivity(SearchResultActivity.class, labelText);
            }
        });
    }

    private void getSearchHistory() {
        MainSubscribe.history(user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSearchHistory(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast("用户未登录");
            }
        }));
    }

    private void setSearchHistory(String result) {
        final HistoryResponseBean bean = GsonUtils.fromJson(result, HistoryResponseBean.class);
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < bean.getData().size(); i++) {
            labels.add(bean.getData().get(i).getTitle());
        }
        acHomeSearchLvHistory.setLabels(labels);
        acHomeSearchLvHistory.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                openActivity(SearchResultActivity.class, labelText);
            }
        });
    }

    @OnClick({R.id.at_home_search_tv_search, R.id.ac_homeSearch_iv_delHistory, R.id.at_home_search_et_search})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_homeSearch_iv_delHistory:
                delHistory();
                break;
            case R.id.at_home_search_tv_search:
                String searchTitle = atHomeSearchEtSearch.getText().toString().trim();
                getSearchResult(searchTitle);
                atHomeSearchEtSearch.setHint(searchTitle);
                break;
            case R.id.at_home_search_et_search:
                atHomeSearchEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            String searchTitle = atHomeSearchEtSearch.getText().toString().trim();
                            openActivity(SearchResultActivity.class, searchTitle);
                        }
                        return true;
                    }
                });
                break;
        }
    }

    private void delHistory() {
        MainSubscribe.delHistory(user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                getSearchHistory();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    public void getSearchResult(String str) {
        openActivity(SearchResultActivity.class, str);
    }


}
