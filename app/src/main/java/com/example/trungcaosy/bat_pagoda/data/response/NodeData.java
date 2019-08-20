package com.example.trungcaosy.bat_pagoda.data.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NodeData implements Serializable {
    @SerializedName("_id")
    public String id;

    @SerializedName("display_type")
    public String displayType;

    @SerializedName("type")
    public String type;

    @SerializedName("name")
    public String name;

    @SerializedName("items")
    public List<NodeData> childList;

    @SerializedName("img_url")
    public String imageUrl;
}
