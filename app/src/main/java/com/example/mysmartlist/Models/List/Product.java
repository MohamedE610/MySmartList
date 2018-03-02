
package com.example.mysmartlist.Models.List;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public Float price;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("favs")
    @Expose
    public Integer favs;
    private final static long serialVersionUID = 5795827173021708936L;

}
