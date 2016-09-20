package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bernie.browseass.R;
import com.bernie.browseass.listener.WeatherListener;
import com.bernie.browseass.utils.Weather;
import com.thinkpage.lib.api.TPWeatherNow;

public class WeatherForecastActivity extends AppCompatActivity implements AMapLocationListener, WeatherListener, OnClickListener {
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
                Weather.getWeatherNow(aMapLocation.getCity(), this, NOW_WEATHER_TAG);
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
}
