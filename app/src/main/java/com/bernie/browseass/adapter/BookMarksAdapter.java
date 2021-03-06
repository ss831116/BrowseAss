package com.bernie.browseass.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.listener.BookMarksListener;
import com.bernie.browseass.utils.BitmapHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bernie.greendao.dao.BrowseAssBookMarks;

/**
 * Created by bernie.shi on 2016/9/7.
 */

public class BookMarksAdapter extends BaseAdapter {
    public List<BrowseAssBookMarks> bookMarksBeanList = new ArrayList<>();
    Context context;
    private LayoutInflater mInflater;
    BookMarksListener bookMarksListener;

    public BookMarksAdapter(Context context, Activity activity, List<BrowseAssBookMarks> bookMarksBeanList, BookMarksListener bookMarksListener) {
        this.context = context;
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
        final BrowseAssBookMarks bookMarks = bookMarksBeanList.get(position);
        if (null == view) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.book_marks_adapter, viewGroup, false);
            viewHolder.webSiteIcon = (SimpleDraweeView) view.findViewById(R.id.webSiteIcon);
            viewHolder.saveTime = (TextView) view.findViewById(R.id.saveTime);
            viewHolder.webSite = (TextView) view.findViewById(R.id.webSite);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.saveTime.setText(bookMarks.getSaveDate());
        viewHolder.webSite.setText(bookMarks.getWebSite());
        viewHolder.title.setText(bookMarks.getTitle());
        if(bookMarks.getWebSiteIcon() != null) {
            Bitmap bitmap = BitmapHelper.byteToBitmap(bookMarks.getWebSiteIcon());
            viewHolder.webSiteIcon.setImageBitmap(bitmap);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMarksListener.chooseBookMark(bookMarks.getWebSite());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                bookMarksListener.fixBookMark(bookMarks);
                return true;
            }
        });
        return view;
    }

    class ViewHolder {
        SimpleDraweeView webSiteIcon;
        TextView webSite;
        TextView saveTime;
        TextView title;
    }
    public void fresh(List<BrowseAssBookMarks> bookMarksBeanList){
        this.bookMarksBeanList = bookMarksBeanList;
        notifyDataSetChanged();
    }
}
