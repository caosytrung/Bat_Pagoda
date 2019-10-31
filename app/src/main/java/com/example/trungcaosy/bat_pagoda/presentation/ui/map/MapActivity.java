package com.example.trungcaosy.bat_pagoda.presentation.ui.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.base.BaseActivity;
import com.example.trungcaosy.bat_pagoda.data.response.ItemDetail;
import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.DetailActivity;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.di.MainContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.di.MapContract;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.di.MapPresenter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.model.MapItem;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.model.MultiDrawable;
import com.example.trungcaosy.bat_pagoda.presentation.ui.map.model.MyItemReader;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.OnClick;

public class MapActivity extends BaseActivity<MapContract.ViewContract, MapContract.PresenterContract>
        implements MapContract.ViewContract,OnMapReadyCallback, ClusterManager.OnClusterItemClickListener<MapItem>, ClusterManager.OnClusterClickListener<MapItem>, ClusterManager.OnClusterInfoWindowClickListener<MapItem>, ClusterManager.OnClusterItemInfoWindowClickListener<MapItem> {
    public static final String MAP_TYPE = "map_types";
    public static final String TYPE_ALL = "map_all";
    public static final String TYPE_SINGLE = "map_single";
    public static final String MAP_ITEM = "mapItem";

    private GoogleMap mMap;
    private ClusterManager<MapItem> mClusterManager;
    private int[] markerArray = new int[]{R.drawable.marker_1,R.drawable.marker_2,R.drawable.marker_3};
    private MapItem clickedClusterItem;
    List<MapDataResponse> dataResponses;

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

    @OnClick(R.id.ivBack)
    public void back(){
        finish();
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
        Intent intent = getIntent();
        if (intent == null)
            return;
        String mapType = getIntent().getStringExtra(MAP_TYPE);
        if (mapType.equals(TYPE_SINGLE)){
            ItemDetail itemDetail = (ItemDetail) intent.getSerializableExtra(MAP_ITEM);
            LatLng sydney = new LatLng(itemDetail.lat, itemDetail.lng);
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .title(itemDetail.name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            return;
        }


        dataResponses = (List<MapDataResponse>) intent.getSerializableExtra("ListMap");
      // getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<>(this, getMap());
        mClusterManager.setRenderer(new MapRenderer());

        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);


        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);


        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
        mMap.setInfoWindowAdapter(new MyCustomAdapterForItems());
    }

    private void readItems() throws JSONException {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dataResponses.get(0).lat, dataResponses.get(0).lng), 10));
        List<MapItem> items = new MyItemReader().read(dataResponses);
        for (MapItem item : items) {
            mClusterManager.addItem(item);
        }
    }

    protected GoogleMap getMap() {
        return mMap;
    }

    @Override
    public boolean onClusterItemClick(MapItem mapItem) {
        clickedClusterItem = mapItem;
        return false;
    }

    @Override
    public boolean onClusterClick(Cluster<MapItem> cluster) {
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<MapItem> cluster) {

    }

    @Override
    public void onClusterItemInfoWindowClick(MapItem mapItem) {
        NodeData nodeData = new NodeData();
        nodeData.id = mapItem.getId();
        nodeData.name = mapItem.getTitle();
        nodeData.type = "item";


        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DataConstant.NODE_DATA, nodeData);
        // intent.putExtra(DataConstant.TOOLBAR_TITLE, nodeData.ge)
        startActivity(new Intent(intent));

    }

    /**
     * Draws profile photos inside markers (using IconGenerator).
     * When there are multiple people in the cluster, draw multiple photos (using MultiDrawable).
     */
        private class MapRenderer extends DefaultClusterRenderer<MapItem> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public MapRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(MapItem person, MarkerOptions markerOptions) {
            Random rand = new Random();
            int n = rand.nextInt(3);
            BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(30.0f);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(markerArray[n]));
        }
    }

    public class MyCustomAdapterForItems implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyCustomAdapterForItems() {
            myContentsView = getLayoutInflater().inflate(
                    R.layout.map_infor, null);
        }
        @Override
        public View getInfoWindow(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView
                    .findViewById(R.id.txtTitle));
            TextView tvSnippet = ((TextView) myContentsView
                    .findViewById(R.id.txtSnippet));
            if (clickedClusterItem != null){
                tvTitle.setText(clickedClusterItem.getTitle());
                tvSnippet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        moveToDetailActivity(clickedClusterItem);
                    }
                });
            }
            return myContentsView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    private void moveToDetailActivity(MapItem clickedClusterItem) {
    }

}
