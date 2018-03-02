
package com.example.mysmartlist.Models.TopProducts;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopProduct implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("total_count")
    @Expose
    public String totalCount;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public Float price;
    @SerializedName("image")
    @Expose
    public String image;
    private final static long serialVersionUID = 8219187924728229967L;

}
