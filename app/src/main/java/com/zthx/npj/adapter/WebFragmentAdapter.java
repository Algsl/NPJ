package com.zthx.npj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.TwjcListResponseBean;

import java.util.ArrayList;
import java.util.List;

public class WebFragmentAdapter extends BaseExpandableListAdapter {

    private ArrayList<TwjcListResponseBean.DataBean> mGroup;
    private Context mContext;
    private int mCurrentItem=0;
    private boolean mClicked=false;

    public void setCurrentItem(int currentItem) {
        mCurrentItem= currentItem;
    }

    public void setmClicked(boolean mClicked) {
        this.mClicked = mClicked;
    }

    public WebFragmentAdapter(Context context, ArrayList<TwjcListResponseBean.DataBean> group){
        mContext=context;
        mGroup=group;
    }

    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mGroup.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mGroup.get(i).getList().get(i1);
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
            view=LayoutInflater.from(mContext).inflate(R.layout.item_web_group,viewGroup,false);
            viewHolderGroup.groupTitle=view.findViewById(R.id.item_webGroup_tv_title);
            viewHolderGroup.img=view.findViewById(R.id.item_webGroup_iv_img);
            viewHolderGroup.zhishi=view.findViewById(R.id.item_webGroup_iv_zhishi);
            view.setTag(viewHolderGroup);
        }else{
            viewHolderGroup= (ViewHolderGroup) view.getTag();
        }
        if(mCurrentItem==i && mClicked){
            viewHolderGroup.img.setImageResource(R.drawable.discover_img_artikel);
            viewHolderGroup.zhishi.setImageResource(R.drawable.xiala_zhishi3);
            viewHolderGroup.groupTitle.setTextColor(mContext.getResources().getColor(R.color.app_theme));
        }else{
            viewHolderGroup.img.setImageResource(R.drawable.discover_img_artical1);
            viewHolderGroup.zhishi.setImageResource(R.drawable.xiala_zhishi4);
            viewHolderGroup.groupTitle.setTextColor(mContext.getResources().getColor(R.color.text3));
        }
        viewHolderGroup.groupTitle.setText(mGroup.get(i).getTitle());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderChild viewHolderChild=null;
        if(view==null){
            viewHolderChild=new ViewHolderChild();
            view=LayoutInflater.from(mContext).inflate(R.layout.item_web_item,viewGroup,false);
            viewHolderChild.childTitle=view.findViewById(R.id.item_webItem_tv_title);
            view.setTag(viewHolderChild);
        }else{
            viewHolderChild= (ViewHolderChild) view.getTag();
        }
        viewHolderChild.childTitle.setText(mGroup.get(i).getList().get(i1).getTitle());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public class ViewHolderGroup{
        private TextView groupTitle;
        private ImageView img,zhishi;
    }
    public class ViewHolderChild{
        private TextView childTitle;
    }

}
