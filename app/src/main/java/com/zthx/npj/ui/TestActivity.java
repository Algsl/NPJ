package com.zthx.npj.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zthx.npj.R;
import com.zthx.npj.net.been.OrderPushBean;
import com.zthx.npj.net.been.OrderPushResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.marquee.AppBus;
import com.zthx.npj.utils.marquee.Looper;
import com.zthx.npj.utils.marquee.LooperBean;
import com.zthx.npj.utils.marquee.LooperImageView;
import com.zthx.npj.utils.marquee.LooperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TestActivity extends AppCompatActivity{

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.marquee_image_view)
    LooperImageView marqueeImageView;
    @BindView(R.id.marquee_text_view)
    LooperTextView marqueeTextView;
    @BindView(R.id.rl_marquee_view)
    RelativeLayout rlMarqueeView;
    private List<LooperBean> looperBeenList;
    private LooperImageView mMarqueeImageView;
    private LooperTextView mMarqueeTextView;
    private int position;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        AppBus.getInstance().register(this);
        initView();

        looperBeenList = generateTips();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMarqueeImageView.setTipList(looperBeenList, position);
        mMarqueeTextView.setTipList(looperBeenList, position);
    }

    public void setPostion(Looper looper) {
        this.position = looper.getPosition();
    }

    private void initView() {
        mMarqueeImageView = (LooperImageView) findViewById(R.id.marquee_image_view);
        mMarqueeTextView = (LooperTextView) findViewById(R.id.marquee_text_view);
    }

    String[] strs = {"http://app.npj-vip.com/public/upload/20190824/2624b76cf7f8aa60133a9ef3e882fcc3.jpg", "http://app.npj-vip.com/public/upload/20190824/1fd1855d6cad1d9f1b183d7e5658bea5.jpg",
            "http://app.npj-vip.com/public/upload/20190824/98bb5f52aa525ad28666fedc27bbd0e2.jpg", "http://app.npj-vip.com/public/upload/20190824/f79aca1bbafec425d3637bbf54f15ccf.jpg",
            "http://app.npj-vip.com/public/upload/20190824/46aabd1dbe02702eaa84461fe2328ace.jpg"};

    private List<LooperBean> generateTips() {
        List<LooperBean> tips = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LooperBean looperBean = new LooperBean();
            looperBean.setMsg("huangxiaoguo" + i);
            looperBean.setHeadImg(strs[i]);
            tips.add(looperBean);
        }
        List<LooperBean> lists=SharePerferenceUtils.getLooperBeans();
        for(int i=0;i<lists.size();i++){
            Log.e("测试", "generateTips: "+lists.get(i).getMsg()+"\n");
        }
        return tips;
    }
}

