package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AddCommonListAdapter;
import com.zthx.npj.adapter.CommentImgAdapter;
import com.zthx.npj.net.api.URLConstant;
import com.zthx.npj.net.been.AddCommentBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.CommonListBean;
import com.zthx.npj.net.been.CommonListResponseBean;
import com.zthx.npj.net.been.UploadPicsResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.HttpUtils;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.DateUtil;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzimagebox.ZzImageBox;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddToCommentActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_addToComment_mcv_pic)
    MyCircleView acAddToCommentMcvPic;
    @BindView(R.id.ac_addToComment_tv_name)
    TextView acAddToCommentTvName;
    @BindView(R.id.ac_addToComment_iv_star1)
    ImageView acAddToCommentIvStar1;
    @BindView(R.id.ac_addToComment_iv_star2)
    ImageView acAddToCommentIvStar2;
    @BindView(R.id.ac_addToComment_iv_star3)
    ImageView acAddToCommentIvStar3;
    @BindView(R.id.ac_addToComment_iv_star4)
    ImageView acAddToCommentIvStar4;
    @BindView(R.id.ac_addToComment_iv_star5)
    ImageView acAddToCommentIvStar5;
    @BindView(R.id.ac_addToComment_ll_star)
    LinearLayout acAddToCommentLlStar;
    @BindView(R.id.ac_addToComment_tv_content)
    TextView acAddToCommentTvContent;
    @BindView(R.id.item_custom_comment_rv)
    RecyclerView itemCustomCommentRv;
    @BindView(R.id.item_custom_comment_rv1)
    RecyclerView itemCustomCommentRv1;
    @BindView(R.id.item_custom_zp)
    LinearLayout itemCustomZp;
    @BindView(R.id.ac_addToComment_tv_date)
    TextView acAddToCommentTvDate;
    @BindView(R.id.ac_addToComment_tv_xiaofei)
    TextView acAddToCommentTvXiaofei;
    @BindView(R.id.ac_addToComment_et_content)
    EditText acAddToCommentEtContent;
    @BindView(R.id.ac_addToComment_iv_img)
    ZzImageBox acAddToCommentIvImg;
    @BindView(R.id.ac_orderComment_rb_goodsStar)
    RatingBar acOrderCommentRbGoodsStar;
    @BindView(R.id.ac_orderComment_tv_goods)
    TextView acOrderCommentTvGoods;

    private String goods_start="5";
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String goodsId;
    private String type;
    private String comId;
    private List<String> paths = new ArrayList<>();
    private static final int CHOOSE_PHOTO = 1;
    private String img="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_comment);
        ButterKnife.bind(this);
        goodsId=getIntent().getStringExtra("goodsId");
        type=getIntent().getStringExtra("type");

        if(type.equals("0")){
            type="1";
        }

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "评价管理");
        titleThemeTvRight.setText("发表");

        //getComment();
        getCommonList();

        acOrderCommentRbGoodsStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch (String.valueOf(v)) {
                    case "1.0":
                        acOrderCommentTvGoods.setText("差");
                        break;
                    case "2.0":
                        acOrderCommentTvGoods.setText("较差");
                        break;
                    case "3.0":
                        acOrderCommentTvGoods.setText("一般");
                        break;
                    case "4.0":
                        acOrderCommentTvGoods.setText("好");
                        break;
                    case "5.0":
                        acOrderCommentTvGoods.setText("很好");
                        break;
                }
                goods_start = String.valueOf(v);
            }
        });

        acAddToCommentIvImg.setOnImageClickListener(new ZzImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String url, String realPath, int realType, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String url, String realPath, int realType) {
                paths.remove(position);
                acAddToCommentIvImg.removeImage(position);
            }

            @Override
            public void onAddClick() {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CHOOSE_PHOTO);*/
                ImageSelectorUtils.openPhoto(AddToCommentActivity.this,CHOOSE_PHOTO,false,5-paths.size());
            }
        });
    }

    private void getCommonList() {
        CommonListBean bean=new CommonListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goodsId);
        bean.setType(type);
        SetSubscribe.commonList(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                CommonListResponseBean bean1=GsonUtils.fromJson(result,CommonListResponseBean.class);
                final CommonListResponseBean.DataBean data=bean1.getData();
                comId=data.getList().getId()+"";
                acAddToCommentTvName.setText(data.getList().getNick_name());
                if(data.getList().getHead_img().split("/")[0].equals("http:")){
                    Glide.with(AddToCommentActivity.this).load(Uri.parse(data.getList().getHead_img())).into(acAddToCommentMcvPic);
                }else{
                    Glide.with(AddToCommentActivity.this).load(Uri.parse("http://app.npj-vip.com"+data.getList().getHead_img())).into(acAddToCommentMcvPic);
                }
                Double star=(data.getList().getGoods_star()+data.getList().getLogistics_star()+data.getList().getService_star())/3.0;
                switch ((int)Math.floor(star)) {
                    case 1:
                        acAddToCommentIvStar1.setImageResource(R.drawable.item_location_store_star);
                        break;
                    case 2:
                        acAddToCommentIvStar1.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar2.setImageResource(R.drawable.item_location_store_star);
                        break;
                    case 3:
                        acAddToCommentIvStar1.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar2.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar3.setImageResource(R.drawable.item_location_store_star);
                        break;
                    case 4:
                        acAddToCommentIvStar1.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar2.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar3.setImageResource(R.drawable.item_location_store_star);
                       acAddToCommentIvStar4.setImageResource(R.drawable.item_location_store_star);
                        break;
                    case 5:
                        acAddToCommentIvStar1.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar2.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar3.setImageResource(R.drawable.item_location_store_star);
                       acAddToCommentIvStar4.setImageResource(R.drawable.item_location_store_star);
                        acAddToCommentIvStar5.setImageResource(R.drawable.item_location_store_star);
                }
                acAddToCommentTvContent.setText(data.getList().getContent());
                acAddToCommentTvDate.setText(DateUtil.timeslashData(data.getList().getCreate_time()+""));
                GridLayoutManager layoutManager=new GridLayoutManager(AddToCommentActivity.this,3);
                itemCustomCommentRv.setLayoutManager(layoutManager);
                CommentImgAdapter adapter=new CommentImgAdapter(AddToCommentActivity.this,data.getList().getImg());
                itemCustomCommentRv.setAdapter(adapter);
                adapter.setOnItemClickListener(new CommentImgAdapter.ItemClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        Intent intent=new Intent(AddToCommentActivity.this,ImageLookActivity.class);
                        intent.putExtra("position",(position+1));
                        intent.putExtra("imgs",data.getList().getImg());
                        startActivity(intent);
                    }
                });

                LinearLayoutManager layoutManager1=new LinearLayoutManager(AddToCommentActivity.this);
                itemCustomCommentRv1.setLayoutManager(layoutManager1);
                AddCommonListAdapter adapter1=new AddCommonListAdapter(AddToCommentActivity.this,data.getList2());
                itemCustomCommentRv1.setAdapter(adapter1);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CHOOSE_PHOTO){
            if(resultCode!=0){
                ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                for(int i=0;i<images.size();i++){
                    paths.add(compress(images.get(i)));
                    acAddToCommentIvImg.addImage(compress(images.get(i)));
                }
            }
        }
    }

    @OnClick(R.id.title_theme_tv_right)
    public void onViewClicked() {
        if(acAddToCommentEtContent.getText().toString().trim().equals("")){
            showToast("请填写对商品的评价");
        }/*else{
            if(paths==null || paths.size()<1){
                img="";
            }else{
                HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                        UploadPicsResponseBean.DataBean data = bean.getData();
                        img = data.getImg();
                        commentConfirm();
                    }
                });
            }
        }*/
        else if(paths==null || paths.size()<1){
            showToast("请上传图片");
        }else{
            HttpUtils.uploadMoreImg(URLConstant.REQUEST_URL1, paths, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadPicsResponseBean bean = GsonUtils.fromJson(response.body().string(), UploadPicsResponseBean.class);
                    UploadPicsResponseBean.DataBean data = bean.getData();
                    img = data.getImg();
                    commentConfirm();
                }
            });
        }
    }

    private void commentConfirm() {
        AddCommentBean bean=new AddCommentBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCom_id(comId);
        bean.setGoods_star(goods_start);
        bean.setContent(acAddToCommentEtContent.getText().toString().trim());
        bean.setImg(img);
        SetSubscribe.addComment(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                showToast("追加评论成功");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    public String compress(String path){
        File file=new File(path);
        Bitmap compressBitmap;
        /*if (file.length()>=2.5*1024*1024){//从相册中选择照片，2.5M以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 3;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else */if(file.length()>=600*1024){//从相册中选择照片，600k以上的用2压缩
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else{
            compressBitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        compressBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        int options1 = 90;
        while (bos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bos.reset(); // 重置baos即清空baos
            compressBitmap.compress(Bitmap.CompressFormat.JPEG, options1, bos);// 这里压缩options%，把压缩后的数据存放到baos中
            options1 -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(bos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        compressBitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        String bmString="";
        try {
            File bmFile=new File(getExternalCacheDir(), System.currentTimeMillis()+".jpg");
            FileOutputStream fos = new FileOutputStream(bmFile);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bmString=bmFile.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmString;
    }
}
