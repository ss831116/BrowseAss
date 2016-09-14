package com.bernie.browseass.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.listener.FragmentListener;

import java.util.List;

import bernie.greendao.dao.HistoryWebPage;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<HistoryWebPage> historyWebPageList;
    FragmentListener fragmentListener;
    public MyItemRecyclerViewAdapter(List<HistoryWebPage> historyWebPageList, FragmentListener fragmentListener) {
        this.historyWebPageList = historyWebPageList;
        this.fragmentListener = fragmentListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.historyWebPage = historyWebPageList.get(position);
        holder.category.setText("A");
        holder.webTitle.setText(holder.historyWebPage.getWebTitle());
        holder.webPageSite.setText(holder.historyWebPage.getWebPageSite());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.setResultPageSelect(holder.historyWebPage.getWebPageSite());
            }
        });
        Log.d("shifuqiang","time = " + holder.historyWebPage.getScanTime());
    }

    @Override
    public int getItemCount() {
        return historyWebPageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView category;
        public final TextView webTitle;
        public final TextView webPageSite;
        public HistoryWebPage historyWebPage;

        public ViewHolder(View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.category);
            webTitle = (TextView) view.findViewById(R.id.webTitle);
            webPageSite = (TextView) view.findViewById(R.id.webPageSite);
        }
    }
}
