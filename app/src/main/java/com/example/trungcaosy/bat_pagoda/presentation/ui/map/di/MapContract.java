package com.example.trungcaosy.bat_pagoda.presentation.ui.map.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;

public interface MapContract  {
    interface ViewContract extends BaseListContract.ViewContract{

    }

    interface PresenterContract extends BaseContract.PresenterContract<ViewContract>{

    }
}