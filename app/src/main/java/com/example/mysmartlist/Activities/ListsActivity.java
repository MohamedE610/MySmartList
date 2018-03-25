package com.example.mysmartlist.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mysmartlist.Fragments.ListsFragment;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Dialogs.ListNameDialog;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.addListRequest;

public class ListsActivity extends AppCompatActivity {

    addListRequest listRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager=getSupportFragmentManager();
        addListsFragment();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

         /*     HashMap<String,String> hashMap=new HashMap<>();
              String name="";
              hashMap.put("name",name);
              MySharedPreferences.setUpMySharedPreferences(ListsActivity.this);
              int id= Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
              listRequest=new AddMultipleProductsRequest(id,hashMap);
              listRequest.setCallbacks(new Callbacks() {
                  @Override
                  public void OnSuccess(Object obj) {
                      addListsFragment();
                  }

                  @Override
                  public void OnFailure(Object obj) {

                  }
              });
              listRequest.start();*/
                ListNameDialog listNameDialog =new ListNameDialog(ListsActivity.this);
                listNameDialog.show();

            }
        });


    }

    static FragmentManager fragmentManager;
    public static void addListsFragment() {
            ListsFragment fragment=new ListsFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_list_container,fragment).commit();
    }

}
