package com.bernie.browseass.bean.weather;

import java.util.List;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Data {

    public RealTime getRealtime() {
        return realtime;
    }

    public void setRealtime(RealTime realtime) {
        this.realtime = realtime;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public Pm25 getPm25() {
        return pm25;
    }

    public void setPm25(Pm25 pm25) {
        this.pm25 = pm25;
    }

    public String getJingqu() {
        return jingqu;
    }

    public void setJingqu(String jingqu) {
        this.jingqu = jingqu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(String isForeign) {
        this.isForeign = isForeign;
    }

    RealTime realtime;
    Life life;
    Pm25 pm25;
    String jingqu;
    String date;
    String isForeign;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    List<Weather> weather;
}
