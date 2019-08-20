package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;
import com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di.SplashPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DetailModule {
    @ActivityScope
    @Binds
    abstract BaseContract.PresenterContract providePresenter(DetailPresenter presenter);
}
