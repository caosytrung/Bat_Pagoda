package com.example.trungcaosy.bat_pagoda.domain.fetcher;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Fetcher {

    private CompositeDisposable mDisposable;

    @Inject
    public Fetcher(CompositeDisposable compositeDisposable){
        mDisposable = compositeDisposable;
    }

    public <T>void fetch(Observable<T> observable, final ResultListener<T> resultListener){
        mDisposable.add(observable.subscribeOn(Schedulers.io    ())
                 .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        resultListener.onRequestStart();
                    }
                }).subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        resultListener.onRequestSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        resultListener.onRequestError(throwable);
                    }
                })

            );

    }

    public void cancelRequest(){
        mDisposable.clear();
    }

    public void clear(){
        mDisposable.clear();
    }

}
