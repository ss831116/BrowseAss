package com.bernie.browseass.bean.calendar;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Result {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    Data data;

    public static class Data {
        public String getAvoid() {
            return avoid;
        }

        public void setAvoid(String avoid) {
            this.avoid = avoid;
        }

        String avoid;

        public String getAnimalsYear() {
            return animalsYear;
        }

        public void setAnimalsYear(String animalsYear) {
            this.animalsYear = animalsYear;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public String getLunarYear() {
            return lunarYear;
        }

        public void setLunarYear(String lunarYear) {
            this.lunarYear = lunarYear;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }

        public String getYear_month() {
            return year_month;
        }

        public void setYear_month(String year_month) {
            this.year_month = year_month;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        String animalsYear;
        String weekday;
        String suit;
        String lunarYear;
        String lunar;
        String year_month;
        String date;

    }
}
