package com.example.trungcaosy.bat_pagoda.presentation.ui.category.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;

import javax.inject.Inject;

public class CategoryPresenter extends BaseApiPresenter<CategoryContract.ViewContract>
        implements CategoryContract.PresenterContract {
    @Inject
    public CategoryPresenter(){

    }
}
