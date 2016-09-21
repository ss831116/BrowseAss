package com.bernie.browseass.utils;

import java.util.List;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class Result {
    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int stat;
    List<Data> data;
}
