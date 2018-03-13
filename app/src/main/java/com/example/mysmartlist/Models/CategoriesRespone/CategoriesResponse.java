
package com.example.mysmartlist.Models.CategoriesRespone;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesResponse implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    private final static long serialVersionUID = 1895936804357359701L;

}
