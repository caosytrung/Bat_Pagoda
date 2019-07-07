package com.example.trungcaosy.bat_pagoda.presentation.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.custom_view.indicator.ViewPagerIndicator;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.adapter.ImagePagerAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends BaseActivity<MainContract.ViewContract, MainContract.PresenterContract>
        implements MainContract.ViewContract {
    @BindView(R.id.vpMain)
    AutoScrollViewPager vpMain;

    @BindView(R.id.view_pager_indicator)
    ViewPagerIndicator indicator;

    @Inject
    MainPresenter mMainPresenter;;


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
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.img_vp_1);
        imageList.add(R.drawable.img_vp_2);
        imageList.add(R.drawable.img_vp_3);
        imageList.add(R.drawable.img_vp_4);

        vpMain.setAdapter(new ImagePagerAdapter(this, imageList).setInfiniteLoop(false));
      //  vpMain.setOnPageChangeListener(new MyOnPageChangeListener());

        vpMain.setInterval(3000);
        vpMain.startAutoScroll();
        vpMain.setCurrentItem(0);

        indicator.setupWithViewPager(vpMain);
        //  viewPagerIndicator.addOnPageChangeListener(mOnPageChangeListener);
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

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
