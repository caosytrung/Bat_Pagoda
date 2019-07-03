package com.example.trungcaosy.bat_pagoda.base.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


public class FragmentUtils  {
    public static void replaceFragment(FragmentActivity activity, Fragment fragment, Bundle bundle){
        if (activity == null) {
            return;
        }

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        //fragmentTransaction.replace(R.id.container, fragment, null);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commitAllowingStateLoss();

    }
}
