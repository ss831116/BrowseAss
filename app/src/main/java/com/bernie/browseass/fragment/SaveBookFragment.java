package com.bernie.browseass.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bernie.browseass.R;
import com.bernie.browseass.adapter.BookMarksAdapter;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.BookMarksListener;
import com.bernie.browseass.widget.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bernie.greendao.dao.BrowseAssBookMarks;
import bernie.greendao.dao.DaoMaster;
import bernie.greendao.dao.DaoSession;


public class SaveBookFragment extends Fragment  implements XListView.IXListViewListener, BookMarksListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DaoMaster daoMaster;
    DaoSession daoSession;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;

    private Handler mHandler;
    private XListView xListView;
    private int mPageNum = 1;
    private int resultCode = 2;
    private String REFRESH_LOADMORE = "REFRESH";
    private List<BrowseAssBookMarks> bookMarksBeanList = new ArrayList<>();
    BookMarksAdapter bookMarksAdapter;
    public SaveBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaveBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SaveBookFragment newInstance(String param1, String param2) {
        SaveBookFragment fragment = new SaveBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoMaster = BrowserAssApplication.instance.getDaoMaster(getActivity().getApplicationContext());
        daoSession = BrowserAssApplication.instance.getDaoSession(getActivity().getApplicationContext());
        sqLiteDatabase = BrowserAssApplication.instance.getSqLiteDatabase(getActivity().getApplicationContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_book, container, false);
    }
    @Override
    public void onActivityCreated(Bundle onSaveInstance){
        super.onActivityCreated(onSaveInstance);
        initView();
    }
    public void initView() {
        mHandler = new Handler();
        xListView = (XListView) getView().findViewById(R.id.xListView);
        xListView.setPullRefreshEnable(false);
        xListView.setPullLoadEnable(true);
        xListView.setAutoLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.setRefreshTime(getTime());
        initListView();
    }
    public List<BrowseAssBookMarks> getPhotoGallery() {
        return daoSession.getBrowseAssBookMarksDao().loadAll();
    }

    public void initListView() {
        bookMarksBeanList = getPhotoGallery();
        bookMarksAdapter = new BookMarksAdapter(getActivity().getApplicationContext(), getActivity(), bookMarksBeanList, this);
        xListView.setAdapter(bookMarksAdapter);
    }
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
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
    public void chooseBookMark(String webSite) {
        Intent mIntent = new Intent();
        mIntent.putExtra("webSite", webSite);
        getActivity().setResult(resultCode, mIntent);
        getActivity().finish();
    }

    @Override
    public void onRefresh() {
        REFRESH_LOADMORE = "REFRESH";
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPageNum = 1;
                getMoreBookMarks(mPageNum);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPageNum++;
                getMoreBookMarks(mPageNum++);
            }
        }, 1000);
    }

    private void onLoad() {
        if (REFRESH_LOADMORE.equals("REFRESH")) {
            xListView.stopRefresh();
            xListView.stopLoadMore();
        } else {
            xListView.stopRefresh();
            xListView.stopLoadMore();
        }
        xListView.setRefreshTime(getTime());
    }

    public void getMoreBookMarks(int pageNum) {
        onLoad();
    }
}
