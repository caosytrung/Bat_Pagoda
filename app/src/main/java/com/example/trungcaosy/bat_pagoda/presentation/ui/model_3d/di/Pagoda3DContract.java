package com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;

public interface Pagoda3DContract {
    interface ViewContract extends BaseListContract.ViewContract{

    }

    interface PresenterContract extends BaseContract.PresenterContract<ViewContract>{

    }
}
