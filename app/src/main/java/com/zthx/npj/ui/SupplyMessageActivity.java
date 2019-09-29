package com.zthx.npj.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.zthx.npj.R;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.base.Const;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.AddPurchaseBean;
import com.zthx.npj.net.been.AddSupplyBean;
import com.zthx.npj.net.been.OfflineBuyBean;
import com.zthx.npj.net.been.OfflineBuyResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.been.UploadChengxinCertResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GetJsonDataUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private ArrayList<String> picPaths1 = new ArrayList<>();//照片路径
    private ArrayList<String> picPaths2 = new ArrayList<>();
    private ArrayList<String> picPaths3=new ArrayList<>();
    private int supplyType = 1;
    AddPurchaseBean purchaseBean = new AddPurchaseBean();
    AddSupplyBean supplyBean = new AddSupplyBean();
    private String address = URLConstant.REQUEST_URL1;
    private boolean isZhiding;
    private String isTop = "0";

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
    private String payMoney="1";
    private String payType;
    private UploadChengxinCertResponseBean.DataBean data1;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private ArrayList<String> imgList=new ArrayList<>();
    private String goodsImg="";
    private ArrayList<String> picPaths0 = new ArrayList<>();
    private String picString0="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_message);
        ButterKnife.bind(this);

        back(titleBack);

        supplyType = getIntent().getIntExtra(Const.SUPPLY_TYPE, 1);

        switch (supplyType) {
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

        atSupplyMessageThreeVideo.setOnlineImageLoader(new ZzImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                iv.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
            }
        });

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

        atSupplyMessageThreeVideo.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                picPaths3.remove(position);
                imgList.remove(position);
                atSupplyMessageThreeVideo.removeImage(position);
            }

            @Override
            public void onAddClick() {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent, CHOOSE_VIDEO);
            }
        });

        //详情图片
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
        switch (requestCode) {
            case CHOOSE_PHOTO1:
                if (resultCode == RESULT_OK) {
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
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String path = cursor.getString(columnIndex);
                picPaths2.add(path);
                atSupplyMessageNinePic.addImage(path);
                break;
            case CHOOSE_VIDEO://视频选一个上传一个
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage3 = data.getData(); //获取系统返回的照片的Uri
                        Log.e("测试", "onActivityResult: "+selectedImage3 );
                        String[] filePathColumn3 = {MediaStore.Images.Media.DATA};
                        Cursor cursor3 = getContentResolver().query(selectedImage3, filePathColumn3, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor3.moveToFirst();
                        int columnIndex3 = cursor3.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        String path3 = cursor3.getString(columnIndex3);  //获取照片路径
                        cursor3.close();
                        Log.e("测试", "onActivityResult: "+path3 );
                        picPaths3.add(path3);
                        picPaths0.add(path3);
                        HttpUtils.uploadVideo(address, picPaths0, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("测试", "onFailure: "+e );
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadPicsResponseBean bean=GsonUtils.fromJson(response.body().string(),UploadPicsResponseBean.class);
                                UploadPicsResponseBean.DataBean data = bean.getData();
                                imgList.add(data.getImg());//视频路径的合成
                                Message msg=new Message();
                                picString0=data.getImages().get(0);
                                msg.what=1;
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
                if(resultCode==1){
                    atSupplyMessageTvAddress.setText(data.getStringExtra("addressDetail"));
                }
                break;
            case 5:
                if(resultCode==1){
                    atQgMessageTvAddress.setText(data.getStringExtra("addressDetail"));
                }
                break;
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                atSupplyMessageThreeVideo.addImageOnline(picString0);
            }
        }
    };

    @OnClick({R.id.at_supply_message_address, R.id.at_supply_message_btn_publish,
            R.id.at_qg_message_address, R.id.at_supply_message_rb_zhiding,
            R.id.at_supply_message_tv_unit,R.id.at_qg_message_tv_unit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //地址选择器
            case R.id.at_supply_message_address:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //initJsonData();
                //showPickerView();
                Intent intent=new Intent(SupplyMessageActivity.this,MapAddressActivity.class);
                startActivityForResult(intent,4);
                break;
            case R.id.at_qg_message_address:
                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //initJsonData();
                //showPickerView();
                Intent intent1=new Intent(SupplyMessageActivity.this,MapAddressActivity.class);
                startActivityForResult(intent1,5);
                break;
            //确认发布
            case R.id.at_supply_message_btn_publish:
                if((picPaths1.size()+picPaths3.size())<1){
                    showToast("请上传商品图片");
                }else if(picPaths2.size()<1){
                    showToast("请上传商品详情图片");
                }else{
                    //拼接视频地址
                    for(int i=0;i<imgList.size();i++){
                        if(i==imgList.size()-1){
                            goodsImg+=imgList.get(i);
                        }else{
                            goodsImg+=imgList.get(i)+",";
                        }
                    }
                    if(supplyType==1){//采购
                        if(atQgMessageTitle.getText().toString().trim().length()==0){
                            showToast("请输入采购品类");
                        }else if(atQgMessageNum.getText().toString().trim().length()==0){
                            showToast("请输入采购数量");
                        }else if(atQgMessageTvAddress.getText().toString().trim().length()==0){
                            showToast("请选择采购地址");
                        }else if(atSupplyMessageEtMin.getText().toString().length()==0 || atSupplyMessageEtMax.getText().toString().length()==0){
                            showToast("请输入最低价和最高价");
                        }else{
                            showToast("信息上传中..");
                            uploadImage();
                        }
                    }else{//供应
                        if(atSupplyMessageTitle.getText().toString().trim().length()==0){
                            showToast("请填写供应标题");
                        }else if(atSupplyMessageName.getText().toString().trim().length()==0){
                            showToast("请填写供应产品名称");
                        }else if(atSupplyMessageNum.getText().toString().trim().length()==0){
                            showToast("请填写供应产品数量");
                        }else if(atSupplyMessageTvAddress.getText().toString().trim().length()==0){
                            showToast("请选择供应地址");
                        }else if(atSupplyMessageWhole.getText().toString().trim().length()==0){
                            showToast("请填写最低批发量");
                        }else if(atSupplyMessageEtPrice.getText().toString().trim().length()==0){
                            showToast("请填写供应价格");
                        }else{
                            showToast("信息上传中..");
                            uploadImage();
                        }
                    }
                }
                break;
            case R.id.at_supply_message_rb_zhiding:
                toggle();
                break;
            case R.id.at_supply_message_tv_unit:
                MyCustomUtils.showUnitPickerView(this,atSupplyMessageTvUnit,atSupplyMessageTvUnit1,atSupplyMessageTvDanwei);
                break;
            case R.id.at_qg_message_tv_unit:
                MyCustomUtils.showUnitPickerView(this,atQgMessageTvUnit,null,atSupplyMessageTvNeedDanwei);
                break;
        }
    }

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

    private void uploadImage() {//上传商品图片

        if(picPaths1.size()==0 || picPaths1==null){//产品图不存在，直接上传详情
            uploadContentImg();
        }else{//产品图存在，上传产品图和详情图
            HttpUtils.uploadMoreImg(address, picPaths1, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    //拼接视频和图片
                    if(!goodsImg.equals("")){//有视频地址需在视频和图片间添加 ","
                        goodsImg+=","+data.getImg();
                    }else{//没有视频地址，直接拼接
                        goodsImg+=data.getImg();
                    }
                    uploadContentImg();
                }
            });
        }
    }





    private void uploadContentImg() {
        HttpUtils.uploadMoreImg(address, picPaths2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("测试", "onResponse: " + response);
                UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                UploadPicsResponseBean.DataBean data = bean.getData();
                switch (supplyType) {
                    case 1:
                        purchaseBean.setImg(goodsImg);
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
                        if(isTop.equals("1")){
                            purchaseBean.setTop_days(payMoney);
                            purchaseBean.setTop_price(payMoney);
                            purchaseBean.setPay_code(payType);
                        }
                        break;
                    case 2:
                        supplyBean.setGoods_img(goodsImg);
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
                        supplyBean.setIs_top(isTop);
                        break;
                }
                uploadData();
            }
        });
    }


    private void uploadData() {
        if(isTop.equals("1")){
            showPublishPopwindow();
        }else{
            switch (supplyType) {
                case 1://采购
                    DiscoverSubscribe.addPurchase(purchaseBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("信息发布成功");
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                        }
                    }));
                    break;
                case 2://供应
                    DiscoverSubscribe.addSupply(supplyBean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("信息发布成功");
                            finish();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                        }
                    }));
                    break;
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
                switch (supplyType) {
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
    private int yourChoice;



    //修改店铺信息弹窗
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

        ImageView cancel=view.findViewById(R.id.pw_zhiding_iv_cancel);
        final TextView item1=view.findViewById(R.id.pw_zhiding_tv_item1);
        final TextView item2=view.findViewById(R.id.pw_zhiding_tv_item2);
        final TextView item3=view.findViewById(R.id.pw_zhiding_tv_item3);
        final TextView item4=view.findViewById(R.id.pw_zhiding_tv_item4);
        final TextView money=view.findViewById(R.id.pw_zhiding_tv_payMoney);
        TextView confirm=view.findViewById(R.id.pw_zhiding_tv_confirm);

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
                payMoney="1";
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
                payMoney="5";
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
                payMoney="10";
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
                payMoney="30";
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
                showToast("订单生成中，请稍等...");
                generateOrder();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_ali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "1";
                showToast("订单生成中，请稍等...");
                generateOrder();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType = "2";
                showToast("订单生成中，请稍等...");
                generateOrder();
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

    public void generateOrder() {
        isTop="1";
        atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_selector);
        /*UploadChengXinCertBean bean=new UploadChengXinCertBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setPay_code(payType);
        bean.setPrice(payMoney);
        CertSubscribe.uploadChengxinCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("用户订单已生成,正在调起支付...");
                UploadChengxinCertResponseBean bean = GsonUtils.fromJson(result, UploadChengxinCertResponseBean.class);
                data1 = bean.getData();
                if (payType.equals("3")) {
                    yue();
                } else if (payType.equals("2")) {
                    wxpay();
                } else {
                    alipay();
                }
            }

            @Override
            public void onFault(String errorMsg) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(errorMsg);
                    Log.e("测试", "onFault: " + obj);
                    int code = obj.getInt("code");
                    if (code == 2) {
                        showToast("余额支付成功");
                        isTop = "1";
                        atSupplyMessageRbZhiding.setImageResource(R.drawable.at_edit_address_selector);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("测试", "onFault: " + (obj == null));
                if (obj == null) {
                    showToast("余额不足");
                }
            }
        }));*/
    }

    private void yue() {

    }

    private void wxpay() {
        GiftSubscribe.pay("weixin", data1.getOrder_sn(), data1.getPay_money(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setWXResult(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
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

    private void alipay() {
        GiftSubscribe.pay("alipay", data1.getOrder_sn(), data1.getPay_money(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setPayResult(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
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
                PayTask alipay = new PayTask(SupplyMessageActivity.this);
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
                        Toast.makeText(SupplyMessageActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SupplyMessageActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
