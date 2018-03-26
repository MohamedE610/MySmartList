package com.example.mysmartlist.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysmartlist.Fragments.OLdListsFragment;
import com.example.mysmartlist.Fragments.ReportsFragment;
import com.example.mysmartlist.R;

public class ReportsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        addReportsFragment();
    }

    private void addReportsFragment() {
        fragmentManager=getSupportFragmentManager();
        ReportsFragment reportsFragment=new ReportsFragment();
        fragmentManager.beginTransaction().add(R.id.old_lists_container,reportsFragment).commit();
    }
}
