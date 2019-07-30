package com.zthx.npj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zthx.npj.R;

import java.util.List;

public class WebFragmentAdapter extends BaseExpandableListAdapter {

    private List<String> mGroup;
    private List<List<String>> mChild;
    private Context mContext;

    public WebFragmentAdapter(Context context,List<String> group,List<List<String>> child){
        mContext=context;
        mGroup=group;
        mChild=child;
    }

    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChild.size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mChild.get(i).get(i1);
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
        View view1=null;
        if(view1==null){
            view1=LayoutInflater.from(mContext).inflate(R.layout.item_web_group,null);
            viewHolderGroup.groupTitle=view1.findViewById(R.id.item_webGroup_tv_title);
            view1.setTag(viewHolderGroup);
        }else{
            viewHolderGroup= (ViewHolderGroup) view1.getTag();
        }
        viewHolderGroup.groupTitle.setText(mGroup.get(i));
        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderChild viewHolderChild=null;
        if(view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.item_web_item,null);
            viewHolderChild.childTitle=view.findViewById(R.id.item_webItem_tv_title);
            view.setTag(viewHolderChild);
        }else{
            viewHolderChild= (ViewHolderChild) view.getTag();
        }
        viewHolderChild.childTitle.setText(mChild.get(i).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public class ViewHolderGroup{
        private TextView groupTitle;
    }
    public class ViewHolderChild{
        private TextView childTitle;
    }
}
