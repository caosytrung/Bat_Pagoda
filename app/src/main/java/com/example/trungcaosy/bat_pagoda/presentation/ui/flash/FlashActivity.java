package com.example.trungcaosy.bat_pagoda.presentation.ui.flash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.application.MyApplication;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.MainActivity;

public class FlashActivity extends Activity {
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        MyApplication myApplication = MyApplication.getInstance();
        if (myApplication.isOpened()){
            moveToMain();
            return;
        }
        myApplication.setOpened(true);
        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                   moveToMain();
                } catch (Exception e){
                }
            }
        }, 3000);
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
}
