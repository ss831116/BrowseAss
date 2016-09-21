package com.bernie.browseass.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bernie.browseass.R;
import com.bernie.browseass.adapter.NewsAdapter;
import com.bernie.browseass.bean.NewsBean;
import com.bernie.browseass.http.HttpRequest;
import com.bernie.browseass.listener.HttpRequestListener;
import com.bernie.browseass.listener.NewsListener;
import com.bernie.browseass.utils.Data;
import com.bernie.browseass.utils.RequestKey;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class NewsTypeOverviewActivity extends AppCompatActivity implements HttpRequestListener,NewsListener {
    TextView newsType;
    private static final String NEWS_TAG = "get_news";
    NewsAdapter newsAdapter;
    ListView newsList;
    ImageView backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_type_overview);
        initView();
    }

    public void initView() {
        newsType = (TextView) findViewById(R.id.newsType);
        newsList = (ListView)findViewById(R.id.newsList);
        backImage = (ImageView)findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsTypeOverviewActivity.this.finish();
            }
        });
        if (getIntent() != null) {
            newsType.setText(getIntent().getStringExtra("typeText"));
        }
        getData(getIntent().getStringExtra("type"));
    }

    public void getData(String type) {
        if (type != null && !"".equals(type)) {
            HttpRequest.httpRequest("http://v.juhe.cn/toutiao/index?type=" + type + RequestKey.NEWS_KEY, this, NEWS_TAG);
        }

    }

    @Override
    public void requestSuccess(JSONObject jsonObject) {
        NewsBean newsBean = new Gson().fromJson(jsonObject.toString(), NewsBean.class);
        List<Data> dataList = newsBean.getResult().getData();
        newsAdapter = new NewsAdapter(getApplicationContext(),dataList,this);
        newsList.setAdapter(newsAdapter);
    }

    @Override
    public void requestFail(VolleyError error) {

    }

    @Override
    public void openNews(String webSite) {
        Uri  uri = Uri.parse(webSite);
        Intent intent = new  Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
