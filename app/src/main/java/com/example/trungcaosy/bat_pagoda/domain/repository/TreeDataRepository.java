package com.example.trungcaosy.bat_pagoda.domain.repository;

import com.example.trungcaosy.bat_pagoda.data.response.TreeData;

import java.util.List;

import io.reactivex.Observable;

public interface TreeDataRepository {
    Observable<List<TreeData>> getGlobalTreeData();
}
