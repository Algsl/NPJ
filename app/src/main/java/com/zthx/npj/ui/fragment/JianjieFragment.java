package com.zthx.npj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AkVideoResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JianjieFragment extends Fragment {

    @BindView(R.id.fg_content)
    TextView fgContent;
    Unbinder unbinder;

    public static Fragment newInstance(String id) {
        JianjieFragment fragment = new JianjieFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVideoList();
    }

    private void getVideoList() {
        DiscoverSubscribe.getKnowledgeVideoList(getArguments().getString("id"), SharePerferenceUtils.getUserId(getActivity()), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                AkVideoResponseBean akVideoResponseBean = GsonUtils.fromJson(result, AkVideoResponseBean.class);
                final ArrayList<AkVideoResponseBean.DataBean> data = akVideoResponseBean.getData();
                fgContent.setText(data.get(0).getContent());
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
