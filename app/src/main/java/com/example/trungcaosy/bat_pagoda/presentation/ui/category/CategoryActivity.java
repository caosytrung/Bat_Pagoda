package com.example.trungcaosy.bat_pagoda.presentation.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.presentation.callback_click.RecyclerViewClick;
import com.example.trungcaosy.bat_pagoda.presentation.ui.category.adapter.ListItemAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.category.di.CategoryContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.category.di.CategoryPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.DetailActivity;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryActivity  extends BaseActivity<CategoryContract.ViewContract, CategoryContract.PresenterContract>
        implements CategoryContract.ViewContract, RecyclerViewClick<NodeData> {
    private NodeData nodeData;
    private String title;

    @Inject
    CategoryPresenter categoryPresenter;

    @BindView(R.id.clToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvListItem)
    RecyclerView rvListItem;

    private ListItemAdapter adapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_category;
    }

    @Override
    public CategoryContract.PresenterContract createPresenter() {
        return categoryPresenter;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        nodeData = (NodeData) getIntent().getSerializableExtra(DataConstant.NODE_DATA);
        title = getIntent().getStringExtra(DataConstant.TOOLBAR_TITLE);
        setupToolbar();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new ListItemAdapter(this, nodeData.childList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListItem.setLayoutManager(linearLayoutManager);
        rvListItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupToolbar() {
        collapsingToolbarLayout.setTitle(nodeData.name);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View v)->{
            finish();
        });

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void onClick(View v, NodeData nodeData) {
        if (DataConstant.TYPE_ITEM.equals(nodeData.type)){
            moveToDetailActivity(nodeData);
        } else if (DataConstant.TYPE_CATEGORY.equals(nodeData.type)){
            moveToMenuActivity(nodeData);
        }
    }

    private void moveToDetailActivity(NodeData nodeData) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DataConstant.NODE_DATA, nodeData);
        // intent.putExtra(DataConstant.TOOLBAR_TITLE, nodeData.ge)
        startActivity(new Intent(intent));
    }

    private void moveToMenuActivity(NodeData nodeData) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(DataConstant.NODE_DATA, nodeData);
        startActivity(new Intent(intent));
    }

    @Override
    public void onLongClick(View v, NodeData nodeData) {

    }
}
