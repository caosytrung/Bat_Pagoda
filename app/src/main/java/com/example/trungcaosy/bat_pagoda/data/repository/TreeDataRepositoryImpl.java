package com.example.trungcaosy.bat_pagoda.data.repository;

import com.example.trungcaosy.bat_pagoda.data.response.TreeData;
import com.example.trungcaosy.bat_pagoda.domain.repository.TreeDataRepository;
import com.example.trungcaosy.bat_pagoda.network.AppAPIService;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TreeDataRepositoryImpl implements TreeDataRepository {

    private AppAPIService appAPIService;

    @Inject
    public TreeDataRepositoryImpl(AppAPIService appAPIService){
        this.appAPIService = appAPIService;
    }


    @Override
    public Observable<List<TreeData>> getGlobalTreeData() {
        return appAPIService.getTreeData(DataConstant.FBCLID);
    }
}
