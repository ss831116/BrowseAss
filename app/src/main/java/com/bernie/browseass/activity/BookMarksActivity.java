package com.bernie.browseass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.bernie.browseass.R;
import com.bernie.browseass.adapter.BookMarksAdapter;
import com.bernie.browseass.bean.BookMarksBean;
import com.bernie.browseass.listener.BookMarksListener;
import com.bernie.browseass.widget.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookMarksActivity extends BaseActivity implements XListView.IXListViewListener, BookMarksListener {
    private Handler mHandler;
    private XListView xListView;
    private int mPageNum = 1;
    private int resultCode = 1;
    private String REFRESH_LOADMORE = "REFRESH";
    private List<BookMarksBean> bookMarksBeanList = new ArrayList<>();
    BookMarksAdapter bookMarksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addBookMarksToList("1111111", "https://www.baidu.com/", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png");
        addBookMarksToList("2222222", "http://www.youku.com/", "http://static.youku.com/youku/dist/img/find/yk-logo-0412.png");
        addBookMarksToList("3333333", "https://www.facebook.com/", "https://fbstatic-a.akamaihd.net/rsrc.php/v3/yx/r/pyNVUg5EM0j.png");
        initListView();
    }

    public void addBookMarksToList(String saveTime, String webSite, String webSiteIcon) {
        BookMarksBean marksBean = new BookMarksBean();
        marksBean.setSaveTime(saveTime);
        marksBean.setWebSite(webSite);
        marksBean.setWebSiteIcon(webSiteIcon);
        bookMarksBeanList.add(marksBean);
    }

    public void initListView() {
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
