package com.example.mysmartlist.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Constants;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.decrementProductCountInListRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.incrementProductCountInListRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 2/21/2018.
 */

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.MyViewHolder> {

    List list;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;
    ArrayList<Integer> checkedList=new ArrayList<>();
    ISendCheckedList iSendCheckedList;
    boolean isEditable;
    int list_id;

    public ListProductsAdapter() {
    }

    public void setISendCheckedListLisnter(ISendCheckedList iSendCheckedList){
        this.iSendCheckedList=iSendCheckedList;
    }

    public ListProductsAdapter(List list, Context context,boolean isEditable,int list_id) {
        this.list = list;
        this.context = context;
        this.isEditable=isEditable;
        this.list_id=list_id;
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

        if(isEditable){
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.checkBox.setVisibility(View.GONE);
        }

        String detailsStr=list.data.products.get(position).name;
        holder.textViewName.setText(detailsStr);
        holder.textViewPrice.setText(list.data.products.get(position).price+"");
        holder.textViewCount.setText(list.data.products.get(position).count+"");


        String urlStr = Constants.BasicUrlImg+list.data.products.get(position).image;
        Picasso.with(context).load(urlStr).into(holder.img);


        holder.textViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String>  hashMap=new HashMap<>();
                hashMap.put("count","1");
                incrementProductCountInListRequest incrementProductCount=new incrementProductCountInListRequest(list_id,list.data.products.get(position).id,hashMap);
                incrementProductCount.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Toast.makeText(context, "العملية تمت بنجاح", Toast.LENGTH_SHORT).show();
                        int count=1;

                        try {
                             count = Integer.valueOf(holder.textViewCount.getText().toString());
                        }catch (Exception e){count=1;}

                        holder.textViewCount.setText((++count)+"");
                    }

                    @Override
                    public void OnFailure(Object obj) {

                    }
                });

                incrementProductCount.start();
            }
        });


        holder.textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String>  hashMap=new HashMap<>();
                hashMap.put("count","1");
                decrementProductCountInListRequest decrementProductCountInList=new decrementProductCountInListRequest(list_id,list.data.products.get(position).id,hashMap);

                decrementProductCountInList.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Toast.makeText(context, "العملية تمت بنجاح", Toast.LENGTH_SHORT).show();

                        int count=1;

                        try {
                            count = Integer.valueOf(holder.textViewCount.getText().toString());
                        }catch (Exception e){count=1;}

                        holder.textViewCount.setText((--count)+"");
                    }

                    @Override
                    public void OnFailure(Object obj) {

                    }
                });

                decrementProductCountInList.start();
            }
        });

       holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

               if(b)
                   checkedList.add(list.data.products.get(position).id);
               else
                   checkedList.remove(list.data.products.get(position).id);

               iSendCheckedList.send(checkedList);

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
        CheckBox checkBox;


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
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
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

   public interface ISendCheckedList{
        void send(ArrayList<Integer> list);
    }

}

