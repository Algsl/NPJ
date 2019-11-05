package com.zthx.npj.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.UploadImgResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.egret.egretnativeandroid.EgretNativeAndroid;
import org.egret.runtime.launcherInterface.INativePlayer;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameActivity extends Activity {

    private final String TAG = "测试";
    private EgretNativeAndroid nativeAndroid;
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    private String RSA_PRIVATE ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx1Lq1TU+c8jDT\n" +
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
    private static final int CHOOSE_PHOTO=1;

    private String pay_code="";
    private String order_sn="";
    private String order_price="";

    private String lat="";
    private String lng="";
    private String SD="0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api= WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");

        nativeAndroid = new EgretNativeAndroid(this);
        if (!nativeAndroid.checkGlEsVersion()) {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        nativeAndroid.config.showFPS = false;
        nativeAndroid.config.fpsLogTime = 30;
        nativeAndroid.config.disableNativeRender = false;
        nativeAndroid.config.clearCache = false;
        nativeAndroid.config.loadingTimeout = 0;

        //nativeAndroid.callExternalInterface("sendToJS","ZHMM,"+user_id+","+token );
        setExternalInterfaces();


        if (!nativeAndroid.initialize("http://tool.egret-labs.org/Weiduan/game/index.html")) {
            Toast.makeText(this, "Initialize native failed.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        setContentView(nativeAndroid.getRootFrameLayout());
    }

    @Override
    protected void onPause() {
        super.onPause();
        nativeAndroid.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nativeAndroid.resume();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            nativeAndroid.exitGame();
        }

        return super.onKeyDown(keyCode, keyEvent);
    }

    private void setExternalInterfaces() {
        nativeAndroid.setExternalInterface("sendToNative", new INativePlayer.INativeInterface() {
            @Override
            public void callback(String message) {
                Log.e(TAG, "callback: "+message );
                String[] strs= Pattern.compile("[&=]").split(message);
                if(strs[1].equals("ZHMM")){
                    nativeAndroid.callExternalInterface("sendToJS","ZHMM,"+user_id+","+token);
                    nativeAndroid.callExternalInterface("sendToJS","WZ,"+SharePerferenceUtils.getLat(GameActivity.this)+","+SharePerferenceUtils.getLng(GameActivity.this));
                }if(strs[1].equals("TP")){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, CHOOSE_PHOTO);
                }else if(strs[1].equals("HYLBWZ")){
                    lat=strs[3];
                    lng=strs[5];
                    SD=strs[7];
                    LatLng latLng=new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
                    getLocation(latLng,1,SD);
                }else if(strs[1].equals("TJHYWZ")){
                    lat=strs[3];
                    lng=strs[5];
                    SD=strs[7];
                    LatLng latLng=new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
                    getLocation(latLng,2,SD);
                }else if(strs[1].equals("ZF")){
                    order_sn=strs[3];
                    pay_code=strs[5];
                    order_price=strs[7];
                    GiftSubscribe.pay(pay_code,order_sn,order_price,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            nativeAndroid.callExternalInterface("sendToJS","1,发起支付");
                            if(pay_code.equals("alipay")){
                                alipay(result);
                            }else if(pay_code.equals("weixin")){
                                setWXResult(result);
                            }else{

                            }
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        final String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        HttpUtils.uploadImg(URLConstant.REQUEST_URL, path, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                UploadImgResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadImgResponseBean.class);
                                UploadImgResponseBean.DataBean data = bean.getData();
                                Log.e(TAG, "onResponse: "+data.getSrcall() );
                                nativeAndroid.callExternalInterface("sendToJS","TP,"+data.getSrcall());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void getLocation(LatLng latLng, final int i, final String sd) {
        GeoCoder geoCoder=GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                Log.e(TAG, "onGetReverseGeoCodeResult: "+"HY,"+reverseGeoCodeResult.getAddress()+","+sd);
                if(i==1){
                    nativeAndroid.callExternalInterface("sendToJS","HY,"+reverseGeoCodeResult.getAddress()+","+sd);
                }else{
                    nativeAndroid.callExternalInterface("sendToJS","TJHY,"+reverseGeoCodeResult.getAddress()+","+sd);
                }
            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setWXResult(String result) {
        PayResponse1Bean bean=GsonUtils.fromJson(result,PayResponse1Bean.class);
        PayResponse1Bean.DataBean data=bean.getData();
        PayReq req = new PayReq();
        req.appId = data.getAppid();//你的微信appid
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp();//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign =data.getSign();//签名
        //req.extData			= "app data";
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    public void alipay(String result){

        PayResponseBean bean=GsonUtils.fromJson(result,PayResponseBean.class);

        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = bean.getData().getAlipay();
        //异步处理
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask(GameActivity.this);
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
                        Toast.makeText(GameActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GameActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
