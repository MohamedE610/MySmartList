
package com.example.mysmartlist.Models.Notes;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("product_id")
    @Expose
    public Integer productId;
    private final static long serialVersionUID = -2420675422299019953L;

}
