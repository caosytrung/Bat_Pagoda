package com.example.trungcaosy.bat_pagoda.core_di.module;

import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.MainActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();


}
