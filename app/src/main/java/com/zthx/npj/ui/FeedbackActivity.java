package com.zthx.npj.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.FeedBackBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;
import okhttp3.MediaType;

public class FeedbackActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_feedBack_tv_title1)
    TextView acFeedBackTvTitle1;
    @BindView(R.id.ac_feedBack_tv_title2)
    TextView acFeedBackTvTitle2;
    @BindView(R.id.ac_feedBack_tv_title3)
    TextView acFeedBackTvTitle3;
    @BindView(R.id.ac_feedBack_tv_title4)
    TextView acFeedBackTvTitle4;
    @BindView(R.id.ac_feedBack_tv_title5)
    TextView acFeedBackTvTitle5;
    @BindView(R.id.ac_feedBack_tv_title6)
    TextView acFeedBackTvTitle6;
    @BindView(R.id.ac_feedBack_et_description)
    EditText acFeedBackEtDescription;
    @BindView(R.id.ac_feedBack_et_realname)
    EditText acFeedBackEtRealname;
    @BindView(R.id.ac_feedBack_et_mobile)
    EditText acFeedBackEtMobile;
    @BindView(R.id.ac_feedBack_btn_commit)
    Button acFeedBackBtnCommit;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String title = "";
    @BindView(R.id.ac_feedBack_zib_pingzheng)
    ZzImageBox acFeedBackZibPingzheng;

    private List<String> paths=new ArrayList<>();
    private static final int CHOOSE_PHOTO=1;
    private TextView[] tvs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        titleThemeTitle.setText("评价反馈");
        back(titleThemeBack);

         tvs=new TextView[] {acFeedBackTvTitle1, acFeedBackTvTitle2, acFeedBackTvTitle3, acFeedBackTvTitle4, acFeedBackTvTitle5, acFeedBackTvTitle6};

        acFeedBackZibPingzheng.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                acFeedBackZibPingzheng.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,CHOOSE_PHOTO);
            }
        });
    }



    private void itemSelect(TextView tv) {
        for (int i = 0; i < tvs.length; i++) {
            if ((tv.getText().toString()).equals(tvs[i].getText().toString())) {
                title = tv.getText().toString();
                tv.setTextColor(getResources().getColor(android.R.color.white));
                tv.setBackgroundColor(getResources().getColor(R.color.app_theme));
            }else{
                tvs[i].setTextColor(getResources().getColor(R.color.text3));
                tvs[i].setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    Uri selectImg=data.getData();
                    String[] filepathColum={MediaStore.Images.Media.DATA};
                    Cursor cursor= getContentResolver().query(selectImg,filepathColum,null,null,null);
                    cursor.moveToFirst();
                    int cursorIndex=cursor.getColumnIndex(filepathColum[0]);
                    String path=cursor.getString(cursorIndex);
                    cursor.close();
                    paths.add(path);
                    acFeedBackZibPingzheng.addImage(path);
                }
                break;
        }
    }

    @OnClick({R.id.ac_feedBack_tv_title1, R.id.ac_feedBack_tv_title2, R.id.ac_feedBack_tv_title3, R.id.ac_feedBack_tv_title4, R.id.ac_feedBack_tv_title5, R.id.ac_feedBack_tv_title6, R.id.ac_feedBack_btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_feedBack_tv_title1:
                itemSelect(acFeedBackTvTitle1);
                break;
            case R.id.ac_feedBack_tv_title2:
                itemSelect(acFeedBackTvTitle2);
                break;
            case R.id.ac_feedBack_tv_title3:
                itemSelect(acFeedBackTvTitle3);
                break;
            case R.id.ac_feedBack_tv_title4:
                itemSelect(acFeedBackTvTitle4);
                break;
            case R.id.ac_feedBack_tv_title5:
                itemSelect(acFeedBackTvTitle5);
                break;
            case R.id.ac_feedBack_tv_title6:
                itemSelect(acFeedBackTvTitle6);
                break;
            case R.id.ac_feedBack_btn_commit:
                FeedBackBean bean = new FeedBackBean();
                bean.setUser_id(user_id);
                bean.setTitle(token);
                bean.setTitle(title);
                bean.setDescription(acFeedBackEtDescription.getText().toString().trim());
                bean.setImg("");
                bean.setRealname(acFeedBackEtRealname.getText().toString().trim());
                bean.setMobile(acFeedBackEtMobile.getText().toString().trim());
                SetSubscribe.feedBack(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
        }
    }
}
