package com.example.trungcaosy.bat_pagoda.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

public interface BaseContract {
    interface ViewContract {

    }

    interface  PresenterContract<V extends ViewContract>{
        Bundle getStateBundle();

        void attachLifeCycle(Lifecycle lifecycle);

        void detachLifeCycle(Lifecycle lifecycle);

        void attachView(V view);

        void detachView();

        V getView();

        boolean isViewAttached();

        void onPresenterCreated();

        void onPresenterDestroyed();
    }
}
