package com.example.trungcaosy.bat_pagoda.core_di.component;

import android.app.Application;

import com.example.trungcaosy.bat_pagoda.application.MyApplication;
import com.example.trungcaosy.bat_pagoda.core_di.module.APIModule;
import com.example.trungcaosy.bat_pagoda.core_di.module.ActivityModule;
import com.example.trungcaosy.bat_pagoda.core_di.module.ApplicationModule;
import com.example.trungcaosy.bat_pagoda.core_di.module.BatPagodaModule;
import com.example.trungcaosy.bat_pagoda.core_di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class,
        BatPagodaModule.class,
        ActivityModule.class,
        FragmentModule.class,
        AndroidSupportInjectionModule.class,
        APIModule.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Override
    void inject(MyApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
