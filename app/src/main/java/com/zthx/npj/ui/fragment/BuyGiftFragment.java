package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.BuyGiftAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GiftListResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.GiftActivity;
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
public class BuyGiftFragment extends Fragment {


    @BindView(R.id.fg_buy_gift_rv)
    RecyclerView fgBuyGiftRv;
    Unbinder unbinder;

    public BuyGiftFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_gift, container, false);
        unbinder = ButterKnife.bind(this, view);

        getGiftList();
        return view;
    }

    private void getGiftList() {
        GiftSubscribe.getGiftList(SharePerferenceUtils.getUserId(getActivity()), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GiftListResponseBean giftListResponseBean = GsonUtils.fromJson(result, GiftListResponseBean.class);
                final ArrayList<GiftListResponseBean.DataBean> data = giftListResponseBean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                fgBuyGiftRv.setLayoutManager(manager);
                BuyGiftAdapter mAdapter = new BuyGiftAdapter(getActivity(), data);
                mAdapter.setOnItemClickListener(new BuyGiftAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Intent intent = new Intent(getActivity(), GiftActivity.class);
                        intent.putExtra(Const.GOODS_ID,data.get(position).getId());
                        startActivity(intent);
                    }
                });
                fgBuyGiftRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
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
