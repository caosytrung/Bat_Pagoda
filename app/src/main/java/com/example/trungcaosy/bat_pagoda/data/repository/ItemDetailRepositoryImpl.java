package com.example.trungcaosy.bat_pagoda.data.repository;

import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.domain.repository.ItemDetailRepository;
import com.example.trungcaosy.bat_pagoda.network.AppAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ItemDetailRepositoryImpl implements ItemDetailRepository {
    private AppAPIService appAPIService;

    @Inject
    public ItemDetailRepositoryImpl(AppAPIService appAPIService){
        this.appAPIService = appAPIService;
    }

    @Override
    public Observable<ItemDetail> getItemDetail(String id) {
        return appAPIService.getItemDetail(id);
    }
}
