package com.example.mysmartlist.Utils.WebCrawler;

import android.content.Context;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mysmartlist.Models.Products.ProductData;
import com.example.mysmartlist.Models.Products.Products;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by abdallah on 3/11/2018.
 */

public class ProductsWebCrawling extends AsyncTask<Void, Void, String> {


    final String productClass = "product-box product-box--add-to-cart";
    final String productImageClass = "product-box__image__element";
    final String productNameClass = "product-box__name";
    final String productPriceClass = "product-price__current-price";
    Context context;

    public ProductsWebCrawling(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        jsoupMethod();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


    public void jsoupMethod() {

        final String urlToLoad = "https://danube.sa/departments/health-and-beauty?p=0";
        final WebView mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new HtmlHandler(), "HtmlHandler");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.loadUrl("javascript:HtmlHandler.handleHtml(document.documentElement.outerHTML);");
            }

        });
        mWebView.loadUrl(urlToLoad);
    }

    class HtmlHandler {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void handleHtml(String html) {
            // scrape the content here
            Document document = null;
            Products  products = new Products();
            products.data = new ArrayList<>();
            try {
                document = Jsoup.parse(html);
                Elements elements = document.getElementsByClass(productClass);
                int count;
                for (int i = 0; i < elements.size(); i++) {
                    Elements imgElements = elements.get(i).select("div." + productImageClass);
                    Elements nameElements = elements.get(i).select("div." + productNameClass);
                    Elements priceElements=elements.get(i).select("div."+productPriceClass);
                    String imgUrl = imgElements.get(0).attr("style");
                    int index, lastIndex;
                    index = imgUrl.indexOf('(');
                    lastIndex = imgUrl.indexOf(')');
                    imgUrl = imgUrl.substring(++index, lastIndex);
                    String name = nameElements.get(0).text();
                    String price = priceElements.get(0).text();
                    price=price.substring(price.indexOf(" "),price.length()-1);
                    price= arabicToDecimal(price);
                    price=price.replace('.',',');
                    ProductData productData = new ProductData();
                    productData.name = name;
                    productData.image = imgUrl;
                    //productData.price=Float.valueOf(price);
                    products.data.add(productData);
                    count = elements.size();
                }

                count = elements.size();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    private static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

}
