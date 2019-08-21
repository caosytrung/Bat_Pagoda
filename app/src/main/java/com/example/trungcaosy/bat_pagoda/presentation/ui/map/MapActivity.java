package com.example.trungcaosy.bat_pagoda.presentation.ui.map;

import android.os.Bundle;
import android.widget.Toast;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.di.MapContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.di.MapPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.model.MapItem;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.model.MyItemReader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class MapActivity extends BaseActivity<MapContract.ViewContract, MapContract.PresenterContract>
        implements MapContract.ViewContract,OnMapReadyCallback {
    private GoogleMap mMap;
    private ClusterManager<MapItem> mClusterManager;

    @Inject
    MapPresenter mapPresenter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_map;
    }

    @Override
    public MapContract.PresenterContract createPresenter() {
        return mapPresenter;
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }


    @Override
    protected void onViewReady(Bundle bundle) {
        setUpMap();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mMap != null) {
            return;
        }
        mMap = googleMap;
        startDemo();
    }

    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<>(this, getMap());

        getMap().setOnCameraIdleListener(mClusterManager);
        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MapItem> items = new MyItemReader().read(inputStream);
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            for (MapItem item : items) {
                LatLng position = item.getPosition();
                double lat = position.latitude + offset;
                double lng = position.longitude + offset;
                MapItem offsetItem = new MapItem(lat, lng);
                mClusterManager.addItem(offsetItem);
            }
        }
    }

    protected GoogleMap getMap() {
        return mMap;
    }

}
