package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.bernie.browseass.listener.WeatherListener;
import com.bernie.browseass.utils.RequestKey;
import com.google.gson.Gson;
import com.thinkpage.lib.api.TPWeatherNow;

import org.json.JSONObject;

public class WeatherForecastActivity extends AppCompatActivity implements AMapLocationListener, WeatherListener, OnClickListener,HttpRequestListener {
    TextView weatherText, addressText;
    ImageView backImage;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private static final String NOW_WEATHER_TAG = "get_weather_now";
    private static final String Daily_WEATHER_TAG = "get_weather_daily";
    private static final String Hourly_WEATHER_TAG = "get_weather_hourly";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        addressText = (TextView) findViewById(R.id.addressText);
        weatherText = (TextView) findViewById(R.id.weatherText);
        backImage = (ImageView) findViewById(R.id.backImage);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
        backImage.setOnClickListener(this);
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                addressText.setText(aMapLocation.getCity() + aMapLocation.getDistrict());
                // Calendar cal = Calendar.getInstance();
                //Date date = cal.getTime();
                //Weather.getWeatherNow(aMapLocation.getCity(), this, NOW_WEATHER_TAG);
                HttpRequest.httpRequest("http://op.juhe.cn/onebox/weather/query?cityname="+aMapLocation.getCity() + RequestKey.WEATHER_KEY,this,NOW_WEATHER_TAG);
            }
        }
    }

    @Override
    public void getWeatherComplete(Object object, String tag) {
        if (tag.equals(NOW_WEATHER_TAG)) {
            TPWeatherNow weatherNow = (TPWeatherNow) object;
            weatherText.setText("temperature : " + String.valueOf(weatherNow.temperature) + "\n"
                    + "windDirection : " + String.valueOf(weatherNow.windDirection) + "\n"
                    + "clouds : " + String.valueOf(weatherNow.clouds) + "\n"
                    + "windDirection : " + weatherNow.windDirection + "\n"
                    + "dewPoint : " + String.valueOf(weatherNow.dewPoint) + "\n"
                    + "humidity : " + String.valueOf(weatherNow.humidity) + "\n"
                    + "feelsLikeTemperature : " + String.valueOf(weatherNow.feelsLikeTemperature) + "\n"
                    + "pressure : " + String.valueOf(weatherNow.pressure) + "\n"
                    + "windSpeed : " + String.valueOf(weatherNow.windSpeed) + "\n"
                    + "windScale : " + String.valueOf(weatherNow.windScale) + "\n"
                    + "windDirectionDegree : " + String.valueOf(weatherNow.windDirectionDegree) + "\n"
                    + "text : " + weatherNow.text + "\n"
                    + "code : " + weatherNow.code + "\n"
                    + "visibility : " + String.valueOf(weatherNow.visibility) + "\n"
                    + "lastUpdateDate : " + String.valueOf(weatherNow.lastUpdateDate)
            );
        }
    }

    @Override
    public void getWeatherFail(String errorInfo) {
        Log.d("shifuqiang", "error = " + errorInfo);
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
        Log.d("NewsActivity","json = " + weatherBean.getReason());
        Log.d("NewsActivity","json = " + weatherBean.getResult().getData().getRealtime().getCity_name());
    }

    @Override
    public void requestFail(VolleyError error) {

    }
}
