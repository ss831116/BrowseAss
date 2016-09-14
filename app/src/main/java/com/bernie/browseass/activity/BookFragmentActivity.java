package com.bernie.browseass.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.fragment.HistoryRecordFragment;
import com.bernie.browseass.fragment.SaveBookFragment;

import java.util.ArrayList;
import java.util.List;

public class BookFragmentActivity extends AppCompatActivity implements OnClickListener {
    TextView collectBook, historyRecord;
    View tabLine;
    ViewPager mViewPager;
    HistoryRecordFragment historyRecordFragment;
    SaveBookFragment saveBookFragment;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fragment);
        initView();
    }

    public void initView() {

        collectBook = (TextView) findViewById(R.id.collectBook);
        historyRecord = (TextView) findViewById(R.id.historyRecord);
        tabLine = findViewById(R.id.tabLine);
        initViewPager();
        initTabLine();
        initOnClickListener();
    }

    private void initOnClickListener() {
        collectBook.setOnClickListener(this);
        historyRecord.setOnClickListener(this);
    }

    public void initViewPager() {
        saveBookFragment = SaveBookFragment.newInstance("", "");
        historyRecordFragment = HistoryRecordFragment.newInstance(1);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mFragments.add(saveBookFragment);
        mFragments.add(historyRecordFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                layoutParams.leftMargin = (int) ((position + positionOffset) * tabLine.getWidth());
                tabLine.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (mViewPager.getCurrentItem() == 0) {
                    collectBook.setTextColor(Color.RED);
                    historyRecord.setTextColor(Color.BLACK);
                } else {
                    collectBook.setTextColor(Color.BLACK);
                    historyRecord.setTextColor(Color.RED);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collectBook:
                break;
            case R.id.historyRecord:
                break;
        }
    }

    private void initTabLine() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabLine.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels / 2;
        tabLine.setLayoutParams(layoutParams);
    }
}
