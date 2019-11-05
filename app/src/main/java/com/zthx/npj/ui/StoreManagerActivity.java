package com.zthx.npj.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.OfflineStoreBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @BindView(R.id.ac_storeManager_llShow)
    LinearLayout acStoreManagerLlShow;
    private List<String> paths = new ArrayList<>();
    private static final int CHOOSE_PHOTO = 2;
    private String store_id = "";

    private List<String> options1Items1 = new ArrayList<>();
    private List<String> options1Items2 = new ArrayList<>();
    private String yingyeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "线下门店入驻");

        initList();

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
                ImageSelectorUtils.openPhoto(StoreManagerActivity.this,CHOOSE_PHOTO,false,4-paths.size());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    /*Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columnIndex);  //获取照片路径
                    paths.add(path);
                    zzImageBox.addImage(path);*/
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i=0;i<images.size();i++){
                        paths.add(compress(images.get(i)));
                        zzImageBox.addImage(compress(images.get(i)));
                    }
                }
                break;
            case 1:
                if (resultCode == 1) {
                    acStoreManagerTvAddress.setText(data.getStringExtra("address"));
                    acStoreManagerEtAddress2.setText(data.getStringExtra("addressDetail"));
                }
                break;
            case 3:
                if (resultCode == 0) {

                } else if (resultCode == 1) {
                    acStoreManagerTvOffer.setText(data.getStringExtra("offer"));
                }
        }
    }

    @OnClick({R.id.at_store_manager_tv_code, R.id.ac_storeManager_btn_ruzhu, R.id.ac_storeManager_tv_address,
            R.id.ac_storeManager_aBegin, R.id.ac_storeManager_aEnd, R.id.ac_storeManager_pBegin, R.id.ac_storeManager_pEnd})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.at_store_manager_tv_code:
                /*Intent intent1 = new Intent(StoreManagerActivity.this, StoreManagerQRCodeActivity.class);
                startActivityForResult(intent1, 3);*/
                break;
            case R.id.ac_storeManager_btn_ruzhu:
                HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        Log.e("测试", "onResponse: " + data.getImages() + " " + data.getImg());
                        offlineStore(data.getImg());
                    }
                });
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
        }
    }


    public String getEtToString(EditText et) {
        return et.getText().toString().trim();
    }

    private void offlineStore(String img) {
        yingyeTime=getTvString(acStoreManagerABegin)+"-"+getTvString(acStoreManagerAEnd)+" "+getTvString(acStoreManagerPBegin)+"-"+getTvString(acStoreManagerPEnd);
        OfflineStoreBean bean = new OfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStore_name(getEtToString(acStoreManagerEtStoreName));
        bean.setConsumption(getEtToString(acStoreManagerEtConsumption));
        bean.setBusiness_hours(yingyeTime);
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
                showToast("线下门店信息上传成功");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void initList() {
        for (int i = 0; i < 24; i++) {
            if (i <10) {
                options1Items1.add("0" + i );
            } else {
                options1Items1.add(i + "");
            }

        }
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                options1Items2.add("0" + i);
            } else {
                options1Items2.add(i+ "");
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
        }).setTitleText("日期选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK).setCyclic(true,true,true) //设置选中项文字颜色.setContentTextSize(20)
                .build();
        pvOptions.setNPicker(options1Items1, options1Items2, null);
        pvOptions.show();
    }

    private String getTvString(TextView tv){
        return tv.getText().toString().trim();
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
