package com.example.trungcaosy.bat_pagoda.presentation.ui.flash;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.application.MyApplication;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di.SplashContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di.SplashModule;
import com.example.trungcaosy.bat_pagoda.presentation.ui.flash.di.SplashPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashContract.ViewContract, SplashContract.PresenterContract>
        implements SplashContract.ViewContract {
    private Handler mWaitHandler = new Handler();

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_flash;
    }

    @Override
    public SplashContract.PresenterContract createPresenter() {
        return splashPresenter;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        MyApplication myApplication = MyApplication.getInstance();
        if (myApplication.isOpened()){
            moveToMain();
            return;
        }

        splashPresenter.getTreeData();
    }

    private void moveToMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWaitHandler != null)
            mWaitHandler.removeCallbacksAndMessages(null);
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
    public void onGetTreeDataSuccessfully() {
        Toast.makeText(this,"Tải dữ liệu thành công",Toast.LENGTH_SHORT).show();

        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                   moveToMain();
                } catch (Exception e){
                }
            }
        }, 1000);

    }

    @Override
    public void showNotice() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        splashPresenter.getTreeData();
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
}
