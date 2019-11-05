package com.zthx.npj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zthx.npj.base.Const;
import com.zthx.npj.entity.NotificationBean;
import com.zthx.npj.utils.marquee.LooperBean;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by huangxin on 2019/5/29.
 */

public class SharePerferenceUtils {

    private static SharedPreferences sp;
    private static String SPXMLNAME = "sp_config";
    private static String cateId;
    private static String cateName;
    private static String totlePrice;
    private static ArrayList<NotificationBean> list=new ArrayList<>();
    private static ArrayList<LooperBean> looperBeans=new ArrayList<>();


    public static String getTotlePrice() {
        return SharePerferenceUtils.totlePrice;
    }
    public static void setTotlePrice(String totlePrice) {
        SharePerferenceUtils.totlePrice = totlePrice;
    }

    public static SharedPreferences getSp() {
        return sp;
    }

    public static void setSp(SharedPreferences sp) {
        SharePerferenceUtils.sp = sp;
    }

    public static String getSPXMLNAME() {
        return SPXMLNAME;
    }

    public static void setSPXMLNAME(String SPXMLNAME) {
        SharePerferenceUtils.SPXMLNAME = SPXMLNAME;
    }

    public static String getCateId() {
        return cateId;
    }

    public static void setCateId(String cateId) {
        SharePerferenceUtils.cateId = cateId;
    }

    public static String getCateName() {
        return cateName;
    }

    public static void setCateName(String cateName) {
        SharePerferenceUtils.cateName = cateName;
    }


    /**
     * @param ctx
     *            上下文环境
     * @param key
     *            要从config.xml移除节点的name的名称
     */
    public static void removeKey(Context ctx, String key) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    // 1,存储boolean变量方法
    public static void putBoolean(Context ctx, String key, boolean value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    // 2,读取boolean变量方法
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context ctx, String key, String value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }


    public static String getString(Context ctx, String key, String defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    //
    public static void putInt(Context ctx, String key, int value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

    public static void setBalance(Context context, String balance) {
        putString(context,"balance",balance);
    }

    public static String getBalance(Context context) {
        return getString(context, "balance", "");
    }


    public static void setUserName(Context context, String user_name) {
        putString(context,"userName",user_name);
    }

    public static String getUserName(Context context) {
        return getString(context, "userName", "");
    }

    public static void setUserMobile(Context context, String userMobile) {
        putString(context,"userMobile",userMobile);
    }

    public static String getUserMobile(Context context) {
        return getString(context, "userMobile", "");
    }


    public static void setUserId(Context context, String id) {
        putString(context,"user_id",id);
    }

    public static String getUserId(Context context) {
        //return "40";
        return getString(context, "user_id", "");
    }

    public static void setVersion(Context context, String version) {
        putString(context,"version",version);
    }

    public static String getVersion(Context context) {
        //return "40";
        return getString(context, "version", "");
    }

    public static String getToken(Context context) {
        //return "e8ed8cfd852bd9ec764b599ec449d597";
        return getString(context, "token", "");
    }

    public static void setUserLevel(Context context, String level) {
        putString(context,"user_level",level);
    }

    public static String getUserLevel(Context context) {
        return getString(context, "user_level", "");
    }

    public static void setHeadPic(Context context, String headPic) {
        putString(context,"headPic",headPic);
    }
    public static String getHeadPic(Context context) {
        return getString(context, "headPic", "");
    }

    public static void setNickName(Context context, String nick_name) {
        putString(context,"nick_name",nick_name);
    }
    public static String getNickName(Context context) {
        return getString(context, "nick_name", "");
    }

    public static String getReputation(Context context) {
        return getString(context, "reputation", "");
    }
    public static void setReputation(Context context, String reputation) {
        putString(context,"reputation",reputation);
    }



    public static ArrayList<NotificationBean> getList() {
        return list;
    }

    public static void setList(ArrayList<NotificationBean> list) {
        SharePerferenceUtils.list = list;
    }

    public static ArrayList<LooperBean> getLooperBeans() {
        return looperBeans;
    }

    public static void setLooperBeans(ArrayList<LooperBean> looperBeans) {
        SharePerferenceUtils.looperBeans = looperBeans;
    }

    public static void setToken(Context context, String token) {
        putString(context,"token",token);
    }


    public static  void setMainBanner(Context context, String value) {
        putString(context, Const.SP_MAIN_BANNER_TYPE,value);
    }

    public static String getMainBanner(Context context) {
        return getString(context, Const.SP_MAIN_BANNER_TYPE, "");
    }

    public static void setMainRecommend(Context context, String result){
        putString(context, Const.SP_MAIN_RECOMMEND, result);
    }

    public static String getMainRecommend(Context context) {
        return getString(context, Const.SP_MAIN_RECOMMEND, "");
    }

    public static String getLat(Context context) {
        return getString(context, Const.SP_LOCATION_LAT,"");
    }

    public static void setLat(Context context,String lat) {
        putString(context,Const.SP_LOCATION_LAT,lat);
    }

    public static void setLng(Context context,String lng) {
        putString(context,Const.SP_LOCATION_LNG,lng);
    }

    public static String getLng(Context context) {
        return getString(context, Const.SP_LOCATION_LNG,"");
    }


}
