package com.example.mysmartlist.Utils;

import com.example.mysmartlist.Models.Category_1;
import com.example.mysmartlist.Models.Product_1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Fahmy on 1/28/2018.
 */

public  class JsonParsingUtils {

    public static ArrayList<Product_1> getAllProducts(String json){
        ArrayList<Product_1> products=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i <jsonArray.length() ; i++) {
            Product_1 product=new Product_1();
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

    public static Product_1 getSingleProduct(String json){
        Product_1 product = new Product_1();
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

    public static ArrayList<Category_1> getAllCategories(String json){
        ArrayList<Category_1> categories=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i <jsonArray.length() ; i++) {
                Category_1 category=new Category_1();
                JSONObject object=jsonArray.getJSONObject(i);
                category.setId(object.getString("id"));
                category.setName(object.getString("name"));
                category.setImgUrl(object.getString("image"));

                categories.add(category);
            }

        }catch (Exception e){}

        return categories;
    }

    public static Category_1 getSingleCategory(String json){
        Category_1 category = new Category_1();
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
