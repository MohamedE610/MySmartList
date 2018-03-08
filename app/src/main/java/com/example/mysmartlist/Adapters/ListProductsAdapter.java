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

import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by abdallah on 2/21/2018.
 */

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.MyViewHolder> {

    List list;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    public ListProductsAdapter() {
    }

    public ListProductsAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String detailsStr=list.data.products.get(position).name;
        holder.textViewName.setText(detailsStr);
        holder.textViewPrice.setText(list.data.products.get(position).price+"");
        holder.textViewCount.setText(list.data.products.get(position).count+"");


        String urlStr = Constants.BasicUrlImg+list.data.products.get(position).image;

        Picasso.with(context).load(urlStr).into(holder.img);

        setAnimation(holder.cardView, position);
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {

        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        if (list == null || list.data.products==null)
            return 0;
        return list.data.products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView textViewName;
        TextView textViewPrice;
        TextView textViewCount;
        TextView textViewPlus;
        TextView textViewMinus;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img_product);
            textViewName = (TextView) itemView.findViewById(R.id.text_name);
            textViewPrice = (TextView) itemView.findViewById(R.id.text_price);
            textViewPlus = (TextView) itemView.findViewById(R.id.text_p);
            textViewMinus = (TextView) itemView.findViewById(R.id.text_m);
            textViewCount = (TextView) itemView.findViewById(R.id.text_count);
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

    /*
                            */

}

