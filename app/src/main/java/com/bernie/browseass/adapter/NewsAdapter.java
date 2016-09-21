package com.bernie.browseass.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.listener.NewsListener;
import com.bernie.browseass.utils.Data;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class NewsAdapter extends BaseAdapter {
    List<Data> dataList;
    Context context;
    private LayoutInflater mInflater;
    NewsListener newsListener;
    public NewsAdapter(Context context, List<Data> dataList, NewsListener newsListener){
        this.context = context;
        this.dataList = dataList;
        this.newsListener = newsListener;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return dataList != null ? dataList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final Data data = dataList.get(i);
        if (null == view) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.news_adapter, viewGroup, false);
            viewHolder.newsIcon = (SimpleDraweeView) view.findViewById(R.id.newsIcon);
            viewHolder.sendTime = (TextView) view.findViewById(R.id.sendTime);
            viewHolder.newsTitle = (TextView) view.findViewById(R.id.newsTitle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.sendTime.setText(data.getDate());
        viewHolder.newsTitle.setText(data.getTitle());
        viewHolder.newsIcon.setImageURI(Uri.parse(data.getThumbnail_pic_s()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsListener.openNews(data.getUrl());
            }
        });

        return view;
    }
    class ViewHolder {
        SimpleDraweeView newsIcon;
        TextView newsTitle;
        TextView sendTime;
    }
}
