package com.example.trungcaosy.bat_pagoda.domain.repository;

import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;


public interface ItemDetailRepository {
    io.reactivex.Observable<ItemDetail> getItemDetail(String id);
}
