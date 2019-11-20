package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AkVideoResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SolutionVideoResponseBean;

import java.util.ArrayList;
import java.util.List;

public class AKVideoAdapter extends RecyclerView.Adapter<AKVideoAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<AkVideoResponseBean.DataBean> mList;
    private ItemClickListener mItemClickListener ;
    private ArrayList<Boolean> isClicked=new ArrayList<>();


    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }
    public AKVideoAdapter(Context context,ArrayList<AkVideoResponseBean.DataBean> list) {
        mContext = context;
        mList = list;
        for(int i=0;i<list.size();i++){
            isClicked.add(false);
        }
        notifyDataSetChanged();
        isClicked.set(0,true);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_video, viewGroup, false);
        return new AKVideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();

                    for(int i=0;i<isClicked.size();i++){
                        isClicked.set(i,false);
                    }
                    isClicked.set(position,true);
                    notifyDataSetChanged();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        viewHolder.mTvTitle.setText(mList.get(i).getTitle());
        viewHolder.mTvTime.setText(mList.get(i).getDuration());
        /*if(mList.get(i).getStatus()==0){
            viewHolder.mTvTime.setText("免费");
        }else{
            viewHolder.mTvTime.setText("付费");
        }*/
        if(isClicked.get(i)){
            viewHolder.selectImg.setImageResource(R.drawable.item_play_theme);
            viewHolder.selectTime.setImageResource(R.drawable.item_play_time_theme);
            viewHolder.mTvTitle.setTextColor(mContext.getResources().getColor(R.color.app_theme));
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.app_theme));
            viewHolder.mTvTitle.setSelected(true);
        }else{
            viewHolder.selectImg.setImageResource(R.drawable.item_play);
            viewHolder.selectTime.setImageResource(R.drawable.item_play_time);
            viewHolder.mTvTitle.setTextColor(mContext.getResources().getColor(R.color.text6));
            viewHolder.mTvTime.setTextColor(mContext.getResources().getColor(R.color.text6));
            viewHolder.mTvTitle.setSelected(false);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvTime;
        ImageView selectImg,selectTime;
        ViewHolder(View itemView) {
            super(itemView);
            selectImg=itemView.findViewById(R.id.item_select_video_iv_img);
            selectTime=itemView.findViewById(R.id.item_select_video_iv_time);
            mTvTitle = itemView.findViewById(R.id.item_select_video_tv_title);
            mTvTime = itemView.findViewById(R.id.item_select_video_tv_time);
        }
    }
}
