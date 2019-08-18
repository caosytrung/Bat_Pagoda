package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;

import javax.inject.Inject;

public class DetailPresenter extends BaseApiPresenter<DetailContract.ViewContract>
        implements DetailContract.PresenterContract {
    @Inject
    public  DetailPresenter(){

    }
}
