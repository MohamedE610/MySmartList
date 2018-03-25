package com.example.mysmartlist.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysmartlist.Activities.MainActivity;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Constants;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeleteFavouriteProductRequest;
import com.squareup.picasso.Picasso;

/**
 * Created by abdallah on 2/21/2018.
 */

public class FavouriteProductAdapter extends RecyclerView.Adapter<FavouriteProductAdapter.MyViewHolder> {

    Products products;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    AddFavouriteProductRequest addfavouriteProductRequest;
    DeleteFavouriteProductRequest deleteFavouriteProductRequest;

    Callbacks addFavCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
            MainActivity.addFavouriteFragment();
        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks deleteFavCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
            MainActivity.addFavouriteFragment();
        }

        @Override
        public void OnFailure(Object obj) {

        }
    };



    public FavouriteProductAdapter() {
    }

    public FavouriteProductAdapter(Products categories, Context context) {
        this.products = categories;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favourite_product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String detailsStr=products.data.get(position).name+"\n"+products.data.get(position).price;
        holder.textView.setText(detailsStr);

        String urlStr = Constants.BasicUrlImg+products.data.get(position).image;
        Picasso.with(context).load(urlStr).into(holder.img);

       // holder.fav= SignupActivity.isFavProduct(products.data.get(position).id);
        if(holder.fav)
            holder.imgFavourite.setImageResource(R.drawable.heart_red);
        else
            holder.imgFavourite.setImageResource(R.drawable.heart);

        MySharedPreferences.setUpMySharedPreferences(context);
        final int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        holder.imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.fav){
                    holder.imgFavourite.setImageResource(R.drawable.heart_red);
                    addfavouriteProductRequest=new AddFavouriteProductRequest(id,
                            products.data.get(position).id);
                    addfavouriteProductRequest.setCallbacks(addFavCallbacks);
                    addfavouriteProductRequest.start();
                }else {
                    holder.imgFavourite.setImageResource(R.drawable.heart);
                    deleteFavouriteProductRequest=new DeleteFavouriteProductRequest(id,
                            products.data.get(position).id);
                    deleteFavouriteProductRequest.setCallbacks(deleteFavCallbacks);
                    deleteFavouriteProductRequest.start();
                }
                holder.fav=!holder.fav;
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
        if (products == null || products.data==null)
            return 0;
        return products.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgFavourite;
        TextView textView;
        CardView cardView;
        boolean fav=true;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgFavourite = (ImageView) itemView.findViewById(R.id.imgFavourite);
            textView = (TextView) itemView.findViewById(R.id.text_details);
            cardView = (CardView) itemView.findViewById(R.id.card);

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

    /* clients = new Clients();
                            clients=(Clients)obj;
                            clientsAdapter=new ClientsAdapter(clients,getActivity());
                            clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                            clientsAdapter.setClickListener(ClientsFragment.this);
                            clientsRecyclerView.setAdapter(clientsAdapter);
                            clientsRecyclerView.scrollToPosition(clients.getData().size()/2);
                            clientsAdapter.notifyDataSetChanged();
                            */

}

