package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserOneResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserOneAdapter extends RecyclerView.Adapter<UserOneAdapter.ViewHolder> {

    private ArrayList<UserOneResponseBean.DataBean> mList;
    private Context mContext;

    public UserOneAdapter(Context context,ArrayList<UserOneResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_zjdyr,viewGroup,false);
        return new UserOneAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mList.get(i).getHead_img()!=null){
            if(mList.get(i).getHead_img().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(mList.get(i).getHead_img())).into(viewHolder.headImg);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getHead_img())).into(viewHolder.headImg);
            }
        }
        viewHolder.nickName.setText(mList.get(i).getNick_name());
        viewHolder.createTime.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:SS").format(new Date(mList.get(i).getCreate_time()*1000)));
        MyCustomUtils.showLevelImg(mList.get(i).getCity_level(),mList.get(i).getBoss_level(),mList.get(i).getTeam_level(),mList.get(i).getLevel(),viewHolder.level);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView headImg,level;
        TextView nickName,createTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headImg=itemView.findViewById(R.id.item_zjdyr_riv_headImg);
            nickName=itemView.findViewById(R.id.item_zjdyr_tv_nickName);
            createTime=itemView.findViewById(R.id.item_zjdyr_tv_createTime);
            level=itemView.findViewById(R.id.item_zjdyr_iv_level);

        }
    }
}
