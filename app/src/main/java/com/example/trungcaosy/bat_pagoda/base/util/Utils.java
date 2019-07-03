package com.example.trungcaosy.bat_pagoda.base.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Utils {
    public static void recyclerViewVerticalOri(RecyclerView recyclerView, Context context){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }
}
