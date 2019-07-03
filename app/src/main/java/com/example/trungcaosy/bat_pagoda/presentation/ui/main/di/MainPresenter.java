package com.example.trungcaosy.bat_pagoda.presentation.ui.main.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;

import javax.inject.Inject;

public class MainPresenter extends BaseApiPresenter<MainContract.ViewContract> implements  MainContract.PresenterContract{

    @Inject
    public  MainPresenter(){

    }

    @Override
    public void test() {
        getView().showLoading("aa");
    }
}
