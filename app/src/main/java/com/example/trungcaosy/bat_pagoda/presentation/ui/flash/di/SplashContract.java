package com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;

public interface SplashContract {
    interface ViewContract extends BaseListContract.ViewContract{

        void onGetTreeDataSuccessfully();

        void showNotice();
    }

    interface PresenterContract extends BaseContract.PresenterContract<SplashContract.ViewContract>{
        void getTreeData();
    }
}
