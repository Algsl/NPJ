package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgricultureKnowledgeActivity extends AppCompatActivity {

    @BindView(R.id.at_ak_rv)
    RecyclerView atAkRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_knowledge);
        ButterKnife.bind(this);


    }
}
