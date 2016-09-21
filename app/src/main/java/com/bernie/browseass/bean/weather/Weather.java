package com.bernie.browseass.bean.weather;

import java.util.List;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Weather {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InFo getInfo() {
        return info;
    }

    public void setInfo(InFo info) {
        this.info = info;
    }

    String date;
    InFo info;
    public static class InFo{
        public List<String> getDawn() {
            return dawn;
        }

        public void setDawn(List<String> dawn) {
            this.dawn = dawn;
        }

        public List<String> getDay() {
            return day;
        }

        public void setDay(List<String> day) {
            this.day = day;
        }

        public List<String> getNight() {
            return night;
        }

        public void setNight(List<String> night) {
            this.night = night;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getNongli() {
            return nongli;
        }

        public void setNongli(String nongli) {
            this.nongli = nongli;
        }

        List<String> dawn;
        List<String> day;
        List<String> night;
        String week;
        String nongli;
    }
}
