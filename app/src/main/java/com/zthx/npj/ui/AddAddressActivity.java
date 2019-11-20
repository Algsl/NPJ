package com.zthx.npj.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.been.AddAddressBean;
import com.zthx.npj.net.been.CityResponseBean;
import com.zthx.npj.net.been.DistrictResponseBean;
import com.zthx.npj.net.been.ProvinceResponseBean;
import com.zthx.npj.net.been.TownResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends ActivityBase {
    @BindView(R.id.ac_address_tv_address)
    TextView acAddressTvAddress;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.province)
    Spinner province;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.district)
    Spinner district;
    @BindView(R.id.town)
    Spinner town;
    private String is_default = "0";

    @BindView(R.id.ac_title)
    TextView mAcTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView mAtLocationStoreTvRuzhu;
    @BindView(R.id.ac_address_et_consignee)
    EditText mAcAddressEtConsignee;
    @BindView(R.id.ac_address_et_mobile)
    EditText mAcAddressEtMobile;

    @BindView(R.id.ac_address_et_houseNumber)
    EditText mAcAddressEtHouseNumber;
    @BindView(R.id.ac_address_iv_isDefault)
    ImageView mAcAddressIvIsDefault;
    @BindView(R.id.ac_address_btn_save)
    Button mAcAddressBtnSave;


    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    private String provinceName = "";
    private String cityName = "";
    private String districtName = "";
    private String townName;

    private String townId;
    private String provinceId;
    private String cityId;
    private String districtId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(mAcTitle, "添加地址");

        getProvince();

        initJsonData();
        mAcAddressIvIsDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAcAddressIvIsDefault.setImageResource(R.drawable.at_edit_address_selector);
                is_default = "1";
            }
        });
    }


    @OnClick({R.id.ac_address_btn_save, R.id.ac_address_tv_address})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_address_btn_save:
                if(!MyCustomUtils.isRegular(mAcAddressEtMobile.getText().toString().trim(),"mobile")){
                    showToast("请正确填写手机号");
                }else{
                    AddAddressBean bean = new AddAddressBean();
                    bean.setUser_id(SharePerferenceUtils.getUserId(this));
                    bean.setToken(SharePerferenceUtils.getToken(this));
                    bean.setConsignee(mAcAddressEtConsignee.getText().toString());
                    bean.setMobile(mAcAddressEtMobile.getText().toString().trim());
                    bean.setAddress(provinceName + cityName + districtName);
                    bean.setHouse_number(mAcAddressEtHouseNumber.getText().toString().trim());
                    bean.setProvince(provinceId);
                    bean.setCity(cityId);
                    bean.setDistrict(districtId);
                    bean.setIs_default(is_default);
                    bean.setTown(townId);
                    SetSubscribe.addAddress(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
                break;
            case R.id.ac_address_tv_address:
                showCityPicker();
                break;
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
                acAddressTvAddress.setText(options1Items.get(options1).getPickerViewText() + " "
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


    public void getProvince() {
        SetSubscribe.getProvince(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ProvinceResponseBean bean = GsonUtils.fromJson(result, ProvinceResponseBean.class);
                final ArrayList<ProvinceResponseBean.DataBean> data = bean.getData();

                String[] provinces = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    provinces[i] = data.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAddressActivity.this, android.R.layout.simple_spinner_item, provinces);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                province.setAdapter(adapter);
                province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        provinceName = data.get(i).getName();
                        provinceId = data.get(i).getId() + "";
                        getCity(data.get(i).getId() + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    public void getCity(String pid) {
        SetSubscribe.getCity(pid, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CityResponseBean bean = GsonUtils.fromJson(result, CityResponseBean.class);
                final ArrayList<CityResponseBean.DataBean> data = bean.getData();

                String[] citys = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    citys[i] = data.get(i).getName();
                }

                Log.e("测试", "onSuccess: " + data.toArray().toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAddressActivity.this, android.R.layout.simple_spinner_item, citys);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                city.setAdapter(adapter);
                city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        cityName = data.get(i).getName();
                        cityId = data.get(i).getId() + "";
                        getDistrict(data.get(i).getId() + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    public void getDistrict(String pid) {
        SetSubscribe.getDistrict(pid, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                DistrictResponseBean bean = GsonUtils.fromJson(result, DistrictResponseBean.class);
                final ArrayList<DistrictResponseBean.DataBean> data = bean.getData();

                String[] districts = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    districts[i] = data.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAddressActivity.this, android.R.layout.simple_spinner_item, districts);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                district.setAdapter(adapter);
                district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        districtName = data.get(i).getName();
                        districtId = data.get(i).getId() + "";

                        getTown(data.get(i).getId() + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getTown(String pid) {
        SetSubscribe.getTown(pid, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: " + result);
                TownResponseBean bean = GsonUtils.fromJson(result, TownResponseBean.class);
                final ArrayList<TownResponseBean.DataBean> data = bean.getData();

                String[] towns = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    towns[i] = data.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAddressActivity.this, android.R.layout.simple_spinner_item, towns);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                town.setAdapter(adapter);

                town.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        townName = data.get(i).getName();
                        townId = data.get(i).getId() + "";

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
