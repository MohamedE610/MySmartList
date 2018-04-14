
package com.example.mysmartlist.Models.Note;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
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
    private final static long serialVersionUID = -6216659949599526682L;

}
