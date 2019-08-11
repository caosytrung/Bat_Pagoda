package com.example.trungcaosy.bat_pagoda.presentation.ui.detail;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.adapter.ListItemAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.Provides;

public class DetailActivity extends BaseActivity<DetailContract.ViewContract, DetailContract.PresenterContract> {
    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.clToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvListItem)
    RecyclerView rvListItem;

    private ListItemAdapter adapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public DetailContract.PresenterContract createPresenter() {
        return detailPresenter;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        collapsingToolbarLayout.setTitle(getString(R.string.nha_hang_quan_));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View v)->{
            finish();
        });

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        adapter = new ListItemAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListItem.setLayoutManager(linearLayoutManager);
        rvListItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }
}

