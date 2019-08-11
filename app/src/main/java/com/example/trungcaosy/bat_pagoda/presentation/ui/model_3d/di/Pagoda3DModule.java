package com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class Pagoda3DModule {

    @ActivityScope
    @Binds
    abstract BaseContract.PresenterContract providePresenter(Pagoda3DPresenter presenter);

}
