package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.BaojiaListResponseBean;
import com.zthx.npj.net.been.BaojiaUserListResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaojiaUserListAdapter extends RecyclerView.Adapter<BaojiaUserListAdapter.ViewHolder> {

    private ArrayList<BaojiaUserListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public BaojiaUserListAdapter(Context context, ArrayList<BaojiaUserListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onSeeClick(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_quotation_people_list,viewGroup,false);
        return new BaojiaUserListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSeeClick(position);
                }
            });
        }
        Glide.with(mContext).load(Uri.parse(mList.get(i).getHead_img())).into(viewHolder.goodsImg);
        viewHolder.name.setText(mList.get(i).getNick_name());
        viewHolder.day.setText("已持续经营365天");
        viewHolder.time.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
        MyCustomUtils.showLevelImg((int)mList.get(i).getLevel(),viewHolder.level);
        /*switch (mList.get(i).getLevel()+""){
            case "0":
                viewHolder.level.setImageResource(R.drawable.level0);break;
            case "1":
                viewHolder.level.setImageResource(R.drawable.level1);break;
            case "2":
                viewHolder.level.setImageResource(R.drawable.level2);break;
            case "3":
                viewHolder.level.setImageResource(R.drawable.level3);break;
            case "4":
                viewHolder.level.setImageResource(R.drawable.level4);break;
            case "5":
                viewHolder.level.setImageResource(R.drawable.level5);break;
            case "6":
                viewHolder.level.setImageResource(R.drawable.level6);break;
            case "7":
                viewHolder.level.setImageResource(R.drawable.level7);break;
            case "8":
                viewHolder.level.setImageResource(R.drawable.level8);break;
            case "9":
                viewHolder.level.setImageResource(R.drawable.level9);break;
            case "10":
                viewHolder.level.setImageResource(R.drawable.level10);break;
        }*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,level;
        TextView name,time,day,see;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_quotation_people_head_pic);
            name=itemView.findViewById(R.id.item_quotation_people_tv_name);
            level=itemView.findViewById(R.id.item_quotaition_peopleList_tv_level);
            time=itemView.findViewById(R.id.item_quotation_people_tv_time);
            day=itemView.findViewById(R.id.item_quotation_people_tv_day);
            see=itemView.findViewById(R.id.item_quotation_people_tv_see);
            //seeInfo=itemView.findViewById(R.id.item_quotation_tv_seeInfo);
        }
    }
}
