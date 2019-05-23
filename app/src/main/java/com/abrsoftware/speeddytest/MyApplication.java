package com.abrsoftware.speeddytest;

import android.app.Application;
import android.content.Context;

/**
 * Created by AbrWin on 23/05/19.
 */

public class MyApplication extends Application {
    private static Context ctx;
    private static MyApplication myApplication;
    private static boolean isDebuggable;


    @Override
    public void onCreate() {
        super.onCreate();
        this.setCtx(getApplicationContext());
    }


    public static Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public static boolean isDebuggable() {
        return isDebuggable;
    }

    public static void setIsDebuggable(boolean isDebuggable) {
        MyApplication.isDebuggable = isDebuggable;
    }
}
