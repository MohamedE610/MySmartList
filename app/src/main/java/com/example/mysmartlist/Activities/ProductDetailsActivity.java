package com.example.mysmartlist.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddPinProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeleteFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeletePinProductRequest;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

        Callbacks addPinCallbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks addFavCallbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks deletePinCallbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks deleteFavCallbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

        }

        @Override
        public void OnFailure(Object obj) {

        }
    };


    AddFavouriteProductRequest addfavouriteProductRequest;
    DeleteFavouriteProductRequest deleteFavouriteProductRequest;

    AddPinProductRequest addPinProductRequest;
    DeletePinProductRequest deletePinProductRequest;

    ImageView img;
    ImageView imgPin;
    ImageView imgFavourit;
    TextView textView;
    CardView cardView;
    TextView market;
    boolean pin = false;
    boolean fav = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        ProductsByClientID product=(ProductsByClientID)getIntent().getSerializableExtra("data");

        img = (ImageView) findViewById(R.id.img);
        imgPin = (ImageView) findViewById(R.id.img_pin);
        imgFavourit = (ImageView) findViewById(R.id.img_favourite);
        textView = (TextView) findViewById(R.id.text_details);
        cardView = (CardView) findViewById(R.id.card);
        market = (TextView) findViewById(R.id.market);

        String detailsStr = product.data.name + "\n" + product.data.price;
        textView.setText(detailsStr);

        String marketId = product.data.market;
        String marketStr;
        if (marketId.equals("1"))
            marketStr = "الدانوب";
        else
            marketStr = "هايبر باندا";
        market.setText(marketStr);

        String urlStr = product.data.image;
        Picasso.with(this).load(urlStr).into(img);

        fav = product.client.fav;
        ///holder.fav= SignupActivity.isFavProduct(products.get(position).data.id);
        if (fav)
            imgFavourit.setImageResource(R.drawable.heart_red);
        else
            imgFavourit.setImageResource(R.drawable.heart);

        MySharedPreferences.setUpMySharedPreferences(this);
        final int id = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        imgFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!fav) {
                    imgFavourit.setImageResource(R.drawable.heart_red);
                    addfavouriteProductRequest = new AddFavouriteProductRequest(id,
                            product.data.id);
                    addfavouriteProductRequest.setCallbacks(addFavCallbacks);
                    addfavouriteProductRequest.start();
                } else {
                    imgFavourit.setImageResource(R.drawable.heart);
                    deleteFavouriteProductRequest = new DeleteFavouriteProductRequest(id,
                            product.data.id);
                    deleteFavouriteProductRequest.setCallbacks(deleteFavCallbacks);
                    deleteFavouriteProductRequest.start();
                }
                fav = !fav;

            }
        });


        pin = product.client.pin;
        if (pin)
            imgPin.setImageResource(R.drawable.pin_red);
        else
            imgPin.setImageResource(R.drawable.pin);
        imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pin) {
                    imgPin.setImageResource(R.drawable.pin_red);
                    addPinProductRequest = new AddPinProductRequest(id,
                            product.data.id);
                    addPinProductRequest.setCallbacks(addPinCallbacks);
                    addPinProductRequest.start();
                } else {
                    imgPin.setImageResource(R.drawable.pin);
                    deletePinProductRequest = new DeletePinProductRequest(id,
                            product.data.id);
                    deletePinProductRequest.setCallbacks(deletePinCallbacks);
                    deletePinProductRequest.start();
                }
                pin = !pin;
            }
        });


    }
}
