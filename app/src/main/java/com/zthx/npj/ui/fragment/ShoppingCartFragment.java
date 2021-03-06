package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommenGoodsAdatper;
import com.zthx.npj.adapter.HomeGoodsAdapter;
import com.zthx.npj.adapter.ShoppingCartAdapter;
import com.zthx.npj.adapter.StoreCouponAdapter;
import com.zthx.npj.entity.GoodsInfo;
import com.zthx.npj.entity.StoreInfo;
import com.zthx.npj.net.been.CartListResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.RecommendResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment implements ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface, ShoppingCartAdapter.GroupEditorListener {

    @BindView(R.id.fg_shopping_elv)
    MyExpandableListView fgShoppingElv;
    @BindView(R.id.fg_shopping_cb_all_check)
    CheckBox fgShoppingCbAllCheck;
    @BindView(R.id.fg_shopping_tv_total_price)
    TextView fgShoppingTvTotalPrice;
    @BindView(R.id.fg_shopping_tv_go_pay)
    TextView fgShoppingTvGoPay;
    @BindView(R.id.order_info)
    LinearLayout orderInfo;
    Unbinder unbinder;
    @BindView(R.id.fg_shopping_iv_message)
    ImageView fgShoppingIvMessage;
    @BindView(R.id.fg_shopping_tv_edit)
    TextView fgShoppingTvEdit;
    @BindView(R.id.fg_shopping_rv_like)
    RecyclerView fgShoppingRvLike;

    private ShoppingCartAdapter adapter;
    private List<StoreInfo> groups; //组元素的列表
    private Map<String, List<GoodsInfo>> childs; //子元素的列表

    private String user_id=SharePerferenceUtils.getUserId(getContext());
    private String token=SharePerferenceUtils.getToken(getContext());

    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initEvent();

        getShoppingCart();
        return view;
    }

    private void getShoppingCart() {
        SetSubscribe.cartList(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setShoppingCart(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }

    private void setShoppingCart(String result) {
        try {
            JSONArray array=new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        adapter = new ShoppingCartAdapter(groups, childs, getActivity());
        adapter.setCheckInterface(this);//关键步骤1：设置复选框的接口
        adapter.setModifyCountInterface(this); //关键步骤2:设置增删减的接口
        adapter.setGroupEditorListener(this);//关键步骤3:监听组列表的编辑状态
        fgShoppingElv.setGroupIndicator(null); //设置属性 GroupIndicator 去掉向下箭头
        fgShoppingElv.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            fgShoppingElv.expandGroup(i); //关键步骤4:初始化，将ExpandableListView以展开的方式显示
        }
        fgShoppingElv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisiablePostion=view.getFirstVisiblePosition();
                int top=-1;
                View firstView=view.getChildAt(firstVisibleItem);
                if(firstView!=null){
                    top=firstView.getTop();
                }
            }
        });
    }

    private void initData() {
        groups = new ArrayList<>();
        childs = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            groups.add(new StoreInfo(i + "", "小黄的第" + (i + 1) + "号当铺"));
            List<GoodsInfo> goods = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
                //i-j 就是商品的id， 对应着第几个店铺的第几个商品，1-1 就是第一个店铺的第一个商品
                goods.add(new GoodsInfo(i + "-" + j, "商品", groups.get(i).getName() + "的第" + (j + 1) + "个商品", 255.00 + new Random().nextInt(1500), 1555 + new Random().nextInt(3000), "第一排", "出头天者", img[j], new Random().nextInt(100)));
            }
            childs.put(groups.get(i).getId(), goods);
        }
        //通过findViewById拿到RecyclerView实例
        //设置RecyclerView管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL,false);
        fgShoppingRvLike.setLayoutManager(layoutManager);
        //初始化适配器
        List<CommentGoodsBeen> list3 = new ArrayList<>();
        CommentGoodsBeen HomeGoodsBeen = new CommentGoodsBeen();
        HomeGoodsBeen.setGoodsPic("123");
        HomeGoodsBeen.setGoodsTitle("1231245124");
        HomeGoodsBeen.setGoodsSellNum("123");
        HomeGoodsBeen.setGoodsPrice("1333");
        for (int i = 0; i < 20; i++) {
            list3.add(HomeGoodsBeen);
        }
        CommenGoodsAdatper mAdapter = new CommenGoodsAdatper(getActivity(), list3);
        /*mAdapter.setOnItemClickListener(new HomeGoodsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<RecommendResponseBean.DataBean> list) {
                Toast.makeText(getActivity(), "position==" + position, Toast.LENGTH_LONG).show();
            }
        });*/
        //设置添加或删除item时的动画，这里使用默认动画
        fgShoppingRvLike.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        fgShoppingRvLike.setAdapter(mAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fg_shopping_cb_all_check, R.id.fg_shopping_tv_total_price, R.id.fg_shopping_tv_go_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_shopping_cb_all_check:
                break;
            case R.id.fg_shopping_tv_total_price:
                break;
            case R.id.fg_shopping_tv_go_pay:
                break;
        }
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {

    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {

    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

    }

    @Override
    public void doUpdate(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {

    }

    @Override
    public void groupEditor(int groupPosition) {
        showPopupWindow();
    }

    private void showPopupWindow() {
        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_store_coupon, null);
        RecyclerView rv = contentView.findViewById(R.id.pop_store_coupon_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        StoreCouponAdapter mAdapter = new StoreCouponAdapter(getActivity(),list);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window=new PopupWindow(contentView, 375, 400, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
