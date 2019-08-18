package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;

public class DetailContract {
    public interface ViewContract extends BaseListContract.ViewContract{

    }

    public interface PresenterContract extends BaseContract.PresenterContract<DetailContract.ViewContract>{

    }
}
