package com.example.mysmartlist.Activities;

import android.support.v4.app.FragmentManager;
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

        addCategoryProductsFragment(getSupportFragmentManager());

    }

    private void addCategoryProductsFragment(FragmentManager supportFragmentManager) {
        CategoryProductsFragment reportsFragment=new CategoryProductsFragment();
        supportFragmentManager.beginTransaction().add(android.R.id.content,reportsFragment).commit();
    }

}
