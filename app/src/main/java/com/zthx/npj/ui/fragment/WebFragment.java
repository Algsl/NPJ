package com.zthx.npj.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.adapter.WebFragmentAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GoodsImgDetailResponseBean;
import com.zthx.npj.net.been.TwjcListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.ImgArticalActivity;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebFragment extends Fragment {
    Unbinder unbinder;
    private OnFragmentInteractionListener mListener;
    private ArrayList<TwjcListResponseBean.DataBean> group=new ArrayList<>();
    private ArrayList<TwjcListResponseBean.DataBean.List> child=new ArrayList<>();
    private ExpandableListView fgWebElv;
   // private List<Boolean> clicked=new ArrayList<>();
    private String videoId="";
    public WebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebFragment newInstance(String id) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString("zuowuId",id);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        fgWebElv=view.findViewById(R.id.fg_web_elv);
        unbinder = ButterKnife.bind(this, view);
        videoId=getArguments().getString("zuowuId");
        getTwjcList();
        //initData();
        //setData();
        return view;
    }

    private void getTwjcList() {
        DiscoverSubscribe.twjcList(videoId,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setTwjcList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setTwjcList(String result) {
        TwjcListResponseBean bean=GsonUtils.fromJson(result,TwjcListResponseBean.class);
        group=bean.getData();
        setData();
    }

    private void setData() {
        final WebFragmentAdapter adapter=new WebFragmentAdapter(getContext(),group);
        fgWebElv.setGroupIndicator(null);
        fgWebElv.setAdapter(adapter);
        fgWebElv.setDivider(null);
        fgWebElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                for(int j=0,count=fgWebElv.getExpandableListAdapter().getGroupCount();j<count;j++){
                    if(i!=j){
                        /*if(clicked.get(j)){
                            clicked.set(j,false);
                        }*/
                        if(group.get(j).isSelected()){
                            group.get(j).setSelected(false);
                        }
                        adapter.notifyDataSetChanged();
                        fgWebElv.collapseGroup(j);
                    }
                }
            }
        });
        fgWebElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //clicked.set(i,!clicked.get(i));
                group.get(i).setSelected(!group.get(i).isSelected());
                adapter.setCurrentItem(i);
                adapter.setmClicked(group.get(i).isSelected());
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        fgWebElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent=new Intent(getContext(),ImgArticalActivity.class);
                intent.putExtra("list_id",group.get(i).getList().get(i1).getId()+"");
                startActivity(intent);
                return false;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
