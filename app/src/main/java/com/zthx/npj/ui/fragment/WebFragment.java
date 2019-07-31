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
import com.zthx.npj.net.been.GoodsImgDetailResponseBean;
import com.zthx.npj.ui.ImgArticalActivity;

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
    private List<String> group;
    private List<List<String>> child;
    private ExpandableListView fgWebElv;
    private List<Boolean> clicked=new ArrayList<>();
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
        initData();
        setData();
        return view;
    }

    private void setData() {
        final WebFragmentAdapter adapter=new WebFragmentAdapter(getContext(),group,child);
        fgWebElv.setGroupIndicator(null);
        fgWebElv.setAdapter(adapter);
        fgWebElv.setDivider(null);
        fgWebElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                for(int j=0,count=fgWebElv.getExpandableListAdapter().getGroupCount();j<count;j++){
                    if(i!=j){
                        if(clicked.get(j)){
                            clicked.set(j,false);
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
                clicked.set(i,!clicked.get(i));
                adapter.setCurrentItem(i);
                adapter.setmClicked(clicked.get(i));
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        fgWebElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                startActivity(new Intent(getContext(),ImgArticalActivity.class));
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

    private void setGoodsDetail() {
        GoodsImgDetailResponseBean bean = new GoodsImgDetailResponseBean();
        ArrayList<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        lists.add("3");
        /*RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        acGoodsDetailRvContent.setLayoutManager(layoutManager);
        GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(getContext(), lists);
        acGoodsDetailRvContent.setAdapter(adapter);*/
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

    public void initData(){
        group = new ArrayList<String>();
        child = new ArrayList<List<String>>();
        addInfo("1.0-蓝莓的简介", new String[] {"1.1蓝莓的生长过程","1.2蓝莓的发源地","1.3蓝莓的生长过程"});
        addInfo("2.0-蓝莓病虫害防治方法",  new String[] {"2.1蓝莓的生长过程","2.2蓝莓的发源地","2.3蓝莓的生长过程"});
        addInfo("3.0-蓝莓的生长及主要虫害",  new String[] {"3.1蓝莓的生长过程","3.2蓝莓的发源地","3.3蓝莓的生长过程"});
        clicked.add(false);
        clicked.add(false);
        clicked.add(false);
    }

    public void addInfo(String g, String[] c){
        group.add(g);
        List<String> item = new ArrayList<>();
        for(int i = 0; i < c.length; i++){
            item.add(c[i]);
        }
        child.add(item);
    }
}
