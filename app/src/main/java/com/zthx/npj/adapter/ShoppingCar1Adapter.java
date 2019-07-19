package com.zthx.npj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CartListResponseBean;
import com.zthx.npj.net.been.ShoppingCarDataBean1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCar1Adapter extends BaseExpandableListAdapter {
    private final Context context;
    private final LinearLayout llSelectAll;
    private final ImageView ivSelectAll;
    private final Button btnOrder;
    private final Button btnDelete;
    private final RelativeLayout rlTotalPrice;
    private final TextView tvTotalPrice;
    private ArrayList<ArrayList<CartListResponseBean.DataBean>> data;
    private boolean isSelectAll = false;
    private double total_price;
    private boolean isSelect_shop=false;

    public ShoppingCar1Adapter(Context context, LinearLayout llSelectAll,
                               ImageView ivSelectAll, Button btnOrder, Button btnDelete,
                               RelativeLayout rlTotalPrice, TextView tvTotalPrice) {
        this.context = context;
        this.llSelectAll = llSelectAll;
        this.ivSelectAll = ivSelectAll;
        this.btnOrder = btnOrder;
        this.btnDelete = btnDelete;
        this.rlTotalPrice = rlTotalPrice;
        this.tvTotalPrice = tvTotalPrice;
    }

    /**
     * 自定义设置数据方法；
     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
     *
     * @param data 需要刷新的数据
     */
    public void setData(ArrayList<ArrayList<CartListResponseBean.DataBean>> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        //加载父布局
        if (convertView == null) {
            convertView =LayoutInflater.from(context).inflate(R.layout.item_shopping_car_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        //获取父布局的数据
        ArrayList<CartListResponseBean.DataBean> datasBean = data.get(groupPosition);
        /**
         * datas [[]]
         * dataBean[] obj0,obj1,obj2,共用一个store_name和store_id
         */
        //店铺ID
        String store_id = datasBean.get(0).getStore_id()+"";
        //店铺名称
        String store_name = datasBean.get(0).getStore_name();
        groupViewHolder.tvStoreName.setText(store_name);


        //店铺内的商品都选中的时候，店铺的也要选中
        for (int i = 0; i < datasBean.size(); i++) {
            CartListResponseBean.DataBean goodsBean = datasBean.get(i);
            boolean isSelect = goodsBean.getSelect();

            if (isSelect) {
                datasBean.get(i).setSelectShop(true);
            } else {
                datasBean.get(i).setSelectShop(false);
                break;
            }
        }

        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        boolean isSelect_shop=true;

        for(int i=0;i<datasBean.size();i++){
            if(!datasBean.get(i).getSelect()){
                isSelect_shop=false;
                break;
            }
        }

        if (isSelect_shop) {
            groupViewHolder.ivSelect.setImageResource(R.drawable.confirm_select);
        } else {
            groupViewHolder.ivSelect.setImageResource(R.drawable.confirm_unselect);
        }
        //店铺选择框的点击事件
        final ArrayList<CartListResponseBean.DataBean> finalDatasBean = datasBean;
        final boolean finalIsSelect_shop = isSelect_shop;
        groupViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDatasBean.get(0).setSelectShop(!finalIsSelect_shop);
                ArrayList<CartListResponseBean.DataBean> goods = finalDatasBean;
                for (int i = 0; i < goods.size(); i++) {
                    CartListResponseBean.DataBean goodsBean=goods.get(i);
                    goodsBean.setSelect(!finalIsSelect_shop);
                }
                notifyDataSetChanged();
            }
        });

        //当所有的选择框都是选中的时候，全选也要选中
        w:
        for (int i = 0; i < data.size(); i++) {
            ArrayList<CartListResponseBean.DataBean> goods = data.get(i);
            for (int y = 0; y < goods.size(); y++) {
                CartListResponseBean.DataBean goodsBean=goods.get(y);
                boolean isSelect = goodsBean.getSelect();
                if (isSelect) {
                    isSelectAll = true;
                } else {
                    isSelectAll = false;
                    break w;//根据标记，跳出嵌套循环
                }
            }
        }
        if (isSelectAll) {
            ivSelectAll.setBackgroundResource(R.drawable.confirm_select);
        } else {
            ivSelectAll.setBackgroundResource(R.drawable.confirm_unselect);
        }

        //全选的点击事件
        llSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = !isSelectAll;//设置是否全部选择
                if (isSelectAll) {
                    //全选：循环选中所有的商品
                    for (int i = 0; i < data.size(); i++) {
                        ArrayList<CartListResponseBean.DataBean> goods = data.get(i);
                        for (int y = 0; y < goods.size(); y++) {
                            CartListResponseBean.DataBean goodsBean=goods.get(y);
                            goodsBean.setSelect(true);
                        }
                    }
                } else {
                    //关闭全选：循环取消所有选中的商品
                    for (int i = 0; i < data.size(); i++) {
                        ArrayList<CartListResponseBean.DataBean> goods = data.get(i);
                        for (int y = 0; y < goods.size(); y++) {
                            CartListResponseBean.DataBean goodsBean=goods.get(y);
                            goodsBean.setSelect(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //合计的计算
        total_price = 0.0;
        tvTotalPrice.setText("¥0.00");
        for (int i = 0; i < data.size(); i++) {
            ArrayList<CartListResponseBean.DataBean> goods = data.get(i);
            for (int y = 0; y < goods.size(); y++) {
                CartListResponseBean.DataBean goodsBean=goods.get(y);
                boolean isSelect = goodsBean.getSelect();//判断每个商品是否选中
                if (isSelect) {
                    //商品选中，计算该商品的价格相加
                    String num = goodsBean.getGoods_num()+"";
                    String price = goodsBean.getMember_price();
                    double v = Double.parseDouble(num);
                    double v1 = Double.parseDouble(price);
                    total_price = total_price + v * v1;
                    //让Double类型完整显示，不用科学计数法显示大写字母E
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    tvTotalPrice.setText("¥" + decimalFormat.format(total_price));
                }
            }
        }

        //去结算的点击事件
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建临时的List，用于存储被选中的商品
                List<CartListResponseBean.DataBean> temp = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    ArrayList<CartListResponseBean.DataBean> goods = data.get(i);
                    for (int y = 0; y < goods.size(); y++) {
                       CartListResponseBean.DataBean goodsBean = goods.get(y);
                        boolean isSelect = goodsBean.getSelect();
                        if (isSelect) {
                            temp.add(goodsBean);
                        }
                    }
                }

                if (temp != null && temp.size() > 0) {//如果有被选中的

                } else {
                }
            }
        });

        //删除的点击事件
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener != null) {
                    mDeleteListener.onDelete();
                }
            }
        });

        return convertView;
    }

    static class GroupViewHolder {
        ImageView ivSelect;
        TextView tvStoreName;
        LinearLayout ll;

        GroupViewHolder(View view) {
            ivSelect=view.findViewById(R.id.iv_select);
            tvStoreName=view.findViewById(R.id.tv_store_name);
            ll=view.findViewById(R.id.ll);
        }
    }

    //------------------------------------------------------------------------------------------------
    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition) != null && data.get(groupPosition).size() > 0) {
            return data.get(groupPosition).size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_car_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final ArrayList<CartListResponseBean.DataBean> datasBean = data.get(groupPosition);
        final CartListResponseBean.DataBean goodsBean = datasBean.get(childPosition);
        final String goods_id=goodsBean.getId()+"";
        Glide.with(context).load(goodsBean.getGoods_img()).into(childViewHolder.ivPhoto);
        childViewHolder.tvName.setText(goodsBean.getGoods_name());
        childViewHolder.tvPriceValue.setText(goodsBean.getMember_price());
        childViewHolder.tvEditBuyNumber.setText(goodsBean.getGoods_num()+"");

        final boolean flag=data.get(groupPosition).get(childPosition).getSelect();
        //商品是否被选中
        if (flag) {
            childViewHolder.ivSelect.setImageResource(R.drawable.confirm_select);
        } else {
            childViewHolder.ivSelect.setImageResource(R.drawable.confirm_unselect);
        }


       //商品选择框的点击事件
        childViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(groupPosition).get(childPosition).setSelect(!data.get(groupPosition).get(childPosition).getSelect());
                notifyDataSetChanged();
            }
        });

       //加号的点击事件
        childViewHolder.ivEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟加号操作
                String num = goodsBean.getGoods_num()+"";
                Integer integer = Integer.valueOf(num);
                integer++;
                goodsBean.setGoods_num(integer);
                notifyDataSetChanged();
                if (mChangeCountListener != null) {
                    mChangeCountListener.onChangeCount(goods_id);
                }
            }
        });
        //减号的点击事件
        childViewHolder.ivEditSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟减号操作
                String num = goodsBean.getGoods_num()+"";
                Integer integer = Integer.valueOf(num);
                if (integer > 1) {
                    integer--;
                    goodsBean.setGoods_num(integer);

                    if (mChangeCountListener != null) {
                        mChangeCountListener.onChangeCount(goods_id);
                    }
                } else {
                    Toast.makeText(context, "商品不能再减少了",Toast.LENGTH_LONG).show();
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ChildViewHolder {
        ImageView ivSelect;
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPriceKey;
        TextView tvPriceValue;
        ImageView ivEditSubtract;
        TextView tvEditBuyNumber;
        ImageView ivEditAdd;
        View view;
        View viewLast;

        ChildViewHolder(View view) {
            ivSelect=view.findViewById(R.id.iv_select);
            ivPhoto=view.findViewById(R.id.iv_photo);
            tvName=view.findViewById(R.id.tv_name);
            tvPriceKey=view.findViewById(R.id.tv_price_key);
            tvPriceValue=view.findViewById(R.id.tv_price_value);
            ivEditSubtract=view.findViewById(R.id.iv_edit_subtract);
            tvEditBuyNumber=view.findViewById(R.id.tv_edit_buy_number);
            ivEditAdd=view.findViewById(R.id.iv_edit_add);
            view=view.findViewById(R.id.view);
        }
    }

    //-----------------------------------------------------------------------------------------------

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //删除的回调
    public interface OnDeleteListener {
        void onDelete();
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        mDeleteListener = listener;
    }

    private OnDeleteListener mDeleteListener;

    //修改商品数量的回调
    public interface OnChangeCountListener {
        void onChangeCount(String goods_id);
    }

    public void setOnChangeCountListener(OnChangeCountListener listener) {
        mChangeCountListener = listener;
    }

    private OnChangeCountListener mChangeCountListener;
}

