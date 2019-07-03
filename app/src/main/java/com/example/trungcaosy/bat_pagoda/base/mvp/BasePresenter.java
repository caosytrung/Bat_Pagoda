package com.example.trungcaosy.bat_pagoda.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.annotation.CallSuper;

public abstract class BasePresenter<V extends  BaseContract.ViewContract> implements  BaseContract.PresenterContract<V>,LifecycleObserver {

    private Bundle mBundle;
    private  V mView;



    @Override
    public Bundle getStateBundle() {
        return mBundle == null
                ? mBundle = new Bundle()
                : mBundle;
    }

    @Override
    public void attachLifeCycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifeCycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }


    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void onPresenterCreated() {

    }

    @CallSuper
    @Override
    public void onPresenterDestroyed() {
        if (mBundle != null && !mBundle.isEmpty()) {
            mBundle.clear();
        }
    }
}
