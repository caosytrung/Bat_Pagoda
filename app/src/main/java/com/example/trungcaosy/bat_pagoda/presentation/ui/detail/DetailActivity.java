package com.example.trungcaosy.bat_pagoda.presentation.ui.detail;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.presentation.custom_view.CustomTextView;
import com.example.trungcaosy.bat_pagoda.presentation.custom_view.indicator.ViewPagerIndicator;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.Adapter.DetailImageAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.di.DetailPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.adapter.ImagePagerAdapter;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;
import com.example.trungcaosy.bat_pagoda.utils.Utils;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import static com.example.trungcaosy.bat_pagoda.presentation.ui.main.MainActivity.DELAY_TIME;

public class DetailActivity extends BaseActivity<DetailContract.ViewContract, DetailContract.PresenterContract>
        implements DetailContract.ViewContract{

    @BindView(R.id.clToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lnInforContainer)
    LinearLayout lnInforContainer;

    @BindView(R.id.tvName)
    TextView tvItemName;

    @BindView(R.id.expand_text_view)
    CustomTextView tvDescription;

    @BindView(R.id.view_pager_indicator)
    ViewPagerIndicator indicator;

    @BindView(R.id.vpMain)
    AutoScrollViewPager scrollViewPager;

    private NodeData nodeData;

    @Inject
    DetailPresenter detailPresenter;

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
        nodeData = (NodeData) getIntent().getSerializableExtra(DataConstant.NODE_DATA);
        setupToolbar();
        detailPresenter.getItemDetail(nodeData.id);
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
    public void showLoading(String message) {
        showNetDialog(message);
    }

    @Override
    public void hideLoading() {
        dismisNetDialog();
    }

    @Override
    public void showError(String title, String message) {
        showAlert(title, message);
    }

    @Override
    public void showNotice() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        detailPresenter.getItemDetail(nodeData.id);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        finish();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo").setMessage("Bạn có muốn thử lại?").setPositiveButton("Có", dialogClickListener)
                .setNegativeButton("không", dialogClickListener);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void updateData(ItemDetail response) {
        tvItemName.setText(response.name);
        tvDescription.setText(response.description);

        addlayoutItem(DataConstant.ADDRESS, response.address);
        addlayoutItem(DataConstant.PHONE, response.phone);
        addlayoutItem(DataConstant.EMAIL,response.email);
        addlayoutItem(DataConstant.WEBSITE,response.website);

        setupViewPager(response.imageUrls);
    }

    private void setupViewPager(List<String> imageList) {
        if (imageList == null || imageList.isEmpty()){
            imageList = new ArrayList<>();
            imageList.add("https://google.com");
        }

        scrollViewPager.setOffscreenPageLimit(24);
        scrollViewPager.setAdapter(new DetailImageAdapter(this, imageList).setInfiniteLoop(false));
        scrollViewPager.setInterval(DELAY_TIME);
        scrollViewPager.startAutoScroll();
        scrollViewPager.setCurrentItem(0);
        indicator.setupWithViewPager(scrollViewPager);
    }

    private void addlayoutItem(String key, String data) {
        if (Utils.isStringNullOrEmpty(data))
            return;

        StringBuilder sb = new StringBuilder(data);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        data =  sb.toString();

        if (key.equals(DataConstant.ADDRESS)){
            lnInforContainer.addView(createItemLayout(R.drawable.ic_location, data));
            return;
        }

        if (key.equals(DataConstant.PHONE)){
            lnInforContainer.addView(createItemLayout(R.drawable.ic_phone, data));
            return;
        }

        if (key.equals(DataConstant.EMAIL)){
            lnInforContainer.addView(createItemLayout(R.drawable.ic_mail, data));
            return;
        }


        if (key.equals(DataConstant.WEBSITE)){
            lnInforContainer.addView(createItemLayout(R.drawable.ic_web, data));
            return;
        }
    }

    private View createItemLayout(int iconID, String data) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_detail_information, null);
        ImageView ivIcon = view.findViewById(R.id.ivIcon);
        TextView tvDisplay = view.findViewById(R.id.tvName);

        tvDisplay.setText(data);
        ivIcon.setBackgroundResource(iconID);
        return view;
    }


}
