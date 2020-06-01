package com.example.notedemo.Observer;

import java.util.ArrayList;
import java.util.List;

//自定义标准的观察者模式
public class MyObserverable implements CustomObserverAble {

    private List<CustomObserver> mList = new ArrayList();
    private String message;


    @Override
    public void addObserver(CustomObserver observer) {
        mList.add(observer);

    }

    @Override
    public void removeObserver(CustomObserver observer) {
        mList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        //
        for (CustomObserver observers : mList) {
            observers.updateMessage(message);
        }
    }

    @Override
    public void pushMessage(String msg) {
        this.message = msg;
        notifyObservers();
    }


}
