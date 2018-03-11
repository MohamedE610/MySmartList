package com.example.mysmartlist.Utils.FetchDataFromServer;

import android.content.Context;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.Categories.CategoryData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by abdallah on 3/11/2018.
 */

public class CategoriesWebCrawling extends AsyncTask<Void, Void, String> {


    final String categoryClass="department-box";
    final String categoryImageClass="department-box__image";
    final String categoryNameClass="department-box__title";
    Context context;
    private int count;

    public CategoriesWebCrawling() {
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
        Categories categories=new Categories();
        categories.data=new ArrayList<>();
        try {
            document = Jsoup.connect(urlToLoad).get();
            Elements elements = document.getElementsByClass(categoryClass);
            for (int i = 0; i < elements.size(); i++) {
                Elements imgElements= elements.get(i).select("div."+categoryImageClass);
                Elements nameElements= elements.get(i).select("div."+categoryNameClass);
                String imgUrl=imgElements.get(0).attr("style");
                int index,lastIndex;
                index=imgUrl.indexOf('(');
                lastIndex=imgUrl.indexOf(')');
                imgUrl=imgUrl.substring(++index,lastIndex);
                String name=nameElements.get(0).text();
                CategoryData categoryData=new CategoryData();
                categoryData.name=name;
                categoryData.image=imgUrl;
                categories.data.add(categoryData);
                count = elements.size();
            }

             count = elements.size();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
