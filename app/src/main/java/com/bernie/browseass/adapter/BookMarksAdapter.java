package com.bernie.browseass.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.bean.BookMarksBean;
import com.bernie.browseass.listener.BookMarksListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernie.shi on 2016/9/7.
 */

public class BookMarksAdapter extends BaseAdapter {
    public List<BookMarksBean> bookMarksBeanList = new ArrayList<>();
    Context context;
    Activity activity;
    private LayoutInflater mInflater;
    BookMarksListener bookMarksListener;
    public BookMarksAdapter(Context context, Activity activity, List<BookMarksBean> bookMarksBeanList, BookMarksListener bookMarksListener) {
        this.context = context;
        this.activity = activity;
        this.bookMarksBeanList = bookMarksBeanList;
        this.bookMarksListener = bookMarksListener;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bookMarksBeanList != null ? bookMarksBeanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return bookMarksBeanList != null ? bookMarksBeanList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final BookMarksBean bookMarksBean = bookMarksBeanList.get(position);
        if (null == view) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.book_marks_adapter, viewGroup, false);
            viewHolder.webSiteIcon =(SimpleDraweeView) view.findViewById(R.id.webSiteIcon);
            viewHolder.saveTime =(TextView) view.findViewById(R.id.saveTime);
            viewHolder.webSite =(TextView) view.findViewById(R.id.webSite);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.saveTime.setText(bookMarksBean.getSaveTime());
        viewHolder.webSite.setText(bookMarksBean.getWebSite());
        Uri uri = Uri.parse(bookMarksBean.getWebSiteIcon());
        viewHolder.webSiteIcon.setImageURI(uri);
        viewHolder.webSiteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMarksListener.chooseBookMark(bookMarksBean.getWebSite());
            }
        });

        return view;
    }
    class ViewHolder {
        SimpleDraweeView webSiteIcon;
        TextView webSite;
        TextView saveTime;
    }
}
