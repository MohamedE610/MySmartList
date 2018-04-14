package com.example.mysmartlist.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mysmartlist.Fragments.ListProductsFragment;
import com.example.mysmartlist.Fragments.SearchFragment;
import com.example.mysmartlist.R;

public class ListProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setTitle("المنتجات");

        Bundle bundle=getIntent().getExtras();
        addListProductsFragment(getSupportFragmentManager(),bundle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.ma ke(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });
    }


    public static void addListProductsFragment(FragmentManager fragmentManager,Bundle bundle) {
        ListProductsFragment fragment=new ListProductsFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment_list_products_container,fragment).commit();

    }


}
