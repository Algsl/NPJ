package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.QuotationPeopleListAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreQuotationListActivity extends AppCompatActivity {

    @BindView(R.id.at_store_quotation_rv)
    RecyclerView atStoreQuotationRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_quotation_list);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        atStoreQuotationRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        QuotationPeopleListAdapter mAdapter = new QuotationPeopleListAdapter(this,list);
        mAdapter.setOnItemClickListener(new QuotationPeopleListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(StoreQuotationListActivity.this, QuotationPeopleDetailActivity.class));
            }
        });
        atStoreQuotationRv.setAdapter(mAdapter);
    }
}
