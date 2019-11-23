package com.zthx.npj.tencent.util;

import android.text.TextUtils;
import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.HashMap;
import java.util.List;

public class TencentUtil {

    //TIM修改并保存用户信息
    public static void updateProfile(String mIconUrl,String nickName,String signature) {
        HashMap<String, Object> hashMap = new HashMap<>();

        // 头像
        if (!TextUtils.isEmpty(mIconUrl)) {
            hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL, mIconUrl);
        }

        // 昵称
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, nickName);

        // 个性签名
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_SELFSIGNATURE, signature);

        // 地区
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_LOCATION, "sz"); // TODO 不加SDK会有个崩溃

        // 加我验证方式
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_ALLOWTYPE, "AllowType_Type_AllowAny");

        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                //DemoLog.e(TAG, "modifySelfProfile err code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess() {
                //DemoLog.i(TAG, "modifySelfProfile success")
            }
        });
    }

    //用户登录
    public static void login(String phone){
        TIMManager.getInstance().login(phone, GenerateTestUserSig.genTestUserSig(phone), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e("测试", "onError: "+s );
            }

            @Override
            public void onSuccess() {

            }
        });
    }

    public static void loginOut(){
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess() {
                //登出成功

            }
        });
    }

    public static String getUnReadNum(){
        long num=0;
        List<TIMConversation> list = TIMManager.getInstance().getConversationList();
        for(int i=0;i<list.size();i++){
            num+=list.get(i).getUnreadMessageNum();
        }
        return num+"";
    }
}
