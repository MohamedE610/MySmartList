package com.example.mysmartlist.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.Activities.SignInActivity;
import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddPinProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeleteFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeletePinProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.addProductToListRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getCurrentClientListsRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 2/21/2018.
 */

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.MyViewHolder> {

    Products products;
    Context context;
    int LastPosition = -1;
    int pos;
    RecyclerViewClickListener ClickListener;

    Callbacks addPinCallbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

            MainActivityFragment.addPinFragment();
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
            MainActivityFragment.addPinFragment();
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

    Callbacks addProductCallbacks = new Callbacks() {
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


    public ProductAdapter1() {
    }

    public ProductAdapter1(Products products, Context context) {
        this.products = products;
        this.context = context;
        MySharedPreferences.setUpMySharedPreferences(context);
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_item, parent, false);
        return new MyViewHolder(view);
    }


    String notNowStr;

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        notNowStr = MySharedPreferences.getUserSetting("notNow");


        pos = position;
        String detailsStr = products.data.get(position).name + "\n" + products.data.get(position).price;
        holder.textView.setText(detailsStr);

        String market = products.data.get(position).market;
        String marketStr;
        if (market.equals("1"))
            marketStr = "الدانوب";
        else
            marketStr = "هايبر باندا";
        holder.market.setText(marketStr);

        //String urlStr = Constants.BasicUrlImg+products.get(position).data.image;
        String urlStr = products.data.get(position).image;
        Picasso.with(context).load(urlStr).into(holder.img);


        holder.imgFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "الرجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, SignInActivity.class));
            }
        });

        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(context, "الرجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, SignInActivity.class));
            }
        });
        holder.linearLayoutAdd.setClickable(true);
        holder.linearLayoutAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "الرجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, SignInActivity.class));

            }
        });


        holder.textViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "الرجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, SignInActivity.class));
            }
        });

        holder.textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "الرجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, SignInActivity.class));
            }
        });

        setAnimation(holder.cardView, position);
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {

        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        if (products == null)
            return 0;
        return products.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgPin;
        ImageView imgFavourit;
        TextView textView;
        CardView cardView;
        TextView market;
        boolean pin = false;
        boolean fav = false;

        LinearLayout linearLayout;
        LinearLayout linearLayoutAdd;
        //TextView textViewAdd;
        TextView textViewPlus;
        TextView textViewMinus;
        TextView textViewCount;
        boolean isReadyToAdd = false;
        int productCount = 1;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgPin = (ImageView) itemView.findViewById(R.id.img_pin);
            imgFavourit = (ImageView) itemView.findViewById(R.id.img_favourite);
            textView = (TextView) itemView.findViewById(R.id.text_details);
            cardView = (CardView) itemView.findViewById(R.id.card);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout1);
            linearLayoutAdd = (LinearLayout) itemView.findViewById(R.id.linearLayoutAdd);
            textViewPlus = (TextView) itemView.findViewById(R.id.text_plus);
            textViewMinus = (TextView) itemView.findViewById(R.id.text_minus);
            textViewCount = (TextView) itemView.findViewById(R.id.text_count);
            market = (TextView) itemView.findViewById(R.id.market);

        }

        @Override
        public void onClick(View view) {
            if (ClickListener != null)
                ClickListener.ItemClicked(view, getAdapterPosition());
        }

        public void clearAnimation() {
            cardView.clearAnimation();
        }
    }

    public interface RecyclerViewClickListener {

        public void ItemClicked(View v, int position);
    }

    private void setAnimation(View viewToAnimate, int position) {

        if (position > LastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            LastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }



}

