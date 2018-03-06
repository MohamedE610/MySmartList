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

import com.example.mysmartlist.Activities.SignupActivity;
import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Models.List.Product;
import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Constants;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchCategoriesData;
import com.example.mysmartlist.Utils.Networking.AddFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.AddPinProductRequest;
import com.example.mysmartlist.Utils.Networking.AddProductRequest;
import com.example.mysmartlist.Utils.Networking.DeleteFavouriteProductRequest;
import com.example.mysmartlist.Utils.Networking.DeletePinProductRequest;
import com.example.mysmartlist.Utils.Networking.addProductToListRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abdallah on 2/21/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    ArrayList<ProductsByClientID>  products;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;

    Callbacks addPinCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

            MainActivityFragment.addPinFragment();
        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks addFavCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks deletePinCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
            MainActivityFragment.addPinFragment();
        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks deleteFavCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {

        }

        @Override
        public void OnFailure(Object obj) {

        }
    };

    Callbacks addProductCallbacks=new Callbacks() {
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

    addProductToListRequest addProductToList;

    public ProductAdapter() {
    }

    public ProductAdapter(  ArrayList<ProductsByClientID>  products, Context context) {
        this.products = products;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_item, parent, false);
        return new MyViewHolder(view);
    }

    MyViewHolder viewHolder;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String detailsStr=products.get(position).data.name+"\n"+products.get(position).data.price;
        holder.textView.setText(detailsStr);

        String urlStr = Constants.BasicUrlImg+products.get(position).data.image;
        Picasso.with(context).load(urlStr).into(holder.img);

        holder.fav= products.get(position).client.fav;
        ///holder.fav= SignupActivity.isFavProduct(products.get(position).data.id);
        if(holder.fav)
            holder.imgFavourit.setImageResource(R.drawable.heart_red);
        else
            holder.imgFavourit.setImageResource(R.drawable.heart);

        holder.imgFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.fav){
                    holder.imgFavourit.setImageResource(R.drawable.heart_red);
                    addfavouriteProductRequest=new AddFavouriteProductRequest(SignupActivity.client.data.id,
                            products.get(position).data.id);
                    addfavouriteProductRequest.setCallbacks(addFavCallbacks);
                    addfavouriteProductRequest.start();
                }else {
                    holder.imgFavourit.setImageResource(R.drawable.heart);
                    deleteFavouriteProductRequest=new DeleteFavouriteProductRequest(SignupActivity.client.data.id,
                            products.get(position).data.id);
                    deleteFavouriteProductRequest.setCallbacks(deleteFavCallbacks);
                    deleteFavouriteProductRequest.start();
                }
                holder.fav=!holder.fav;
            }
        });

        //holder.pin= SignupActivity.isPinProduct(products.data.get(position).id);
        holder.pin= products.get(position).client.pin;
        if(holder.pin)
            holder.imgPin.setImageResource(R.drawable.pin_red);
        else
            holder.imgPin.setImageResource(R.drawable.pin);
        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.pin){
                    holder.imgPin.setImageResource(R.drawable.pin_red);
                    addPinProductRequest =new AddPinProductRequest(SignupActivity.client.data.id,
                            products.get(position).data.id);
                    addPinProductRequest.setCallbacks(addPinCallbacks);
                    addPinProductRequest.start();
                }else{
                    holder.imgPin.setImageResource(R.drawable.pin);
                    deletePinProductRequest=new DeletePinProductRequest(SignupActivity.client.data.id,
                            products.get(position).data.id);
                    deletePinProductRequest.setCallbacks(deletePinCallbacks);
                    deletePinProductRequest.start();
                }
                holder.pin=!holder.pin;
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
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgPin;
        ImageView imgFavourit;
        TextView textView;
        CardView cardView;
        boolean pin=false;
        boolean fav=false;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgPin = (ImageView) itemView.findViewById(R.id.img_pin);
            imgFavourit = (ImageView) itemView.findViewById(R.id.img_favourite);
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

