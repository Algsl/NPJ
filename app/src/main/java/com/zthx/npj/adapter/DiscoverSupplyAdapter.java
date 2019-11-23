package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import java.util.ArrayList;

public class DiscoverSupplyAdapter extends RecyclerView.Adapter<DiscoverSupplyAdapter.ViewHolder>{

    private ArrayList<SupplyListResponseBean.DataBean> list;
    private Context mContext;
    private Boolean mIsSearch;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position,ArrayList<SupplyListResponseBean.DataBean> list) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public DiscoverSupplyAdapter(Context context, ArrayList<SupplyListResponseBean.DataBean> list,boolean isSearch) {
        this.list = list;
        mContext = context;
        mIsSearch=isSearch;
    }

    public void addData(ArrayList<SupplyListResponseBean.DataBean> goodsData){
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover_supply, viewGroup, false);
        return new DiscoverSupplyAdapter.ViewHolder(view);
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
                    mItemClickListener.onItemClick(position,list);
                }
            });
        }

        String url = list.get(i).getGoods_img();
        if(list.get(i).getGoods_img()==null|| list.get(i).getGoods_img().equals("")){
            viewHolder.mIvPic.setImageResource(R.drawable.logo);
        }
        if (url.substring(url.length() - 4).equals(".mp4")) {
            viewHolder.ivVideo.setVisibility(View.VISIBLE);
            viewHolder.mIvPic.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
        } else {
            if(list.get(i).getGoods_img().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(list.get(i).getGoods_img())).into(viewHolder.mIvPic);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+list.get(i).getGoods_img())).into(viewHolder.mIvPic);
            }
        }
        viewHolder.mTvPrice.setText(list.get(i).getPrice());

        if(mIsSearch){
            viewHolder.mTvDistance.setVisibility(View.GONE);
        }else{
            viewHolder.mTvDistance.setVisibility(View.VISIBLE);
            if(Integer.valueOf(list.get(i).getDistance())>1000){
                viewHolder.mTvDistance.setText((Integer.valueOf(list.get(i).getDistance())/1000)+"km");
            }else{
                viewHolder.mTvDistance.setText(list.get(i).getDistance()+"m");
            }
        }
        viewHolder.mTvTitle.setText(list.get(i).getTitle());
        viewHolder.mTvSupplyUnit.setText("元/"+list.get(i).getGoods_unit());

        if(list.get(i).getIs_top().equals("1")){
            viewHolder.zhiding.setVisibility(View.VISIBLE);
        }else{
            viewHolder.zhiding.setVisibility(View.GONE);
        }


        if(list.get(i).getCert()==null || list.get(i).getCert().equals("")){
            viewHolder.supplyLl.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.supplyLl.setVisibility(View.VISIBLE);
            String cert=list.get(i).getCert();
            String[] strs=cert.split(",");
            for(String str:strs){
                if(str.equals("1")){
                    viewHolder.realName.setVisibility(View.VISIBLE);
                }else if(str.equals("2")){
                    viewHolder.enterPrice.setVisibility(View.VISIBLE);
                }else if(str.equals("3")){
                    viewHolder.purchase.setVisibility(View.VISIBLE);
                }else if(str.equals("4")){
                    viewHolder.trust.setVisibility(View.VISIBLE);
                }else if(str.equals("5")){
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
        TextView mTvPrice;
        RelativeLayout mRlSupply;
        RelativeLayout mRlNeed;
        TextView mTvSupplyUnit;
        TextView mTvSellNum;
        TextView mTvDistance;
        TextView realName,enterPrice,purchase,trust,zizhi;
        TextView zhiding;
        LinearLayout supplyLl;

        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_discover_supply_pic);
            mTvTitle = itemView.findViewById(R.id.item_discover_supply_tv_title);
            mTvPrice = itemView.findViewById(R.id.item_discover_supply_tv_price);
            mTvDistance= itemView.findViewById(R.id.item_discover_supply_tv_distance);
            mTvSupplyUnit = itemView.findViewById(R.id.item_discover_supply_tv_price_danwei);
            mRlSupply = itemView.findViewById(R.id.item_discover_supply_rl);
            mRlNeed = itemView.findViewById(R.id.item_discover_need_rl);
            zhiding= itemView.findViewById(R.id.item_discover_need_tv_isTop);

            realName=itemView.findViewById(R.id.item_discoverSupply_tv_realName);
            enterPrice=itemView.findViewById(R.id.item_discoverSupply_tv_enterPrice);
            purchase=itemView.findViewById(R.id.item_discoverSupply_purchase);
            trust=itemView.findViewById(R.id.item_discoverSupply_tv_trust);
            zizhi=itemView.findViewById(R.id.item_discoverSupply_tv_zizhi);
            supplyLl=itemView.findViewById(R.id.item_discoverSupply_ll);

            ivVideo=itemView.findViewById(R.id.item_storeQuotation_iv_video);
        }
    }

    public void updateList(ArrayList<SupplyListResponseBean.DataBean> newData){
        if(newData!=null){
            list.addAll(newData);
        }
        notifyDataSetChanged();
    }
}
