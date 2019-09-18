package com.zthx.npj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

public class GradViewAdapter extends BaseAdapter {

    private Context mContext;
    private int[] mImages;
    private String[] mString;

    public GradViewAdapter(Context context,int[] imgs){
        mContext=context;
        mImages=imgs;
    }
    public GradViewAdapter(Context context,String[] str){
        mContext=context;
        mString=str;
    }

    @Override
    public int getCount() {
        //return mImages.length;
        return mString.length;
    }

    @Override
    public Object getItem(int i) {
        return mImages[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.item_gridview,viewGroup,false);
            holder=new ViewHolder();
            //holder.img=view.findViewById(R.id.image);
            holder.str=view.findViewById(R.id.str);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        //holder.img.setImageResource(mImages[i]);
        holder.str.setText(mString[i]);
        return view;
    }


    class ViewHolder{
        private ImageView img;
        private TextView str;
    }
}
