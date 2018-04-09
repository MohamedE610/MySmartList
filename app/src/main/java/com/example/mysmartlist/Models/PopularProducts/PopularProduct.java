
package com.example.mysmartlist.Models.PopularProducts;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularProduct implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("popularity")
    @Expose
    public Integer popularity;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public Float price;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("market")
    @Expose
    public String market;

    private final static long serialVersionUID = 7314724218558418418L;

}
