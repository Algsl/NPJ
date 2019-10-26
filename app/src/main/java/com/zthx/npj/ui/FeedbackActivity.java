package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.FeedBackBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    @BindView(R.id.ac_feedBack_tv_number)
    TextView acFeedBackTvNumber;

    private List<String> paths = new ArrayList<>();
    private static final int CHOOSE_PHOTO = 1;
    private TextView[] tvs;
    private String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        titleThemeTitle.setText("评价反馈");
        back(titleThemeBack);

        tvs = new TextView[]{acFeedBackTvTitle1, acFeedBackTvTitle2, acFeedBackTvTitle3, acFeedBackTvTitle4, acFeedBackTvTitle5, acFeedBackTvTitle6};

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
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);*/
                ImageSelectorUtils.openPhoto(FeedbackActivity.this,CHOOSE_PHOTO,false,3);
            }
        });
    }


    private void itemSelect(TextView tv) {
        for (int i = 0; i < tvs.length; i++) {
            if ((tv.getText().toString()).equals(tvs[i].getText().toString())) {
                title = tv.getText().toString();
                tv.setTextColor(getResources().getColor(android.R.color.white));
                tv.setBackgroundResource(R.drawable.theme_conner_5);
            } else {
                tvs[i].setTextColor(getResources().getColor(R.color.text3));
                tvs[i].setBackgroundResource(R.drawable.stroke_gray_10);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                /*if (resultCode == RESULT_OK) {
                    Uri selectImg = data.getData();
                    String[] filepathColum = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectImg, filepathColum, null, null, null);
                    cursor.moveToFirst();
                    int cursorIndex = cursor.getColumnIndex(filepathColum[0]);
                    String path = cursor.getString(cursorIndex);
                    cursor.close();
                    paths.add(path);
                    acFeedBackZibPingzheng.addImage(path);
                }*/
                if(resultCode!=0){
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i=0;i<images.size();i++){
                        paths.add(compress(images.get(i)));
                        acFeedBackZibPingzheng.addImage(compress(images.get(i)));
                    }
                }
                break;
        }
    }

    @OnClick({R.id.ac_feedBack_tv_title1, R.id.ac_feedBack_tv_title2, R.id.ac_feedBack_tv_title3,
            R.id.ac_feedBack_tv_title4, R.id.ac_feedBack_tv_title5, R.id.ac_feedBack_tv_title6,
            R.id.ac_feedBack_btn_commit, R.id.ac_feedBack_et_description})
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
                if (title.equals("")) {
                    showToast("请选择遇到的问题");
                } else if (acFeedBackEtDescription.getText().toString().trim().equals("")) {
                    showToast("请填写问题详情");
                } else if (acFeedBackEtRealname.getText().toString().trim().equals("")) {
                    showToast("请选择联系人姓名");
                } else if (acFeedBackEtMobile.getText().toString().trim().equals("")) {
                    showToast("请填写联系方式");
                } else if (paths.size() <= 0) {
                    showToast("请上传凭证");
                } else {
                    HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                            img = bean.getData().getImg();
                            uploadData();
                        }
                    });
                }
                break;
            case R.id.ac_feedBack_et_description:
                acFeedBackEtDescription.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if(!acFeedBackEtDescription.getText().toString().trim().equals("")){
                            acFeedBackTvNumber.setText(acFeedBackEtDescription.getText().toString().trim().length()+"/200");
                        }else{
                            acFeedBackTvNumber.setText("0/200");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                break;
        }
    }

    public void uploadData() {
        FeedBackBean bean = new FeedBackBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setTitle(title);
        bean.setDescription(acFeedBackEtDescription.getText().toString().trim());
        bean.setImg(img);
        bean.setRealname(acFeedBackEtRealname.getText().toString().trim());
        bean.setMobile(acFeedBackEtMobile.getText().toString().trim());
        SetSubscribe.feedBack(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CommonDialog dialog = new CommonDialog(FeedbackActivity.this, R.style.dialog, "坚持真实的反馈，能够获得更多的葫芦币哦", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            finish();
                        }
                    }
                });
                dialog.setPositiveButton("关闭");
                dialog.setTitle("反馈成功");
                dialog.show();
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    public String compress(String path){
        File file=new File(path);
        Bitmap compressBitmap;
        /*if (file.length()>=2.5*1024*1024){//从相册中选择照片，2.5M以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 3;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else */if(file.length()>=600*1024){//从相册中选择照片，600k以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else{
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        compressBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        int options1 = 90;
        while (bos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bos.reset(); // 重置baos即清空baos
            compressBitmap.compress(Bitmap.CompressFormat.JPEG, options1, bos);// 这里压缩options%，把压缩后的数据存放到baos中
            options1 -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(bos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        compressBitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        String bmString="";
        try {
            File bmFile=new File(getExternalCacheDir(), System.currentTimeMillis()+".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString=bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
    }
}
