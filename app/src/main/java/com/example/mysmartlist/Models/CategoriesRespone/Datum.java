
package com.example.mysmartlist.Models.CategoriesRespone;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("link")
    @Expose
    public String link;

    @SerializedName("market")
    @Expose
    public String market;

    private final static long serialVersionUID = 3459718118117989941L;

}
