package com.zthx.npj.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.HomeAdapter;
import com.zthx.npj.adapter.HomesAdapter;
import com.zthx.npj.adapter.MenuAdapter;
import com.zthx.npj.net.been.CategoryBean;
import com.zthx.npj.net.been.CategoryResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

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
public class ClassfiysActivity extends ActivityBase {

    @BindView(R.id.at_classfiy_lv_menu)
    ListView lvMenu;
    @BindView(R.id.at_classfiy_lv_home)
    ListView lvHome;
    @BindView(R.id.at_classfiy_tv_title)
    TextView tvTitile;
    @BindView(R.id.at_classfiy_iv_back)
    ImageView atClassfiyIvBack;
    @BindView(R.id.at_classfiy_et_search)
    EditText atClassfiyEtSearch;

    private List<String> menuList = new ArrayList<>();
    private List<CategoryResponseBean.DataBean> homeList = new ArrayList<>();
    private List<Integer> showTitle=new ArrayList<>();
    private MenuAdapter menuAdapter;
    private HomesAdapter homeAdapter;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy);
        ButterKnife.bind(this);
        back(atClassfiyIvBack);
        loadData();
        initView();
    }


    private void loadData() {
        MainSubscribe.category(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setClassify(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setClassify(String result) {
        CategoryResponseBean bean=GsonUtils.fromJson(result,CategoryResponseBean.class);
        ArrayList<CategoryResponseBean.DataBean> data=bean.getData();
        for(int i=0;i<data.size();i++){
            menuList.add(data.get(i).getName());
            showTitle.add(i);
            homeList.add(data.get(i));
        }
        tvTitile.setText(data.get(0).getName());

    }

    private void initView() {
        menuAdapter = new MenuAdapter(this, menuList);
        lvMenu.setAdapter(menuAdapter);

        homeAdapter = new HomesAdapter(this, homeList);
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
