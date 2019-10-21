package com.zthx.npj.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.adapter.GradViewAdapter;
import com.zthx.npj.entity.JsonBean;
import com.zthx.npj.net.been.UpLoadPicResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;

import org.json.JSONArray;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyCustomUtils {


    //等级图片显示
    public static void showLevelImg(int level,ImageView img){
        switch (level) {
            case 0: img.setImageResource(R.drawable.level0);break;
            case 1: img.setImageResource(R.drawable.level1);break;
            case 2: img.setImageResource(R.drawable.level2);break;
            case 3: img.setImageResource(R.drawable.level3);break;
            case 4: img.setImageResource(R.drawable.level4);break;
            case 5: img.setImageResource(R.drawable.level5);break;
            case 6: img.setImageResource(R.drawable.level6);break;
            case 7: img.setImageResource(R.drawable.level7);break;
            case 8: img.setImageResource(R.drawable.level8);break;
            case 9: img.setImageResource(R.drawable.level9);break;
            case 10: img.setImageResource(R.drawable.level10);break;
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
        String[] strs={"斤","吨","件","袋","箱","克","个","颗"};
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

}
