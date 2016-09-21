package com.bernie.browseass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.bernie.browseass.R;

public class NewsActivity extends AppCompatActivity implements OnClickListener {
    private static final String NEWS_TAG = "get_news";
    ImageView backImage;
    Button society, domestic, international, entertainment, sports, military, science, finance, fashion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }

    public void initView() {
        backImage = (ImageView) findViewById(R.id.backImage);
        society = (Button) findViewById(R.id.society);
        domestic = (Button) findViewById(R.id.domestic);
        international = (Button) findViewById(R.id.international);
        entertainment = (Button) findViewById(R.id.entertainment);
        sports = (Button) findViewById(R.id.sports);
        military = (Button) findViewById(R.id.military);
        science = (Button) findViewById(R.id.science);
        finance = (Button) findViewById(R.id.finance);
        fashion = (Button) findViewById(R.id.fashion);
        initOnClickListener();
    }

    public void initOnClickListener() {
        backImage.setOnClickListener(this);
        society.setOnClickListener(this);
        domestic.setOnClickListener(this);
        international.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        sports.setOnClickListener(this);
        military.setOnClickListener(this);
        science.setOnClickListener(this);
        finance.setOnClickListener(this);
        fashion.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
            case R.id.society:
                gotoNewsTypeActivity("shehui",society.getText().toString());
                break;
            case R.id.domestic:
                gotoNewsTypeActivity("guonei",domestic.getText().toString());
                break;
            case R.id.international:
                gotoNewsTypeActivity("guoji",international.getText().toString());
                break;
            case R.id.entertainment:
                gotoNewsTypeActivity("yule",entertainment.getText().toString());
                break;
            case R.id.sports:
                gotoNewsTypeActivity("tiyu",sports.getText().toString());
                break;
            case R.id.military:
                gotoNewsTypeActivity("junshi",military.getText().toString());
                break;
            case R.id.science:
                gotoNewsTypeActivity("keji",science.getText().toString());
                break;
            case R.id.finance:
                gotoNewsTypeActivity("caijing",finance.getText().toString());
                break;
            case R.id.fashion:
                gotoNewsTypeActivity("shishang",fashion.getText().toString());
                break;
        }
    }
    public void gotoNewsTypeActivity(String type,String typeText){
        Intent intent = new Intent(this,NewsTypeOverviewActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("typeText",typeText);
        startActivity(intent);
    }
}
