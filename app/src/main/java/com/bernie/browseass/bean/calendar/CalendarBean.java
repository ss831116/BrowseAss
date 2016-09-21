package com.bernie.browseass.bean.calendar;


/**
 * Created by bernie.shi on 2016/9/21.
 */

public class CalendarBean {
    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    int error_code;
    String reason;
    Result result;
}
