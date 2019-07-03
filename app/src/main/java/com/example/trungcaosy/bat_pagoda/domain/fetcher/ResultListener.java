package com.example.trungcaosy.bat_pagoda.domain.fetcher;

public interface ResultListener<T> {
    void onRequestStart();

    void onRequestSuccess(T response);

    void onRequestError(Throwable throwable);
}
