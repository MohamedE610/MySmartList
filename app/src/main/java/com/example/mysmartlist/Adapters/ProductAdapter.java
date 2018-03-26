package com.example.mysmartlist.Adapters;

import android.content.Context;
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

import com.example.mysmartlist.Fragments.MainActivityFragment;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.Constants;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    ArrayList<ProductsByClientID>  products;
    Context context;
    int LastPosition = -1;
    int pos;
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
        pos=position;
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

        MySharedPreferences.setUpMySharedPreferences(context);
        final int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        holder.imgFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!holder.fav){
                    holder.imgFavourit.setImageResource(R.drawable.heart_red);
                    addfavouriteProductRequest=new AddFavouriteProductRequest(id,
                            products.get(position).data.id);
                    addfavouriteProductRequest.setCallbacks(addFavCallbacks);
                    addfavouriteProductRequest.start();
                }else {
                    holder.imgFavourit.setImageResource(R.drawable.heart);
                    deleteFavouriteProductRequest=new DeleteFavouriteProductRequest(id,
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
                    addPinProductRequest =new AddPinProductRequest(id,
                            products.get(position).data.id);
                    addPinProductRequest.setCallbacks(addPinCallbacks);
                    addPinProductRequest.start();
                }else{
                    holder.imgPin.setImageResource(R.drawable.pin);
                    deletePinProductRequest=new DeletePinProductRequest(id,
                            products.get(position).data.id);
                    deletePinProductRequest.setCallbacks(deletePinCallbacks);
                    deletePinProductRequest.start();
                }
                holder.pin=!holder.pin;
            }
        });
        holder.linearLayoutAdd.setClickable(true);
        holder.linearLayoutAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(holder.isReadyToAdd){
                     showPopUpMenu(holder,position);
                }else{
                    holder.isReadyToAdd=true;
                    holder.linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });



        holder.textViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.productCount++;
                holder.textViewCount.setText(holder.productCount+"");
            }
        });

        holder.textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.productCount>1) {
                    holder.productCount--;
                    holder.textViewCount.setText(holder.productCount+"");
                }else if (holder.productCount==1){
                    //holder.linearLayoutAdd.setVisibility(View.GONE);
                    //holder.isReadyToAdd=false;
                    holder.textViewCount.setText(holder.productCount+"");
                }
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

    public void addProduct(MyViewHolder holder, int position){
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("product_id",products.get(position).data.id.toString());
            hashMap.put("count",holder.productCount+"");
            MySharedPreferences.setUpMySharedPreferences(context);
            int list_id=Integer.valueOf(MySharedPreferences.getUserSetting("list_id"));
            productToListRequest=new addProductToListRequest(list_id,hashMap);
            productToListRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void OnFailure(Object obj) {

                }
            });

            productToListRequest.start();


    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgPin;
        ImageView imgFavourit;
        TextView textView;
        CardView cardView;
        boolean pin=false;
        boolean fav=false;

        LinearLayout linearLayout;
        LinearLayout linearLayoutAdd;
        //TextView textViewAdd;
        TextView textViewPlus;
        TextView textViewMinus;
        TextView textViewCount;
        boolean isReadyToAdd=false;
        int productCount=1;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgPin = (ImageView) itemView.findViewById(R.id.img_pin);
            imgFavourit = (ImageView) itemView.findViewById(R.id.img_favourite);
            textView = (TextView) itemView.findViewById(R.id.text_details);
            cardView = (CardView) itemView.findViewById(R.id.card);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout1);
            linearLayoutAdd = (LinearLayout) itemView.findViewById(R.id.linearLayoutAdd);
            textViewPlus = (TextView) itemView.findViewById(R.id.text_plus);
            textViewMinus= (TextView) itemView.findViewById(R.id.text_minus);
            textViewCount = (TextView) itemView.findViewById(R.id.text_count);

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

    addProductToListRequest productToListRequest;

      int groubId=11;
        PopupMenu popupMenu;

        public void showPopUpMenu(final MyViewHolder holder, final int position) {

        popupMenu=new PopupMenu(context,holder.linearLayoutAdd);
        //popupMenu.getMenu().add(11,1001,0,"add");
        MySharedPreferences.setUpMySharedPreferences(context);
        int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        getCurrentClientListsRequest currentClientListsRequest=new getCurrentClientListsRequest(id);
        currentClientListsRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                final ClientLists clientLists=(ClientLists)obj;
                for (int i = 0; i <clientLists.data.size() ; i++) {
                    popupMenu.getMenu().add(groubId,1000+i,i,clientLists.data.get(i).name);
                   }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id =item.getItemId();
                        for (int j = 0; j <clientLists.data.size() ; j++) {
                            if(1000+j==id){
                                MySharedPreferences.setUserSetting("list_id",clientLists.data.get(j).id+"");
                                addProduct(holder,position);
                                break;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        currentClientListsRequest.start();
    }

}

