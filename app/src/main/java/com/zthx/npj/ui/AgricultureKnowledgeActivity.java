package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.zthx.npj.R;
import com.zthx.npj.adapter.AKAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_knowledge);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atAkRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        AKAdapter mAdapter = new AKAdapter(this, list);
        mAdapter.setOnItemClickListener(new AKAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(AgricultureKnowledgeActivity.this, AgricultureVideoMainActivity.class));
            }
        });
        atAkRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.at_ak_iv_search)
    public void onViewClicked() {
        startActivity(new Intent(this, HomeSearchActivity.class));
    }
}
