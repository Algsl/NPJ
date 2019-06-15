package com.zthx.npj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zthx.npj.base.Const;

/**
 * Created by huangxin on 2019/5/29.
 */

public class SharePerferenceUtils {

    private static SharedPreferences sp;
    private static String SPXMLNAME = "sp_config";

    /**
     * @param ctx
     *            上下文环境
     * @param key
     *            要从config.xml移除节点的name的名称
     */
    public static void removeKey(Context ctx, String key) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    // 1,存储boolean变量方法
    public static void putBoolean(Context ctx, String key, boolean value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    // 2,读取boolean变量方法
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context ctx, String key, String value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    //
    public static void putInt(Context ctx, String key, int value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPXMLNAME, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }



    public static void setUserId(Context context, String id) {
        putString(context,"user_id",id);
    }

    public static String getUserId(Context context) {
        return getString(context, "user_id", "23");
    }


    public static void setToken(Context context, String token) {
        putString(context,"token",token);
    }

    public static String getToken(Context context) {
        return getString(context, "token", "");
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
