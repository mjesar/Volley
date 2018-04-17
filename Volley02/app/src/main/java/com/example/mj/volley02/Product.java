package com.example.mj.volley02;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MJ on 5/26/2017.
 */

public class Product {

    @SerializedName("pid")
    public int pid;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public double price;
    @SerializedName("image_url")
    public String image_url;
}
