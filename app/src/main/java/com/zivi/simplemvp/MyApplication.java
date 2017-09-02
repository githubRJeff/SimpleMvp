package com.zivi.simplemvp;

import android.app.Application;

/**
 * Created by zivi on 2017/8/24.
 */

public class MyApplication extends Application
{
    private static MyApplication application;

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
    }

    public static MyApplication getApplication()
    {
        return application;
    }
}
