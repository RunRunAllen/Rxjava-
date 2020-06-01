package com.example.notedemo;

import android.util.Log;

import com.example.notedemo.Observer.CustomObserver;

public class MyObserver implements CustomObserver {

    @Override
    public void updateMessage(String msg) {
        Log.i("haha", "====msg====" + msg);
    }
}
