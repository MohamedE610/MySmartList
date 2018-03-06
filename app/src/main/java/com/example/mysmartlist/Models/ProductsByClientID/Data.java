
package com.example.mysmartlist.Models.ProductsByClientID;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
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
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("favs")
    @Expose
    public Integer favs;
    private final static long serialVersionUID = 8587654032520806074L;

}
