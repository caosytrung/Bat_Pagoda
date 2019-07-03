package com.example.trungcaosy.bat_pagoda.base.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;


public class BaseViewModel<V extends BaseContract.ViewContract, P extends  BaseContract.PresenterContract> extends ViewModel {
    private P mPresenter;

    public void setPresenter(P presenter){
        if(mPresenter == null && presenter != null){
            mPresenter = presenter;
        }
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mPresenter.onPresenterDestroyed();
        mPresenter = null;
    }
}
