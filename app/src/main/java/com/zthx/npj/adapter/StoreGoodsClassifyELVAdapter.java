package com.zthx.npj.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CategoryResponseBean;

import java.util.ArrayList;

public class StoreGoodsClassifyELVAdapter extends BaseExpandableListAdapter {

    private ArrayList<CategoryResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClick mItemClick;
    private int mCurrentItem=0;
    private boolean mClicked=false;

    public int getmCurrentItem() {
        return mCurrentItem;
    }

    public void setmCurrentItem(int mCurrentItem) {
        this.mCurrentItem = mCurrentItem;
    }

    public boolean ismClicked() {
        return mClicked;
    }

    public void setmClicked(boolean mClicked) {
        this.mClicked = mClicked;
    }

    public StoreGoodsClassifyELVAdapter(Context context, ArrayList<CategoryResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClick(ItemClick itemClick){
        mItemClick=itemClick;
    }


    public interface ItemClick{
        void childId(int position,String title);
        void groupId(int position,String title);
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return mList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mList.get(i).getChild().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderGroup viewHolderGroup=null;
        if(view==null){
            viewHolderGroup=new ViewHolderGroup();
            view=LayoutInflater.from(mContext).inflate(R.layout.activity_storegoods_classify_group,viewGroup,false);
            viewHolderGroup.title=view.findViewById(R.id.ac_storeGoodsClassify_tv_title);
            viewHolderGroup.arrow=view.findViewById(R.id.ac_storeGoodsClassify_iv);
            view.setTag(viewHolderGroup);
        }else{
            viewHolderGroup= (ViewHolderGroup) view.getTag();
        }
        if(mCurrentItem==i && mClicked){
           viewHolderGroup.arrow.setImageResource(R.drawable.xiala_zhishi2);
        }else{
            viewHolderGroup.arrow.setImageResource(R.drawable.goods_detail_select);
        }
        viewHolderGroup.title.setText(mList.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final int a=i;
        ViewHolderChild viewHolderChild=null;
        if(view==null){
            viewHolderChild=new ViewHolderChild();
            view=LayoutInflater.from(mContext).inflate(R.layout.activity_storegoods_classify_child,viewGroup,false);
            viewHolderChild.seeAll=view.findViewById(R.id.ac_storeGoodsClassify_tv_seeAll);
            viewHolderChild.rv=view.findViewById(R.id.ac_storeGoodsClassify_rv);
            view.setTag(viewHolderChild);
        }else{
            viewHolderChild= (ViewHolderChild) view.getTag();
        }
        GridLayoutManager layoutManager=new GridLayoutManager(mContext,3);
        viewHolderChild.rv.setLayoutManager(layoutManager);
        StoreGoodsClassifyAdapter adapter=new StoreGoodsClassifyAdapter(mContext,mList.get(i).getChild());
        viewHolderChild.rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new StoreGoodsClassifyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mItemClick.childId((int)mList.get(a).getChild().get(position).getId(),mList.get(a).getChild().get(position).getName());
            }
        });
        viewHolderChild.seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.groupId((int)mList.get(i).getId(),mList.get(i).getName());
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class ViewHolderGroup{
        private TextView title;
        private ImageView arrow;
    }
    public class ViewHolderChild{
        private RecyclerView rv;
        private TextView seeAll;
    }
}
