package com.example.mysmartlist.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysmartlist.Fragments.OLdListsFragment;
import com.example.mysmartlist.R;

public class OldListsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_lists);

        addOldListsFragment();

    }

    private void addOldListsFragment() {
        fragmentManager=getSupportFragmentManager();
        OLdListsFragment oLdListsFragment=new OLdListsFragment();
        fragmentManager.beginTransaction().add(R.id.old_lists_container,oLdListsFragment).commit();
    }


}
