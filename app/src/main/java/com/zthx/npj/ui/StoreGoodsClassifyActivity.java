package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import butterknife.OnClick;

public class StoreGoodsClassifyActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_storeGoodsClassify_elv)
    ExpandableListView acStoreGoodsClassifyElv;
    @BindView(R.id.ac_storeGoodsClassify_rl)
    RelativeLayout acStoreGoodsClassifyRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storegoods_classify);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "店铺商品分类");

        getCategory();
    }

    private void getCategory() {
        MainSubscribe.category(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                final CategoryResponseBean bean = GsonUtils.fromJson(result, CategoryResponseBean.class);
                final StoreGoodsClassifyELVAdapter adapter = new StoreGoodsClassifyELVAdapter(StoreGoodsClassifyActivity.this, bean.getData());
                acStoreGoodsClassifyElv.setAdapter(adapter);
                acStoreGoodsClassifyElv.setGroupIndicator(null);
                acStoreGoodsClassifyElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int i) {
                        for (int j = 0, count = acStoreGoodsClassifyElv.getExpandableListAdapter().getGroupCount(); j < count; j++) {
                            if (i != j) {
                                if (bean.getData().get(j).isSelected()) {
                                    bean.getData().get(j).setSelected(false);
                                }
                                adapter.notifyDataSetChanged();
                                acStoreGoodsClassifyElv.collapseGroup(j);
                            }
                        }
                    }
                });
                acStoreGoodsClassifyElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                        bean.getData().get(i).setSelected(!bean.getData().get(i).isSelected());
                        adapter.setmCurrentItem(i);
                        adapter.setmClicked(bean.getData().get(i).isSelected());
                        adapter.notifyDataSetChanged();
                        return false;
                    }
                });
                adapter.setOnItemClick(new StoreGoodsClassifyELVAdapter.ItemClick() {
                    @Override
                    public void childId(int position, String title) {
                        Intent intent = getIntent();
                        intent.putExtra("cate_id", position + "");
                        intent.putExtra("title", title);
                        intent.putExtra("type", "2");
                        setResult(1, intent);
                        finish();
                    }

                    @Override
                    public void groupId(int position, String title) {
                        Intent intent = getIntent();
                        intent.putExtra("cate_id", position + "");
                        intent.putExtra("title", title);
                        intent.putExtra("type", "1");
                        setResult(1, intent);
                        finish();
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick(R.id.ac_storeGoodsClassify_rl)
    public void onViewClicked() {
        Intent intent=getIntent();
        setResult(2,intent);
        finish();
    }
}
