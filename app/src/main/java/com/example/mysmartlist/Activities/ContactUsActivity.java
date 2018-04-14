package com.example.mysmartlist.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mysmartlist.R;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView facebookImg;
    ImageView twitterImg;
    ImageView emailImg;
    ImageView whatsUpImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        setTitle("تواصل معنا");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        facebookImg=(ImageView)findViewById(R.id.facebook_img);
        twitterImg=(ImageView)findViewById(R.id.twitter);
        emailImg=(ImageView)findViewById(R.id.email);
        whatsUpImg=(ImageView)findViewById(R.id.whatsup_img);
        
        facebookImg.setOnClickListener(this);
        whatsUpImg.setOnClickListener(this);
        emailImg.setOnClickListener(this);
        twitterImg.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        
        switch (id){
            case R.id.facebook_img:
                facebookMethod();
                break;
            case R.id.twitter:
                twitterMethod();
                break;
            case R.id.email:
                emailMethod();
                break;
            case R.id.whatsup_img:
                whatsUpMethod();
                break;
        }
    }

    private void whatsUpMethod() {
    }

    private void emailMethod() {
    }

    private void twitterMethod() {
    }

    private void facebookMethod() {
    }
}
