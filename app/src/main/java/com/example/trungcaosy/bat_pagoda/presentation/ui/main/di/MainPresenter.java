package com.example.trungcaosy.bat_pagoda.presentation.ui.main.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;
import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.ResultListener;
import com.example.trungcaosy.bat_pagoda.domain.interactor.GetTreeDataUseCase;
import com.example.trungcaosy.bat_pagoda.domain.interactor.MapUseCase;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BaseApiPresenter<MainContract.ViewContract> implements  MainContract.PresenterContract{

    private GetTreeDataUseCase getTreeDataUseCase;
    private MapUseCase mapUseCase;

    @Inject
    public  MainPresenter(GetTreeDataUseCase getTreeDataUseCase, MapUseCase mapUseCase){
        this.getTreeDataUseCase = getTreeDataUseCase;
        this.mapUseCase = mapUseCase;
    }

    @Override
    public void getTreeData() {
        fetch(getTreeDataUseCase.getTreeData(), new ResultListener<List<NodeData>>() {
            @Override
            public void onRequestStart() {
                if (isViewAttached())
                    getView().showLoading(DataConstant.LOADING);
            }

            @Override
            public void onRequestSuccess(List<NodeData> response) {
                if(!isViewAttached())
                    return;
            }

            @Override
            public void onRequestError(Throwable throwable) {
                if(!isViewAttached())
                    return;

                getView().hideLoading();
                getView().showError("Thông báo", "Không thể kết nối dữ liệu");
            }
        });
    }

    @Override
    public void getMapData() {
        fetch(mapUseCase.getMaps(), new ResultListener<List<MapDataResponse>>() {
            @Override
            public void onRequestStart() {
                if (isViewAttached())
                    getView().showLoading(DataConstant.LOADING);
            }

            @Override
            public void onRequestSuccess(List<MapDataResponse> response) {
                if(!isViewAttached())
                    return;
                getView().hideLoading();
                getView().onGetMapSuccess(response);
            }

            @Override
            public void onRequestError(Throwable throwable) {
                if(!isViewAttached())
                    return;

                getView().hideLoading();
                getView().showError("Thông báo", "Không thể kết nối dữ liệu");
            }
        });
    }
}
