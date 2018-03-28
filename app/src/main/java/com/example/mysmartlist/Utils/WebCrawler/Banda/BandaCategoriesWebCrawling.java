package com.example.mysmartlist.Utils.WebCrawler.Banda;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mysmartlist.Models.CategoriesRespone.CategoriesResponse;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddMultipleCategoriesRequest;
import com.example.mysmartlist.Utils.WebCrawler.Danob.DanobProductsWebCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 3/11/2018.
 */

public class BandaCategoriesWebCrawling extends AsyncTask<Void, Void, String> {


    final String categoryClass="department-box";
    final String categoryImageClass="department-box__image";
    final String categoryNameClass="department-box__title";
    final String categoryLinkClass="department-box__all-link";
    Context context;
    private int count;

    public BandaCategoriesWebCrawling(Context context) {
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
        final String urlToLoad = "http://www.panda.com.sa/stores/riyadh/categories";
        Document document = null;
        final HashMap<String,ArrayList<HashMap<String,String>>> categories=new HashMap<>();
        final ArrayList<HashMap<String,String>> hashMaps=new ArrayList<>();
        try {
            document = Jsoup.connect(urlToLoad).get();
            Elements elements = document.getElementsByTag("dt");
            for (int i = 1; i < elements.size(); i++) {
                Elements element= elements.get(i).getElementsByTag("a");
                String imgUrl="none";
                String name=element.get(0).text();
                String link=element.get(0).attr("href");
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

                           /* MySharedPreferences.setUpMySharedPreferences(context);
                            MySharedPreferences.setUserSetting("wc_cat","1");*/

                    for (int i = 0; i <hashMaps.size() ; i++) {
                        BandaProductsWebCrawling  bandaProductsWebCrawling =new BandaProductsWebCrawling(context,categoriesResponse.data.get(0).id+"",hashMaps.get(0).get("link"));
                        bandaProductsWebCrawling.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    }
                }

                @Override
                public void OnFailure(Object obj) {

                  /*  MySharedPreferences.setUpMySharedPreferences(context);
                    MySharedPreferences.setUserSetting("wc_cat","-1");*/
                }
            });

            //multipleCategoriesRequest.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


