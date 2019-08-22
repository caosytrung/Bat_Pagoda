package com.example.trungcaosy.bat_pagoda.domain.repository;

import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;

import java.util.List;

import io.reactivex.Observable;

public interface MapRepository {
    Observable<List<MapDataResponse>> getMapData();
}
