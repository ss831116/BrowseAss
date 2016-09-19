package com.bernie.browseass.utils;

import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.WeatherListener;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherDaily;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.util.Date;

/**
 * Created by bernie.shi on 2016/9/19.
 */

public class Weather {
    public static void getWeatherNow(String address, final WeatherListener weatherListener,final String TAG) {
        BrowserAssApplication.weatherManager.getWeatherNow(new TPCity(address)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new TPListeners.TPWeatherNowListener() {
                    @Override
                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                        if (weatherNow != null) {
                            weatherListener.getWeatherComplete(weatherNow,TAG);
                        } else {
                            weatherListener.getWeatherFail(errorInfo);
                        }
                    }
                });
    }

    public static void getWeatherSeveral(String address,int days,Date date,final WeatherListener weatherListener,final String TAG) {
       // Calendar cal = Calendar.getInstance();
        //Date date = cal.getTime();
        BrowserAssApplication.weatherManager.getWeatherDailyArray(new TPCity(address)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , date
                , days
                , new TPListeners.TPWeatherDailyListener() {

                    @Override
                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String errorInfo) {
                        if (tpWeatherDailies != null) {
                            weatherListener.getWeatherComplete(tpWeatherDailies,TAG);
                        } else {
                            weatherListener.getWeatherFail(errorInfo);
                        }
                    }
                });
    }
}
