package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.OfflineStoreBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

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

public class StoreManagerActivity extends ActivityBase {

    @BindView(R.id.at_store_manager_tv_code)
    TextView atStoreManagerTvCode;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.iv_zhekou)
    ImageView ivZhekou;
    @BindView(R.id.tv_malv)
    TextView tvMalv;
    @BindView(R.id.ac_storeManager_btn_ruzhu)
    Button acStoreManagerBtnRuzhu;
    @BindView(R.id.ac_storeManager_et_storeName)
    EditText acStoreManagerEtStoreName;
    @BindView(R.id.ac_storeManager_et_consumption)
    EditText acStoreManagerEtConsumption;
    @BindView(R.id.ac_storeManager_tv_businessHours)
    TextView acStoreManagerTvBusinessHours;
    @BindView(R.id.ac_storeManager_et_contact)
    EditText acStoreManagerEtContact;
    @BindView(R.id.ac_storeManager_tv_address)
    TextView acStoreManagerTvAddress;
    @BindView(R.id.ac_storeManager_et_address2)
    EditText acStoreManagerEtAddress2;
    @BindView(R.id.ac_storeManager_tv_offer)
    TextView acStoreManagerTvOffer;
    @BindView(R.id.ac_storeManager_et_relife)
    EditText acStoreManagerEtRelife;
    @BindView(R.id.zz_image_box)
    ZzImageBox zzImageBox;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_store_manager_rv)
    RecyclerView acStoreManagerRv;
    private List<String> paths = new ArrayList<>();
    private static final int CHOOSE_PHOTO = 1;
    private String store_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);

        back(titleBack);


        if (getIntent().getStringExtra("key0").equals(null)) {
            changeTitle(acTitle,"线下门店入驻");
        } else {
            store_id=getIntent().getStringExtra("key0");
            changeTitle(acTitle,"门店管理");
            getComment();
        }

        zzImageBox.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                zzImageBox.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
    }

    private void getComment() {
        MainSubscribe.getStoreComment(store_id,"5",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setComment(String result) {
        String test="{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 50,\n" +
                "            \"user_id\": 25,\n" +
                "            \"goods_id\": 1,\n" +
                "            \"store_id\": 23,\n" +
                "            \"content\": \"商品质量非常好，期待下次合作\",\n" +
                "            \"img\": [\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\",\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\"\n" +
                "            ],\n" +
                "            \"status\": 0,\n" +
                "            \"create_time\": 1556095903,\n" +
                "            \"type\": 1,\n" +
                "            \"goods_star\": 5,\n" +
                "            \"logistics_star\": 5,\n" +
                "            \"service_star\": 5,\n" +
                "            \"nick_name\": \"用户15853102073\",\n" +
                "            \"head_img\": \"http://app.npj-vip.com/public/upload/20190711/80d23137c98abfb897db59eb918b892e.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"msg\": \"加载成功\"\n" +
                "}";
        CommentResponseBean bean=GsonUtils.fromJson(test,CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acStoreManagerRv.setLayoutManager(layoutManager);
        CommentAdapter adapter=new CommentAdapter(this,data);
        acStoreManagerRv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columnIndex);  //获取照片路径
                    paths.add(path);
                    zzImageBox.addImage(path);
                }
        }
    }

    @OnClick({R.id.at_store_manager_tv_code, R.id.ac_storeManager_btn_ruzhu, R.id.ac_storeManager_tv_address})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.at_store_manager_tv_code:
                startActivity(new Intent(StoreManagerActivity.this, StoreManagerQRCodeActivity.class));
                break;
            case R.id.ac_storeManager_btn_ruzhu:
                HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL, paths, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        offlineStore(data.getImg());
                    }
                });
                break;
            case R.id.ac_storeManager_tv_address:

                break;
        }
    }

    public String getEtToString(EditText et) {
        return et.getText().toString().trim();
    }

    private void offlineStore(String img) {
        OfflineStoreBean bean = new OfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStore_name(getEtToString(acStoreManagerEtStoreName));
        bean.setConsumption(getEtToString(acStoreManagerEtConsumption));
        bean.setBusiness_hours("9-12 2-6");
        bean.setContact(getEtToString(acStoreManagerEtContact));
        bean.setAddress("111");
        bean.setAddress2(getEtToString(acStoreManagerEtAddress2));
        bean.setOffer("10");
        bean.setRelief(getEtToString(acStoreManagerEtRelife));
        bean.setStore_img(img);
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        SetSubscribe.offlineStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


}
