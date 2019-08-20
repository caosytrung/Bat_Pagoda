package com.example.trungcaosy.bat_pagoda.presentation.callback_click;

import android.view.View;

public interface RecyclerViewClick<T> {
    void onClick(View v, T t);

    void onLongClick(View v, T t);
}
