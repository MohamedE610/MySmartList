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

import com.example.mysmartlist.Models.Product;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchCategoriesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abdallah on 2/21/2018.
 */

public class PinProductAdapter extends RecyclerView.Adapter<PinProductAdapter.MyViewHolder> {

    ArrayList<Product> products;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    public PinProductAdapter() {
    }

    public PinProductAdapter(ArrayList<Product> categories, Context context) {
        this.products = new  ArrayList<Product>();
        this.products = categories;
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

        String detailsStr=products.get(position).getName()+"\n"+products.get(position).getPrice();
        holder.textView.setText(detailsStr);

        String urlStr = FetchCategoriesData.BasicUrl+products.get(position).getImgUrl();
        Picasso.with(context).load(urlStr).into(holder.img);

        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        TextView textView;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgPin = (ImageView) itemView.findViewById(R.id.imgPin);
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

