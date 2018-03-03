
package com.example.mysmartlist.Models.Client;

import java.io.Serializable;
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
    public java.util.List<com.example.mysmartlist.Models.Client.List> lists = null;
    @SerializedName("favs")
    @Expose
    public java.util.List<Fav> favs = null;
    @SerializedName("keywords")
    @Expose
    public java.util.List<String> keywords = null;
    @SerializedName("pins")
    @Expose
    public java.util.List<Pin> pins = null;
    private final static long serialVersionUID = -3259815550809910005L;

}
