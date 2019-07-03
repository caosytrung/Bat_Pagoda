package com.example.trungcaosy.bat_pagoda.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.viewmodel.BaseViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends BaseContract.ViewContract, P extends  BaseContract.PresenterContract>
        extends AppCompatActivity
        implements  BaseContract.ViewContract  {

    protected  P mPresenter;
    private Unbinder mUnbinder;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new ProgressDialog(this);
        mDialog.setCanceledOnTouchOutside(false);
        AndroidInjection.inject(this);

        if(getResourceLayout() != 0){
            setContentView(getResourceLayout());
            mUnbinder = ButterKnife.bind(this);
        }

        BaseViewModel<V,P> baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        boolean isPresenterCreated  = false;

        if( baseViewModel.getPresenter() == null){
            baseViewModel.setPresenter(createPresenter());
            isPresenterCreated = true;
        }

        mPresenter = baseViewModel.getPresenter();
        mPresenter.attachLifeCycle(getLifecycle());
        mPresenter.attachView((V) this);

        if(isPresenterCreated){
            mPresenter.onPresenterCreated();
        }

        onViewReady(savedInstanceState);


    }

    public void showNetDialog(String mesage){
        if(mDialog != null && !mDialog.isShowing()){
            mDialog.setMessage(mesage);
            mDialog.show();
        }
    }

    public void dismisNetDialog(){
        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public void showAlert(String title,String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setTitle(title);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @LayoutRes
    protected abstract int getResourceLayout();

    public abstract P createPresenter();

    protected  abstract  void onViewReady(Bundle bundle);
}
