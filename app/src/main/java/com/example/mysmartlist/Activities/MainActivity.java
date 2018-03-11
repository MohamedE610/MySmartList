package com.example.mysmartlist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mysmartlist.Fragments.ListsFragment;
import com.example.mysmartlist.Fragments.CategoriesFragment;
import com.example.mysmartlist.Fragments.FavouriteFragment;
import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Fragments.SearchFragment;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseCheckAuth;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseSignOut;
import com.example.mysmartlist.Utils.MySharedPreferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;
    //private FirebaseAuth.AuthStateListener authListener;
    //private FirebaseAuth auth;
    FirebaseSignOut firebaseSignOut;
    FirebaseCheckAuth firebaseCheckAuth;

    static FragmentManager fragmentManager;

    class HtmlHandler {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void handleHtml(String html) {
            // scrape the content here
            Document document= Jsoup.parse(html);
            Elements elements =document.getElementsByClass("product-box product-box--add-to-cart");
            Elements elements1 =document.getElementsByClass("product-box");
            //Elements elements=document.select("div.product-box product-box--add-to-cart");
            //Elements elements=document.getElementsByTag("script");
            int i=elements.size();
        }
    }

    public void jsouptest(){
        final String urlToLoad="https://danube.sa/departments/just-for-women?p=1";
        final WebView mWebView=new WebView(this);
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

        /*new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    //URL url=new URL("https://danube.sa/departments/just-for-women?p=1");
                    //DesiredCapabilities dc=new DesiredCapabilities("android","4.4", Platform.ANDROID);
                    //WebDriver webDriver=new SelendroidDriver(url,dc);
                    //String htmlSrt=webDriver.getPageSource();
                     Document document= Jsoup.parse("");
                    *//*Document document=Jsoup.connect("https://danube.sa/departments/just-for-women?p=1")
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0").get();*//*
                    Elements elements =document.getElementsByClass("product-box product-box--add-to-cart");
                    Elements elements1 =document.getElementsByClass("product-box");
                    //Elements elements=document.select("div.product-box product-box--add-to-cart");
                    //Elements elements=document.getElementsByTag("script");
                    int i=elements.size();
                    Element sc=elements.get(0);
                    String s=sc.html();
                    String ss=s.toLowerCase();
                    String sss=ss+s;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();*/

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //jsouptest();

        fragmentManager=getSupportFragmentManager();

        MySharedPreferences.setUpMySharedPreferences(this);
        int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));

        /************************  SignupActivity.getClient(uid);  **************************/
        //SignupActivity.getClient(uid);

        //final TextView textView=(TextView) findViewById(R.id.asd);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

               startActivity(new Intent(MainActivity.this,ListsActivity.class));
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(this);
        addHomeFragment();
        tabLayout.getTabAt(0).setIcon(R.drawable.home__selected);

        //get firebase auth instance
        //auth = FirebaseAuth.getInstance();
        firebaseSignOut=new FirebaseSignOut();

        firebaseCheckAuth=new FirebaseCheckAuth();
        firebaseCheckAuth.setCallback(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                MainActivity.this.finish();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        firebaseCheckAuth.checkFirebaseAuth();

        /*authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    MainActivity.this.finish();
                }
            }
        };*/
    }


    //sign out method
    public void signOut() {
        //auth.signOut();
        firebaseSignOut.signOut();
        startActivity(new Intent(this, SignInActivity.class));
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        for (int i = 0; i < 5 ; i++) {
            if(i==0) {
                tabLayout.getTabAt(i).setIcon(R.drawable.home);
            }else if(i==1) {
                tabLayout.getTabAt(i).setIcon(R.drawable.view_grid);
            }else if(i==2) {
                tabLayout.getTabAt(i).setIcon(R.drawable.magnify_plus_outline);
            }else if(i==3) {
                tabLayout.getTabAt(i).setIcon(R.drawable.heart_outline);
            }else if(i==4) {
                tabLayout.getTabAt(i).setIcon(R.drawable.settings);
            }
        }

        int position=tab.getPosition();
        switch (position){

            case 0:
                tab.setIcon(R.drawable.home__selected);
                addHomeFragment();
                break;
            case 1:
                tab.setIcon(R.drawable.view_grid_selected);
                addCategoriesFragment();
                break;
            case 2:
                tab.setIcon(R.drawable.magnify_plus_outline_selected);
                addSearchFragment();
                break;
            case 3:
                tab.setIcon(R.drawable.heart_outline_selected);
                addFavouriteFragment();
                break;
            case 4:
                tab.setIcon(R.drawable.settings_selected);
                addFragment();
                break;
            default:
                break;
        }

    }

    public static void addFavouriteFragment() {
        FavouriteFragment fragment=new FavouriteFragment();
        FragmentManager fragmentManager1=fragmentManager;
        fragmentManager1.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }

    public static void addFragment() {
        ListsFragment fragment=new ListsFragment();
        FragmentManager fragmentManager1=fragmentManager;
        fragmentManager1.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }

    private void addSearchFragment() {
        SearchFragment fragment=new SearchFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }


    public static void addHomeFragment() {
        MainActivityFragment fragment=new MainActivityFragment();
        FragmentManager fragmentManager1=fragmentManager;
        fragmentManager1.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }

    public void addCategoriesFragment() {
        CategoriesFragment fragment=new CategoriesFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
