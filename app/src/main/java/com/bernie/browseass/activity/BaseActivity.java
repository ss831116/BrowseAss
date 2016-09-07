package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bernie.shi on 2016/9/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
    }
    @Override
    protected void onStart(){
        super.onStart();
        initView();
    }
    public abstract void initView();
}
