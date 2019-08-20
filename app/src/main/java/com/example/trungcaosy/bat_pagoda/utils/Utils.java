package com.example.trungcaosy.bat_pagoda.utils;


public class Utils {
    public static boolean isStringNullOrEmpty(String str){
        return  str == null || str.isEmpty();
    }

    public static boolean isNotStringEmpty(String str){
        return str != null && str.length() > 0;
    }

}
