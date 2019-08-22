package com.example.trungcaosy.bat_pagoda.domain.interactor;

import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;
import com.example.trungcaosy.bat_pagoda.domain.repository.ItemDetailRepository;
import com.example.trungcaosy.bat_pagoda.domain.repository.MapRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MapUseCase {

    private MapRepository mapRepository;

    @Inject
    public MapUseCase(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public Observable<List<MapDataResponse>> getMaps(){
        return  mapRepository.getMapData();
    }
}
