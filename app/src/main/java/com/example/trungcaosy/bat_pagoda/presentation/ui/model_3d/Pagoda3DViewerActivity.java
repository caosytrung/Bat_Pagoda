package com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.di.Pagoda3DContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.di.Pagoda3DPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.model.ExampleSceneLoader;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.model.ModelSurfaceView;
import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.model.SceneLoader;

import org.andresoviedo.util.android.ContentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class Pagoda3DViewerActivity extends BaseActivity<Pagoda3DContract.ViewContract,
        Pagoda3DContract.PresenterContract> implements Pagoda3DContract.ViewContract {

    private static final int REQUEST_CODE_LOAD_TEXTURE = 1000;
    private static final int FULLSCREEN_DELAY = 10000;
    private boolean immersiveMode = true;
    private static final String MODEL_FILE_NAME = "/ChuaDoi_Mesh.obj";


    @BindView(R.id.modelView)
    public ModelSurfaceView gLView;

    private SceneLoader scene;
    private Handler handler;
    private float[] backgroundColor = new float[]{0f, 0f, 0f, 1.0f};
    private int paramType;
    private Uri paramUri;

    @Inject
    Pagoda3DPresenter pagoda3DPresenter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_pagoda_3d_viewer;
    }

    @Override
    public Pagoda3DContract.PresenterContract createPresenter() {
        return pagoda3DPresenter;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        ContentUtils.provideAssets(this);
        this.immersiveMode = true;
        this.paramUri = Uri.parse("assets://" + getPackageName() + MODEL_FILE_NAME);

        handler = new Handler(getMainLooper());

        // Create our 3D sceneario
        if (paramUri == null) {
            scene = new ExampleSceneLoader(this);
        } else {
            scene = new SceneLoader(this);
        }
        scene.init();
        scene.setDoAnimation(false);
        scene.setDrawLighting(false);
        scene.setRotatingLight(false);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        try {
            gLView.init(this);
//            gLView = new ModelSurfaceView(this);
//            setContentView(gLView);
        } catch (Exception e) {
            Toast.makeText(this, "Error loading OpenGL view:\n" +e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Show the Up button in the action bar.
        setupActionBar();

        // TODO: Alert user when there is no multitouch support (2 fingers). He won't be able to rotate or zoom
        ContentUtils.printTouchCapabilities(getPackageManager());

        setupOnSystemVisibilityChangeListener();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        // }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupOnSystemVisibilityChangeListener() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            // Note that system bars will only be "visible" if none of the
            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                // The system bars are visible. Make any desired
                hideSystemUIDelayed();
            }
        });
    }
    private void hideSystemUIDelayed() {
        if (!this.immersiveMode) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(this::hideSystemUI, FULLSCREEN_DELAY);

    }

    private void hideSystemUI() {
        if (!this.immersiveMode) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideSystemUIKitKat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            hideSystemUIJellyBean();
        }
    }


    // This snippet hides the system bars.
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideSystemUIKitKat() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void hideSystemUIJellyBean() {
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LOW_PROFILE);
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

    public SceneLoader getScene() {
        return scene;
    }

    public Uri getParamUri() {
        return paramUri;
    }

    public int getParamType() {
        return paramType;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public ModelSurfaceView getGLView() {
        return gLView;
    }

    @OnClick(R.id.iv_zoom_in)
    public void zoomIn(){
        if (scene!= null)
            scene.zoomIn();
    }

    @OnClick(R.id.iv_zoom_out)
    public void zoomOut(){
        if (scene!= null)
            scene.zoomOut();
    }
}
