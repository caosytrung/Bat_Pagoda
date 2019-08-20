package com.example.trungcaosy.bat_pagoda.domain.interactor;

import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.domain.repository.ItemDetailRepository;
import com.example.trungcaosy.bat_pagoda.domain.repository.TreeDataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetItemDetailUseCase {

    private ItemDetailRepository itemDetailRepository;

    @Inject
    public GetItemDetailUseCase(ItemDetailRepository itemDetailRepository) {
        this.itemDetailRepository = itemDetailRepository;
    }

    public Observable<ItemDetail> getItemDetail(String id){
        return  itemDetailRepository.getItemDetail(id);
    }
}
