package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.AttentionResponseBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.EditOfflineStoreBean;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.been.OfflineStoreBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.SimpleUtil;

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

public class EditMyOfflineStoreActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
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
    @BindView(R.id.iv_zhekou)
    ImageView ivZhekou;
    @BindView(R.id.tv_malv)
    TextView tvMalv;
    @BindView(R.id.ac_storeManager_tv_offer)
    TextView acStoreManagerTvOffer;
    @BindView(R.id.at_store_manager_tv_code)
    TextView atStoreManagerTvCode;
    @BindView(R.id.ac_storeManager_et_relife)
    EditText acStoreManagerEtRelife;
    @BindView(R.id.zz_image_box)
    ZzImageBox zzImageBox;
    @BindView(R.id.ac_storeManager_btn_ruzhu)
    Button acStoreManagerBtnRuzhu;
    @BindView(R.id.ac_store_manager_rv)
    RecyclerView acStoreManagerRv;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MyOfflineStoreResponseBean.DataBean data;
    private List<String> paths = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();
    private List<String> paths3 = new ArrayList<>();
    private String is_open="1";
    private static final int CHOOSE_PHOTO = 2;

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"商家管理");
        atLocationStoreTvRuzhu.setText("管理");


        zzImageBox.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(EditMyOfflineStoreActivity.this).load(url).into(iv);
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
        getMyOfflineStore();
    }

    private void getMyOfflineStore() {
        SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOfflineStore(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setMyOfflineStore(String result) {
        MyOfflineStoreResponseBean bean = GsonUtils.fromJson(result, MyOfflineStoreResponseBean.class);
        data = bean.getData();
        acStoreManagerEtStoreName.setText(data.getStore_name());
        acStoreManagerEtConsumption.setText(data.getConsumption());
        acStoreManagerTvBusinessHours.setText(data.getBusiness_hours());
        acStoreManagerEtContact.setText(data.getContact());
        acStoreManagerTvAddress.setText(data.getAddress());
        acStoreManagerEtAddress2.setText(data.getAddress2());
        acStoreManagerTvOffer.setText(data.getOffer()+"%");
        acStoreManagerEtRelife.setText(data.getRelief());
        is_open=data.getIs_open();
        for(int i=0;i<data.getStore_img().size();i++){
            zzImageBox.addImageOnline(data.getStore_img().get(i));
            paths.add(data.getStore_img().get(i));
        }
        getComment();
    }

    private String getEtToString(EditText et) {
        return et.getText().toString().trim();
    }

    @OnClick({R.id.ac_storeManager_btn_ruzhu,R.id.at_store_manager_tv_code,R.id.at_location_store_tv_ruzhu})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.at_store_manager_tv_code:
                Intent intent1=new Intent(EditMyOfflineStoreActivity.this,StoreManagerQRCodeActivity.class);
                intent1.putExtra("img",data.getStore_img().get(0));
                startActivityForResult(intent1,3);
                break;
            case R.id.ac_storeManager_btn_ruzhu:
                for(String str:paths){
                    if(str.split("http://app.npj-vip.com").length==1){
                        paths2.add(str);
                    }else{
                        paths3.add(str.split("http://app.npj-vip.com")[1]);
                    }
                }
                HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        Log.e("测试", "onResponse: "+data.getImages() +" "+data.getImg() );
                        String paths3Str="";
                        for(String str:paths3){
                           paths3Str+=str+",";
                        }
                        offlineStore(paths3Str+data.getImg());
                    }
                });
                break;
            case R.id.at_location_store_tv_ruzhu:
                showItemPopwindow();
                break;
        }
    }

    private void offlineStore(String img) {
        OfflineStoreBean bean = new OfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStore_name(getEtToString(acStoreManagerEtStoreName));
        bean.setConsumption(getEtToString(acStoreManagerEtConsumption));
        bean.setBusiness_hours("9-12 2-6");
        bean.setContact(getEtToString(acStoreManagerEtContact));
        bean.setAddress(acStoreManagerTvAddress.getText().toString());
        bean.setAddress2(acStoreManagerEtAddress2.getText().toString());
        bean.setOffer(acStoreManagerTvOffer.getText().toString());
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
                showToast(errorMsg);
            }
        }));
    }


    private void getComment() {
        MainSubscribe.getStoreComment(data.getId()+"","5",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
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
                break;
            case 1:
                if(resultCode==1){
                    acStoreManagerTvAddress.setText(data.getStringExtra("address"));
                    acStoreManagerEtAddress2.setText(data.getStringExtra("addressDetail"));
                }
                break;
            case 3:
                if(resultCode==0){

                }else if(resultCode==1){
                    acStoreManagerTvOffer.setText(data.getStringExtra("offer"));
                }
        }
    }

    private void setComment(String result) {
        CommentResponseBean bean=GsonUtils.fromJson(result,CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data=bean.getData();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acStoreManagerRv.setLayoutManager(layoutManager);
        CommentAdapter adapter=new CommentAdapter(this,data);
        acStoreManagerRv.setItemAnimator(new DefaultItemAnimator());
        acStoreManagerRv.setAdapter(adapter);
    }

    public void showItemPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_manage, null);
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_90));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_100));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 0, 0);

        TextView manageCenter=contentView.findViewById(R.id.pw_storeManager_tv_managerCenter);
        final TextView openStore=contentView.findViewById(R.id.pw_storeManager_tv_openStore);

        manageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(StoreManagerCenterActivity.class);
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        if(is_open.equals("0")){
            openStore.setText("开启店铺");
        }else{
            openStore.setText("关闭店铺");
        }
        openStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_open.equals("0")){
                    MainSubscribe.openStore(user_id,"1",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {

                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }else{
                    MainSubscribe.openStore(user_id,"0",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {

                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
