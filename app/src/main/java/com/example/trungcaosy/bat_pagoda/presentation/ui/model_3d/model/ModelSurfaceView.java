package com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.model;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.trungcaosy.bat_pagoda.presentation.ui.model_3d.Pagoda3DViewerActivity;


import java.io.IOException;

/**
 * This is the actual opengl view. From here we can detect touch gestures for example
 *
 * @author andresoviedo
 */
public class ModelSurfaceView extends GLSurfaceView {

    private Pagoda3DViewerActivity parent;
    private ModelRenderer mRenderer;
    private TouchController touchHandler;

    public ModelSurfaceView(Context context) {
        super(context);
    }

    public ModelSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    public ModelSurfaceView(Pagoda3DViewerActivity parent) throws IllegalAccessException, IOException {
//        super(parent);
//
//        // parent component
//    }

    public void init(Pagoda3DViewerActivity activity) {
        this.parent = activity;

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // This is the actual renderer of the 3D space
        try {
            mRenderer = new ModelRenderer(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        // TODO: enable this?
        // setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        touchHandler = new TouchController(this, mRenderer);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchHandler.onTouchEvent(event);
    }

    public Pagoda3DViewerActivity getModelActivity() {
        return parent;
    }

    public ModelRenderer getModelRenderer() {
        return mRenderer;
    }

}