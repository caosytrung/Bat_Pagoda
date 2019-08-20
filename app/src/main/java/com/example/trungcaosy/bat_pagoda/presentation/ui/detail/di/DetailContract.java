package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;
import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di.SplashContract;

public interface DetailContract {
    interface ViewContract extends BaseListContract.ViewContract{
        void showNotice();

        void updateData(ItemDetail response);
    }

    interface PresenterContract extends BaseContract.PresenterContract<DetailContract.ViewContract>{
        void getItemDetail(String id);
    }
}
