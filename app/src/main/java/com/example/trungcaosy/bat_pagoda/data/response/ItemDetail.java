package com.example.trungcaosy.bat_pagoda.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDetail {

    @SerializedName("_id")
    public String id;

    @SerializedName("address")
    public String address;

    @SerializedName("description")
    public String description;

    @SerializedName("name")
    public String name;

    @SerializedName("phone")
    public String phone;

    @SerializedName("website")
    public String website;

    @SerializedName("email")
    public String email;

    @SerializedName("img_urls")
    public List<String> imageUrls;

}
