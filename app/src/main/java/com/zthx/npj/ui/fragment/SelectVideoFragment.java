package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.AKVideoAdapter;
import com.zthx.npj.adapter.SelectVideoAdapter;
import com.zthx.npj.net.been.AkVideoResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SolutionVideoResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class SelectVideoFragment extends Fragment {

    @BindView(R.id.fg_select_video_rv)
    RecyclerView fgSelectVideoRv;
    Unbinder unbinder;

    private static String videoId;

    private OnFragmentInteractionListener mListener;

    public SelectVideoFragment() {
    }

    public static SelectVideoFragment newInstance(String id) {
        SelectVideoFragment fragment = new SelectVideoFragment();
        Bundle args = new Bundle();
        videoId = id;
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(AkVideoResponseBean.DataBean dataBean) {
        if (mListener != null) {
            mListener.onFragmentInteraction(dataBean);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataList(videoId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void getDataList(String videoId) {
        DiscoverSubscribe.getKnowledgeVideoList(videoId, SharePerferenceUtils.getUserId(getActivity()), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                AkVideoResponseBean akVideoResponseBean = GsonUtils.fromJson(result, AkVideoResponseBean.class);
                final ArrayList<AkVideoResponseBean.DataBean> data = akVideoResponseBean.getData();
                if (mListener != null) {
                    mListener.onDataGet(data.get(0));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                fgSelectVideoRv.setLayoutManager(manager);
                AKVideoAdapter mAdapter = new AKVideoAdapter(getActivity(), data);
                mAdapter.setOnItemClickListener(new AKVideoAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        onButtonPressed(data.get(position));
                    }
                });
                fgSelectVideoRv.setItemAnimator(new DefaultItemAnimator());
                fgSelectVideoRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectVideoFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(AkVideoResponseBean.DataBean dataBean);
        void onDataGet(AkVideoResponseBean.DataBean dataBean);
    }
}
