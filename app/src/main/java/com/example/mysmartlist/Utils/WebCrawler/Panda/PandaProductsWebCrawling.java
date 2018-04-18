package com.example.mysmartlist.Utils.WebCrawler.Panda;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddMultipleProductsRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 3/11/2018.
 */

public class PandaProductsWebCrawling extends AsyncTask<Void, Void, String> {

    Context context;
    private int count;
    private String category_id;
    String url;

    public PandaProductsWebCrawling(Context context, String category_id , String url) {
        this.context=context;
        this.category_id=category_id;
        this.url=url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            jsoupMethod();
        }catch (Exception e){}

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
        //final String urlToLoad = "http://www.panda.com.sa/stores/riyadh/food-products.html";
        final String urlToLoad = url;

        Document document = null;
        final HashMap<String,Object> products=new HashMap<>();
        final ArrayList<HashMap<String,String>> hashMaps=new ArrayList<>();
        try {
            document = Jsoup.connect(urlToLoad).get();
            Elements firstElements = document.getElementsByClass("col-lg-4 col-md-4 col-sm-4 col-xs-6 _item first product-col  ");
            Elements lastElements = document.getElementsByClass("col-lg-4 col-md-4 col-sm-4 col-xs-6 _item last product-col  ");
            Elements midElements = document.getElementsByClass("col-lg-4 col-md-4 col-sm-4 col-xs-6 _item product-col  ");

            Elements elements =new Elements();
            elements.addAll(firstElements);
            elements.addAll(lastElements);
            elements.addAll(midElements);

            for (int i = 0; i < elements.size(); i++) {
                Elements imgElement= elements.get(i).getElementsByTag("img");
                String imgUrl=imgElement.get(0).attr("src");

                Elements nameElement=elements.get(i).getElementsByClass("product-name name");
                nameElement= nameElement.get(0).getElementsByTag("a");
                String name=nameElement.get(0).text();


                Elements priceElement= elements.get(i).getElementsByClass("price-box");
                priceElement= priceElement.get(0).getElementsByClass("price");
                String price=priceElement.get(0).text();
                price=price.substring(price.indexOf(" "),price.length()-1);
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("image",imgUrl);
                hashMap.put("price",price);
                hashMap.put("category_id",category_id);
                hashMaps.add(hashMap);
            }
            products.put("data",hashMaps);
            products.put ("market_id","2");

            AddMultipleProductsRequest addMultipleProductsRequest=new AddMultipleProductsRequest(products);
            addMultipleProductsRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                    String s=obj.toString();
                }

                @Override
                public void OnFailure(Object obj) {

                    String s=obj.toString();
                }
            });

            addMultipleProductsRequest.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


