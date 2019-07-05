package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditNicknameActivity extends AppCompatActivity {
    private long  lastClickTime=0l;
    private static final int FAST_CLICK_DELAY_TIME=1000;


    @BindView(R.id.ac_edit_nickname)
    EditText acEditNickname;
    @BindView(R.id.ac_save)
    Button   acSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nickname);
        ButterKnife.bind(this);
        final String type = getIntent().getStringExtra("type");
        if(type.equals("1")){
            acEditNickname.setHint("昵称");
        }else{
            acEditNickname.setHint("签名");
        }
        final String user_id = SharePerferenceUtils.getUserId(this);
        //final String token   =SharePerferenceUtils.getToken(this);
        final String token   ="1f27405d66fa30be262785b395b622a6";
        acSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(System.currentTimeMillis()-lastClickTime<FAST_CLICK_DELAY_TIME){
                    return;
                }
                lastClickTime=System.currentTimeMillis();
                final String title =acEditNickname.getText().toString();
                SetSubscribe.editNickname(user_id,token,type,title,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        startActivity(new Intent(EditNicknameActivity.this,SettingsActivity.class));
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
    }
}
