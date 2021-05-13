package com.example.delivery;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {

    private SPUtils spUtils;

    private static MyApplication myApplication;

    private static Context context;

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (myApplication == null) {
            myApplication = this;
        }
        if (context == null) {
            context = getApplicationContext();
        }

        Utils.init(this);
        Realm.init(this);
        if (spUtils == null) {
            spUtils = SPUtils.getInstance("delivery");
        }
        LogUtils.getConfig().setGlobalTag("SDUT");
    }

    public SPUtils getSputils() {
        if (spUtils == null) {
            spUtils = SPUtils.getInstance("delivery");
        }
        return spUtils;
    }
}