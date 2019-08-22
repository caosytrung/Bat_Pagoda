package com.example.trungcaosy.bat_pagoda.data.repository;

import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;
import com.example.trungcaosy.bat_pagoda.domain.repository.MapRepository;
import com.example.trungcaosy.bat_pagoda.network.AppAPIService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MapDataRepositoryImpl implements MapRepository {
    private AppAPIService appAPIService;

    @Inject
    public MapDataRepositoryImpl(AppAPIService appAPIService){
        this.appAPIService = appAPIService;
    }

    @Override
    public Observable<List<MapDataResponse>> getMapData() {
        return appAPIService.getMapData();
    }
}
