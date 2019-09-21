package com.zthx.npj.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadCaigouBean;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PurchaserCertification2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_purchaser_certification2_et_mobile)
    EditText atPurchaserCertification2EtMobile;
    @BindView(R.id.at_purchaser_certification2_et_id)
    TextView atPurchaserCertification2EtId;
    @BindView(R.id.at_purchaser_certification2_et_company_name)
    EditText atPurchaserCertification2EtCompanyName;
    @BindView(R.id.at_purchaser_certification2_et_address)
    TextView atPurchaserCertification2EtAddress;
    @BindView(R.id.at_purchaser_certification2_ll_name)
    LinearLayout atPurchaserCertification2LlName;
    @BindView(R.id.at_purchaser_certification2_et_dangkou_name)
    EditText atPurchaserCertification2EtDangkouName;
    @BindView(R.id.at_purchaser_certification2_et_shichang_name)
    TextView atPurchaserCertification2EtShichangName;
    @BindView(R.id.at_purchaser_certification2_et_dangkou_address)
    EditText atPurchaserCertification2EtDangkouAddress;
    @BindView(R.id.at_purchaser_certification2_ll_dangkou)
    LinearLayout atPurchaserCertification2LlDangkou;
    @BindView(R.id.at_purchaser_certification2_rl_pic)
    RelativeLayout atPurchaserCertification2RlPic;
    @BindView(R.id.at_purchaser_certification2_tv_dangkou_pic)
    TextView atPurchaserCertification2TvDangkouPic;
    @BindView(R.id.at_purchaser_certification2_tv_dangkou_id_card)
    TextView atPurchaserCertification2TvDangkouIdCard;
    @BindView(R.id.at_purchaser_certification2_ll_dangkou_pic)
    LinearLayout atPurchaserCertification2LlDangkouPic;
    @BindView(R.id.at_purchaser_certification2_rl_fei_dangkou_pic)
    RelativeLayout atPurchaserCertification2RlFeiDangkouPic;
    @BindView(R.id.at_purchaser_certification2_btn_confirm)
    Button atPurchaserCertification2BtnConfirm;
    @BindView(R.id.at_purchaser_certification2_tv_tuijian)
    TextView atPurchaserCertification2TvTuijian;
    @BindView(R.id.at_purchaser_certification2_tv_dianjishangchuan)
    TextView atPurchaserCertification2TvDianjishangchuan;
    @BindView(R.id.at_purchaser_certification2_et_name)
    EditText atPurchaserCertification2EtName;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_purchaser_certification_tv_hint)
    TextView acPurchaserCertificationTvHint;

    private int type;
    private int picType;
    private String path;
    private String img;
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private String cert_id="";
    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_certification2);
        ButterKnife.bind(this);
        initJsonData();

        if(getIntent().getStringExtra("key0")!=null){
            cert_id=getIntent().getStringExtra("key0");
        }

        back(titleBack);
        changeTitle(acTitle, "采购商认证");
        acPurchaserCertificationTvHint.setText("1.营业执照需盖红章和企业名称清晰可见\n" +
                "2.使用附近件需加盖公司红章\n" +
                "3.建议图片上传完毕后检查能否看清字体通过更有把握");

    }

    @OnClick({R.id.at_purchaser_certification2_et_id, R.id.at_purchaser_certification2_btn_confirm,
            R.id.at_purchaser_certification2_rl_pic, R.id.at_purchaser_certification2_tv_dangkou_pic,
            R.id.at_purchaser_certification2_tv_dangkou_id_card, R.id.at_purchaser_certification2_et_address,
            R.id.at_purchaser_certification2_et_shichang_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_purchaser_certification2_rl_pic:
                picType = 0;
                showPicDialog();
                break;
            case R.id.at_purchaser_certification2_tv_dangkou_pic:
                picType = 1;
                atPurchaserCertification2TvTuijian.setText("上传档口照片进行认证");
                atPurchaserCertification2TvDianjishangchuan.setText("1.需要您与档口的合照\n" +
                        "2.确保图片中能清晰看到档口名称和档口正门\n");
                break;
            case R.id.at_purchaser_certification2_tv_dangkou_id_card:
                picType = 2;
                atPurchaserCertification2TvTuijian.setText("上传名片或工牌进行认证");
                atPurchaserCertification2TvDianjishangchuan.setText(
                        "1.图片中需要展示名片/工牌的全部信息，名片包含但不限于联系人、联系方式、企业/档口名称、地址等信息。工牌需包含但不限于联系人、企业名称、部门/职位等信息。\n" +
                                "2.图片上传完毕，请确保内容清晰，无阅读障碍，以保顺利通过。\n");
                break;
            case R.id.at_purchaser_certification2_btn_confirm:
                if(atPurchaserCertification2EtName.getText().toString().trim().equals("")){
                    showToast("请填写真实姓名");
                }else if(atPurchaserCertification2EtMobile.getText().toString().trim().equals("")){
                    showToast("请填写手机号");
                }else if(atPurchaserCertification2EtId.getText().toString().trim().equals("请选择采购商身份")){
                    showToast("请选择身份");
                }else{
                    if(type==1){
                        if(atPurchaserCertification2EtDangkouName.getText().toString().equals("")){
                            showToast("请填写档口名称");
                        }else if(atPurchaserCertification2EtShichangName.getText().toString().equals("")){
                            showToast("请选择市场所在地");
                        }else if(atPurchaserCertification2EtDangkouAddress.getText().toString().equals("")){
                            showToast("请填写详细地址");
                        }else if(path==null){
                            showToast("请上传图片");
                        }else{
                            HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                    UploadImgResponseBean.DataBean data = bean.getData();
                                    img=data.getSrc();
                                    uploadData();
                                }
                            });
                        }
                    }else{
                        if(atPurchaserCertification2EtCompanyName.getText().toString().trim().equals("")){
                            showToast("请填写企业名称");
                        }else if(atPurchaserCertification2EtAddress.getText().toString().trim().equals("")){
                            showToast("请选择市场所在地");
                        }else if(path==null){
                            showToast("请上传图片");
                        }else{
                            HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                    UploadImgResponseBean.DataBean data = bean.getData();
                                    img=data.getSrc();
                                    uploadData();
                                }
                            });
                        }
                    }
                }

                break;
            case R.id.at_purchaser_certification2_et_id:
                showBottomDialog();
                break;
            case R.id.at_purchaser_certification2_et_address:
                showPickerView(atPurchaserCertification2EtAddress);
                break;
            case R.id.at_purchaser_certification2_et_shichang_name:
                showPickerView(atPurchaserCertification2EtShichangName);
                break;
        }
    }

    private void uploadData() {
        UploadCaigouBean bean = new UploadCaigouBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setName(atPurchaserCertification2EtName.getText().toString().trim());
        bean.setPurchase_identity(atPurchaserCertification2EtId.getText().toString());
        if (type == 1) {
            bean.setStall_name(atPurchaserCertification2EtDangkouName.getText().toString().trim());
            bean.setLocation(atPurchaserCertification2EtShichangName.getText().toString().trim());
            bean.setAddress(atPurchaserCertification2EtDangkouAddress.getText().toString().trim());
        } else {
            bean.setCompany_name(atPurchaserCertification2EtCompanyName.getText().toString().trim());
            bean.setLocation(atPurchaserCertification2EtAddress.getText().toString().trim());
        }
        if (picType == 0) {
            bean.setBusiness_license(img);
        } else if (picType == 1) {
            bean.setStall_image(img);
        } else {
            bean.setBusiness_card(img);
        }
        if(!cert_id.equals("")){
            bean.setCert_id(cert_id);
            CertSubscribe.upLoadCaigouCert3(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    startActivity(new Intent(PurchaserCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
                }

                @Override
                public void onFault(String errorMsg) {
                    showToast(errorMsg);
                }
            }));
        }else{
            CertSubscribe.upLoadCaigouCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    startActivity(new Intent(PurchaserCertification2Activity.this, ConfirmAttestationSuccessActivity.class));
                }

                @Override
                public void onFault(String errorMsg) {
                    showToast(errorMsg);
                }
            }));
        }
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_dangkou_shenfen_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_dangkou_laoban).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("档口老板");
                type = 1;
                atPurchaserCertification2LlDangkou.setVisibility(View.VISIBLE);
                atPurchaserCertification2LlName.setVisibility(View.GONE);
                atPurchaserCertification2LlDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_chaoshicaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("超市采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_chukoucaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("出口采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_canyinqiye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("餐饮企业");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_jiagongcahngcaigou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("加工厂采购");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_qita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                atPurchaserCertification2EtId.setText("其他");
                type = 0;
                atPurchaserCertification2LlDangkou.setVisibility(View.GONE);
                atPurchaserCertification2LlName.setVisibility(View.VISIBLE);
                atPurchaserCertification2RlFeiDangkouPic.setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.dl_dangkou_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void showPicDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_photo_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_photo_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(PurchaserCertification2Activity.this,
                            "com.zthx.npj.file_provider",
                            outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        atPurchaserCertification2RlPic.setBackground(new BitmapDrawable(bitmap));
                        path = getExternalCacheDir() + "/output_image.jpg";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        atPurchaserCertification2RlPic.setBackground(new BitmapDrawable(bitmap));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private void initJsonData() {//解析数据 （省市区三级联动）
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    private void showPickerView(final TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(options1Items.get(options1).getPickerViewText() + "  "
                        + options2Items.get(options1).get(options2) + "  "
                        + options3Items.get(options1).get(options2).get(options3));
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
