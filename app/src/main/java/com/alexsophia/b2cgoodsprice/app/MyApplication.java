package com.alexsophia.b2cgoodsprice.app;

import android.app.Application;

/**
 * MyApplication
 * <p>
 * Created by Alexander on 2016/10/24.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    public MyApplication() {
        myApplication = MyApplication.this;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
