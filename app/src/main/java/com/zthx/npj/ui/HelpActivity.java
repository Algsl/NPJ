package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ProblemAdapter;
import com.zthx.npj.net.been.ProblemResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class HelpActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.at_help_tv_kefu_tel)
    TextView atHelpTvKefuTel;
    @BindView(R.id.at_help_tv_kefu_online)
    TextView atHelpTvKefuOnline;
    @BindView(R.id.at_help_tv_pingjia)
    TextView atHelpTvPingjia;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.ac_help_rv_problem1)
    RecyclerView acHelpRvProblem1;
    @BindView(R.id.ac_help_rv_problem2)
    RecyclerView acHelpRvProblem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"帮助与客服");

        getProblem("1");
        getProblem("2");
    }

    private void getProblem(final String type) {
        SetSubscribe.problem(user_id,token,type,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setProblem(result,type);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setProblem(String result,String type) {
        ProblemResponseBean bean= GsonUtils.fromJson(result,ProblemResponseBean.class);
        final ArrayList<ProblemResponseBean.DataBean> data=bean.getData();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        ProblemAdapter adapter=new ProblemAdapter(this,data);
        switch (type){
            case "1":
                acHelpRvProblem1.setLayoutManager(layoutManager);
                acHelpRvProblem1.setAdapter(adapter);
                break;
            case "2":
                acHelpRvProblem2.setLayoutManager(layoutManager);
                acHelpRvProblem2.setAdapter(adapter);
                break;
        }
        adapter.setOnItemClickListener(new ProblemAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(HelpActivity.this,HelpDetailActivity.class);
                intent.putExtra("id",data.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.title_theme_back, R.id.at_help_tv_kefu_tel, R.id.at_help_tv_kefu_online, R.id.at_help_tv_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_theme_back:
                break;
            case R.id.at_help_tv_kefu_tel:
                new CommonDialog(this, R.style.dialog, "400-800-1234", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                }).show();
                break;
            case R.id.at_help_tv_kefu_online:
                JMessageClient.login("18435224024", "18435224024", new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i==0){
                            openActivity(ServicesChatActivity.class);
                        }
                    }
                });
                break;
            case R.id.at_help_tv_pingjia:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
        }
    }

}
