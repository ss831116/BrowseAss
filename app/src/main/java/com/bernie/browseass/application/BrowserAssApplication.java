package com.bernie.browseass.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.tauth.Tencent;

import bernie.greendao.dao.DaoMaster;
import bernie.greendao.dao.DaoSession;


/**
 * Created by bernie.shi on 2016/9/6.
 */

public class BrowserAssApplication extends Application {
    DaoMaster daoMaster;
    private SQLiteDatabase sqLiteDatabase;
    DaoSession daoSession;
    public static BrowserAssApplication instance;
    private static Tencent mTencent;
    private static final String APP_ID = "1105614121";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);

    }
    public static Tencent getTencent(){
        if(mTencent == null){
            mTencent = Tencent.createInstance(APP_ID, instance.getApplicationContext());
        }
        return mTencent;
    }

    public DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getSqLiteDatabase(context));
        }
        return daoMaster;
    }

    public SQLiteDatabase getSqLiteDatabase(Context context) {
        if (sqLiteDatabase == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "browseAss-db", null);
            sqLiteDatabase = helper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
