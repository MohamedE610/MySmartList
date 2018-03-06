
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fav implements Serializable
{

    @SerializedName("product")
    @Expose
    public Product product;
    private final static long serialVersionUID = -1116194551354110445L;

}
