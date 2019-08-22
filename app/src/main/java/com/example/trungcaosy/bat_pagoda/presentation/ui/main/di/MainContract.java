package com.example.trungcaosy.bat_pagoda.presentation.ui.main.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;
import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;

import java.util.List;

public interface MainContract {
    interface ViewContract extends BaseListContract.ViewContract{

        void onGetMapSuccess(List<MapDataResponse> response);
    }

    interface PresenterContract extends BaseContract.PresenterContract<ViewContract>{
        void getTreeData();

        void getMapData();
    }
}
