package com.example.mysmartlist.Utils.WebCrawler;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.Categories.CategoryData;
import com.example.mysmartlist.Models.CategoriesRespone.CategoriesResponse;
import com.example.mysmartlist.Models.List.Product;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.AddMultipleCategoriesRequest;
import com.google.gson.internal.LinkedTreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by abdallah on 3/11/2018.
 */

public class CategoriesWebCrawling extends AsyncTask<Void, Void, String> {


    final String categoryClass="department-box";
    final String categoryImageClass="department-box__image";
    final String categoryNameClass="department-box__title";
    final String categoryLinkClass="department-box__all-link";
    Context context;
    private int count;

    public CategoriesWebCrawling(Context context) {
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        jsoupMethod();
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


    public void jsoupMethod() {
        final String urlToLoad = "https://danube.sa/departments";
        Document document = null;
        final HashMap<String,ArrayList<HashMap<String,String>>> categories=new HashMap<>();
        final ArrayList<HashMap<String,String>> hashMaps=new ArrayList<>();
        try {
            document = Jsoup.connect(urlToLoad).get();
            Elements elements = document.getElementsByClass(categoryClass);
            for (int i = 3; i < elements.size(); i++) {
                Elements imgElements= elements.get(i).select("div."+categoryImageClass);
                Elements nameElements= elements.get(i).select("div."+categoryNameClass);
                Elements linkElements= elements.get(i).select("a."+categoryLinkClass);

                String imgUrl=imgElements.get(0).attr("style");
                int index,lastIndex;
                index=imgUrl.indexOf('(');
                lastIndex=imgUrl.indexOf(')');
                imgUrl=imgUrl.substring(++index,lastIndex);
                String name=nameElements.get(0).text();
                String link=linkElements.get(0).attr("href");
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("image",imgUrl);
                hashMap.put("link",link);
                hashMaps.add(hashMap);
            }
            categories.put("data",hashMaps);

            AddMultipleCategoriesRequest multipleCategoriesRequest=new AddMultipleCategoriesRequest(categories);
            multipleCategoriesRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                            CategoriesResponse categoriesResponse=(CategoriesResponse)obj;
                            MySharedPreferences.setUpMySharedPreferences(context);
                            MySharedPreferences.setUserSetting("wc_cat","1");

                    ProductsWebCrawling productsWebCrawling=new ProductsWebCrawling(context,categoriesResponse.data.get(0).id+"",hashMaps.get(0).get("link"));
                    productsWebCrawling.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    //for (int i = 0; i <hashMaps.size() ; i++) {
                    //}
                }

                @Override
                public void OnFailure(Object obj) {

                    MySharedPreferences.setUpMySharedPreferences(context);
                    MySharedPreferences.setUserSetting("wc_cat","-1");
                }
            });

            multipleCategoriesRequest.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


