package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;

public class ImgFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_img,container,false);
        ImageView imgLook=view.findViewById(R.id.fg_img_look);
        Glide.with(getContext()).load(Uri.parse(getArguments().getString("imgUrl"))).into(imgLook);
        return view;
    }

    public ImgFragment newIntent(String url){
        ImgFragment fragment=new ImgFragment();
        Bundle bundle=new Bundle();
        bundle.putString("imgUrl",url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
