package com.bernie.browseass.bean.weather;

import java.util.List;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Info {
    public List<String> getChuanyi() {
        return chuanyi;
    }

    public void setChuanyi(List<String> chuanyi) {
        this.chuanyi = chuanyi;
    }

    public List<String> getGanmao() {
        return ganmao;
    }

    public void setGanmao(List<String> ganmao) {
        this.ganmao = ganmao;
    }

    public List<String> getKongtiao() {
        return kongtiao;
    }

    public void setKongtiao(List<String> kongtiao) {
        this.kongtiao = kongtiao;
    }

    public List<String> getWuran() {
        return wuran;
    }

    public void setWuran(List<String> wuran) {
        this.wuran = wuran;
    }

    public List<String> getXiche() {
        return xiche;
    }

    public void setXiche(List<String> xiche) {
        this.xiche = xiche;
    }

    public List<String> getYundong() {
        return yundong;
    }

    public void setYundong(List<String> yundong) {
        this.yundong = yundong;
    }

    public List<String> getZiwaixian() {
        return ziwaixian;
    }

    public void setZiwaixian(List<String> ziwaixian) {
        this.ziwaixian = ziwaixian;
    }

    List<String> chuanyi;
    List<String> ganmao;
    List<String> kongtiao;
    List<String> wuran;
    List<String> xiche;
    List<String> yundong;
    List<String> ziwaixian;
}
