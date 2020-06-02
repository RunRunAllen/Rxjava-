package com.example.notedemo.rxjava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notedemo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

//Rxjava demo
public class TestOneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRxjava();
//
//        //标准观察者模式逻辑：
//        MyObserverable myObserverable = new MyObserverable();
//        MyObserver myObserver = new MyObserver();
//        myObserverable.addObserver(myObserver);
//        myObserverable.pushMessage("我要更新啦。。。。。");


        //hook 全局
        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
            @Override
            public Observable apply(Observable observable) throws Exception {
                // 全局所有的hook 点
                return observable;
            }
        });


    }

    private void initRxjava() {

        //初级用法 起点 被观察者
        Observable.just("url")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() { // 终点 /观察者
                    @Override
                    public void onSubscribe(Disposable d) {
                        //

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Observable.just("url")
                .compose(ObserverTransform())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

        //hook
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return scheduler;
            }
        });


        // Observable 的创建过程
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("dd");
            }

            //ObservableCreate 对象创建的
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return null;
            }
        })
                //对上层代码分配异步线程
                .subscribeOn(Schedulers.io())
                //给下面的代码分配线程
                .observeOn(AndroidSchedulers.mainThread())

                //2. subsribe 的订阅流程
                // ObservableMap  对象 创建的
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //防抖动 rxBinding
//        TextView tv = findViewById(R.id.tv);
//        RxView.clicks(tv)
//                .throttleFirst(2000, TimeUnit.MICROSECONDS)
//                .subscribe()

    }

    public <UD> ObservableTransformer<UD, UD> ObserverTransform() {
        return new ObservableTransformer<UD, UD>() {
            @Override
            public ObservableSource<UD> apply(Observable<UD> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<UD, UD>() {
                            @Override
                            public UD apply(UD ud) throws Exception {
                                return ud;
                            }
                        });
            }
        };
    }
}
