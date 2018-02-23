package com.example.mysmartlist.Models;

/**
 * Created by abdallah on 2/20/2018.
 */

public class Product {

    String id;
    String name;
    String price;
    String cat_id;
    String imgUrl;



    public Product(String id, String name, String price, String cat_id, String imgUrl) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.cat_id = cat_id;
        this.imgUrl = imgUrl;
    }

    public Product() {

    }

    public Product(String id, String name, String price, String cat_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cat_id = cat_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
