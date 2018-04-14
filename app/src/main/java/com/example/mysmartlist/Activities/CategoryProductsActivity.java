package com.example.mysmartlist.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysmartlist.Fragments.CategoryProductsFragment;
import com.example.mysmartlist.Fragments.ReportsFragment;
import com.example.mysmartlist.R;

public class CategoryProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            String name = bundle.getString("cat_name");
            setTitle(name);
        }
        addCategoryProductsFragment(getSupportFragmentManager(),bundle);
    }

    private void addCategoryProductsFragment(FragmentManager supportFragmentManager,Bundle bundle) {
        CategoryProductsFragment categoryProductsFragment=new CategoryProductsFragment();
        categoryProductsFragment.setArguments(bundle);
        supportFragmentManager.beginTransaction().add(android.R.id.content,categoryProductsFragment).commit();
    }

}
