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

import com.example.mysmartlist.Fragments.CategoriesFragment;
import com.example.mysmartlist.Fragments.FavouriteFragment;
import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Fragments.SearchFragment;
import com.example.mysmartlist.Fragments.SettingsFragment;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseCheckAuth;
import com.example.mysmartlist.Utils.MySharedPreferences;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;
    FirebaseCheckAuth firebaseCheckAuth;

    static FragmentManager fragmentManager;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MySharedPreferences.setUpMySharedPreferences(this);


        fragmentManager=getSupportFragmentManager();
        int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));

        /************************  SignupActivity.getClient(uid);  **************************/
        //SignupActivity.getClient(uid);
        //final TextView textView=(TextView) findViewById(R.id.asd);

        fab = (FloatingActionButton) findViewById(R.id.fab);
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
        tabLayout.getTabAt(4).setIcon(R.drawable.home__selected);
        tabLayout.getTabAt(4).select();

        //get firebase auth instance
        //auth = FirebaseAuth.getInstance();

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

    }


    //sign out method
    public void signOut() {
        //auth.signOut();
        /*firebaseSignOut.signOut();
        startActivity(new Intent(this, SignInActivity.class));
        this.finish();*/
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
            if(i==4) {
                tabLayout.getTabAt(i).setIcon(R.drawable.home);
            }else if(i==3) {
                tabLayout.getTabAt(i).setIcon(R.drawable.view_grid);
            }else if(i==2) {
                tabLayout.getTabAt(i).setIcon(R.drawable.magnify_plus_outline);
            }else if(i==1) {
                tabLayout.getTabAt(i).setIcon(R.drawable.heart_outline);
            }else if(i==0) {
                tabLayout.getTabAt(i).setIcon(R.drawable.settings);
            }
        }

        int position=tab.getPosition();
        switch (position){

            case 4:
                tab.setIcon(R.drawable.home__selected);
                fab.setVisibility(View.VISIBLE);
                addHomeFragment();
                break;
            case 3:
                tab.setIcon(R.drawable.view_grid_selected);
                addCategoriesFragment();
                fab.setVisibility(View.GONE);
                break;
            case 2:
                tab.setIcon(R.drawable.magnify_plus_outline_selected);
                fab.setVisibility(View.GONE);
                addSearchFragment();
                break;
            case 1:
                tab.setIcon(R.drawable.heart_outline_selected);
                fab.setVisibility(View.GONE);
                addFavouriteFragment();
                break;
            case 0:
                tab.setIcon(R.drawable.settings_selected);
                fab.setVisibility(View.GONE);
                addSettingsFragment();
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

    public void addSettingsFragment() {
        SettingsFragment fragment=new SettingsFragment();
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
