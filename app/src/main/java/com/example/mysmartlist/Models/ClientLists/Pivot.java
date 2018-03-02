
package com.example.mysmartlist.Models.ClientLists;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot implements Serializable
{

    @SerializedName("list_id")
    @Expose
    public Integer listId;
    @SerializedName("product_id")
    @Expose
    public Integer productId;
    @SerializedName("count")
    @Expose
    public Integer count;
    private final static long serialVersionUID = -1942185797542816352L;

}
