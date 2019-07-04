package com.example.trungcaosy.bat_pagoda.application;

import android.app.Activity;
import android.app.Application;

import com.example.trungcaosy.bat_pagoda.core_di.component.AppComponent;
import com.example.trungcaosy.bat_pagoda.core_di.component.DaggerAppComponent;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MyApplication extends Application   implements HasActivityInjector,HasSupportFragmentInjector {

    private static WeakReference<MyApplication> applicationWeakReference;
    private volatile boolean needToInject = true;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> fragmentInjector;

    private boolean isOpened;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    public static MyApplication getInstance(){
        return applicationWeakReference.get();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        injectIfNecessary();
        applicationWeakReference = new WeakReference<>(this);
    }

    private void injectIfNecessary() {
        if (needToInject) {
            synchronized (this) {
                if (needToInject) {
                    @SuppressWarnings("unchecked")
                    AndroidInjector<MyApplication> applicationInjector =
                            (AndroidInjector<MyApplication>) applicationInjector();
                    applicationInjector.inject(this);
                    if (needToInject) {
                        throw new IllegalStateException(
                                "The AndroidInjector returned from applicationInjector() did not inject the "
                                        + "DaggerApplication");
                    }
                }
            }
        }
    }

    @Inject
    void setInject(){
        needToInject = false;
    }

    private AndroidInjector<? extends MyApplication>  applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

        return  appComponent;
    }


    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
