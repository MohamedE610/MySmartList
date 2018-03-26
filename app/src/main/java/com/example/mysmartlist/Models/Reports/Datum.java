
package com.example.mysmartlist.Models.Reports;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("total_expenses")
    @Expose
    public Integer totalExpenses;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    private final static long serialVersionUID = -2370403798929702289L;

}
