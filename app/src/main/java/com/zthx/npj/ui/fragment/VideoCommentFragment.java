package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zthx.npj.R;
import com.zthx.npj.adapter.VideoCommentAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

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

    public VideoCommentFragment() {
        // Required empty public constructor
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
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        VideoCommentAdapter mAdapter = new VideoCommentAdapter(getActivity(), list);
        fgVideoCommentRv.setAdapter(mAdapter);
        return view;
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
