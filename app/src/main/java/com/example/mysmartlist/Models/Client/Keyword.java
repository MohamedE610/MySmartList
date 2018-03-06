
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword implements Serializable
{

    @SerializedName("keyword")
    @Expose
    public String keyword;
    @SerializedName("keyword_count")
    @Expose
    public Integer keywordCount;
    private final static long serialVersionUID = 6890562431106048708L;

}
