package com.zthx.npj.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
import com.zthx.npj.net.been.UploadPicsBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.been.UploadPurchaseBean;
import com.zthx.npj.net.been.UploadSupplyBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ActivityBase;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;

public class SupplyMessageActivity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_supply_message_three_pic)
    ZzImageBox atSupplyMessageThreePic;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.at_supply_message_title)
    EditText atSupplyMessageTitle;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.at_supply_message_name)
    EditText atSupplyMessageName;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.at_supply_message_num)
    EditText atSupplyMessageNum;
    @BindView(R.id.iv_go)
    ImageView ivGo;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.at_supply_message_tv_address)
    TextView atSupplyMessageTvAddress;
    @BindView(R.id.at_supply_message_address)
    RelativeLayout atSupplyMessageAddress;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.at_supply_message_et_price)
    EditText atSupplyMessageEtPrice;
    @BindView(R.id.iv_go5)
    ImageView ivGo5;
    @BindView(R.id.at_supply_message_tv_danwei)
    TextView atSupplyMessageTvDanwei;
    @BindView(R.id.at_supply_message_nine_pic)
    ZzImageBox atSupplyMessageNinePic;
    @BindView(R.id.at_supply_message_et_beizhu)
    EditText atSupplyMessageEtBeizhu;
    @BindView(R.id.at_supply_message_btn_publish)
    Button atSupplyMessageBtnPublish;
    @BindView(R.id.at_supply_message_tv_unit)
    TextView atSupplyMessageTvUnit;
    @BindView(R.id.at_supply_message_rl_supply)
    RelativeLayout atSupplyMessageRlSupply;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.at_supply_message_et_min)
    EditText atSupplyMessageEtMin;
    @BindView(R.id.view_min2max)
    View viewMin2max;
    @BindView(R.id.at_supply_message_et_max)
    EditText atSupplyMessageEtMax;
    @BindView(R.id.iv_go6)
    ImageView ivGo6;
    @BindView(R.id.at_supply_message_tv_need_danwei)
    TextView atSupplyMessageTvNeedDanwei;
    @BindView(R.id.at_supply_message_rl_need)
    RelativeLayout atSupplyMessageRlNeed;
    @BindView(R.id.at_supply_message_rb_zhiding)
    RadioButton atSupplyMessageRbZhiding;
    @BindView(R.id.at_supply_message_ll_zhiding)
    LinearLayout atSupplyMessageLlZhiding;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    private ArrayList<String> picPaths = new ArrayList<>();//照片路径

    private int supplyType = 1;
    private UploadPurchaseBean uploadPurchaseBean = new UploadPurchaseBean();
    private UploadSupplyBean uploadSupplyBean = new UploadSupplyBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_message);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"发布供应");

        supplyType = getIntent().getIntExtra(Const.SUPPLY_TYPE, 1);
        setView(supplyType);

        atSupplyMessageThreePic.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {

                atSupplyMessageThreePic.removeImage(position);
            }

            @Override
            public void onAddClick() {
                atSupplyMessageThreePic.addImage(getPicPath());
            }
        });

        atSupplyMessageNinePic.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                atSupplyMessageNinePic.removeImage(position);
            }

            @Override
            public void onAddClick() {
                atSupplyMessageNinePic.addImage(getPicPath());
            }
        });
    }


    private void setView(int type) {

        if (type == 2) {
            text1.setText("采购品类");
            text2.setText("规格要求");
            text3.setText("采购数量");
            text4.setText("指定产地");
            atSupplyMessageRlSupply.setVisibility(View.GONE);
            atSupplyMessageRlNeed.setVisibility(View.VISIBLE);
            atSupplyMessageLlZhiding.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 差个获取照片方法
     *
     * @return
     */
    private String getPicPath() {
        return null;
    }

    @OnClick({R.id.at_supply_message_address, R.id.at_supply_message_btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_supply_message_address:
                initJsonData();
                showPickerView();
                break;
            case R.id.at_supply_message_btn_publish:
                getData();
                break;
        }
    }

    /**
     * 上传所有信息
     */
    private void uploadData() {

        if (supplyType == 1) {
            DiscoverSubscribe.uploadSupply(uploadSupplyBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {

                    Toast.makeText(SupplyMessageActivity.this, "上传成功！", Toast.LENGTH_SHORT);
                    finish();
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }, this));
        } else {

            DiscoverSubscribe.uploadPurchase(uploadPurchaseBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {

                    Toast.makeText(SupplyMessageActivity.this, "上传成功！", Toast.LENGTH_SHORT);
                    finish();
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }
    }

    /**
     * 先上传3张的banner图片
     */
    private void getData() {
        List<String> allRealPath = atSupplyMessageThreePic.getAllRealPath();
        UploadPicsBean pics = new UploadPicsBean();
        File[] files = new File[allRealPath.size()];
        for (int i = 0; i < allRealPath.size(); i++) {
            files[i] = new File(allRealPath.get(i));
        }
        pics.setImges(files);
        SetSubscribe.upLoadFiles(pics, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                UploadPicsResponseBean uploadPicsResponseBean = GsonUtils.fromJson(result, UploadPicsResponseBean.class);
                if (supplyType == 1) {
                    uploadSupplyBean.setGoods_img(uploadPicsResponseBean.getData().getImg());
                } else {
                    uploadPurchaseBean.setImg(uploadPicsResponseBean.getData().getImg());
                }
                setNinePic();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));

    }

    /**
     * 再上传9张的详情图片
     */
    private void setNinePic() {
        List<String> allRealPath = atSupplyMessageNinePic.getAllRealPath();
        UploadPicsBean pics = new UploadPicsBean();
        File[] files = new File[allRealPath.size()];
        for (int i = 0; i < allRealPath.size(); i++) {
            files[i] = new File(allRealPath.get(i));
        }
        SetSubscribe.upLoadFiles(pics, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UploadPicsResponseBean uploadPicsResponseBean = GsonUtils.fromJson(result, UploadPicsResponseBean.class);

                if (supplyType == 1) {
                    uploadSupplyBean.setContent(uploadPicsResponseBean.getData().getImg());
                    uploadSupplyBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageActivity.this));
                    uploadSupplyBean.setToken(SharePerferenceUtils.getToken(SupplyMessageActivity.this));
                    uploadSupplyBean.setTitle(atSupplyMessageTitle.getText().toString().trim());
                    uploadSupplyBean.setPrice(atSupplyMessageEtPrice.getText().toString().trim());
                    uploadSupplyBean.setLng(SharePerferenceUtils.getLng(SupplyMessageActivity.this));
                    uploadSupplyBean.setLat(SharePerferenceUtils.getLat(SupplyMessageActivity.this));
                    uploadSupplyBean.setGoods_unit(atSupplyMessageTvUnit.getText().toString());
                    uploadSupplyBean.setGoods_num(atSupplyMessageNum.getText().toString());
                    uploadSupplyBean.setGoods_name(atSupplyMessageName.getText().toString());
                } else {

                    uploadPurchaseBean.setContent(uploadPicsResponseBean.getData().getImg());
                    uploadPurchaseBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageActivity.this));
                    uploadPurchaseBean.setToken(SharePerferenceUtils.getToken(SupplyMessageActivity.this));
                    uploadPurchaseBean.setTitle(atSupplyMessageTitle.getText().toString().trim());
                    uploadPurchaseBean.setLng(SharePerferenceUtils.getLng(SupplyMessageActivity.this));
                    uploadPurchaseBean.setLat(SharePerferenceUtils.getLat(SupplyMessageActivity.this));
                    uploadPurchaseBean.setUnit(atSupplyMessageTvUnit.getText().toString());
                    uploadPurchaseBean.setAmount(atSupplyMessageNum.getText().toString());
                    uploadPurchaseBean.setMin_price(atSupplyMessageEtMin.getText().toString());
                    uploadPurchaseBean.setMax_price(atSupplyMessageEtMax.getText().toString());
                    uploadPurchaseBean.setIs_top(atSupplyMessageRbZhiding.isChecked() ? "1" : "0");
                }
                uploadData();

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
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
                atSupplyMessageTvAddress.setText(options1Items.get(options1).getPickerViewText() + "  "
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
}
