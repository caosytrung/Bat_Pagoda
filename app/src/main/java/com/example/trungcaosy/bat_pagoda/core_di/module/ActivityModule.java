package com.example.trungcaosy.bat_pagoda.core_di.module;

import com.example.trungcaosy.bat_pagoda.core_di.scope.ActivityScope;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.DetailActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailModule;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.MainActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainModule;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.Pagoda3DViewerActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.di.Pagoda3DModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Pagoda3DModule.class)
    abstract Pagoda3DViewerActivity pagoda3DViewerActivityActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity detailActivity();
}
