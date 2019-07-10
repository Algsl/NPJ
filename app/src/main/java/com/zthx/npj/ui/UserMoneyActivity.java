package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zthx.npj.R;

import java.util.ArrayList;

public class UserMoneyActivity extends AppCompatActivity {

    private String group_list;
    private ArrayList<String> child_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_money);


    }
}
