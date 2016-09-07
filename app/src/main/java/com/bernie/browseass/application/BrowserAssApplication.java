package com.bernie.browseass.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by bernie.shi on 2016/9/6.
 */

public class BrowserAssApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }
}
