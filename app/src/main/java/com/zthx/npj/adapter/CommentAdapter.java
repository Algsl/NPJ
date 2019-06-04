package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.utils.DateUtil;
import com.zthx.npj.view.MyCircleView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private ArrayList<CommentResponseBean.DataBean> mList;
    private Context mContext;

    private CommentAdapter.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public CommentAdapter(Context context, ArrayList<CommentResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_comment, viewGroup, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
// 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        Glide.with(mContext).load(mList.get(i).getHead_img()).into(viewHolder.mIvHead);
        viewHolder.mTvName.setText(mList.get(i).getNick_name());
        switch (mList.get(i).getGoods_star()) {
            case 1:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 2:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 3:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 4:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star4.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 5:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star4.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star5.setBackgroundResource(R.drawable.item_location_store_star);
        }
        viewHolder.mTvContent.setText(mList.get(i).getContent());
        viewHolder.mTvDate.setText(DateUtil.timeslashData(mList.get(i).getCreate_time()+""));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MyCircleView mIvHead;
        TextView mTvName;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;
        TextView mTvContent;
        TextView mTvDate;

        ViewHolder(View itemView) {
            super(itemView);
            mIvHead = itemView.findViewById(R.id.item_comment_mcv_pic);
            mTvName = itemView.findViewById(R.id.item_comment_tv_name);
            star1 = itemView.findViewById(R.id.item_comment_iv_star1);
            star2 = itemView.findViewById(R.id.item_comment_iv_star2);
            star3 = itemView.findViewById(R.id.item_comment_iv_star3);
            star4 = itemView.findViewById(R.id.item_comment_iv_star4);
            star5 = itemView.findViewById(R.id.item_comment_iv_star5);

            mTvContent = itemView.findViewById(R.id.item_comment_tv_content);
            mTvDate = itemView.findViewById(R.id.item_comment_tv_date);
        }
    }
}
