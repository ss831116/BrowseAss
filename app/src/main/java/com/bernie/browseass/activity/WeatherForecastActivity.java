package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bernie.browseass.R;
import com.bernie.browseass.listener.WeatherListener;
import com.bernie.browseass.utils.Weather;

import java.util.Calendar;
import java.util.Date;

public class WeatherForecastActivity extends AppCompatActivity implements AMapLocationListener,WeatherListener {
    TextView weatherText, addressText;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private static final String WEATHER_TAG = "get_weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        addressText = (TextView) findViewById(R.id.addressText);
        weatherText = (TextView) findViewById(R.id.weatherText);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                addressText.setText(aMapLocation.getCity());
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
                Weather.getWeatherSeveral(aMapLocation.getCity(),3,date,this,"several_tag");
            }
        }
    }
    @Override
    public void getWeatherComplete(Object object,String TAG) {

    }

    @Override
    public void getWeatherFail(String errorInfo) {

    }
}
