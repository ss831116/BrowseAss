package com.bernie.browseass.bean.weather;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class WeatherBean {
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    String reason;
    int error_code;
    Result result;
}
