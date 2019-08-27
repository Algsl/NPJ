package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class ReportActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_report_tv_reason)
    TextView acReportTvReason;
    @BindView(R.id.ac_report_et_scribe)
    EditText acReportEtScribe;
    @BindView(R.id.ac_report_zib_img)
    ZzImageBox acReportZibImg;
    @BindView(R.id.ac_report_et_phone)
    EditText acReportEtPhone;
    @BindView(R.id.ac_report_btn_commit)
    Button acReportBtnCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "举报");

        String reason=getIntent().getStringExtra("key0");
        acReportTvReason.setText("举报理由："+reason);

        acReportZibImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {

            }

            @Override
            public void onAddClick() {

            }
        });
    }

    @OnClick(R.id.ac_report_btn_commit)
    public void onViewClicked() {
        String scribe=acReportEtScribe.getText().toString().trim();
        String phone=acReportEtPhone.getText().toString().trim();
        showToast(scribe+" "+phone);
    }
}
