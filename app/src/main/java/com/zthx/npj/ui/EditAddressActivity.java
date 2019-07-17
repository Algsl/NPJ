package com.zthx.npj.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.been.AddressInfoResponseBean;
import com.zthx.npj.net.been.EditAddressBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAddressActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_editAddress_et_consignee)
    EditText acEditAddressEtConsignee;
    @BindView(R.id.ac_editAddress_et_mobile)
    EditText acEditAddressEtMobile;
    @BindView(R.id.ac_editAddress_et_houseNumber)
    EditText acEditAddressEtHouseNumber;
    @BindView(R.id.ac_editAddress_iv_isDefault)
    ImageView acEditAddressIvIsDefault;
    @BindView(R.id.ac_editAddress_btn_save)
    Button acEditAddressBtnSave;
    @BindView(R.id.ac_editAddress_tv_address)
    TextView acEditAddressTvAddress;
    @BindView(R.id.title_back)
    ImageView titleBack;


    private boolean flag = false;
    private String isDefault = "0";

    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String address_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        initJsonData();
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"编辑收货地址");

        address_id = getIntent().getStringExtra("key0");
        getAddressInfo();
    }

    private void getAddressInfo() {
        SetSubscribe.getAddressInfo(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setAddressInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setAddressInfo(String result) {
        AddressInfoResponseBean bean = GsonUtils.fromJson(result, AddressInfoResponseBean.class);
        AddressInfoResponseBean.DataBean data = bean.getData();
        acEditAddressEtConsignee.setText(data.getConsignee());
        acEditAddressEtMobile.setText(data.getMobile());
        acEditAddressTvAddress.setText(data.getAddress());
        acEditAddressEtHouseNumber.setText(data.getHouse_number());
        if (data.getIs_default() == 0) {
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_not_selector);
        } else {
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
        }
    }

    public String getEtString(EditText et) {
        return et.getText().toString().trim();
    }

    @OnClick({R.id.ac_editAddress_iv_isDefault, R.id.ac_editAddress_btn_save, R.id.ac_editAddress_tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_editAddress_iv_isDefault:
                toggle();
                break;
            case R.id.ac_editAddress_tv_address:
                showCityPicker();
                break;
            case R.id.ac_editAddress_btn_save:
                EditAddressBean bean = new EditAddressBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setAddress_id(address_id);
                bean.setConsignee(getEtString(acEditAddressEtConsignee));
                bean.setAddress(acEditAddressTvAddress.getText().toString());
                bean.setMobile(getEtString(acEditAddressEtMobile));
                bean.setHouse_number(getEtString(acEditAddressEtHouseNumber));
                bean.setIs_default(isDefault);
                SetSubscribe.editAddressInfo(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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

    private void toggle() {
        flag = !flag;
        if (flag) {
            isDefault = "1";
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
            SetSubscribe.defaultAddress(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        } else {
            isDefault = "0";
            acEditAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }

    private void initJsonData() {//解析数据

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
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
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

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
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

    private void showCityPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                acEditAddressTvAddress.setText(options1Items.get(options1).getPickerViewText() + " "
                        + options2Items.get(options1).get(options2) + " "
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

    static class ViewHolder {
        @BindView(R.id.ac_editAddress_tv_address)
        TextView acEditAddressTvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
