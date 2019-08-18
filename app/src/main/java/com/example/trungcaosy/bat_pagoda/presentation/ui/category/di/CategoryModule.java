package com.example.trungcaosy.bat_pagoda.presentation.ui.category.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DetailModule {
    @ActivityScope
    @Binds
    abstract BaseContract.PresenterContract providePresenter(CategoryPresenter presenter);
}
