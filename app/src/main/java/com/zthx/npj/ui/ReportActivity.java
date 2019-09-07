package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.ReportBean;
import com.zthx.npj.net.been.ReportResponseBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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

    private String reason="";
    private String user_id="";
    private String img="";
    private static final int CHOOSE_PHOTO=1;

    private List<String> paths=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "举报");

        reason=getIntent().getStringExtra("key0");
        user_id=getIntent().getStringExtra("key1");
        acReportTvReason.setText("举报理由："+reason);

        acReportZibImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                acReportZibImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
    }

    @OnClick(R.id.ac_report_btn_commit)
    public void onViewClicked() {
        if(paths.size()==1){
            HttpUtils.uploadImg(URLConstant.REQUEST_URL, paths.get(0), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UpLoadPicResponseBean bean=GsonUtils.fromJson(response.body().string(),UpLoadPicResponseBean.class);
                    img=bean.getData().getSrc();
                    uploadData();
                }
            });
        }else if(paths.size()>=2){
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean=GsonUtils.fromJson(response.body().string(),UploadPicsResponseBean.class);
                    img=bean.getData().getImg();
                    uploadData();
                }
            });
        }else{
            showToast("请选择图片");
        }

    }

    private void uploadData() {
        ReportBean bean=new ReportBean();
        String scribe=acReportEtScribe.getText().toString().trim();
        String phone=acReportEtPhone.getText().toString().trim();
        bean.setTitle(reason);
        bean.setUser_id(user_id);
        bean.setDescription(scribe);
        bean.setMobile(phone);
        bean.setImg(img);
        SetSubscribe.report(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ReportResponseBean bean=GsonUtils.fromJson(result,ReportResponseBean.class);
                if(bean.getData().getStatus()==1){
                    showToast("举报成功");
                }else{
                    showToast("举报失败");
                }
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        paths.add(path);
                        acReportZibImg.addImage(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
