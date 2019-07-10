package com.zthx.npj.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.ui.ConfirmMyOrderActivity;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

        private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
        IWXAPI api;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ConfirmMyOrderActivity.api.handleIntent(getIntent(), this);
        }

        @Override
        public void onReq(BaseReq req) {

        }

        @SuppressLint("LongLogTag")
        @Override
        public void onResp(BaseResp resp) {
            Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

            if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("支付结果");
                builder.setMessage(resp.errCode+" "+resp.errStr);
                builder.show();
            }
        }
}
