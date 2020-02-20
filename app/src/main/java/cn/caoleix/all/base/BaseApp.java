package cn.caoleix.all.base;

import android.app.Application;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    protected void init() {

    }

}