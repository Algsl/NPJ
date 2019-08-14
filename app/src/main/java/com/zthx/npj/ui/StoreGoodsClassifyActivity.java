package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsClassifyELVAdapter;
import com.zthx.npj.net.been.CategoryResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreGoodsClassifyActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_storeGoodsClassify_elv)
    ExpandableListView acStoreGoodsClassifyElv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storegoods_classify);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"店铺商品分类");

        getCategory();
    }

    private void getCategory() {
        MainSubscribe.category(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CategoryResponseBean bean=GsonUtils.fromJson(result,CategoryResponseBean.class);
                StoreGoodsClassifyELVAdapter adapter=new StoreGoodsClassifyELVAdapter(StoreGoodsClassifyActivity.this,bean.getData());
                acStoreGoodsClassifyElv.setAdapter(adapter);
                adapter.setOnItemClick(new StoreGoodsClassifyELVAdapter.ItemClick() {
                    @Override
                    public void returnNum(int position) {
                        Log.e("测试", "returnNum: "+position);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

}
