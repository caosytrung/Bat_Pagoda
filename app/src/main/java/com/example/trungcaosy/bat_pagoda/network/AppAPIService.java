package com.example.trungcaosy.bat_pagoda.network;

import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppAPIService {
    @GET("list")
    Observable<List<NodeData>> getTreeData(@Query("fbclid") String fbclid);

    @GET("details/{id}")
    Observable<ItemDetail> getItemDetail(@Path("id") String id);
}
