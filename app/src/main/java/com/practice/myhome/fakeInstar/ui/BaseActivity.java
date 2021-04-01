package com.practice.myhome.fakeInstar.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.practice.myhome.fakeInstar.util.Util;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Util.setCurrentActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Util.setCurrentActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Util.setCurrentActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.setCurrentActivity(null);
    }
}