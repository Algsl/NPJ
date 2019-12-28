package com.zthx.npj.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.ui.ConfirmOrderActivity;
import com.zthx.npj.ui.OrderFinishActivity;
import com.zthx.npj.ui.WXPayFinishActivity;
import com.zthx.npj.utils.SharePerferenceUtils;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915");
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@SuppressLint("LongLogTag")
	@Override
	public void onResp(BaseResp resp) {
		if(resp.errCode==-2){
			Toast.makeText(WXPayEntryActivity.this,"您取消了支付",Toast.LENGTH_SHORT).show();
			finish();
		}else if(resp.errCode==0){
			Intent intent=new Intent(this,WXPayFinishActivity.class);
			intent.putExtra("title","微信支付");
			intent.putExtra("content","微信支付成功");
			intent.putExtra("type","1");
			startActivity(new Intent(this,WXPayFinishActivity.class));
		}
		/*if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("支付提示");
			builder.setMessage(resp.errCode);
			builder.show();
		}*/
	}
}