package com.example.trungcaosy.bat_pagoda.network;

import com.example.trungcaosy.bat_pagoda.data.response.TreeData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AppAPIService {
    @GET("list")
    Observable<List<TreeData>> getTreeData(@Query("fbclid") String fbclid);
}
