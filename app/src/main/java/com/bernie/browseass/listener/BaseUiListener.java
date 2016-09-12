package com.bernie.browseass.listener;

import android.util.Log;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by bernie.shi on 2016/9/12.
 */

public class BaseUiListener implements IUiListener {

    protected void doComplete(JSONObject values) {
        Log.d("onCancel:", "values = " + values.toString());
    }

    @Override
    public void onComplete(Object o) {
        doComplete((JSONObject) o);
    }

    @Override
    public void onError(UiError e) {
        Log.d("onError:", "code:" + e.errorCode + ", msg:"
                + e.errorMessage + ", detail:" + e.errorDetail);
    }
    @Override
    public void onCancel() {
        Log.d("onCancel:", "onCancel:");
    }
}
