package com.example.mysmartlist.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysmartlist.Fragments.ProfileFragment;
import com.example.mysmartlist.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("الصفحة الشخصيه");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_profile_container,new ProfileFragment()).commit();
    }
}
