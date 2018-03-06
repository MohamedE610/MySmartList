
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pin implements Serializable
{

    @SerializedName("product")
    @Expose
    public Product_ product;
    private final static long serialVersionUID = -8916437502788895961L;

}
