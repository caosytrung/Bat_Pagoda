package com.example.trungcaosy.bat_pagoda.presentation.ui.main.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;
import com.example.trungcaosy.bat_pagoda.data.response.TreeData;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.ResultListener;
import com.example.trungcaosy.bat_pagoda.domain.interactor.GetTreeDataUseCase;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BaseApiPresenter<MainContract.ViewContract> implements  MainContract.PresenterContract{

    private GetTreeDataUseCase getTreeDataUseCase;

    @Inject
    public  MainPresenter(GetTreeDataUseCase getTreeDataUseCase){
        this.getTreeDataUseCase = getTreeDataUseCase;
    }

    @Override
    public void getTreeData() {
        fetch(getTreeDataUseCase.getTreeData(), new ResultListener<List<TreeData>>() {
            @Override
            public void onRequestStart() {
                if (isViewAttached())
                    getView().showLoading(DataConstant.LOADING);
            }

            @Override
            public void onRequestSuccess(List<TreeData> response) {
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
}
