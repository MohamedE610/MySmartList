
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pin implements Serializable
{

    @SerializedName("product")
    @Expose
    public Product__ product;
    private final static long serialVersionUID = 3986788957534849711L;

}
