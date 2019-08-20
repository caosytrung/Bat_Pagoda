package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;
import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.domain.fetcher.ResultListener;
import com.example.trungcaosy.bat_pagoda.domain.interactor.GetItemDetailUseCase;

import javax.inject.Inject;

public class DetailPresenter extends BaseApiPresenter<DetailContract.ViewContract> implements DetailContract.PresenterContract{

    private GetItemDetailUseCase getItemDetailUseCase;

    @Inject
    public DetailPresenter(GetItemDetailUseCase getItemDetailUseCase){
        this.getItemDetailUseCase = getItemDetailUseCase;
    }

    @Override
    public void getItemDetail(String id) {
        fetch(getItemDetailUseCase.getItemDetail(id), new ResultListener<ItemDetail>() {
            @Override
            public void onRequestStart() {
                getView().showLoading("Đang tải");
            }

            @Override
            public void onRequestSuccess(ItemDetail response) {
                if (!isViewAttached())
                    return;
                getView().hideLoading();
                getView().updateData(response);
            }

            @Override
            public void onRequestError(Throwable throwable) {
                if (!isViewAttached())
                    return;

                getView().hideLoading();
                getView().showNotice();
            }
        });
    }
}
