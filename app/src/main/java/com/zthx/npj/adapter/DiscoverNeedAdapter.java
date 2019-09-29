package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.GoodsListResponseBean;
import com.zthx.npj.net.been.NeedListResponseBean;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import java.util.ArrayList;

public class DiscoverNeedAdapter extends RecyclerView.Adapter<DiscoverNeedAdapter.ViewHolder> {

    private ArrayList<NeedListResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClick(int position,ArrayList<NeedListResponseBean.DataBean> list);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }

    public DiscoverNeedAdapter(Context context, ArrayList<NeedListResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }

    public void addData(ArrayList<NeedListResponseBean.DataBean> goodsData){
        int size=goodsData.size();
        list.addAll(goodsData);
        notifyItemChanged(size,list.size());
    }
    public void clearData(){
        list.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover_need, viewGroup, false);
        return new DiscoverNeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position,list);
                }
            });
        }
        String url = list.get(i).getImg();
        if (url.substring(url.length() - 4).equals(".mp4")) {
            viewHolder.mIvPic.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
            viewHolder.ivVideo.setVisibility(View.VISIBLE);
        } else {
            Glide.with(mContext).load(Uri.parse(url)).into(viewHolder.mIvPic);
        }
        viewHolder.mTvNeedNum.setText(list.get(i).getAmount());
        if (list.get(i).getDistance() > 1000) {
            viewHolder.mTvDistance.setText((list.get(i).getDistance() / 1000) + "km");
        } else {
            viewHolder.mTvDistance.setText(list.get(i).getDistance() + "m");
        }
        viewHolder.mTvTitle.setText(list.get(i).getTitle());
        if (list.get(i).getIs_top() == 0) {
            viewHolder.mTvIsTop.setVisibility(View.GONE);
        } else {
            viewHolder.mTvIsTop.setVisibility(View.VISIBLE);
        }

        if(list.get(i).getCert()==null || list.get(i).getCert().equals("")){
            viewHolder.needLl.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.needLl.setVisibility(View.VISIBLE);
            String[] strs = list.get(i).getCert().split(",");
            for (String str : strs) {
                if (str.equals("1")) {
                    viewHolder.realName.setVisibility(View.VISIBLE);
                } else if (str.equals("2")) {
                    viewHolder.enterPrice.setVisibility(View.VISIBLE);
                }else if (str.equals("3")) {
                    viewHolder.purchase.setVisibility(View.VISIBLE);
                } else if (str.equals("4")) {
                    viewHolder.trust.setVisibility(View.VISIBLE);
                }else if (str.equals("5")) {
                    viewHolder.zizhi.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic,ivVideo;
        TextView mTvTitle;
        TextView mTvNeedNum;
        TextView mTvDistance;
        TextView mTvIsTop;
        TextView realName,enterPrice,purchase,trust,zizhi;
        LinearLayout needLl;


        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_discover_need_pic);
            mTvTitle = itemView.findViewById(R.id.item_discover_need_tv_title);
            mTvNeedNum = itemView.findViewById(R.id.item_discover_need_tv_num);
            mTvDistance = itemView.findViewById(R.id.item_discover_need_tv_distance);
            mTvIsTop = itemView.findViewById(R.id.item_discover_need_tv_isTop);

            realName=itemView.findViewById(R.id.item_discoverNeed_tv_realName);
            enterPrice=itemView.findViewById(R.id.item_discoverNeed_tv_enterPrice);
            purchase=itemView.findViewById(R.id.item_discoverNeed_tv_purchase);
            trust=itemView.findViewById(R.id.item_discoverNeed_tv_trust);
            zizhi=itemView.findViewById(R.id.item_discoverNeed_tv_zizhi);
            needLl=itemView.findViewById(R.id.item_discoverNeed_ll);

            ivVideo=itemView.findViewById(R.id.item_storeQuotation_iv_video);
        }
    }
}
