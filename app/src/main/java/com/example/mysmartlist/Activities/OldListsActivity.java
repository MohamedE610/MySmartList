package com.example.mysmartlist.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysmartlist.Fragments.OLdListsFragment;
import com.example.mysmartlist.R;

public class OldListsActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_lists);
        fragmentManager=getSupportFragmentManager();
        addOldListsFragment();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setTitle("القوائم السابقة");
    }

    public static void addOldListsFragment() {
        OLdListsFragment oLdListsFragment=new OLdListsFragment();
        fragmentManager.beginTransaction().replace(R.id.old_lists_container,oLdListsFragment).commit();
    }


}
