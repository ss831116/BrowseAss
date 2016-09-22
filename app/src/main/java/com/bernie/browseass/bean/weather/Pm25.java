package com.bernie.browseass.bean.weather;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Pm25 {
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(String show_desc) {
        this.show_desc = show_desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public PM25 getPm25() {
        return pm25;
    }

    public void setPm25(PM25 pm25) {
        this.pm25 = pm25;
    }

    String show_desc;
    String dateTime;
    String cityName;
    PM25 pm25;
    public static class PM25{
        String curPm;
        String pm25;

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        String pm10;
        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getCurPm() {
            return curPm;
        }

        public void setCurPm(String curPm) {
            this.curPm = curPm;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        String level;
        String quality;
        String des;
    }
}
