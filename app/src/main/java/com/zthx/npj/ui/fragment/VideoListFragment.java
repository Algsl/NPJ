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
import com.zthx.npj.adapter.SelectVideoAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.SolutionVideoResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoListFragment extends Fragment {

    @BindView(R.id.fg_video_list_rv)
    RecyclerView fgVideoListRv;
    Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    private static String id;

    public VideoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoListFragment newInstance(String video_id) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        id = video_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取视频播放列表
        DiscoverSubscribe.getSolutionVideoList(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SolutionVideoResponseBean solutionVideoResponseBean = GsonUtils.fromJson(result, SolutionVideoResponseBean.class);
                final ArrayList<SolutionVideoResponseBean.DataBean> data = solutionVideoResponseBean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                fgVideoListRv.setLayoutManager(manager);
                //未发生点击事件时，默认播放第一个视频
                if (mListener!= null) {
                    mListener.onDataGet(data.get(0));
                }
                SelectVideoAdapter mAdapter = new SelectVideoAdapter(getContext(), data);
                mAdapter.setOnItemClickListener(new SelectVideoAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        onButtonPressed(data.get(position));
                    }
                });
                fgVideoListRv.setItemAnimator(new DefaultItemAnimator());
                fgVideoListRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(SolutionVideoResponseBean.DataBean dataBean) {
        if (mListener != null) {
            mListener.onFragmentInteraction(dataBean);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //点击播放接口
        void onFragmentInteraction(SolutionVideoResponseBean.DataBean dataBean);

        //自动播放接口
        void onDataGet(SolutionVideoResponseBean.DataBean dataBean);
    }
}
