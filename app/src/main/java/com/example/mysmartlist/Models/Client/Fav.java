
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fav implements Serializable
{

    @SerializedName("product")
    @Expose
    public Product_ product;
    private final static long serialVersionUID = 1214983920772794035L;

}
