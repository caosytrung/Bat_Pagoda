package com.example.trungcaosy.bat_pagoda.presentation.ui.map.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MapModule {
    @ActivityScope
    @Binds
    abstract BaseContract.PresenterContract providePresenter(MapPresenter presenter);
}