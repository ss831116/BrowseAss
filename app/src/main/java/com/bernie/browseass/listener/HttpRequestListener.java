package com.bernie.browseass.listener;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by bernie.shi on 2016/9/19.
 */

public interface HttpRequestListener {
    void requestSuccess(JSONObject jsonObject);
    void requestFail(VolleyError error);
}
