package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.UserAppLogResponseBean;
import com.zthx.npj.net.been.UserAppResponseBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AskForPartnerAdapter extends RecyclerView.Adapter<AskForPartnerAdapter.ViewHolder>{
    private List<UserAppLogResponseBean.DataBean> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public AskForPartnerAdapter(Context context, List<UserAppLogResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ask_for_partner, viewGroup, false);
        return new AskForPartnerAdapter.ViewHolder(view);
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

        if(mList.get(i).getStatus()==0){
            viewHolder.askStatus.setText("等待审核");
        }else{
            viewHolder.askStatus.setText("已审核");
            viewHolder.askStatus.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.askStatus.setTextColor(mContext.getResources().getColor(R.color.text9));
        }
        if (mList!= null && mList.size() > 0) {
            Log.e("测试", "onBindViewHolder: "+mList.get(i).getCreate_time() );
            Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getHead_img())).into(viewHolder.mIvGoods);
            viewHolder.userName.setText(mList.get(i).getNick_name());
            viewHolder.createTime.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(Long.parseLong(mList.get(i).getCreate_time())*1000)));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView userName,createTime,askStatus;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods=itemView.findViewById(R.id.item_ask_for_partner_head);
            userName=itemView.findViewById(R.id.item_ask_for_partner_tv_name);
            createTime=itemView.findViewById(R.id.item_ask_tv_time);
            askStatus=itemView.findViewById(R.id.item_ask_tv_status);
        }
    }
}
