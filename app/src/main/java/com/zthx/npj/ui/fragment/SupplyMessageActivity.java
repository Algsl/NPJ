package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.AddPurchaseBean;
import com.zthx.npj.net.been.AddSupplyBean;
import com.zthx.npj.net.been.UploadPicsBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ActivityBase;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;

import java.io.File;
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

public class SupplyMessageActivity extends ActivityBase {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.at_supply_message_three_pic)
    ZzImageBox atSupplyMessageThreePic;
    @BindView(R.id.at_supply_message_title)
    EditText atSupplyMessageTitle;
    @BindView(R.id.at_supply_message_name)
    EditText atSupplyMessageName;
    @BindView(R.id.at_supply_message_num)
    EditText atSupplyMessageNum;
    @BindView(R.id.at_supply_message_tv_unit)
    TextView atSupplyMessageTvUnit;
    @BindView(R.id.at_supply_message_tv_address)
    TextView atSupplyMessageTvAddress;
    @BindView(R.id.at_supply_message_address)
    RelativeLayout atSupplyMessageAddress;
    @BindView(R.id.at_supply_message_whole)
    EditText atSupplyMessageWhole;
    @BindView(R.id.at_supply_message_tv_unit1)
    TextView atSupplyMessageTvUnit1;
    @BindView(R.id.at_supply_message_et_price)
    EditText atSupplyMessageEtPrice;
    @BindView(R.id.at_supply_message_tv_danwei)
    TextView atSupplyMessageTvDanwei;
    @BindView(R.id.at_supply_message_rl_supply)
    RelativeLayout atSupplyMessageRlSupply;
    @BindView(R.id.ac_supply_ll)
    LinearLayout acSupplyLl;
    @BindView(R.id.at_qg_message_title)
    EditText atQgMessageTitle;
    @BindView(R.id.at_qg_message_name)
    EditText atQgMessageName;
    @BindView(R.id.at_qg_message_num)
    EditText atQgMessageNum;
    @BindView(R.id.at_qg_message_tv_unit)
    TextView atQgMessageTvUnit;
    @BindView(R.id.at_qg_message_tv_address)
    TextView atQgMessageTvAddress;
    @BindView(R.id.at_qg_message_address)
    RelativeLayout atQgMessageAddress;
    @BindView(R.id.at_supply_message_et_min)
    EditText atSupplyMessageEtMin;
    @BindView(R.id.view_min2max)
    View viewMin2max;
    @BindView(R.id.at_supply_message_et_max)
    EditText atSupplyMessageEtMax;
    @BindView(R.id.at_supply_message_tv_need_danwei)
    TextView atSupplyMessageTvNeedDanwei;
    @BindView(R.id.at_supply_message_rl_need)
    RelativeLayout atSupplyMessageRlNeed;
    @BindView(R.id.ac_qiugou_ll)
    LinearLayout acQiugouLl;
    @BindView(R.id.at_supply_message_nine_pic)
    ZzImageBox atSupplyMessageNinePic;
    @BindView(R.id.at_supply_message_et_beizhu)
    EditText atSupplyMessageEtBeizhu;
    @BindView(R.id.at_supply_message_rb_zhiding)
    ImageView atSupplyMessageRbZhiding;
    @BindView(R.id.at_supply_message_ll_zhiding)
    LinearLayout atSupplyMessageLlZhiding;
    @BindView(R.id.at_supply_message_btn_publish)
    Button atSupplyMessageBtnPublish;

    private static final int CHOOSE_PHOTO1 = 1;
    private static final int CHOOSE_PHOTO2 = 2;

    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private ArrayList<String> picPaths1 = new ArrayList<>();//照片路径
    private ArrayList<String> picPaths2=new ArrayList<>();
    private int supplyType = 1;
    AddPurchaseBean purchaseBean=new AddPurchaseBean();
    AddSupplyBean supplyBean=new AddSupplyBean();
    private String address=URLConstant.REQUEST_URL1;
    private boolean isZhiding;
    private String isTop="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_message);
        ButterKnife.bind(this);

        back(titleBack);

        supplyType = getIntent().getIntExtra(Const.SUPPLY_TYPE, 1);

        switch (supplyType){
            case 1:
                changeTitle(acTitle, "采购信息");
                acSupplyLl.setVisibility(View.GONE);
                acQiugouLl.setVisibility(View.VISIBLE);
                break;
            case 2:
                changeTitle(acTitle, "供应信息");
                acSupplyLl.setVisibility(View.VISIBLE);
                acQiugouLl.setVisibility(View.GONE);
                break;
        }

        //添加商品图片
        atSupplyMessageThreePic.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {
            }
            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                picPaths1.remove(position);
                atSupplyMessageThreePic.removeImage(position);
            }
            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO1);
            }
        });

        //上传9张图片
        atSupplyMessageNinePic.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {
            }
            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                picPaths2.remove(position);
                atSupplyMessageNinePic.removeImage(position);
            }
            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_PHOTO1:
                if(resultCode==RESULT_OK){
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columnIndex);  //获取照片路径
                    picPaths1.add(path);
                    atSupplyMessageThreePic.addImage(path);
                }
                break;
            case CHOOSE_PHOTO2:
                Uri selectedImage=data.getData();
                String[] filePathColumn={MediaStore.Images.Media.DATA};
                Cursor cursor=getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
                String path=cursor.getString(columnIndex);
                picPaths2.add(path);
                atSupplyMessageNinePic.addImage(path);
                break;
        }
    }

    @OnClick({R.id.at_supply_message_address, R.id.at_supply_message_btn_publish,R.id.at_qg_message_address,R.id.at_supply_message_rb_zhiding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //地址选择器
            case R.id.at_supply_message_address:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                initJsonData();
                showPickerView();
                break;
            case R.id.at_qg_message_address:
                InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
                initJsonData();
                showPickerView();
                break;
            //确认发布
            case R.id.at_supply_message_btn_publish:
                uploadImage();
                break;
            case R.id.at_supply_message_rb_zhiding:
                toggle();
                break;
        }
    }

    private void toggle() {
        isZhiding=!isZhiding;
        if(isZhiding){
            isTop="1";
            atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_selector);
        }else{
            isTop="0";
            atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }

    private void uploadImage() {
        HttpUtils.uploadMoreImg(address, picPaths1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                switch (supplyType){
                    case 1:
                        purchaseBean.setImg(data.getImg());
                        break;
                    case 2:
                        supplyBean.setGoods_img(data.getImg());
                        break;
                }
                uploadContentImg();
            }
        });
    }

    private void uploadContentImg() {
        HttpUtils.uploadMoreImg(address, picPaths2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("测试", "onResponse: "+response);
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                switch (supplyType){
                    case 1:
                        purchaseBean.setContent(data.getImg());
                        purchaseBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageActivity.this));
                        purchaseBean.setToken(SharePerferenceUtils.getToken(SupplyMessageActivity.this));
                        purchaseBean.setTitle(atQgMessageTitle.getText().toString().trim());
                        purchaseBean.setLng(SharePerferenceUtils.getLng(SupplyMessageActivity.this));
                        purchaseBean.setLat(SharePerferenceUtils.getLat(SupplyMessageActivity.this));
                        purchaseBean.setUnit(atQgMessageTvUnit.getText().toString());
                        purchaseBean.setAmount(atQgMessageNum.getText().toString());
                        purchaseBean.setMin_price(atSupplyMessageEtMin.getText().toString());
                        purchaseBean.setMax_price(atSupplyMessageEtMax.getText().toString());
                        purchaseBean.setIs_top(isTop);
                        purchaseBean.setCity(atQgMessageTvAddress.getText().toString());
                        break;
                    case 2:
                        supplyBean.setContent(data.getImg());
                        supplyBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageActivity.this));
                        supplyBean.setToken(SharePerferenceUtils.getToken(SupplyMessageActivity.this));
                        supplyBean.setTitle(atSupplyMessageTitle.getText().toString().trim());
                        supplyBean.setPrice(atSupplyMessageEtPrice.getText().toString().trim());
                        supplyBean.setLng(SharePerferenceUtils.getLng(SupplyMessageActivity.this));
                        supplyBean.setLat(SharePerferenceUtils.getLat(SupplyMessageActivity.this));
                        supplyBean.setGoods_unit(atSupplyMessageTvUnit.getText().toString());
                        supplyBean.setGoods_num(atSupplyMessageNum.getText().toString());
                        supplyBean.setGoods_name(atSupplyMessageName.getText().toString());
                        supplyBean.setCity(atSupplyMessageTvAddress.getText().toString());
                        supplyBean.setBuy_num(atSupplyMessageWhole.getText().toString().trim());
                        //supplyBean.setIs_recommend2(isTop);
                        break;
                }
                uploadData();
            }
        });
    }


    private void uploadData() {
        switch (supplyType){
            case 1://采购
                DiscoverSubscribe.addPurchase(purchaseBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }
                    @Override
                    public void onFault(String errorMsg) {
                    }
                }));
                break;
            case 2://供应
                DiscoverSubscribe.addSupply(supplyBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }
                    @Override
                    public void onFault(String errorMsg) {
                    }
                }));
                break;
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

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                switch (supplyType){
                    case 1:
                        atQgMessageTvAddress.setText(options1Items.get(options1).getPickerViewText() + "  "
                                + options2Items.get(options1).get(options2) + "  "
                                + options3Items.get(options1).get(options2).get(options3));
                        break;
                    case 2:
                        atSupplyMessageTvAddress.setText(options1Items.get(options1).getPickerViewText() + "  "
                                + options2Items.get(options1).get(options2) + "  "
                                + options3Items.get(options1).get(options2).get(options3));
                        break;
                }

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
}
