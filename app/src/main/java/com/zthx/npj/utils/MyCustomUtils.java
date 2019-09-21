package com.zthx.npj.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyCustomUtils {

    //路径转为Uri
    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(mediaUri,
                null,
                MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                new String[] {path.substring(path.lastIndexOf("/") + 1)},
                null);

        Uri uri = null;
        if(cursor.moveToFirst()) {
            uri = ContentUris.withAppendedId(mediaUri,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
        }
        cursor.close();
        return uri;
    }

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


    //地址选择器
    public static void chooseAddress(Context context,TextView textView){
        initJsonData(context);
        showPickerView(context,textView);
    }
    private static ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private static void initJsonData(Context context) {//解析数据 （省市区三级联动）
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
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
            options2Items.add(CityList);
            options3Items.add(Province_AreaList);
        }
    }




    private static void showPickerView(Context context,final TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(options1Items.get(options1).getPickerViewText() + "  "
                        + options2Items.get(options1).get(options2) + "  "
                        + options3Items.get(options1).get(options2).get(options3));
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

    public static ArrayList<JsonBean> parseData(String result) {//Gson 解析
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


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
       // avatarUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP_SMALL_PICTURE);
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //intent.putExtra("return-data", true);

        //uritempFile为Uri类变量，实例化uritempFile
        //uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
       // intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //startActivityForResult(intent, CROP_RESULT_CODE);
    }


    private File bitmapToFile(Bitmap bitmap) {
        File file = new File(Environment.getDataDirectory() + "/1234");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File createOriImageFile() throws IOException {
        String imgNameOri = "HomePic_" + new SimpleDateFormat(
                "yyyyMMdd_HHmmss").format(new Date());
        //getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture"
        File pictureDirOri = new File("");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();
        }
        File image = File.createTempFile(
                imgNameOri,         /* prefix */
                ".jpg",             /* suffix */
                pictureDirOri       /* directory */
        );
        //avatarUri = Uri.parse(image.getAbsolutePath());
        return image;
    }

    public static File saveBitmapFile(Bitmap bitmap) {

        File file = new File("storage/emulated/legacy/s.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


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
}
