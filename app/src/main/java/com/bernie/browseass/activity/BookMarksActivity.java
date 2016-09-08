package com.bernie.browseass.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

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

public class BookMarksActivity extends BaseActivity implements XListView.IXListViewListener, BookMarksListener {
    private Handler mHandler;
    private XListView xListView;
    private int mPageNum = 1;
    private int resultCode = 1;
    private String REFRESH_LOADMORE = "REFRESH";
    private List<BrowseAssBookMarks> bookMarksBeanList = new ArrayList<>();
    BookMarksAdapter bookMarksAdapter;
    DaoMaster daoMaster;
    DaoSession daoSession;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoMaster = BrowserAssApplication.instance.getDaoMaster(getApplicationContext());
        daoSession = BrowserAssApplication.instance.getDaoSession(getApplicationContext());
        sqLiteDatabase = BrowserAssApplication.instance.getSqLiteDatabase(getApplicationContext());
        setContentView(R.layout.activity_book_marks);
    }

    @Override
    public void initView() {
        mHandler = new Handler();
        xListView = (XListView) findViewById(R.id.xListView);
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
        bookMarksAdapter = new BookMarksAdapter(getApplicationContext(), this, bookMarksBeanList, this);
        xListView.setAdapter(bookMarksAdapter);
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
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

    @Override
    public void chooseBookMark(String webSite) {
        Log.d("BookMarksActivity", "web = " + webSite);
        Intent mIntent = new Intent();
        mIntent.putExtra("webSite", webSite);
        setResult(resultCode, mIntent);
        finish();
    }
}
