package com.example.trungcaosy.bat_pagoda.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TreeData {
    @SerializedName("_id")
    public String id;

    @SerializedName("display_type")
    public String displayType;

    @SerializedName("type")
    public String type;

    @SerializedName("name")
    public String name;

    @SerializedName("items")
    public List<TreeData> childList;
}
