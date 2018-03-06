
package com.example.mysmartlist.Models.ProductsByClientID;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsByClientID implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("client")
    @Expose
    public Client client;
    private final static long serialVersionUID = -733895913621001084L;

}
