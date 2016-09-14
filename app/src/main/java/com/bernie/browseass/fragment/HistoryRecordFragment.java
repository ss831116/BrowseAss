package com.bernie.browseass.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bernie.browseass.R;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.FragmentListener;

import java.util.List;

import bernie.greendao.dao.DaoMaster;
import bernie.greendao.dao.DaoSession;
import bernie.greendao.dao.HistoryWebPage;


public class HistoryRecordFragment extends Fragment implements FragmentListener{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int resultCode = 2;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryRecordFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HistoryRecordFragment newInstance(int columnCount) {
        HistoryRecordFragment fragment = new HistoryRecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    DaoMaster daoMaster;
    DaoSession daoSession;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoMaster = BrowserAssApplication.instance.getDaoMaster(getActivity().getApplicationContext());
        daoSession = BrowserAssApplication.instance.getDaoSession(getActivity().getApplicationContext());
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(getHistoryWebList(),this));
        }
        return view;
    }

    public List<HistoryWebPage> getHistoryWebList() {
        return daoSession.getHistoryWebPageDao().loadAll();
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
    public void setResultPageSelect(Object object) {
        //String webSite = (String)object;
        Intent mIntent = new Intent();
        mIntent.putExtra("webSite", (String)object);
        getActivity().setResult(resultCode, mIntent);
        getActivity().finish();
    }
}
