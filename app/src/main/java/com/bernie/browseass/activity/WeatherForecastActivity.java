package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.android.volley.VolleyError;
import com.bernie.browseass.R;
import com.bernie.browseass.bean.weather.WeatherBean;
import com.bernie.browseass.http.HttpRequest;
import com.bernie.browseass.listener.HttpRequestListener;
import com.bernie.browseass.utils.RequestKey;
import com.google.gson.Gson;

import org.json.JSONObject;

public class WeatherForecastActivity extends AppCompatActivity implements AMapLocationListener, OnClickListener, HttpRequestListener {
    TextView dateTime, addressText, weather, wind, pm25, life;
    ImageView backImage, weatherBgImg;
    public AMapLocationClient mLocationClient = null;
    private static final String NOW_WEATHER_TAG = "get_weather_now";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
        initView();
    }

    public void initView() {
        addressText = (TextView) findViewById(R.id.addressText);
        dateTime = (TextView) findViewById(R.id.dateTime);
        backImage = (ImageView) findViewById(R.id.backImage);
        weatherBgImg = (ImageView) findViewById(R.id.weatherBgImg);
        weather = (TextView) findViewById(R.id.weather);
        wind = (TextView) findViewById(R.id.wind);
        pm25 = (TextView) findViewById(R.id.pm25);
        life = (TextView) findViewById(R.id.life);
        initOnClcikListener();
    }

    public void initOnClcikListener() {
        backImage.setOnClickListener(this);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                addressText.setText(aMapLocation.getCity() + aMapLocation.getDistrict());
                HttpRequest.httpRequest("http://op.juhe.cn/onebox/weather/query?cityname=" + aMapLocation.getCity() + RequestKey.WEATHER_KEY, this, NOW_WEATHER_TAG);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
        }
    }

    @Override
    public void requestSuccess(JSONObject jsonObject) {
        WeatherBean weatherBean = new Gson().fromJson(jsonObject.toString(), WeatherBean.class);
        playResultWeather(weatherBean);
    }

    public void playResultWeather(WeatherBean weatherBean) {
        weatherBgImg.setBackgroundResource(getWeatherBg(weatherBean.getResult().getData().getRealtime().getWeather().getInfo()));
        dateTime.setText(weatherBean.getResult().getData().getRealtime().getDate() + "  " + weatherBean.getResult().getData().getRealtime().getTime() + "\n"
                + "农历 : " + weatherBean.getResult().getData().getRealtime().getMoon());
        weather.setText("温度:  " + weatherBean.getResult().getData().getRealtime().getWeather().getTemperature() + "\n" +
                "湿度:  " + weatherBean.getResult().getData().getRealtime().getWeather().getHumidity() + "\n" +
                "天气:  " + weatherBean.getResult().getData().getRealtime().getWeather().getInfo());
        wind.setText("风向:  " + weatherBean.getResult().getData().getRealtime().getWind().getDirect() + "\n" +
                "风力:  " + weatherBean.getResult().getData().getRealtime().getWind().getPower() + "\n" +
                "风速:  " + weatherBean.getResult().getData().getRealtime().getWind().getWindspeed());
        pm25.setText("当前PM指数:  " + weatherBean.getResult().getData().getPm25().getPm25().getCurPm() + "\n" +
                "PM25:  " + weatherBean.getResult().getData().getPm25().getPm25().getPm25() + "\n" +
                "PM10:  " + weatherBean.getResult().getData().getPm25().getPm25().getPm10() + "\n" +
                "空气等级:  " + weatherBean.getResult().getData().getPm25().getPm25().getLevel() + "\n" +
                "空气质量:  " + weatherBean.getResult().getData().getPm25().getPm25().getQuality() + "\n" +
                "活动:  " + weatherBean.getResult().getData().getPm25().getPm25().getDes());
        life.setText("穿衣:  " + weatherBean.getResult().getData().getLife().getInfo().getChuanyi() + "\n" +
                "感冒:  " + weatherBean.getResult().getData().getLife().getInfo().getGanmao() + "\n" +
                "空调:  " + weatherBean.getResult().getData().getLife().getInfo().getKongtiao() + "\n" +
                "污染:  " + weatherBean.getResult().getData().getLife().getInfo().getWuran() + "\n" +
                "洗车:  " + weatherBean.getResult().getData().getLife().getInfo().getXiche() + "\n" +
                "运动:  " + weatherBean.getResult().getData().getLife().getInfo().getYundong() + "\n" +
                "紫外线:  " + weatherBean.getResult().getData().getLife().getInfo().getZiwaixian());
    }

    public int getWeatherBg(String weather) {
        int id = 0;
        if (weather.contains("多云")) {
            id = R.drawable.clouds;
        } else if (weather.contains("晴")) {
            id = R.drawable.qingtian;
        } else if (weather.contains("阴")) {
            id = R.drawable.yintian;
        } else if (weather.contains("小雨")) {
            id = R.drawable.xiaoyu;
        } else if (weather.contains("中雨")) {
            id = R.drawable.zhongyu;
        } else if (weather.contains("大雨")) {
            id = R.drawable.dayu;
        } else if (weather.contains("阵雨")) {
            id = R.drawable.zhenyu;
        } else if (weather.contains("暴雨")) {
            id = R.drawable.baoyu;
        } else if (weather.contains("雷电")) {
            id = R.drawable.leidian;
        } else {
            id = R.drawable.qingtian;
        }
        return id;
    }

    @Override
    public void requestFail(VolleyError error) {

    }
}
