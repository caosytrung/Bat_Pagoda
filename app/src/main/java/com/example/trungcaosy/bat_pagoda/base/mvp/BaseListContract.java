package com.example.trungcaosy.bat_pagoda.base.mvp;

public interface BaseListContract {
    interface ViewContract extends BaseContract.ViewContract {
        void showLoading(String message);

        void hideLoading();

        void showError(String title, String message);

    }

    interface PresenterContract extends BaseContract.PresenterContract<ViewContract>{

    }


}
