package com.example.trungcaosy.bat_pagoda.presentation.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.ViewContract, MainContract.PresenterContract>
        implements MainContract.ViewContract {
    @BindView(R.id.vpMain)
    ViewPager vpMain;

    @Inject
    MainPresenter mMainPresenter;;


    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.PresenterContract createPresenter() {
        return mMainPresenter;
    }


    @Override
    protected void onViewReady(Bundle bundle) {

    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String title, String message) {

    }
}
