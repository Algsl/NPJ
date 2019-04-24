package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.PreSellAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreSellActivity extends AppCompatActivity {

    @BindView(R.id.at_pre_sell_iv)
    ImageView atPreSellIv;
    @BindView(R.id.at_pre_sell_rv)
    RecyclerView atPreSellRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sell);
        ButterKnife.bind(this);
        LinearLayoutManager ll = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        atPreSellRv.setLayoutManager(ll);
        List<CommentGoodsBeen> list = new ArrayList<>();
        CommentGoodsBeen been = new CommentGoodsBeen();
        been.setTotalNum("2332");
        been.setGoodsTitle("德国进口正品西门子蒸馏机");
        been.setGoodsPrice("200");
        been.setGoodsSellNum("23");
        for (int i =0;i<10;i++) {
            list.add(been);
        }
        PreSellAdapter adapter = new PreSellAdapter(this,list);
        adapter.setOnItemClickListener(new PreSellAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        atPreSellRv.setAdapter(adapter);

    }
}
