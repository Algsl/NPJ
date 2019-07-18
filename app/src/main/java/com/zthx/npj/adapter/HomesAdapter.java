package com.zthx.npj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CategoryBean;
import com.zthx.npj.net.been.CategoryResponseBean;
import com.zthx.npj.ui.fragment.ClassfiyDetailActivity;
import com.zthx.npj.view.GridViewForScrollView;

import java.util.List;

/**
 * 右侧主界面ListView的适配器
 *
 * @author Administrator
 */
public class HomesAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryResponseBean.DataBean> foodDatas;

    public HomesAdapter(Context context, List<CategoryResponseBean.DataBean> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_classfiy_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        HomesItemAdapter adapter = new HomesItemAdapter(context, foodDatas.get(position).getChild());
        viewHold.blank.setText(foodDatas.get(position).getName());
        viewHold.gridView.setAdapter(adapter);
        viewHold.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(context,ClassfiyDetailActivity.class);
                intent.putExtra("title",foodDatas.get(position).getChild().get(i).getName());
                context.startActivity(intent);
                //context.startActivity(new Intent(context, ClassfiyDetailActivity.class));
            }
        });
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }

}
