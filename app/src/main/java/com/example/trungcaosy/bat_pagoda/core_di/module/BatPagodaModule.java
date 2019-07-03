package com.example.trungcaosy.bat_pagoda.core_di.module;

import android.app.Application;

import com.example.trungcaosy.bat_pagoda.data.AppDataRepository;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.Fetcher;
import com.example.trungcaosy.bat_pagoda.domain.repository.AppRepository;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class BatPagodaModule {
    @Provides
    AppRepository provideRepository(){
        return  new AppDataRepository();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable( ){
        return  new CompositeDisposable();
    }

    @Singleton
    @Provides
    Fetcher provideFetcher(CompositeDisposable disposable) {
        return new Fetcher(disposable);
    }

    @Provides
    @Singleton
    MqttAndroidClient provideMqttAndroidClient (Application application) {
        String clientId = MqttClient.generateClientId();
        return new MqttAndroidClient(application, DataConstant.MQTT_SERVER,
                clientId);
    }
}
