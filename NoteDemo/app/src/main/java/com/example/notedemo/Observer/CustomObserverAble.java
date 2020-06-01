package com.example.notedemo.Observer;

//被观察者
public interface CustomObserverAble {

    void addObserver(CustomObserver observer);

    void removeObserver(CustomObserver observer);

    void notifyObservers();

    void pushMessage(String msg);

}
