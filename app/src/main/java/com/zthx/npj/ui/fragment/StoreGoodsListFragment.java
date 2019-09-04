package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsListAdapter;
import com.zthx.npj.net.been.MyGoodsResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.StoreGoodsInfoActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class StoreGoodsListFragment extends Fragment {


    @BindView(R.id.fg_store_goods_list_rv)
    RecyclerView fgStoreGoodsListRv;
    Unbinder unbinder;
    String user_id = SharePerferenceUtils.getUserId(getContext());
    String token = SharePerferenceUtils.getToken(getContext());
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public StoreGoodsListFragment() {
        // Required empty public constructor
    }

    public StoreGoodsListFragment newIntent(String type) {
        StoreGoodsListFragment fragment = new StoreGoodsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_goods_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getStoreGoods();
                refreshlayout.finishRefresh();
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getStoreGoods();
    }

    private void getStoreGoods() {
        String type = getArguments().getString("type");
        SetSubscribe.myGoods(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreGoods(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setStoreGoods(String result) {
        MyGoodsResponseBean bean = GsonUtils.fromJson(result, MyGoodsResponseBean.class);
        final ArrayList<MyGoodsResponseBean.DataBean> data = bean.getData();
        if (data.size() <= 0) {
            fgStoreGoodsListRv.setVisibility(View.GONE);
        } else {
            fgStoreGoodsListRv.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fgStoreGoodsListRv.setLayoutManager(manager);
        StoreGoodsListAdapter adapter = new StoreGoodsListAdapter(getContext(), data, getArguments().getString("type"));
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new StoreGoodsListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSaleClick(int position) {
                String goods_id = data.get(position).getId() + "";
                String type = getArguments().getString("type");
                SetSubscribe.outGoods(user_id, token, goods_id, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getStoreGoods();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }

            @Override
            public void onShareClick(int position) {

            }

            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(getContext(), StoreGoodsInfoActivity.class);
                intent.putExtra("goods_id", data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onDelete(int position) {
                String goods_id = data.get(position).getId() + "";
                SetSubscribe.delGoods(user_id, token, goods_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        getStoreGoods();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        fgStoreGoodsListRv.setItemAnimator(new DefaultItemAnimator());
        fgStoreGoodsListRv.setAdapter(adapter);
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
}
