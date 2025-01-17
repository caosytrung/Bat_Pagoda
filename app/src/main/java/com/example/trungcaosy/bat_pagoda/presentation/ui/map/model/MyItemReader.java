package com.example.trungcaosy.bat_pagoda.presentation.ui.map.model;

import com.example.trungcaosy.bat_pagoda.data.response.MapDataResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyItemReader {

    /*
     * This matches only once in whole input,
     * so Scanner.next returns whole InputStream as a String.
     * http://stackoverflow.com/a/5445161/2183804
     */
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<MapItem> read(InputStream inputStream) throws JSONException {
        List<MapItem> items = new ArrayList<MapItem>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            String title = null;
            String snippet = null;
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            if (!object.isNull("title")) {
                title = object.getString("title");
            }
            if (!object.isNull("snippet")) {
                snippet = object.getString("snippet");
            }
            items.add(new MapItem(lat, lng, title, snippet));
        }
        return items;
    }

    public List<MapItem> read(List<MapDataResponse> dataResponses) {
        List<MapItem> items = new ArrayList<>();
        for (MapDataResponse mapDataResponse : dataResponses){
            items.add(MapItem.createMapItem(mapDataResponse));
        }

        return items;
    }
}
