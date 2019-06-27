package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.AKAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AkListResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureKnowledgeActivity extends AppCompatActivity {

    @BindView(R.id.at_ak_rv)
    RecyclerView atAkRv;
    @BindView(R.id.at_ak_iv_search)
    EditText atAkIvSearch;
    @BindView(R.id.at_ak_iv_back)
    ImageView atAkIvBack;
    @BindView(R.id.at_ak_iv_1)
    ImageView atAkIv1;
    @BindView(R.id.at_ak_tv_1)
    TextView atAkTv1;
    @BindView(R.id.at_ak_ll_1)
    LinearLayout atAkLl1;
    @BindView(R.id.at_ak_iv_2)
    ImageView atAkIv2;
    @BindView(R.id.at_ak_tv_2)
    TextView atAkTv2;
    @BindView(R.id.at_ak_ll_2)
    LinearLayout atAkLl2;
    @BindView(R.id.at_ak_iv_3)
    ImageView atAkIv3;
    @BindView(R.id.at_ak_tv_3)
    TextView atAkTv3;
    @BindView(R.id.at_ak_ll_3)
    LinearLayout atAkLl3;
    @BindView(R.id.at_ak_iv_4)
    ImageView atAkIv4;
    @BindView(R.id.at_ak_tv_4)
    TextView atAkTv4;
    @BindView(R.id.at_ak_ll_4)
    LinearLayout atAkLl4;

    private AKAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_knowledge);
        ButterKnife.bind(this);
        getData("1");
    }


    @OnClick({R.id.at_ak_ll_1, R.id.at_ak_ll_2, R.id.at_ak_ll_3, R.id.at_ak_ll_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_ak_ll_1:
                getData("1");
                changeBackground("1");
                break;
            case R.id.at_ak_ll_2:
                getData("2");
                changeBackground("2");
                break;
            case R.id.at_ak_ll_3:
                getData("3");
                changeBackground("3");
                break;
            case R.id.at_ak_ll_4:
                getData("4");
                changeBackground("4");
                break;
            case R.id.at_ak_iv_search:
                startActivity(new Intent(this, HomeSearchActivity.class));
                break;
        }
    }

    private void getData(String id) {
        DiscoverSubscribe.getKnowledgeList(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                AkListResponseBean akListResponseBean = GsonUtils.fromJson(result, AkListResponseBean.class);
                final ArrayList<AkListResponseBean.DataBean> data = akListResponseBean.getData();

                if (mAdapter != null) {
                    mAdapter.setNewData(data);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(AgricultureKnowledgeActivity.this, LinearLayoutManager.VERTICAL, false);
                    atAkRv.setLayoutManager(manager);
                    mAdapter = new AKAdapter(AgricultureKnowledgeActivity.this, data);
                    mAdapter.setOnItemClickListener(new AKAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(AgricultureKnowledgeActivity.this, AgricultureVideoMainActivity.class);
                            intent.putExtra(Const.VIDEO_ID, data.get(position).getId()+"");
                            startActivity(intent);
                        }
                    });
                }
                atAkRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },this));
    }

    private void changeBackground(String id) {
        atAkTv1.setBackgroundResource(R.color.text3);
        atAkLl1.setBackgroundResource(R.drawable.agriculture_profiser_gray);
        atAkTv2.setBackgroundResource(R.color.text3);
        atAkLl2.setBackgroundResource(R.drawable.yinanzazeng_gray);
        atAkTv3.setBackgroundResource(R.color.text3);
        atAkLl3.setBackgroundResource(R.drawable.new_teachkonowledge_gray);
        atAkTv4.setBackgroundResource(R.color.text3);
        atAkLl4.setBackgroundResource(R.drawable.everthing_knowledge);
        if ("1".equals(id)) {
            atAkTv1.setBackgroundResource(R.color.app_theme);
            atAkLl1.setBackgroundResource(R.drawable.agriculture_profiser_theme);
        } else if ("2".equals(id)) {
            atAkTv2.setBackgroundResource(R.color.app_theme);
            atAkLl2.setBackgroundResource(R.drawable.yinanzazheng_theme);
        } else if ("3".equals(id)) {
            atAkTv3.setBackgroundResource(R.color.app_theme);
            atAkLl3.setBackgroundResource(R.drawable.new_teachkonowledge_theme);
        } else {
            atAkTv4.setBackgroundResource(R.color.app_theme);
            atAkLl4.setBackgroundResource(R.drawable.everthing_knowledge_theme);
        }
    }
}
