package com.practice.myhome.fakeInstar;

import android.app.Application;

import com.practice.myhome.fakeInstar.util.Util;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Util.init(this);
    }

}
