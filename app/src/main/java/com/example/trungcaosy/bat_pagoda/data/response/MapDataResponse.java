package com.example.trungcaosy.bat_pagoda.data.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MapDataResponse implements Serializable{
    @SerializedName("_id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("lat")
    public double lat;

    @SerializedName("lng")
    public double lng;

    @SerializedName("address")
    public String address;
}
