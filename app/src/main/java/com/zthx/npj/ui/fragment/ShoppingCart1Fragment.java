package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.adapter.ShoppingCar1Adapter;
import com.zthx.npj.net.been.CartListResponseBean;
import com.zthx.npj.net.been.CategoryResponseBean;
import com.zthx.npj.net.been.ShoppingCarDataBean1;
import com.zthx.npj.net.been.UpdateCartBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ShopingCartConfirmActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;

public class ShoppingCart1Fragment extends Fragment {
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.elv_shopping_car)
    ExpandableListView elvShoppingCar;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.ll_select_all)
    LinearLayout llSelectAll;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.rl_total_price)
    RelativeLayout rlTotalPrice;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.ll_gouwuche)
    LinearLayout llGouwuche;
    @BindView(R.id.iv_no_contant)
    ImageView ivNoContant;
    @BindView(R.id.rl_no_contant)
    RelativeLayout rlNoContant;
    Unbinder unbinder;

    private ArrayList<ArrayList<CartListResponseBean.DataBean>> datas;
    private Context context;
    private ShoppingCar1Adapter shoppingCarAdapter;
    private String user_id=SharePerferenceUtils.getUserId(getContext());
    private String token=SharePerferenceUtils.getToken(getContext());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initExpandableListView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCartList();
    }

    private void getCartList() {
        SetSubscribe.cartList(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setCartList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setCartList(String result) {
        CartListResponseBean bean=GsonUtils.fromJson(result,CartListResponseBean.class);
        ArrayList<ArrayList<CartListResponseBean.DataBean>> data=bean.getData();
        datas=data;
        initExpandableListViewData(data);
    }

    private void initData() {
        //使用Gson解析购物车数据，
        //ShoppingCarDataBean为bean类，Gson按照bean类的格式解析数据
        /**
         * 实际开发中，通过请求后台接口获取购物车数据并解析
         */
       /* Gson gson = new Gson();
        ShoppingCarDataBean1 shoppingCarDataBean = gson.fromJson(shoppingCarData, ShoppingCarDataBean1.class);
        datas = shoppingCarDataBean.getDatas();*/


    }

    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        shoppingCarAdapter = new ShoppingCar1Adapter(getContext(), llSelectAll, ivSelectAll, btnOrder, btnDelete, rlTotalPrice, tvTotalPrice);
        elvShoppingCar.setAdapter(shoppingCarAdapter);

        //删除的回调
        shoppingCarAdapter.setOnDeleteListener(new ShoppingCar1Adapter.OnDeleteListener() {
            @Override
            public void onDelete() {
                initDelete();
            }
        });

        //结算的回调
        shoppingCarAdapter.setOnSubmitListener(new ShoppingCar1Adapter.OnSubmitListener() {
            @Override
            public void onSubmit() {
                String cart_id = "";
                List<String> cart_ids=new ArrayList<>();
                for(int i=0;i<datas.size();i++){
                    for(int j=0;j<datas.get(i).size();j++){
                        if(datas.get(i).get(j).getSelect()){
                            cart_ids.add(datas.get(i).get(j).getId()+"");
                        }
                    }
                }
                for(int i=0;i<cart_ids.size();i++){
                    if(i+1>=cart_ids.size()){
                        cart_id+=cart_ids.get(i);
                    }else{
                        cart_id+=cart_ids.get(i)+",";
                    }
                }
                if(!cart_id.equals("")){
                    final String finalCart_id = cart_id;
                    SetSubscribe.cartOrder(user_id,token,cart_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            Intent intent=new Intent(getActivity(),ShopingCartConfirmActivity.class);
                            intent.putExtra("info",result);
                            intent.putExtra("cart_id",finalCart_id);
                            startActivity(intent);
                        }
                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }else{
                    Toast.makeText(getContext(), "请选择要结算的商品", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //修改商品数量的回调
        shoppingCarAdapter.setOnChangeCountListener(new ShoppingCar1Adapter.OnChangeCountListener() {
            @Override
            public void onChangeCount(String goods_id, String goods_num) {
                UpdateCartBean bean=new UpdateCartBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setCart_id(goods_id);
                bean.setGoods_num(goods_num);
                SetSubscribe.updateCart(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
    }

    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
    private void initExpandableListViewData(ArrayList<ArrayList<CartListResponseBean.DataBean>> datas) {
        if (datas != null && datas.size() > 0) {
            //刷新数据时，保持当前位置
            shoppingCarAdapter.setData(datas);

            //使所有组展开
            for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                elvShoppingCar.expandGroup(i);
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    return true;
                }
            });
            tvTitlebarRight.setVisibility(View.VISIBLE);
            tvTitlebarRight.setText("编辑");
            rlNoContant.setVisibility(View.GONE);
            elvShoppingCar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            rlTotalPrice.setVisibility(View.VISIBLE);
            btnOrder.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
        } else {
            tvTitlebarRight.setVisibility(View.GONE);
            rlNoContant.setVisibility(View.VISIBLE);
            elvShoppingCar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    }

    /**
     * 判断是否要弹出删除的dialog
     * 通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
     * GoodsBean的isSelect属性，判断商品是否被选中，
     */
    private void initDelete() {
        String cart_id = "";
        for(int i=0;i<datas.size();i++){
            for(int j=0;j<datas.get(i).size();j++){
                if(datas.get(i).get(j).getSelect()){
                    cart_id+=""+datas.get(i).get(j).getId()+",";
                }
            }
        }
        if(!cart_id.equals("")){
            SetSubscribe.delCart(user_id,token,cart_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    getCartList();
                    initExpandableListView();
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }else{
            Toast.makeText(getContext(), "请选择要删除的商品", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({ R.id.tv_titlebar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right://编辑
                String edit = tvTitlebarRight.getText().toString().trim();
                if (edit.equals("编辑")) {
                    tvTitlebarRight.setText("完成");
                    rlTotalPrice.setVisibility(View.GONE);
                    btnOrder.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.VISIBLE);
                } else {
                    tvTitlebarRight.setText("编辑");
                    rlTotalPrice.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
