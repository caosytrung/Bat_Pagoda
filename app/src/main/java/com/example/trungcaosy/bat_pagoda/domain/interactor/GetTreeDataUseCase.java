package com.example.trungcaosy.bat_pagoda.domain.interactor;

import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.domain.repository.TreeDataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTreeDataUseCase {
    private TreeDataRepository treeDataRepository;

    @Inject
    public GetTreeDataUseCase(TreeDataRepository treeDataRepository) {
        this.treeDataRepository = treeDataRepository;
    }

    public Observable<List<NodeData>> getTreeData(){
        return  treeDataRepository.getGlobalTreeData();
    }
}
