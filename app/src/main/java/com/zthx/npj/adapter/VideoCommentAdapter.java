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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.LookUserResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoCommentAdapter extends RecyclerView.Adapter<VideoCommentAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CommentResponseBean.DataBean> mList;
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }
    public VideoCommentAdapter(Context context, ArrayList<CommentResponseBean.DataBean> list) {
        mContext = context;
        mList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment_video,viewGroup,false);
        return new VideoCommentAdapter.ViewHolder(view);
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
        DiscoverSubscribe.lookUser(mList.get(i).getUser_id()+"", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LookUserResponseBean bean = GsonUtils.fromJson(result, LookUserResponseBean.class);
                LookUserResponseBean.DataBean data = bean.getData();
                MyCustomUtils.showLevelImg((int)data.getLevel(),viewHolder.level);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
        if (mList!= null && mList.size() > 0) {
            Glide.with(mContext).load(Uri.parse(mList.get(i).getHead_img())).into(viewHolder.mIvGoods);
            viewHolder.userName.setText(mList.get(i).getNick_name());
            viewHolder.content.setText(mList.get(i).getContent());
            viewHolder.createTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods,level;
        TextView userName,createTime,content;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_comment_video_cv_pic);
            userName=itemView.findViewById(R.id.item_commentVideo_tv_userName);
            createTime=itemView.findViewById(R.id.item_comment_video_tv_time);
            content=itemView.findViewById(R.id.item_comment_video_tv_comment);
            level=itemView.findViewById(R.id.item_commenVideo_iv_level);
        }
    }
}
