
package com.example.mysmartlist.Models.Products;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<ProductData> data = null;
    private final static long serialVersionUID = 8050862254064154696L;

}
