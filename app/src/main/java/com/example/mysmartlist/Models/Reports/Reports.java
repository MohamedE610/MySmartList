
package com.example.mysmartlist.Models.Reports;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reports implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<ReportData> data = null;
    private final static long serialVersionUID = -5500741264001846378L;

}
