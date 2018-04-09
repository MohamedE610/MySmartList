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

import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Constants;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.AddPinProductRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.DeletePinProductRequest;
import com.squareup.picasso.Picasso;

/**
 * Created by abdallah on 2/21/2018.
 */

public class PinProductAdapter extends RecyclerView.Adapter<PinProductAdapter.MyViewHolder> {

    //ArrayList<Product_1> products;
    Products products;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;

    AddPinProductRequest addPinProductRequest;
    DeletePinProductRequest deletePinProductRequest;


    Callbacks addPinCallbacks=new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
            MainActivityFragment.addPinFragment();
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




    public PinProductAdapter() {
    }

    public PinProductAdapter(Products products, Context context) {
        this.products = products;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pin_product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String detailsStr=products.data.get(position).name+"\n"+products.data.get(position).price;
        holder.textView.setText(detailsStr);

        String market=products.data.get(position).market;
        String marketStr;
        if(market.equals("1"))
            marketStr="الدانوب";
        else
            marketStr="هايبر باندا";
        holder.market.setText(marketStr);
        //String urlStr = Constants.BasicUrlImg+products.data.get(position).image;
        String urlStr =products.data.get(position).image;
        Picasso.with(context).load(urlStr).into(holder.img);

        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //holder.pin= SignupActivity.isPinProduct(products.data.get(position).id);
        if(holder.pin)
            holder.imgPin.setImageResource(R.drawable.pin_red);
        else
            holder.imgPin.setImageResource(R.drawable.pin);

        MySharedPreferences.setUpMySharedPreferences(context);
        final int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.pin){
                    holder.imgPin.setImageResource(R.drawable.pin_red);
                    addPinProductRequest =new AddPinProductRequest(id,
                            products.data.get(position).id);
                    addPinProductRequest.setCallbacks(addPinCallbacks);
                    addPinProductRequest.start();
                }else{
                    holder.imgPin.setImageResource(R.drawable.pin);
                    deletePinProductRequest=new DeletePinProductRequest(id,
                            products.data.get(position).id);
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
        if (products == null || products.data==null)
            return 0;
        return products.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgPin;
        TextView textView;
        CardView cardView;
        TextView market;
        boolean pin=true;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgPin = (ImageView) itemView.findViewById(R.id.imgPin);
            textView = (TextView) itemView.findViewById(R.id.text_details);
            cardView = (CardView) itemView.findViewById(R.id.card);
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

