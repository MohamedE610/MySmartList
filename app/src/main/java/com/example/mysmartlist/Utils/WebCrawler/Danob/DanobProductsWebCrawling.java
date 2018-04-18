package com.example.mysmartlist.Utils.WebCrawler.Danob;

import android.content.Context;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddMultipleProductsRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 3/11/2018.
 */

public class DanobProductsWebCrawling extends AsyncTask<Void, Void, String> {


    final String productClass = "product-box product-box--add-to-cart";
    final String productImageClass = "product-box__image__element";
    final String productNameClass = "product-box__name";
    final String productPriceClass = "product-price__current-price";
    Context context;

    String category_id="2";
    String category_link;


    public DanobProductsWebCrawling(Context context, String category_id, String category_link) {
        this.context = context;
        this.category_id=category_id;
        this.category_link=category_link;

    }

    public DanobProductsWebCrawling(Context context) {
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
        //final String urlToLoad = Constants.danubeBasicUrl+category_link;
        final String urlToLoad ="https://danube.sa/departments/fresh-fruits-and-vegetables";
        final WebView mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        //mWebView.addJavascriptInterface(new HtmlHandler(), "HtmlHandler");
        mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //mWebView.loadUrl("javascript:HtmlHandler.handleHtml(document.documentElement.outerHTML);");
                mWebView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }

        });
        mWebView.loadUrl(urlToLoad);
    }


    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            // process the html as needed by the app
            productsCrawling(html);
        }
    }

    class HtmlHandler {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void handleHtml(String html) {
            // scrape the content here
            productsCrawling(html);
        }
    }

    void productsCrawling(String html){
        Document document = null;
        try {
            document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass(productClass);
            HashMap<String,Object> products=new HashMap<>();
            ArrayList<HashMap<String,String>> hashMaps=new ArrayList<>();

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
                price=price.substring(0,price.indexOf(" "));
                price= convertArabicNumToEnglishNum(price);
                price=price.replace("٫",".");
                HashMap<String,String> productData = new HashMap<>();
                productData.put("name",name);
                productData.put("image",imgUrl);
                productData.put("price",price);

                productData.put("category_id",category_id);
                hashMaps.add(productData);

            }
            products.put("data",hashMaps);
            products.put("market_id","1");

            AddMultipleProductsRequest addMultipleProductsRequest=new AddMultipleProductsRequest(products);
            addMultipleProductsRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                    String s=obj.toString();
                }

                @Override
                public void OnFailure(Object obj)
                {
                    String s=obj.toString();
                }
            });

            //addMultipleProductsRequest.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    private static String convertArabicNumToEnglishNum(String number) {
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
