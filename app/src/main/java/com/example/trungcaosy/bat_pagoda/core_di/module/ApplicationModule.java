package com.example.trungcaosy.bat_pagoda.core_di.module;


import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule  {

    @Binds
    abstract Context bindContext(Application application);

}
