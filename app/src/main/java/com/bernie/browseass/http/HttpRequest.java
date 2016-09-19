package com.bernie.browseass.http;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.HttpRequestListener;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by bernie.shi on 2016/9/19.
 */

public class HttpRequest {
    public static void httpRequest(String webAddress, Map<Object, Object> params, final HttpRequestListener httpRequestListener, String tag) {
        BrowserAssApplication.getHttpQueues().cancelAll(tag);
        JsonObjectRequest newMissRequest = new JsonObjectRequest(
                Request.Method.POST, webAddress,
                new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonobj) {
                httpRequestListener.requestSuccess(jsonobj);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpRequestListener.requestFail(error);
            }
        });
        newMissRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        newMissRequest.setTag(tag);
        BrowserAssApplication.getHttpQueues().add(newMissRequest);
    }
}
