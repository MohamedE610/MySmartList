package com.example.mysmartlist.Utils;

import com.example.mysmartlist.Models.Category;
import com.example.mysmartlist.Models.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Fahmy on 1/28/2018.
 */

public  class JsonParsingUtils {

    public static ArrayList<Product> getAllProducts(String json){
        ArrayList<Product> products=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i <jsonArray.length() ; i++) {
            Product product=new Product();
            JSONObject object=jsonArray.getJSONObject(i);
            product.setId(object.getString("id"));
            product.setName(object.getString("name"));
            product.setPrice(object.getString("price"));
            product.setCat_id(object.getString("category_id"));
            product.setImgUrl(object.getString("image"));

            products.add(product);
            }

        }catch (Exception e){}

        return products;
    }

    public static Product getSingleProduct(String json){
        Product product = new Product();
        try{
                JSONObject jsonObject = new JSONObject(json);
                JSONObject object = jsonObject.getJSONObject("data");
                product.setId(object.getString("id"));
                product.setName(object.getString("name"));
                product.setPrice(object.getString("price"));
                product.setCat_id(object.getString("category_id"));
                product.setImgUrl(object.getString("image"));

        }catch (Exception e){}

        return product;
    }

    public static ArrayList<Category> getAllCategories(String json){
        ArrayList<Category> categories=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i <jsonArray.length() ; i++) {
                Category  category=new Category();
                JSONObject object=jsonArray.getJSONObject(i);
                category.setId(object.getString("id"));
                category.setName(object.getString("name"));
                category.setImgUrl(object.getString("image"));

                categories.add(category);
            }

        }catch (Exception e){}

        return categories;
    }

    public static Category getSingleCategory(String json){
        Category  category = new Category();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONObject object = jsonObject.getJSONObject("data");
            category.setId(object.getString("id"));
            category.setName(object.getString("name"));
            category.setImgUrl(object.getString("image"));
        }catch (Exception e){}

        return category;
    }


}
