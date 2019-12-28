package com.zthx.npj.utils;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yzq.zxinglibrary.common.Constant;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.net.been.SystemMsgResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.LoginActivity;
import com.zthx.npj.ui.MainActivity;
import com.zthx.npj.ui.MembershipPackageActivity;
import com.zthx.npj.ui.MyAttestationActivity;
import com.zthx.npj.view.CommonDialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCustomUtils {

    public static String path="";



    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    //位置转为经纬度
    public static Address getGeoPointBystr(Context context,String str) {
        Address address_temp = null;
        if (str != null) {
            Geocoder gc = new Geocoder(context, Locale.CHINA);
            List<Address> addressList = null;
            try {
                addressList = gc.getFromLocationName(str, 1);
                if (!addressList.isEmpty()) {
                    address_temp = addressList.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address_temp;
    }

    //等级图片显示
    public static void showLevelImg(int level,ImageView img){
        switch (level) {
            case 0: img.setImageResource(R.drawable.level0);break;
            case 1: img.setImageResource(R.drawable.level1);break;

            case 2: img.setImageResource(R.drawable.level2);break;
            case 3: img.setImageResource(R.drawable.level3);break;
            case 8: img.setImageResource(R.drawable.level8);break;
            case 5: img.setImageResource(R.drawable.level5);break;

            case 6: img.setImageResource(R.drawable.level6);break;
            case 7: img.setImageResource(R.drawable.level7);break;
            case 4: img.setImageResource(R.drawable.level4);break;
            case 9: img.setImageResource(R.drawable.level9);break;

            case 10: img.setImageResource(R.drawable.level10);break;
        }
    }

    public static void showLevelImg(int city_level,int boss_level,int team_level,int level,ImageView img){
        if(city_level!=0){
            img.setImageResource(R.drawable.level10);
        }else if(boss_level!=0){
            switch (boss_level){
                case 1: img.setImageResource(R.drawable.level6);break;
                case 2: img.setImageResource(R.drawable.level7);break;
                case 3: img.setImageResource(R.drawable.level4);break;
                case 4: img.setImageResource(R.drawable.level9);break;
            }
        }else if(team_level!=0){
            switch (team_level){
                case 1: img.setImageResource(R.drawable.level2);break;
                case 2: img.setImageResource(R.drawable.level3);break;
                case 3: img.setImageResource(R.drawable.level8);break;
                case 4: img.setImageResource(R.drawable.level5);break;
            }
        }else{
            if(level==0){
                img.setImageResource(R.drawable.level0);
            }else{
                img.setImageResource(R.drawable.level1);
            }
        }
    }

    public static void showLevelText(int city_level,int boss_level,int team_level,int level,TextView tv){
        if(city_level!=0){
            tv.setText("城市代言人");
        }else if(boss_level!=0){
            switch (boss_level){
                case 1:tv.setText("天使股东代言人");break;
                case 2:tv.setText("金牌股东代言人");break;
                case 3:tv.setText("钻石股东代言人");break;
                case 4:tv.setText("首席股东代言人");break;
            }
        }else if(team_level!=0){
            switch (team_level){
                case 1:tv.setText("天使代言人");break;
                case 2:tv.setText("金牌代言人");break;
                case 3:tv.setText("钻石代言人");break;
                case 4:tv.setText("首席代言人");break;
            }
        }else{
            if(level==0){
                tv.setText("普通用户");
            }else{
                tv.setText("VIP代言人");
            }
        }
    }


    //获取视频第一帧图片
    public static Bitmap getVideoThumbnail(String url) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //（）根据文件路径获取缩略图
            //retriever.setDataSource(filePath);
            retriever.setDataSource(url, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        Log.v("bitmap", "bitmap="+bitmap);
        return bitmap;
    }



    //视频拼接
    public static String listToString(List<String> lists){
        String str="";
        for(int i=0;i<lists.size();i++){
            if(i==lists.size()-1){
                str+=lists.get(i);
            }else{
                str+=lists.get(i)+",";
            }
        }
        return str;
    }
    public static void splitUrl(List<String> str1,List<String> str2,List<String> str3){
        for (String str : str1) {
            if (str.split("http://app.npj-vip.com").length == 1) {//普通图片需上传
                str2.add(str);
            } else {//全链接形式的图片不用上传
                str3.add(str.split("http://app.npj-vip.com")[1]);
            }
        }
    }

    //单位选择器显示
    private static ArrayList<String> unit = new ArrayList<>(); //省
    public static void showUnitPickerView(Context context, final TextView textView, final TextView textView2,final TextView textView3) {
        String[] strs={"斤","公斤","吨","件","袋","箱","克","个","颗"};
        for(int i=0;i<strs.length;i++){
            unit.add(strs[i]);
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(unit.get(options1));
                if(textView2!=null){
                    textView2.setText(unit.get(options1));
                }
                textView3.setText("元/"+unit.get(options1));
            }
        }).setTitleText("单位选择").setDividerColor(Color.BLACK).setTextColorCenter(Color.BLACK).setContentTextSize(20).build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(unit);//三级选择器
        pvOptions.show();
    }


    //根据经纬度显示详细地址
    public static void getLocateinfo(String lat, String lng, final TextView ...tvs) {
        LatLng latLng=new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }
            @Override public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if(tvs.length==1){
                    tvs[0].setText(reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
                }else{
                    tvs[0].setText(reverseGeoCodeResult.getAddress());
                    tvs[1].setText(reverseGeoCodeResult.getSematicDescription());
                }
            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    //采样点缩放
    public static void compress(int inSampleSize,File file,ImageView img) {
       /* File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "test.jpg");*/
       File originFile=new File(Environment.getExternalStorageDirectory(),file.getName());

        BitmapFactory.Options options = new BitmapFactory.Options();

        //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
        options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        Bitmap resultBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        img.setImageBitmap(resultBitmap);

        File file3=new File(Environment.getExternalStorageDirectory(),"out.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file3);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //尺寸缩放
    public static void compress(Bitmap bitmap,ImageView img) {
        int radio = 4;
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / radio, bitmap.getHeight() / radio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        RectF rectF = new RectF(0, 0, bitmap.getWidth() / radio, bitmap.getHeight() / radio);
        //将原图画在缩放之后的矩形上
        canvas.drawBitmap(bitmap, null, rectF, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        img.setImageBitmap(result);
    }


    public static ArrayList<String> getImgStr(String htmlStr) {
        ArrayList<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    //正则匹配
    public static boolean isRegular(String str,String type) {
        String regular="";
        switch (type){
            case "mobile":
                regular="^1[3456789]\\d{9}$";
                break;
            case "IdCard":
                regular="(^\\d{18})|(^\\d{15})|(^\\d{17}(\\d|X|x))$";
                break;
        }
        Pattern p = Pattern.compile(regular);
        Matcher m =p.matcher(str);
        return m.matches();
    }
    public static void loginDialog(final Context context){
        CommonDialog dialog = new CommonDialog(context, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            }
        });
        dialog.setTitle("提示");
        dialog.setPositiveButton("去登录");
        dialog.show();
    }
    public static void showDialog(final Context context) {
        CommonDialog commonDialog = new CommonDialog(context, R.style.dialog, "您的账号已在其他客户端登录",true, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{
                    BaseApp.getApp().onTerminate();
                }
            }
        });
        commonDialog.setCanceledOnTouchOutside(false);
        commonDialog.setCancelable(false);
        commonDialog.setTitle("提示");
        commonDialog.setNegativeButton("退出");
        commonDialog.setPositiveButton("重新登录");
        commonDialog.show();
    }

    public static void setSystemMsg(final Context context, String user_id, String token) {
        if(!user_id.equals("") && !token.equals("")){
            MainSubscribe.systemMsg(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    SystemMsgResponseBean bean=GsonUtils.fromJson(result,SystemMsgResponseBean.class);
                    final ArrayList<SystemMsgResponseBean.DataBean> data=bean.getData();
                    SharePerferenceUtils.setMessageNum(context,data.size());
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }
    }

}
