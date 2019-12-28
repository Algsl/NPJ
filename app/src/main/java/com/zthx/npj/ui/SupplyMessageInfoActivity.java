package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.CityResponseBean;
import com.zthx.npj.net.been.DistrictResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.ProvinceResponseBean;
import com.zthx.npj.net.been.PurchaseEdit2Bean;
import com.zthx.npj.net.been.PurchaseEditResponseBean;
import com.zthx.npj.net.been.SupplyEdit2Bean;
import com.zthx.npj.net.been.SupplyEditResponseBean;
import com.zthx.npj.net.been.TownResponseBean;
import com.zthx.npj.net.been.UploadChengxinCertResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SupplyMessageInfoActivity extends ActivityBase {


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
    /*@BindView(R.id.at_supply_message_tv_address)
    TextView atSupplyMessageTvAddress;*/
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
    //@BindView(R.id.at_qg_message_name)
    //EditText atQgMessageName;
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
    private static final int CHOOSE_VIDEO = 3;
    @BindView(R.id.at_supply_message_three_video)
    ZzImageBox atSupplyMessageThreeVideo;
    @BindView(R.id.province)
    Spinner province;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.district)
    Spinner district;
    @BindView(R.id.town)
    Spinner town;
    @BindView(R.id.at_supply_message_et_address)
    EditText atSupplyMessageEtAddress;
    @BindView(R.id.at_supply_message_chooseAddress)
    LinearLayout atSupplyMessageChooseAddress;
    @BindView(R.id.at_supply_message_tv_allAddress)
    TextView atSupplyMessageTvAllAddress;
    @BindView(R.id.at_supply_message_allAddress)
    RelativeLayout atSupplyMessageAllAddress;

    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private ArrayList<String> picPaths1 = new ArrayList<>();//商品图片
    private ArrayList<String> picPaths2 = new ArrayList<>();//详情图片
    private ArrayList<String> picPaths3 = new ArrayList<>();//视频地址,保存的为/public
    private ArrayList<String> picPaths0 = new ArrayList<>();
    private String picString0 = "";

    //商品图片URL及本地路径
    private List<String> paths1 = new ArrayList<>();
    private List<String> paths2 = new ArrayList<>();

    //商品详情URL及本地路径
    private List<String> paths3 = new ArrayList<>();
    private List<String> paths4 = new ArrayList<>();

    //视频URL及本地路径
    private List<String> paths5 = new ArrayList<>();
    private List<String> paths6 = new ArrayList<>();

    private SupplyEditResponseBean.DataBean supplyData;
    private PurchaseEditResponseBean.DataBean purchaseData;
    private String address = URLConstant.REQUEST_URL1;
    private boolean isZhiding;
    private boolean isUnit;
    private String isTop = "0";

    private String lat;
    private String lng;

    PurchaseEdit2Bean purchaseBean = new PurchaseEdit2Bean();
    SupplyEdit2Bean supplyBean = new SupplyEdit2Bean();

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String supplyId;
    private String supplyType;

    private String goodsImg;
    private String goodsContent;


    private String provinceName = "";
    private String cityName = "";
    private String districtName = "";
    private String townName;

    private String townId="";
    private String provinceId="";
    private String cityId="";
    private String districtId="";

    private Address supplyAddress;

    private String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx1Lq1TU+c8jDT\n" +
            "NEU5up1siPOXKJBU0ypde7oPfm9gyy2ajgcw6v3KF2ryjot5AKlBED6qdQPRa5Sk\n" +
            "jIf8ZE1W+x8CVOvEC2m1lCglpm5zbAw2EGXdE4NNH6D0tcxIHza94RFkVilx1rjc\n" +
            "5hQ1OnwnLCDWN2UbOBl6jyom+iqUWSnFu7pEm5J8ZlNyr654LmDvCQXoPio28VSk\n" +
            "uedjQLdM+OkQZdidUYMaKsWmc6Xy6qmRqrgfUjvArjCZb0MKaNMq7bm7K9tr9dmJ\n" +
            "cj4X1WSm7txR81FkDDREEYiwFBoocm/G3wqUDIho0vT++kMlz0tnoPEx4q339eJL\n" +
            "t9pkdQvFAgMBAAECggEAUzoMZ+3W5M00rKQ6Adqk8rblykjhw9FQcpAFdFroJZTx\n" +
            "svPlya8xN/PdyceM3wTAMgM4UO6S6uA+oQRkYGtRBvRgfubfsNDmmGTOpVBPQRXA\n" +
            "YU0rX1xShzXWTrEG+nohVJyRVzQ8EVs9CaVkr8S/dlXgyGEEoMiQpBt8zuEmLGcG\n" +
            "MTK/OyIbHQKtjfRoXfL8xfW2bifFhg4fGlmgbO2DfiljpRwEY3mU9cSLH3oYWr8y\n" +
            "z3rSWlFCtV5v8syHNCZ+2+DTfcAMw1kPk/g16u/KPHA7duvnWLwGDm6Ktv14CMM2\n" +
            "mq9SpeFl3uicBKIcdK2I5k0lCJY/8aik/f1d0BNpwQKBgQDlz0keZqzY4itvNOpe\n" +
            "McJzzNyok6+rl42mDBJwb7QD+9X9ZtFhsYbdSgXWLbjILNpFKcZK9PGMuMXj8Cx7\n" +
            "C26W6zH/zQWsypLT940owzjNy0eckFH8BFm4UQHinc7GILbuUtAJNBBCdKKIdep6\n" +
            "/4zQl35v62wAa+ijy2Lox1fOzwKBgQDGGPXghnOMEm+vntbjay81cEZNReHLSzo9\n" +
            "rr/QGktC6SIaroYYpQgBsgScX/srDdi5wAy9pgHkywCxeZ2jzOset2O6NZCHAcFp\n" +
            "Wb4BrG9Gf0nWVP/DG0uqvEVBmgPEa6lZaIZH13jFFVH8P+Vwn26zza46lW0gD2Kn\n" +
            "z4ClplGBKwKBgHskvjuqLUjyuO+YXVYoN9ixmDRFH0dFqMOniGHzmXThB+QHqn89\n" +
            "D9WYitQgH/oz/qo9HmKgKqeLg48G7e7pS1NXqK04Aah7zH4FEwEay1+LZE5DD4uK\n" +
            "EUGxNt9mTJzifuPqQEwOOABEW6vf88wBEEXeSARVFMSNDlZm8BNobmcFAoGAMT4V\n" +
            "KLnjUSdoEezXF/MV6h+9qgm8Bg/uK1UcIzvWB4zySFWnycqEQf+he8m0ItCvVgUy\n" +
            "ZZY1lE0OIA/OKuCOdbU6mhgklBrQnEKNo9bcVlbf4OKCLVrEpW1lfdguJY5pq2r7\n" +
            "LjKWt88D8UNk4mkPWKzBKZjpZnXMnVBMd2Dvk78CgYB8DC/wQzY/0ibckmXnqE9e\n" +
            "hjBuRaG3964je78O5JaCEIVXUNX6nn5TMTK+uWrQNyqgXs92kw98Xi4ZSuER3zXk\n" +
            "Vmc1SOW4LjF98RAFdVMct8fP2u9xZ2zHV/SZ/ot0D1Bmz+P0dQL38+kSJ4w1N1rz\n" +
            "HCGInP32FNZD8bmcY+gFXw==";
    public static final String APPID = "2019062565701049";
    public static IWXAPI api;
    private static final int SDK_PAY_FLAG = 1001;
    private String payMoney = "1";
    private String payType;
    private UploadChengxinCertResponseBean.DataBean data1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_message);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");

        back(titleBack);

        getProvince();
        atSupplyMessageAllAddress.setVisibility(View.VISIBLE);
        atSupplyMessageChooseAddress.setVisibility(View.GONE);

        supplyId = getIntent().getStringExtra("key0");
        supplyType = getIntent().getStringExtra("key1");
        atSupplyMessageRbZhiding.setVisibility(View.GONE);

        switch (supplyType) {
            case "1":
                changeTitle(acTitle, "采购信息");
                acSupplyLl.setVisibility(View.GONE);
                acQiugouLl.setVisibility(View.VISIBLE);
                getPurchaseInfo();
                break;
            case "2":
                changeTitle(acTitle, "供应信息");
                acSupplyLl.setVisibility(View.VISIBLE);
                acQiugouLl.setVisibility(View.GONE);
                getSupplyInfo();
                break;
        }

        //图片视频回显设置
        atSupplyMessageThreePic.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(SupplyMessageInfoActivity.this).load(url).into(iv);
            }
        });
        atSupplyMessageThreeVideo.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                iv.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
            }
        });
        atSupplyMessageNinePic.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(SupplyMessageInfoActivity.this).load(url).into(iv);
            }
        });

        //添加商品图片、视频和详情
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
                ImageSelectorUtils.openPhoto(SupplyMessageInfoActivity.this, CHOOSE_PHOTO1, false, 5 - picPaths1.size());
            }
        });
        atSupplyMessageThreeVideo.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                picPaths3.remove(position);
                atSupplyMessageThreeVideo.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent, CHOOSE_VIDEO);
            }
        });
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
                ImageSelectorUtils.openPhoto(SupplyMessageInfoActivity.this, CHOOSE_PHOTO2);
            }
        });
    }

    //获取供应信息
    private void getSupplyInfo() {
        SetSubscribe.supplyEdit(user_id, token, supplyId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyEditResponseBean bean = GsonUtils.fromJson(result, SupplyEditResponseBean.class);
                supplyData = bean.getData();


                for (String str : supplyData.getGoods_img()) {//获取商品图片信息
                    if (str.substring(str.length() - 4).equals(".mp4")) {//根据类型，放在不同的显示器内显示
                        picPaths3.add(str.split("http://app.npj-vip.com")[1]);
                        atSupplyMessageThreeVideo.addImageOnline(str);
                    } else {
                        picPaths1.add(str);
                        atSupplyMessageThreePic.addImageOnline(str);
                    }
                }
                for (String str : supplyData.getContent()) {
                    picPaths2.add(str);
                    atSupplyMessageNinePic.addImageOnline(str);
                }
                atSupplyMessageTitle.setText(supplyData.getTitle());
                atSupplyMessageEtPrice.setText(supplyData.getPrice());
                atSupplyMessageName.setText(supplyData.getGoods_name());
                atSupplyMessageNum.setText(supplyData.getGoods_num());
                atSupplyMessageTvAllAddress.setText(supplyData.getCity());
                atSupplyMessageWhole.setText(supplyData.getBuy_num());
                lat = supplyData.getLat();
                lng = supplyData.getLng();
                if (supplyData.getGoods_unit().equals("吨")) {
                    atSupplyMessageTvUnit.setText("吨");
                    atSupplyMessageTvUnit1.setText("吨");
                    atSupplyMessageTvDanwei.setText("元/吨");
                    isUnit = true;
                }

                if (supplyData.getProvince()!=null || !supplyData.getProvince().equals("")) {
                    provinceId = supplyData.getProvince();
                    cityId = supplyData.getCity1();
                    districtId = supplyData.getDistrict();
                    townId = supplyData.getTown();
                }else{
                    supplyAddress = MyCustomUtils.getGeoPointBystr(SupplyMessageInfoActivity.this, supplyData.getCity());
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    //获取求购信息
    private void getPurchaseInfo() {
        SetSubscribe.purchaseEdit(user_id, token, supplyId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PurchaseEditResponseBean bean = GsonUtils.fromJson(result, PurchaseEditResponseBean.class);
                purchaseData = bean.getData();
                Log.e("测试", "onSuccess: " + purchaseData.getImg());
                for (String str : purchaseData.getImg()) {//获取商品图片信息
                    if (str.substring(str.length() - 4).equals(".mp4")) {//根据类型，放在不同的显示器内显示
                        picPaths3.add(str.split("http://app.npj-vip.com")[1]);
                        atSupplyMessageThreeVideo.addImageOnline(str);
                    } else {
                        Log.e("测试", "onSuccess: " + str);
                        picPaths1.add(str);
                        atSupplyMessageThreePic.addImageOnline(str);
                    }
                }
                for (String str : purchaseData.getContent()) {
                    picPaths2.add(str);
                    atSupplyMessageNinePic.addImageOnline(str);
                }
                atQgMessageTitle.setText(purchaseData.getTitle());
                atQgMessageNum.setText(purchaseData.getAmount());
                atSupplyMessageEtMin.setText(purchaseData.getMin_price());
                atSupplyMessageEtMax.setText(purchaseData.getMax_price());
                atQgMessageTvAddress.setText(purchaseData.getCity());
                lat = purchaseData.getLat();
                lng = purchaseData.getLng();
                if (purchaseData.getUnit().equals("吨")) {
                    atQgMessageTvUnit.setText("吨");
                    atSupplyMessageTvNeedDanwei.setText("元/吨");
                    isUnit = true;
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }

    //页面返回值处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO1:
                if (resultCode != 0) {
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for (int i = 0; i < images.size(); i++) {
                        picPaths1.add(compress(images.get(i)));
                        atSupplyMessageThreePic.addImage(compress(images.get(i)));
                    }
                }
                break;
            case CHOOSE_PHOTO2:
                if (resultCode != 0) {
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for (int i = 0; i < images.size(); i++) {
                        picPaths2.add(compress(images.get(i)));
                        atSupplyMessageNinePic.addImage(compress(images.get(i)));
                    }
                }
                break;
            case CHOOSE_VIDEO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage3 = data.getData(); //获取系统返回的照片的Uri
                        Log.e("测试", "onActivityResult: " + selectedImage3);
                        String[] filePathColumn3 = {MediaStore.Images.Media.DATA};
                        Cursor cursor3 = getContentResolver().query(selectedImage3, filePathColumn3, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor3.moveToFirst();
                        int columnIndex3 = cursor3.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        String path3 = cursor3.getString(columnIndex3);  //获取照片路径
                        cursor3.close();
                        Log.e("测试", "onActivityResult: " + path3);
                        picPaths0.add(path3);
                        HttpUtils.uploadVideo(address, picPaths0, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("测试", "onFailure: " + e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                                UploadPicsResponseBean.DataBean data = bean.getData();
                                picPaths3.add(data.getImg());
                                Message msg = new Message();
                                picString0 = data.getImages().get(0);
                                msg.what = 1;
                                handler.sendMessage(msg);
                                picPaths0.clear();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 4:
                if (resultCode == 1) {
                    lat = data.getStringExtra("lat");
                    lng = data.getStringExtra("lng");
                    //atSupplyMessageTvAddress.setText(data.getStringExtra("addressDetail"));
                }
                break;
            case 5:
                if (resultCode == 1) {
                    lat = data.getStringExtra("lat");
                    lng = data.getStringExtra("lng");
                    atQgMessageTvAddress.setText(data.getStringExtra("addressDetail"));
                }
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                atSupplyMessageThreeVideo.addImageOnline(picString0);
            }
        }
    };

    //点击事件处理
    @OnClick({R.id.at_supply_message_allAddress, R.id.at_supply_message_btn_publish,
            R.id.at_qg_message_address, R.id.at_supply_message_rb_zhiding,
            R.id.at_supply_message_tv_unit, R.id.at_qg_message_tv_unit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //地址选择器
            case R.id.at_supply_message_allAddress:
                /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //initJsonData();
                //showPickerView();
                Intent intent=new Intent(SupplyMessageInfoActivity.this,MapAddressActivity.class);
                startActivityForResult(intent,4);*/
                atSupplyMessageChooseAddress.setVisibility(View.VISIBLE);
                atSupplyMessageAllAddress.setVisibility(View.GONE);
                break;
            case R.id.at_qg_message_address:
                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //initJsonData();
                //showPickerView();
                Intent intent1 = new Intent(SupplyMessageInfoActivity.this, MapAddressActivity.class);
                startActivityForResult(intent1, 5);
                break;
            //确认发布
            case R.id.at_supply_message_btn_publish:
                if ((picPaths1.size() + picPaths3.size()) < 1) {
                    showToast("请上传商品图片");
                } else if (picPaths2.size() < 1) {
                    showToast("请上传商品详情图片");
                } else {
                    if (supplyType.equals("1")) {//采购
                        if (atQgMessageTitle.getText().toString().trim().length() == 0) {
                            showToast("请输入采购品类");
                        } else if (atQgMessageNum.getText().toString().trim().length() == 0) {
                            showToast("请输入采购数量");
                        } else if (atQgMessageTvAddress.getText().toString().trim().equals("全国或指定城市")) {
                            showToast("请选择采购地址");
                        } else if (atSupplyMessageEtMin.getText().toString().length() == 0 || atSupplyMessageEtMax.getText().toString().length() == 0) {
                            showToast("请输入最低价和最高价");
                        } else if (Double.parseDouble(atSupplyMessageEtMin.getText().toString().trim()) >= Double.parseDouble(atSupplyMessageEtMax.getText().toString().trim())) {
                            showToast("请正确填写最低价和最高价");
                        } else {
                            /*//置顶是弹出置顶天数
                            if (isTop.equals("1")) {
                                showPublishPopwindow();
                            } else {//上传图片、上传信息
                                getImgPath();
                            }*/
                            getImgPath();
                        }
                    } else {//供应
                        if (atSupplyMessageTitle.getText().toString().trim().length() == 0) {
                            showToast("请填写供应标题");
                        } else if (atSupplyMessageName.getText().toString().trim().length() == 0) {
                            showToast("请填写供应产品名称");
                        } else if (atSupplyMessageNum.getText().toString().trim().length() == 0) {
                            showToast("请填写供应产品数量");
                        }/*else if(atSupplyMessageTvAddress.getText().toString().trim().equals("全国或指定城市")){
                            showToast("请选择供应地址");
                        }*/ else if (atSupplyMessageWhole.getText().toString().trim().length() == 0) {
                            showToast("请填写最低批发量");
                        } else if (atSupplyMessageEtPrice.getText().toString().trim().length() == 0) {
                            showToast("请填写供应价格");
                        } else {
                            //置顶是弹出置顶天数
                            /*if (isTop.equals("1")) {
                                showPublishPopwindow();
                            } else {//上传图片、上传信息
                                getImgPath();
                            }*/
                            getImgPath();
                        }
                    }
                }
                break;
            case R.id.at_supply_message_rb_zhiding:
                toggle();
                break;
            case R.id.at_supply_message_tv_unit:
                unitToggle();
                break;
            case R.id.at_qg_message_tv_unit:
                unitToggle();
                break;
        }
    }

    //单位显示处理
    private void unitToggle() {
        isUnit = !isUnit;
        if (isUnit) {
            atSupplyMessageTvUnit.setText("斤");
            atSupplyMessageTvUnit1.setText("斤");
            atSupplyMessageTvDanwei.setText("元/斤");

            atQgMessageTvUnit.setText("斤");
            atSupplyMessageTvNeedDanwei.setText("元/斤");
        } else {
            atSupplyMessageTvUnit.setText("吨");
            atSupplyMessageTvUnit1.setText("吨");
            atSupplyMessageTvDanwei.setText("元/吨");

            atQgMessageTvUnit.setText("吨");
            atSupplyMessageTvNeedDanwei.setText("元/吨");
        }
    }

    //置顶切换处理
    private void toggle() {
        isZhiding = !isZhiding;
        if (isZhiding) {
            isTop = "1";
            atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_selector);
        } else {
            isTop = "0";
            atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_not_selector);
        }
    }


    //上传数据信息
    private void uploadData() {
        switch (supplyType) {
            case "1"://采购
                purchaseBean.setId(supplyId);
                purchaseBean.setImg(goodsImg);
                purchaseBean.setContent(goodsContent);
                purchaseBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageInfoActivity.this));
                purchaseBean.setToken(SharePerferenceUtils.getToken(SupplyMessageInfoActivity.this));
                purchaseBean.setTitle(atQgMessageTitle.getText().toString().trim());
                purchaseBean.setLng(lng);
                purchaseBean.setLat(lat);
                purchaseBean.setUnit(atQgMessageTvUnit.getText().toString());
                purchaseBean.setAmount(atQgMessageNum.getText().toString());
                purchaseBean.setMin_price(atSupplyMessageEtMin.getText().toString());
                purchaseBean.setMax_price(atSupplyMessageEtMax.getText().toString());
                purchaseBean.setIs_top(isTop);
                purchaseBean.setCity(atQgMessageTvAddress.getText().toString());

                if (isTop.equals("1")) {
                    purchaseBean.setPay_code(payType);
                    purchaseBean.setTop_price(payMoney);
                    purchaseBean.setTop_days(payMoney);
                }

                SetSubscribe.purchaseEdit2(purchaseBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("采购商品编辑完成");
                        if (isTop.equals("1")) {
                            data1 = GsonUtils.fromJson(result, UploadChengxinCertResponseBean.class).getData();
                            DiscoverSubscribe.supplyPay(data1.getPay_code(), data1.getOrder_sn(), data1.getPay_money(), "6", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    if (payType.equals("1")) {
                                        setPayResult(result);
                                    } else if (payType.equals("2")) {
                                        setWXResult(result);
                                    } else {
                                        finish();
                                    }
                                }

                                @Override
                                public void onFault(String errorMsg) {
                                    //showToast(errorMsg);
                                }
                            }));
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                    }
                }));
                break;
            case "2"://供应
                supplyBean.setId(supplyId);
                supplyBean.setGoods_img(goodsImg);
                supplyBean.setContent(goodsContent);
                supplyBean.setUser_id(SharePerferenceUtils.getUserId(SupplyMessageInfoActivity.this));
                supplyBean.setToken(SharePerferenceUtils.getToken(SupplyMessageInfoActivity.this));
                supplyBean.setTitle(atSupplyMessageTitle.getText().toString().trim());
                supplyBean.setPrice(atSupplyMessageEtPrice.getText().toString().trim());
                supplyBean.setGoods_unit(atSupplyMessageTvUnit.getText().toString());
                supplyBean.setGoods_num(atSupplyMessageNum.getText().toString());
                supplyBean.setGoods_name(atSupplyMessageName.getText().toString());
                supplyBean.setCity(atSupplyMessageTvAllAddress.getText().toString().trim());
                supplyBean.setProvince(provinceId);
                supplyBean.setCity1(cityId);
                supplyBean.setDistrict(districtId);
                supplyBean.setTown(townId);
                supplyBean.setBuy_num(atSupplyMessageWhole.getText().toString().trim());
                supplyBean.setIs_top(isTop);

                if (isTop.equals("1")) {
                    supplyBean.setPay_code(payType);
                    supplyBean.setTop_price(payMoney);
                    supplyBean.setTop_days(payMoney);
                }

                if(!atSupplyMessageEtAddress.getText().toString().trim().equals("")){
                    supplyAddress=MyCustomUtils.getGeoPointBystr(SupplyMessageInfoActivity.this,provinceName+cityName+districtName+townName);
                    supplyBean.setCity(atSupplyMessageEtAddress.getText().toString().trim());
                    supplyBean.setLat(supplyAddress.getLatitude()+"");
                    supplyBean.setLng(supplyAddress.getLongitude()+"");
                }else{
                    supplyBean.setLng(lng);
                    supplyBean.setLat(lat);
                }

                if (supplyData.getProvince()!=null || !supplyData.getProvince().equals("")) {
                    supplyBean.setProvince(provinceId);
                    supplyBean.setCity1(cityId);
                    supplyBean.setDistrict(districtId);
                    supplyBean.setTown(townId);
                }

                SetSubscribe.supplyEdit2(supplyBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("供应商品编辑完成");
                        finish();
                        /*if (isTop.equals("1")) {
                            data1 = GsonUtils.fromJson(result, UploadChengxinCertResponseBean.class).getData();
                            DiscoverSubscribe.supplyPay(data1.getPay_code(), data1.getOrder_sn(), data1.getPay_money(), "6", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    if (payType.equals("1")) {
                                        setPayResult(result);
                                    } else if (payType.equals("2")) {
                                        setWXResult(result);
                                    } else {
                                        finish();
                                    }
                                }

                                @Override
                                public void onFault(String errorMsg) {
                                    //showToast(errorMsg);
                                }
                            }));
                        } else {
                            finish();
                        }*/
                    }

                    @Override
                    public void onFault(String errorMsg) {
                    }
                }));
                break;
        }
    }


    //图片压缩处理
    public String compress(String path) {
        File file = new File(path);
        Bitmap compressBitmap;
        /*if (file.length()>=2.5*1024*1024){//从相册中选择照片，2.5M以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 3;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else */
        if (file.length() >= 600 * 1024) {//从相册中选择照片，600k以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            compressBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        } else {
            compressBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
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

        String bmString = "";
        try {
            File bmFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString = bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
    }


    public void getImgPath() {
        for (String str : picPaths1) {
            if (str.split("http://app.npj-vip.com").length == 1) {
                paths1.add(str);
            } else {//解析全链接
                paths2.add(str.split("http://app.npj-vip.com")[1]);
            }
        }
        if (paths1.size() == 0) {//没有上传新的图片
            String paths3Str = "";
            for (int i = 0; i < paths2.size(); i++) {
                if (i == paths2.size() - 1) {
                    paths3Str += paths2.get(i);
                } else {
                    paths3Str += paths2.get(i) + ",";
                }
            }
            goodsImg = paths3Str;
            if (goodsImg.equals("")) {
                for (int i = 0; i < picPaths3.size(); i++) {
                    if (i == picPaths3.size() - 1) {
                        goodsImg += picPaths3.get(i);
                    } else {
                        goodsImg += picPaths3.get(i) + ",";
                    }
                }
            } else {
                for (String str : picPaths3) {
                    goodsImg += "," + str;
                }
            }
            getContentPath();
        } else {//上传了新的图片
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths1, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    String paths3Str = "";
                    for (String str : paths2) {
                        paths3Str += str + ",";
                    }
                    goodsImg = paths3Str + data.getImg();
                    for (String str : picPaths3) {
                        goodsImg += "," + str;
                    }
                    getContentPath();
                }
            });
        }
    }

    public void getContentPath() {
        for (String str : picPaths2) {
            if (str.split("http://app.npj-vip.com").length == 1) {
                paths3.add(str);
            } else {//解析全链接
                paths4.add(str.split("http://app.npj-vip.com")[1]);
            }
        }
        if (paths3.size() == 0) {//没有上传新的图片
            String paths3Str = "";
            for (int i = 0; i < paths4.size(); i++) {
                if (i == paths4.size() - 1) {
                    paths3Str += paths4.get(i);
                } else {
                    paths3Str += paths4.get(i) + ",";
                }
            }
            goodsContent = paths3Str;
            uploadData();
        } else {//上传了新的图片
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths3, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    String paths3Str = "";
                    for (String str : paths4) {
                        paths3Str += str + ",";
                    }
                    goodsContent = paths3Str + data.getImg();
                    uploadData();
                }
            });
        }
    }

    public void showPublishPopwindow() {
       /* View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_zhiding, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_450));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_275));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);*/
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.popupwindow_zhiding, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        ImageView cancel = view.findViewById(R.id.pw_zhiding_iv_cancel);
        final TextView item1 = view.findViewById(R.id.pw_zhiding_tv_item1);
        final TextView item2 = view.findViewById(R.id.pw_zhiding_tv_item2);
        final TextView item3 = view.findViewById(R.id.pw_zhiding_tv_item3);
        final TextView item4 = view.findViewById(R.id.pw_zhiding_tv_item4);
        final TextView money = view.findViewById(R.id.pw_zhiding_tv_payMoney);
        TextView confirm = view.findViewById(R.id.pw_zhiding_tv_confirm);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item1.setTextColor(getResources().getColor(R.color.white));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "1";
                money.setText("￥1.00");
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.white));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "5";
                money.setText("￥5.00");
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));
                item4.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.white));
                item4.setTextColor(getResources().getColor(R.color.text9));
                payMoney = "10";
                money.setText("￥10.00");
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item2.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item3.setBackground(getResources().getDrawable(R.drawable.stroke_gray1_15));
                item4.setBackground(getResources().getDrawable(R.drawable.zd_item_bg));

                item1.setTextColor(getResources().getColor(R.color.text9));
                item2.setTextColor(getResources().getColor(R.color.text9));
                item3.setTextColor(getResources().getColor(R.color.text9));
                item4.setTextColor(getResources().getColor(R.color.white));
                payMoney = "30";
                money.setText("￥30.00");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_pay_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_pay_yue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "3";
                getImgPath();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_ali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "1";
                getImgPath();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "2";
                getImgPath();
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


    private void setWXResult(String result) {
        PayResponse1Bean bean = GsonUtils.fromJson(result, PayResponse1Bean.class);
        PayResponse1Bean.DataBean data = bean.getData();
        PayReq req = new PayReq();
        req.appId = data.getAppid();//你的微信appid
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp();//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign = data.getSign();//签名
        //req.extData			= "app data";
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }


    private void setPayResult(String result) {
        PayResponseBean bean = GsonUtils.fromJson(result, PayResponseBean.class);
        PayResponseBean.DataBean data = bean.getData();
        alipay(data.getAlipay());
    }

    public void alipay(String str) {
        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = str;
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask(SupplyMessageInfoActivity.this);
                //获取支付结果
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Intent intent=new Intent(SupplyMessageInfoActivity.this,WXPayFinishActivity.class);
                        intent.putExtra("title","支付宝支付");
                        intent.putExtra("content","支付宝支付成功");
                        intent.putExtra("type","1");
                        startActivity(intent);
                        //Toast.makeText(SupplyMessageInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SupplyMessageInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SupplyMessageInfoActivity.this, android.R.layout.simple_spinner_item, provinces);
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SupplyMessageInfoActivity.this, android.R.layout.simple_spinner_item, citys);
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SupplyMessageInfoActivity.this, android.R.layout.simple_spinner_item, districts);
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
                TownResponseBean bean = GsonUtils.fromJson(result, TownResponseBean.class);
                final ArrayList<TownResponseBean.DataBean> data = bean.getData();

                String[] towns = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    towns[i] = data.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SupplyMessageInfoActivity.this, android.R.layout.simple_spinner_item, towns);
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
