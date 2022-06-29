package com.gddg.lookout;

import android.app.Application;

import com.gddg.lookout.utils.LookoutUtils;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LookoutUtils.init(this);
    }
}

