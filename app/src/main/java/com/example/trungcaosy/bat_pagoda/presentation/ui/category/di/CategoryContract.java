package com.example.trungcaosy.bat_pagoda.presentation.ui.category.di;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.mvp.BaseListContract;

public class CategoryContract {
    public interface ViewContract extends BaseListContract.ViewContract{

    }

    public interface PresenterContract extends BaseContract.PresenterContract<CategoryContract.ViewContract>{

    }
}
