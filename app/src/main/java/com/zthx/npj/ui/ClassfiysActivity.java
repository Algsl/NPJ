package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ClassifyAdapter;
import com.zthx.npj.adapter.MenuAdapter;
import com.zthx.npj.net.been.CategoryResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.fragment.ClassfiyDetailActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分类页面
 */
public class ClassfiysActivity extends ActivityBase {

    @BindView(R.id.at_classfiy_lv_menu)
    ListView lvMenu;
    @BindView(R.id.at_classfiy_iv_back)
    ImageView atClassfiyIvBack;
    @BindView(R.id.at_classfiy_et_search)
    EditText atClassfiyEtSearch;
    @BindView(R.id.ac_classify_rv)
    RecyclerView acClassifyRv;

    private List<String> menuList = new ArrayList<>();
    private ArrayList<CategoryResponseBean.DataBean> data;
    private List<Integer> showTitle = new ArrayList<>();
    private MenuAdapter menuAdapter;
    private ClassifyAdapter classifyAdapter;
    private int currentItem = 0;

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
        CategoryResponseBean bean = GsonUtils.fromJson(result, CategoryResponseBean.class);
        data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            menuList.add(data.get(i).getName());
            showTitle.add(i);
        }
        setRight();

    }

    private void initView() {
        menuAdapter = new MenuAdapter(this, menuList);
        lvMenu.setAdapter(menuAdapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                currentItem = position;
                setRight();
            }
        });
    }

    public void setRight() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        acClassifyRv.setLayoutManager(layoutManager);
        classifyAdapter = new ClassifyAdapter(this, data.get(currentItem).getChild());
        acClassifyRv.setAdapter(classifyAdapter);
        classifyAdapter.setOnItemClickListener(new ClassifyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String id = data.get(currentItem).getChild().get(position).getId() + "";
                String title = data.get(currentItem).getChild().get(position).getName();
                openActivity(ClassfiyDetailActivity.class, id, title);
            }
        });
    }


    @OnClick(R.id.at_classfiy_et_search)
    public void onViewClicked() {
        atClassfiyEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_SEARCH){
                    openActivity(SearchResultActivity.class,atClassfiyEtSearch.getText().toString().trim());
                }
                return true;
            }
        });
    }
}
