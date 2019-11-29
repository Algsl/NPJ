package com.zthx.npj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CommonListResponseBean;
import com.zthx.npj.ui.AddToCommentActivity;
import com.zthx.npj.ui.ImageLookActivity;
import com.zthx.npj.utils.DateUtil;

import java.util.ArrayList;

public class AddCommonListAdapter extends RecyclerView.Adapter<AddCommonListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<CommonListResponseBean.DataBean.List2> mList;

    public AddCommonListAdapter(Context context,ArrayList<CommonListResponseBean.DataBean.List2> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_add_commonlist,viewGroup,false);
        return new AddCommonListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.content.setText(mList.get(i).getContent());
        GridLayoutManager layoutManager=new GridLayoutManager(mContext,3);
        viewHolder.imgRv.setLayoutManager(layoutManager);

        CommentImgAdapter adapter=new CommentImgAdapter(mContext,mList.get(i).getImg());
        viewHolder.imgRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new CommentImgAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(mContext,ImageLookActivity.class);
                intent.putExtra("position",(position+1));
                intent.putExtra("imgs",mList.get(i).getImg());
                mContext.startActivity(intent);
            }
        });

        viewHolder.createTime.setText(DateUtil.timeslashData(mList.get(i).getCreate_time()+"")+"追评");
        switch ((int)mList.get(i).getGoods_star()) {
            case 1:
                viewHolder.star1.setImageResource(R.drawable.item_location_store_star);
                break;
            case 2:
                viewHolder.star1.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star2.setImageResource(R.drawable.item_location_store_star);
                break;
            case 3:
                viewHolder.star1.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star2.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star3.setImageResource(R.drawable.item_location_store_star);
                break;
            case 4:
                viewHolder.star1.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star2.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star3.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star4.setImageResource(R.drawable.item_location_store_star);
                break;
            case 5:
                viewHolder.star1.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star2.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star3.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star4.setImageResource(R.drawable.item_location_store_star);
                viewHolder.star5.setImageResource(R.drawable.item_location_store_star);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        RecyclerView imgRv;
        ImageView star1,star2,star3,star4,star5;
        TextView createTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.item_addToComment_tv_content);
            imgRv=itemView.findViewById(R.id.item_addToComment_rv);
            star1=itemView.findViewById(R.id.item_addToComment_iv_star1);
            star2=itemView.findViewById(R.id.item_addToComment_iv_star2);
            star3=itemView.findViewById(R.id.item_addToComment_iv_star3);
            star4=itemView.findViewById(R.id.item_addToComment_iv_star4);
            star5=itemView.findViewById(R.id.item_addToComment_iv_star5);
            createTime=itemView.findViewById(R.id.item_addToComment_tv_date);
        }
    }
}
