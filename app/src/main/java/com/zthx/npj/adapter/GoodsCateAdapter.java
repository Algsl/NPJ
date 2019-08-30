package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.GoodsCateResponseBean;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

public class GoodsCateAdapter extends BaseExpandableListAdapter {

    private ArrayList<GoodsCateResponseBean.DataBean> mList;
    private Context mContext;

    public GoodsCateAdapter(Context context, ArrayList<GoodsCateResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }


    public interface ItemClickListener{
        void groupMsg(String cate_id,String cate_name);
        void childMsg(String cate_id,String cate_name);
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
        GroupHolder groupHolder=null;
        if(view==null){
            groupHolder=new GroupHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.activity_goodscate_group,viewGroup,false);
            groupHolder.groupText=view.findViewById(R.id.ac_goodsCate_tv_title);
            view.setTag(groupHolder);
        }else{
            groupHolder= (GroupHolder) view.getTag();
        }
        groupHolder.groupText.setText(mList.get(i).getName());
        mItemClickListener.groupMsg(mList.get(i).getId()+"",mList.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder childHolder=null;
        if(view==null){
            childHolder=new ChildHolder();
            view=LayoutInflater.from(mContext).inflate(R.layout.activity_goodscate_child,viewGroup,false);
            childHolder.childRv=view.findViewById(R.id.ac_goodsCate_rv);
            view.setTag(childHolder);
        }else{
            childHolder= (ChildHolder) view.getTag();
        }

        GridLayoutManager layoutManager=new GridLayoutManager(mContext,3);
        childHolder.childRv.setLayoutManager(layoutManager);
        GoodsCateItemAdapter adapter=new GoodsCateItemAdapter(mContext,mList.get(i).getChild());
        childHolder.childRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new GoodsCateItemAdapter.ItemClickListener() {
            @Override
            public void onResult(String id, String name) {
                mItemClickListener.childMsg(id,name);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    public class GroupHolder{
        private TextView groupText;
    }
    public class ChildHolder{
        private RecyclerView childRv;
    }
}
