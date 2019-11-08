package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.EditOfflineStoreBean;
import com.zthx.npj.net.been.MyOfflineStoreResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

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

public class EditMyOfflineStoreActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_storeManager_et_storeName)
    EditText acStoreManagerEtStoreName;
    @BindView(R.id.ac_storeManager_et_consumption)
    EditText acStoreManagerEtConsumption;
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
    @BindView(R.id.ac_storeManager_aBegin)
    TextView acStoreManagerABegin;
    @BindView(R.id.ac_storeManager_aEnd)
    TextView acStoreManagerAEnd;
    @BindView(R.id.ac_storeManager_pBegin)
    TextView acStoreManagerPBegin;
    @BindView(R.id.ac_storeManager_pEnd)
    TextView acStoreManagerPEnd;
    @BindView(R.id.ac_storeManager_llEdit)
    LinearLayout acStoreManagerLlEdit;
    @BindView(R.id.ac_storeManager_tv_businessHours)
    TextView acStoreManagerTvBusinessHours;
    @BindView(R.id.ac_storeManager_llShow)
    LinearLayout acStoreManagerLlShow;
    @BindView(R.id.at_location_store_tv_ruzhu1)
    TextView atLocationStoreTvRuzhu1;
    private List<String> paths = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();
    private List<String> paths3 = new ArrayList<>();
    private String is_open = "1";
    private static final int CHOOSE_PHOTO = 2;
    private String store_id = "";
    private String offer = "";
    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private String yingyeTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "商家管理");
        atLocationStoreTvRuzhu.setText("管理");

        getMyOfflineStore();
        initList();

        atLocationStoreTvRuzhu1.setVisibility(View.VISIBLE);


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
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);*/
                ImageSelectorUtils.openPhoto(EditMyOfflineStoreActivity.this,CHOOSE_PHOTO,false,4-paths.size());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getMyOfflineStore() {
        SetSubscribe.myOfflineStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOfflineStore(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
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
        acStoreManagerTvOffer.setText(data.getOffer());
        acStoreManagerEtRelife.setText(data.getRelief());
        offer = data.getOffer();
        is_open = data.getIs_open();
        if (is_open.equals("0")) {
            atLocationStoreTvRuzhu1.setText("开启");
        } else {
            atLocationStoreTvRuzhu1.setText("关闭");
        }
        store_id = data.getId() + "";
        for (int i = 0; i < data.getStore_img().size(); i++) {
            zzImageBox.addImageOnline(data.getStore_img().get(i));
            paths.add(data.getStore_img().get(i));
        }
        getComment();
    }

    private String getEtToString(TextView et) {
        return et.getText().toString().trim();
    }

    @OnClick({R.id.ac_storeManager_btn_ruzhu, R.id.at_store_manager_tv_code, R.id.at_location_store_tv_ruzhu, R.id.ac_storeManager_tv_address,
            R.id.ac_storeManager_aBegin, R.id.ac_storeManager_aEnd, R.id.ac_storeManager_pBegin, R.id.ac_storeManager_pEnd, R.id.ac_storeManager_tv_businessHours,
            R.id.at_location_store_tv_ruzhu1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_store_manager_tv_code://收款码
                Intent intent1 = new Intent(EditMyOfflineStoreActivity.this, StoreManagerQRCodeActivity.class);
                intent1.putExtra("img", data.getStore_img().get(0));
                intent1.putExtra("store_id", store_id);
                intent1.putExtra("offer", data.getOffer());
                startActivityForResult(intent1, 3);
                break;
            case R.id.ac_storeManager_btn_ruzhu:
                for (String str : paths) {
                    if (str.split("http://app.npj-vip.com").length == 1) {
                        paths2.add(str);
                    } else {//解析全链接
                        paths3.add(str.split("http://app.npj-vip.com")[1]);
                    }
                }
                if (paths2.size() == 0) {//没有上传新的图片
                    String paths3Str = "";
                    for (int i = 0; i < paths3.size(); i++) {
                        if (i == paths3.size() - 1) {
                            paths3Str += paths3.get(i);
                        } else {
                            paths3Str += paths3.get(i) + ",";
                        }
                    }
                    offlineStore(paths3Str);
                } else {//上传了新的图片
                    HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths2, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                            UploadPicsResponseBean.DataBean data = bean.getData();
                            String paths3Str = "";
                            for (String str : paths3) {
                                paths3Str += str + ",";
                            }
                            offlineStore(paths3Str + data.getImg());
                        }
                    });
                }
                break;
            case R.id.at_location_store_tv_ruzhu:
                openActivity(StoreManagerCenterActivity.class);
                break;
            case R.id.at_location_store_tv_ruzhu1:
                toggle();
                /*if (is_open.equals("0")) {
                    MainSubscribe.openStore(user_id, "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("店铺开启成功");
                            atLocationStoreTvRuzhu1.setText("关闭");
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                } else {
                    MainSubscribe.openStore(user_id, "0", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("店铺关闭成功");
                            atLocationStoreTvRuzhu1.setText("开启");
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }*/
                break;
            case R.id.ac_storeManager_tv_address:
                Intent intent = new Intent(this, MapAddressActivity.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.ac_storeManager_aBegin:
                showCityPicker(acStoreManagerABegin);
                break;
            case R.id.ac_storeManager_aEnd:
                showCityPicker(acStoreManagerAEnd);
                break;
            case R.id.ac_storeManager_pBegin:
                showCityPicker(acStoreManagerPBegin);
                break;
            case R.id.ac_storeManager_pEnd:
                showCityPicker(acStoreManagerPEnd);
                break;
            case R.id.ac_storeManager_tv_businessHours:
                acStoreManagerLlEdit.setVisibility(View.VISIBLE);
                acStoreManagerLlShow.setVisibility(View.GONE);
                break;
        }
    }

    private void toggle() {
        if(is_open.equals("0")){//关闭->开启
            is_open="1";
            MainSubscribe.openStore(user_id, "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    showToast("店铺开启成功");
                    atLocationStoreTvRuzhu1.setText("关闭");
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }else{//开启->关闭
            is_open="0";
            MainSubscribe.openStore(user_id, "0", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    showToast("店铺关闭成功");
                    atLocationStoreTvRuzhu1.setText("开启");
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }
    }

    private void offlineStore(String img) {
        yingyeTime = getEtToString(acStoreManagerABegin) + "-" + getEtToString(acStoreManagerAEnd) + " " + getEtToString(acStoreManagerPBegin) + "-" + getEtToString(acStoreManagerPEnd);
        EditOfflineStoreBean bean = new EditOfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStore_name(getEtToString(acStoreManagerEtStoreName));
        bean.setConsumption(getEtToString(acStoreManagerEtConsumption));
        bean.setBusiness_hours(yingyeTime);
        bean.setContact(getEtToString(acStoreManagerEtContact));
        bean.setAddress(acStoreManagerTvAddress.getText().toString());
        bean.setAddress2(acStoreManagerEtAddress2.getText().toString());
        bean.setOffer(offer);
        bean.setRelief(getEtToString(acStoreManagerEtRelife));
        bean.setStore_img(img);
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        bean.setId(store_id);
        SetSubscribe.editOfflineStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("修改成功");
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }


    private void getComment() {
        MainSubscribe.getStoreComment(data.getId() + "", "5", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if(resultCode!=0){
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i=0;i<images.size();i++){
                        paths.add(compress(images.get(i)));
                        zzImageBox.addImage(compress(images.get(i)));
                    }
                }
                break;
            case 1://地址返回值
                if (resultCode == 1) {
                    acStoreManagerTvAddress.setText(data.getStringExtra("address"));
                    acStoreManagerEtAddress2.setText(data.getStringExtra("addressDetail"));
                }
                break;
            case 3://收款码优惠比率返回值
                if (resultCode == 0) {

                } else if (resultCode == 1) {
                    acStoreManagerTvOffer.setText(data.getStringExtra("offer"));
                    offer = data.getStringExtra("offer");
                }
        }
    }

    private void setComment(String result) {
        CommentResponseBean bean = GsonUtils.fromJson(result, CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acStoreManagerRv.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(this, data);
        acStoreManagerRv.setItemAnimator(new DefaultItemAnimator());
        acStoreManagerRv.setAdapter(adapter);
    }

    private void initList() {
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                options1Items1.add("0" + i);
            } else {
                options1Items1.add(i + "");
            }

        }
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                options1Items2.add("0" + i);
            } else {
                options1Items2.add(i + "");
            }

        }
    }

    private void showCityPicker(final TextView tv) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options, View v) {
                //返回的分别是三个级别的选中位置
                //acMyWalletTvChooseTime.setText();
                tv.setText(options1Items1.get(options1) + ":" + options1Items2.get(options2));
            }
        }).setTitleText("时间选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK).setCyclic(true, true, true) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();

        /*TimePickerView tpv=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

            }
        }).setTitleText("时间选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK).build();*/

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
