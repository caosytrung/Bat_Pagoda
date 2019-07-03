package com.example.trungcaosy.bat_pagoda.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trungcaosy.bat_pagoda.base.mvp.BaseContract;
import com.example.trungcaosy.bat_pagoda.base.viewmodel.BaseViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends  BaseContract.ViewContract, P extends  BaseContract.PresenterContract<V>>
        extends dagger.android.support.DaggerFragment
        implements BaseContract.ViewContract {

    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    private  P presenter;

    private Unbinder mUnbinder;

    protected ViewGroup mRootview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            handleArguments(getArguments());
        }

        if (getActivity() != null && getActivity().getIntent() != null && getActivity().getIntent().getExtras() != null) {
            handleExtras(getActivity().getIntent().getExtras());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getResourceLayout() != 0 ){
            mRootview = (ViewGroup) inflater.inflate(getResourceLayout(),container,false);
            mUnbinder = ButterKnife.bind(this,mRootview);
        }
        return mRootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseViewModel<V,P> baseViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        boolean isPresenterCreated = false;

        if(baseViewModel.getPresenter() == null){
            baseViewModel.setPresenter(initPresenter());

            isPresenterCreated = true;
        }

        presenter = baseViewModel.getPresenter();
        presenter.attachView((V)this);
        presenter.attachLifeCycle(getLifecycle());

        if(isPresenterCreated){
            presenter.onPresenterCreated();
        }

        onViewReady(savedInstanceState);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachLifeCycle(getLifecycle());
        presenter.detachView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRootview = null;
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    protected abstract void onViewReady(Bundle savedInstanceState);

    protected abstract void handleExtras(Bundle extras);

    @LayoutRes
    protected abstract int getResourceLayout();

    protected void handleArguments(Bundle arguments) {}

    protected abstract P initPresenter();

//    public MainActivity getMainActivity(){
//        return getActivity() instanceof MainActivity ? (MainActivity) getActivity() : null;
//    }
}
