package com.example.trungcaosy.bat_pagoda.base.mvp;

import android.support.annotation.CallSuper;

import com.example.trungcaosy.bat_pagoda.domain.fetcher.Fetcher;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.ResultListener;

import javax.inject.Inject;

import io.reactivex.Observable;

public abstract class BaseApiPresenter<V extends  BaseListContract.ViewContract> extends  BasePresenter<V>{
    @Inject
    Fetcher mFetcher;

    public <R>void fetch(Observable<R> observable, ResultListener<R> resultListener){
        mFetcher.fetch(observable , resultListener);
    }

    @CallSuper
    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
        mFetcher.clear();
    }
}
