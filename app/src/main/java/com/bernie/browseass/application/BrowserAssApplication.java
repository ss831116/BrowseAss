package com.bernie.browseass.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;

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

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
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
