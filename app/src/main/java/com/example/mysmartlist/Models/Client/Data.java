
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("firebase_id")
    @Expose
    public String firebaseId;
    @SerializedName("family_members")
    @Expose
    public Integer familyMembers;
    @SerializedName("budget")
    @Expose
    public String budget;
    @SerializedName("lists")
    @Expose
    public List<Object> lists = null;
    @SerializedName("favs")
    @Expose
    public List<Fav> favs = null;
    @SerializedName("pins")
    @Expose
    public List<Pin> pins = null;
    @SerializedName("keywords")
    @Expose
    public List<Keyword> keywords = null;
    private final static long serialVersionUID = -7126546955113879796L;

}
