package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.LocalSpokesmanAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalSpokesmanActivity extends AppCompatActivity {

    @BindView(R.id.at_local_spokesman_rv)
    RecyclerView atLocalSpokesmanRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_spokesman);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        atLocalSpokesmanRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        LocalSpokesmanAdapter mAdapter = new LocalSpokesmanAdapter(this,list);
        atLocalSpokesmanRv.setAdapter(mAdapter);
    }
}
