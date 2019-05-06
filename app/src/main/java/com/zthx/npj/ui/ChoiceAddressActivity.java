package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ChoiceAddressAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoiceAddressActivity extends AppCompatActivity {

    @BindView(R.id.at_choice_address_rv)
    RecyclerView atChoiceAddressRv;
    @BindView(R.id.at_choice_address_btn_add)
    Button atChoiceAddressBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_address);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        atChoiceAddressRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        ChoiceAddressAdapter mAdpter = new ChoiceAddressAdapter(this,list);
        atChoiceAddressRv.setAdapter(mAdpter);
    }

    @OnClick(R.id.at_choice_address_btn_add)
    public void onViewClicked() {
        startActivity(new Intent(this, AddAddressActivity.class));
    }
}
