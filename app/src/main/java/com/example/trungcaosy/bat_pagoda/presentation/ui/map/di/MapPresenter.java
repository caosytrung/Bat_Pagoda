package com.example.trungcaosy.bat_pagoda.presentation.ui.map.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseApiPresenter;
import com.example.trungcaosy.bat_pagoda.domain.interactor.GetTreeDataUseCase;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;

import javax.inject.Inject;

public class MapPresenter   extends BaseApiPresenter<MapContract.ViewContract> implements  MapContract.PresenterContract {
    @Inject
    public  MapPresenter(){
    }
}
