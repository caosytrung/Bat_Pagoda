package com.example.trungcaosy.bat_pagoda.presentation.ui.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.application.MyApplication;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.presentation.custom_view.indicator.ViewPagerIndicator;
import com.example.trungcaosy.bat_pagoda.presentation.ui.category.CategoryActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.DetailActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.adapter.ImagePagerAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.MapActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.Pagoda3DViewerActivity;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends BaseActivity<MainContract.ViewContract, MainContract.PresenterContract>
        implements MainContract.ViewContract, View.OnClickListener {
    public static int DELAY_TIME = 3000;
    @BindView(R.id.vpMain)
    AutoScrollViewPager vpMain;

    @BindView(R.id.view_pager_indicator)
    ViewPagerIndicator indicator;

    @BindView(R.id.lnContainer)
    LinearLayout lnContainer;

    @Inject
    MainPresenter mMainPresenter;
    ;


    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.PresenterContract createPresenter() {
        return mMainPresenter;
    }


    @Override
    protected void onViewReady(Bundle bundle) {
        setupViewPager();
        setupButton();
    }

    private void setupButton() {
        MyApplication myApplication = MyApplication.getInstance();
        if (myApplication == null) return;

        List<NodeData> treeDataList = myApplication.getTreeDataList();

        for (NodeData treeData : treeDataList) {
            addButton(treeData);
        }
    }

    private void addButton(NodeData treeData) {
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int dimen12Dp = getResources().getDimensionPixelOffset(R.dimen.dm_12);
        int dimen4Dp = getResources().getDimensionPixelOffset(R.dimen.dm_4);
        int dimen6Dp = getResources().getDimensionPixelOffset(R.dimen.dm_6);
        layoutParam.setMargins(dimen12Dp, dimen4Dp, dimen12Dp, dimen4Dp);

        Button btn = new Button(this);
        btn.setTag(treeData);
        btn.setLayoutParams(layoutParam);
        btn.setText(treeData.name);
        btn.setLines(1);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_18));
        btn.setAllCaps(true);
        btn.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        btn.setBackgroundResource(R.drawable.bg_main_btn_1);
        btn.setPadding(dimen6Dp, dimen6Dp, dimen6Dp, dimen6Dp);
        btn.setTextColor(getResources().getColor(R.color.btn_selector));
        btn.setOnClickListener(this);
        lnContainer.addView(btn);
    }

    private void setupViewPager() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.img_vp_1);
        imageList.add(R.drawable.img_vp_2);
        imageList.add(R.drawable.img_vp_3);
        imageList.add(R.drawable.img_vp_4);
        vpMain.setAdapter(new ImagePagerAdapter(this, imageList).setInfiniteLoop(false));
        vpMain.setInterval(DELAY_TIME);
        vpMain.startAutoScroll();
        vpMain.setCurrentItem(0);
        indicator.setupWithViewPager(vpMain);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vpMain.startAutoScroll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vpMain.stopAutoScroll();
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

    @OnClick(R.id.iv3dViewer)
    public void open3DViewer() {
        startActivity(new Intent(this, Pagoda3DViewerActivity.class));
    }

    @OnClick(R.id.ivMaps)
    public void openMaps() {
        startActivity(new Intent(this, MapActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button){
            NodeData nodeData = (NodeData)view.getTag();
            if (DataConstant.TYPE_ITEM.equals(nodeData.type)){
                moveToDetailActivity(nodeData);
            } else if (DataConstant.TYPE_CATEGORY.equals(nodeData.type)){
                moveToMenuActivity(nodeData);
            }
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
}
