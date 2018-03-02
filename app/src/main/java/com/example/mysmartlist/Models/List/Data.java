
package com.example.mysmartlist.Models.List;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("products")
    @Expose
    public List<Product> products = null;
    @SerializedName("expenses")
    @Expose
    public Float expenses;
    @SerializedName("created")
    @Expose
    public String created;
    private final static long serialVersionUID = 6459685682024509227L;

}
