package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.VideoCommentAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

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
public class VideoCommentFragment extends Fragment {


    @BindView(R.id.fg_video_comment_rv)
    RecyclerView fgVideoCommentRv;
    Unbinder unbinder;

    private String id;

    public VideoCommentFragment() {
        // Required empty public constructor
    }

    public Fragment newInstance(String id){
        VideoCommentFragment fragment=new VideoCommentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        fgVideoCommentRv.setLayoutManager(manager);
        id=getArguments().getString("id");
        getVideoComment();
        return view;
    }

    private void getVideoComment() {
        MainSubscribe.getStoreComment(id,"6",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CommentResponseBean bean = GsonUtils.fromJson(result, CommentResponseBean.class);
                ArrayList<CommentResponseBean.DataBean> data=bean.getData();
                VideoCommentAdapter mAdapter = new VideoCommentAdapter(getContext(), data);
                fgVideoCommentRv.setItemAnimator(new DefaultItemAnimator());
                fgVideoCommentRv.setAdapter(mAdapter);
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
