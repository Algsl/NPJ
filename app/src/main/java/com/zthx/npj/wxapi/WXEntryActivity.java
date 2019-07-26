package com.zthx.npj.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.net.been.AuthLoginBean;
import com.zthx.npj.net.been.AuthLoginResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.CellPhoneLoginActivity;
import com.zthx.npj.ui.InputInvitationCodeActivity;
import com.zthx.npj.ui.LoginActivity;
import com.zthx.npj.ui.MainActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "WXEntryActivity";
	private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
	private static final int RETURN_MSG_TYPE_SHARE = 2; //分享

	IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
		api.registerApp("wx76500efa65d19915");
		LoginActivity.mWxApi.handleIntent(getIntent(), this);
	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	/**
	 * 得到支付结果回调
	 */
	@Override
	public void onResp(final BaseResp resp) {
		Log.i(TAG, "onResp:------>");
		Log.i(TAG, "error_code:---->" + resp.errCode);
		int type = resp.getType(); //类型：分享还是登录
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				//用户拒绝授权
				showToast("拒绝授权微信登录");
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				//用户取消
				String message = "";
				if (type == RETURN_MSG_TYPE_LOGIN) {
					message = "取消了微信登录";
				} else if (type == RETURN_MSG_TYPE_SHARE) {
					message = "取消了微信分享";
				}
				showToast(message);
				break;
			case BaseResp.ErrCode.ERR_OK:
				//用户同意
				if (type == RETURN_MSG_TYPE_LOGIN) {
					//用户换取access_token的code，仅在ErrCode为0时有效
					final String code = ((SendAuth.Resp) resp).code;
					Log.e("测试", "code:------>" + code);
					HttpUtils.sendOkHttpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx76500efa65d19915&secret=a42f02f8f260d62a636e23b1fa585c7a&code=" + code + "&grant_type=authorization_code", new Callback() {
						@Override
						public void onFailure(Call call, IOException e) {

						}

						@Override
						public void onResponse(Call call, Response response) throws IOException {
							String responseText=response.body().string();
							try {
								JSONObject obj=new JSONObject(responseText);
								String access_token=obj.getString("access_token");
								String openid=obj.getString("openid");
								getUserInfo(access_token,openid);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

						private void getUserInfo(String access_token, String openid) {
							HttpUtils.sendOkHttpRequest("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid, new Callback() {
								@Override
								public void onFailure(Call call, IOException e) {

								}

								@Override
								public void onResponse(Call call, Response response) throws IOException {
                                    Log.e("测试",!SharePerferenceUtils.isIsBindWx()+"" );
								    if(SharePerferenceUtils.isIsBindWx()){
										Intent intent=new Intent(WXEntryActivity.this,CellPhoneLoginActivity.class);
										intent.putExtra("flag",true);
										intent.putExtra("response",response.body().string());
										startActivity(intent);
									}else if(!SharePerferenceUtils.isIsBindSpokes()){
										startActivity(new Intent(WXEntryActivity.this, InputInvitationCodeActivity.class));
									}else{
										startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
									}
								}
							});
						}
					});
					//这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
				} else if (type == RETURN_MSG_TYPE_SHARE) {
					showToast( "微信分享成功");
				}
				break;
		}
	}


	private void showToast(String str) {
		Toast.makeText(this,str,Toast.LENGTH_LONG).show();
	}
}