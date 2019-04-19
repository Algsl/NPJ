package com.zthx.npj.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.HomeAdapter;
import com.zthx.npj.adapter.MenuAdapter;
import com.zthx.npj.net.been.CategoryBean;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类页面
 */
public class ClassfiyActivity extends AppCompatActivity {

    @BindView(R.id.lv_menu)
    ListView lvMenu;
    @BindView(R.id.lv_home)
    ListView lvHome;
    @BindView(R.id.tv_titile)
    TextView tvTitile;

    private List<String> menuList = new ArrayList<>();
    private List<CategoryBean.DataBean> homeList = new ArrayList<>();
    private List<Integer> showTitle;
    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy);
        ButterKnife.bind(this);
        initView();
        loadData();
    }


    private void loadData() {

        String json = getJson(this, "category.json");
        CategoryBean categoryBean = JSONObject.parseObject(json, CategoryBean.class);
        showTitle = new ArrayList<>();
        for (int i = 0; i < categoryBean.getData().size(); i++) {
            CategoryBean.DataBean dataBean = categoryBean.getData().get(i);
            menuList.add(dataBean.getModuleTitle());
            showTitle.add(i);
            homeList.add(dataBean);
        }
        tvTitile.setText(categoryBean.getData().get(0).getModuleTitle());

        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();
    }

    private void initView() {
        menuAdapter = new MenuAdapter(this, menuList);
        lvMenu.setAdapter(menuAdapter);

        homeAdapter = new HomeAdapter(this, homeList);
        lvHome.setAdapter(homeAdapter);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                tvTitile.setText(menuList.get(position));
                lvHome.setSelection(showTitle.get(position));
            }
        });


        lvHome.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
//				lv_home.setSelection(current);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    tvTitile.setText(menuList.get(currentItem));
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
