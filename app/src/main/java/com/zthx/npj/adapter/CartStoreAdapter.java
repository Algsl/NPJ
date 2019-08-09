package com.zthx.npj.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CartOrderResponseBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

public class CartStoreAdapter extends RecyclerView.Adapter<CartStoreAdapter.ViewHolder> {

    private ArrayList<ArrayList<CartOrderResponseBean.DataBean.List>> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private Activity mActivity;
    private ArrayList<LocalStoreResponseBean.DataBean> localData=new ArrayList<>();

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onZitiClick(int position);
        void onPeisongClick(int position);
    }

    public CartStoreAdapter(Context context,ArrayList<ArrayList<CartOrderResponseBean.DataBean.List>> list,Activity activity){
        mContext=context;
        mList=list;
        mActivity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_cart_store,viewGroup,false);
        getLocalStore();
        return new CartStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if(mItemClickListener!=null){
            viewHolder.btnZiti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onZitiClick(position);
                    viewHolder.btnZiti.setBackgroundColor(mContext.getResources().getColor(R.color.app_theme));
                    viewHolder.btnZiti.setTextColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnPeisong.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnPeisong.setTextColor(mContext.getResources().getColor(R.color.text3));
                    viewHolder.rlZiti.setVisibility(View.VISIBLE);
                    viewHolder.rlPeisong.setVisibility(View.GONE);
                    showPublishPopwindow(viewHolder.zitiStore,i);
                }
            });
            viewHolder.btnPeisong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onPeisongClick(position);
                    viewHolder.btnPeisong.setBackgroundColor(mContext.getResources().getColor(R.color.app_theme));
                    viewHolder.btnPeisong.setTextColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnZiti.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnZiti.setTextColor(mContext.getResources().getColor(R.color.text3));
                    viewHolder.rlPeisong.setVisibility(View.VISIBLE);
                    viewHolder.rlZiti.setVisibility(View.GONE);
                }
            });
            viewHolder.remark.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mList.get(i).get(0).setRemark(viewHolder.remark.getText().toString().trim());
                }
            });
        }
        viewHolder.storeName.setText(mList.get(i).get(0).getStore_name());
        long goodsNum=0;
        double orderPrice=0;
        for(int j=0;j<mList.get(i).size();j++){
            goodsNum+=mList.get(i).get(j).getGoods_num();
            orderPrice+=Double.parseDouble(mList.get(i).get(j).getPrice())*mList.get(i).get(j).getGoods_num();
        }
        viewHolder.goodsNum.setText("共"+goodsNum+"件商品  小计：");
        viewHolder.orderPrice.setText("￥ "+orderPrice);



        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext);
        viewHolder.itemCartStoreRv.setLayoutManager(layoutManager);
        CartGoodsAdapter adapter=new CartGoodsAdapter(mContext,mList.get(i));
        viewHolder.itemCartStoreRv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName,goodsNum,orderPrice,zitiStore;
        EditText remark;
        RecyclerView itemCartStoreRv;
        RelativeLayout rlZiti,rlPeisong;
        Button btnZiti,btnPeisong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCartStoreRv=itemView.findViewById(R.id.item_cartStore_rv);
            storeName=itemView.findViewById(R.id.item_cartStore_tv_store_name);
            goodsNum=itemView.findViewById(R.id.item_cartStore_tv_allGoodsNum);
            orderPrice=itemView.findViewById(R.id.item_cartStore_tv_orderPrice);
            rlZiti=itemView.findViewById(R.id.item_cartStore_rl_ziti);
            rlPeisong=itemView.findViewById(R.id.item_cartStore_rl_peisong);
            btnZiti=itemView.findViewById(R.id.item_cartStore_btn_ziti);
            btnPeisong=itemView.findViewById(R.id.item_cartStore_btn_peisong);
            zitiStore=itemView.findViewById(R.id.item_cartStore_tv_to_store);
            remark=itemView.findViewById(R.id.item_cartStore_tv_remark);
        }
    }

    public void showPublishPopwindow(final TextView str, final int i) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_local_store, null);
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) mContext.getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) mContext.getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        //window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        final RecyclerView localStore=contentView.findViewById(R.id.pw_localStore_rv_storeList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext);
        localStore.setLayoutManager(layoutManager);
        LocalStoreAdapter localStoreAdapter=new LocalStoreAdapter(mContext,localData);
        localStore.setAdapter(localStoreAdapter);

        localStoreAdapter.setOnItemClickListener(new LocalStoreAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mList.get(i).get(0).setZiti(true);
                mList.get(i).get(0).setZiti_id(localData.get(position).getId()+"");
                str.setText(localData.get(position).getStore_name());
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mActivity.getWindow().setAttributes(lp);
    }

    private void getLocalStore() {
        LocalStoreBean bean = new LocalStoreBean();
        bean.setLat(SharePerferenceUtils.getLat(mContext));
        bean.setLng(SharePerferenceUtils.getLng(mContext));
        bean.setPage("1");
        bean.setType("2");
        MainSubscribe.getLocalStore(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LocalStoreResponseBean bean=GsonUtils.fromJson(result,LocalStoreResponseBean.class);
                localData=bean.getData();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
