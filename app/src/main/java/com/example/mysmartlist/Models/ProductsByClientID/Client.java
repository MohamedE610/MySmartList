
package com.example.mysmartlist.Models.ProductsByClientID;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client implements Serializable
{

    @SerializedName("fav")
    @Expose
    public Boolean fav;
    @SerializedName("pin")
    @Expose
    public Boolean pin;
    private final static long serialVersionUID = -1637731601326122997L;

}
