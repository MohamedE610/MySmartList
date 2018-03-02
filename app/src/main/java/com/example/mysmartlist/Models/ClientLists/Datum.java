
package com.example.mysmartlist.Models.ClientLists;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("client_id")
    @Expose
    public Integer clientId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    public Object deletedAt;
    @SerializedName("expenses")
    @Expose
    public Float expenses;
    @SerializedName("products")
    @Expose
    public List<Product> products = null;
    private final static long serialVersionUID = -4882622600112501301L;

}
