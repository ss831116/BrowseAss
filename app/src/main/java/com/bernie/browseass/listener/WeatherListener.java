package com.bernie.browseass.listener;

/**
 * Created by bernie.shi on 2016/9/19.
 */

public interface WeatherListener {
    void getWeatherComplete(Object object,String TAG);
    void getWeatherFail(String errorInfo);
}
