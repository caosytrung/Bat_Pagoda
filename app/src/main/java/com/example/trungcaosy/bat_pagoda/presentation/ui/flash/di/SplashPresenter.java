package com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di;

import com.example.trungcaosy.bat_pagoda.application.MyApplication;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.ResultListener;
import com.example.trungcaosy.bat_pagoda.domain.interactor.GetTreeDataUseCase;

import java.util.List;

import javax.inject.Inject;

public class SplashPresenter extends BaseApiPresenter<SplashContract.ViewContract> implements SplashContract.PresenterContract {

    private GetTreeDataUseCase getTreeDataUseCase;

    @Inject
    public  SplashPresenter(GetTreeDataUseCase getTreeDataUseCase){
        this.getTreeDataUseCase = getTreeDataUseCase;
    }

    @Override
    public void getTreeData() {
        fetch(getTreeDataUseCase.getTreeData(), new ResultListener<List<NodeData>>() {
            @Override
            public void onRequestStart() {
            }

            @Override
            public void onRequestSuccess(List<NodeData> treeData) {
                if(!isViewAttached())
                    return;
                getView().hideLoading();
                if (treeData != null && !treeData.isEmpty()){
                    MyApplication myApplication = MyApplication.getInstance();
                    myApplication.setOpened(true);
                    myApplication.setTreeDataList(treeData);
                    getView().onGetTreeDataSuccessfully();
                } else {
                    getView().showNotice();
                }

            }

            @Override
            public void onRequestError(Throwable throwable) {
                if(!isViewAttached())
                    return;
                getView().hideLoading();
                getView().showNotice();
            }
        });
    }
}
